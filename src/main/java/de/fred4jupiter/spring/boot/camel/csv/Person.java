package de.fred4jupiter.spring.boot.camel.csv;

public class Person {

    private String name;

    private Integer iq;

    private String description;

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
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", iq=" + iq +
                ", description='" + description + '\'' +
                '}';
    }
}
