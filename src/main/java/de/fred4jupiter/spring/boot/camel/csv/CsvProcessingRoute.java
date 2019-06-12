package de.fred4jupiter.spring.boot.camel.csv;

import de.fred4jupiter.spring.boot.camel.MyGlobalExceptionHandler;
import org.apache.camel.LoggingLevel;
import org.apache.camel.dataformat.csv.CsvDataFormat;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.apache.camel.spring.SpringRouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CsvProcessingRoute extends SpringRouteBuilder {

    @Autowired
    private PersonCreator personCreator;

    @Autowired
    private MyGlobalExceptionHandler myGlobalExceptionHandler;

    @Override
    public void configure() {
        onException(Exception.class).process(myGlobalExceptionHandler);

        CsvDataFormat csv = new CsvDataFormat();
        csv.setDelimiter(';');
        csv.setSkipHeaderRecord(true);

        from("file:inbox/csv?moveFailed=error/${file:name.noext}-${date:now:yyyyMMddHHmmssSSS}.${file:ext}")
                .id("CsvProcessingRoute").unmarshal(csv).bean(personCreator).marshal().json(JsonLibrary.Gson, true)
                .log(LoggingLevel.DEBUG, "processed CSV file: ${header.CamelFileName}, ID: ${id}")
                .to("file:outbox/json?fileName=sample-data.json", "mock:json-out");
    }
}
