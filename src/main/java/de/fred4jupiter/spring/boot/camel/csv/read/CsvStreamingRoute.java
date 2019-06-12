package de.fred4jupiter.spring.boot.camel.csv.read;

import de.fred4jupiter.spring.boot.camel.MyGlobalExceptionHandler;
import de.fred4jupiter.spring.boot.camel.csv.Person;
import org.apache.camel.dataformat.bindy.csv.BindyCsvDataFormat;
import org.apache.camel.spring.SpringRouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class CsvStreamingRoute extends SpringRouteBuilder {

    @Override
    public void configure() {
        onException(Exception.class).process(MyGlobalExceptionHandler.NAME);

        BindyCsvDataFormat csvDataFormat = new BindyCsvDataFormat(Person.class);

        from("file:{{inbox.csv.folder}}/stream")
                .split().tokenize("\r\n", 100)
                .unmarshal(csvDataFormat)
                .bean("streamProcessor")
                .to("mock:streaming-out");
    }
}
