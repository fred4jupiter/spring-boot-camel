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

        BindyCsvDataFormat personCsvDataFormat = new BindyCsvDataFormat(Person.class);

        BindyCsvDataFormat greetingCsvDataFormat = new BindyCsvDataFormat(Greeting.class);

        from("file:{{inbox.csv.folder}}/stream")
                .setHeader("id", constant("streamfile"))
                .split()
                .tokenize("\r\n", 100)
                .streaming()
                .unmarshal(personCsvDataFormat)
                .bean(StreamProcessor.NAME)
                .aggregate(header("id"), new GreetingAggregationStrategy())
                .completionTimeout(5000)
                .marshal(greetingCsvDataFormat)
                .to("file:{{outbox.csv.folder}}/stream", "mock:streaming-out");
    }
}
