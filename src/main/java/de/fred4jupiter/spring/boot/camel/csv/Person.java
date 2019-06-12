package de.fred4jupiter.spring.boot.camel.csv;

import org.apache.camel.dataformat.bindy.annotation.CsvRecord;
import org.apache.camel.dataformat.bindy.annotation.DataField;
import org.apache.commons.lang3.builder.ToStringBuilder;

@CsvRecord(separator = ";", generateHeaderColumns = true, skipFirstLine = true)
public class Person {

    @DataField(pos = 1)
    private String name;

    @DataField(pos = 2)
    private Integer iq;

    @DataField(pos = 3)
    private String description;

    public Person() {
    }

    public Person(String name, Integer iq, String description) {
        this.name = name;
        this.iq = iq;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public Integer getIq() {
        return iq;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        Person person = (Person) o;

        return new org.apache.commons.lang3.builder.EqualsBuilder()
                .append(name, person.name)
                .append(iq, person.iq)
                .append(description, person.description)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new org.apache.commons.lang3.builder.HashCodeBuilder(17, 37)
                .append(name)
                .append(iq)
                .append(description)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("name", name)
                .append("iq", iq)
                .append("description", description)
                .toString();
    }
}
