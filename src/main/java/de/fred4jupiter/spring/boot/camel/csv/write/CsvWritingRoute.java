package de.fred4jupiter.spring.boot.camel.csv.write;

import de.fred4jupiter.spring.boot.camel.MyGlobalExceptionHandler;
import de.fred4jupiter.spring.boot.camel.csv.Person;
import org.apache.camel.dataformat.bindy.csv.BindyCsvDataFormat;
import org.apache.camel.spring.SpringRouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class CsvWritingRoute extends SpringRouteBuilder {

    @Override
    public void configure() {
        onException(Exception.class).process(MyGlobalExceptionHandler.NAME);

        BindyCsvDataFormat csvDataFormat = new BindyCsvDataFormat(Person.class);

        from("direct:start")
                .marshal(csvDataFormat)
                .to("file:outbox/csv-write?fileName=sample-data.csv", "mock:csv-out")
                .unmarshal(csvDataFormat)
                .to("mock:end");
    }
}
