package de.fred4jupiter.spring.boot.camel.csv.read;

import de.fred4jupiter.spring.boot.camel.MyGlobalExceptionHandler;
import de.fred4jupiter.spring.boot.camel.csv.Person;
import org.apache.camel.LoggingLevel;
import org.apache.camel.dataformat.bindy.csv.BindyCsvDataFormat;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.apache.camel.spring.SpringRouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class CsvReadingRoute extends SpringRouteBuilder {

    @Override
    public void configure() {
        onException(Exception.class).process(MyGlobalExceptionHandler.NAME);

        BindyCsvDataFormat csvDataFormat = new BindyCsvDataFormat(Person.class);

        from("file:{{inbox.csv.folder}}?moveFailed=error/${file:name.noext}-${date:now:yyyyMMddHHmmssSSS}.${file:ext}")
                .id("CsvReadingRoute")
                .unmarshal(csvDataFormat)
                .marshal().json(JsonLibrary.Gson, true)
                .log(LoggingLevel.DEBUG, "processed CSV file: ${header.CamelFileName}, ID: ${id}")
                .to("file:{{outbox.json.folder}}?fileName=sample-data.json", "mock:json-out");
    }
}
