package de.fred4jupiter.spring.boot.camel.csv.read.streaming;

import org.apache.camel.dataformat.bindy.annotation.CsvRecord;
import org.apache.camel.dataformat.bindy.annotation.DataField;

@CsvRecord(separator = ";", generateHeaderColumns = true, skipFirstLine = true)
public class Greeting {

    @DataField(pos = 1)
    private String greetingText;

    public String getGreetingText() {
        return greetingText;
    }

    public void setGreetingText(String greetingText) {
        this.greetingText = greetingText;
    }
}
