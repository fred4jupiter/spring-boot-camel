package de.fred4jupiter.spring.boot.camel.csv;

import org.apache.camel.dataformat.csv.CsvDataFormat;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.apache.camel.spring.SpringRouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CsvProcessingRoute extends SpringRouteBuilder {

    @Autowired
    private PersonCreator instanceCreator;

    @Override
    public void configure() throws Exception {
        CsvDataFormat csv = new CsvDataFormat();
        csv.setDelimiter(';');
        csv.setSkipHeaderRecord(true);

        from("file:csv-inbox").unmarshal(csv).bean(instanceCreator).marshal().json(JsonLibrary.Gson, true).to("file:json-outbox?fileName=sample-data.json", "mock:json-out");
    }
}
