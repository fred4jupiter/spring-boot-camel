package de.fred4jupiter.spring.boot.camel.csv.read;

import org.apache.camel.Exchange;
import org.apache.camel.processor.aggregate.AggregationStrategy;

import java.util.ArrayList;
import java.util.List;

public class GreetingAggregationStrategy implements AggregationStrategy {

    @Override
    public Exchange aggregate(Exchange oldExchange, Exchange newExchange) {
        if(oldExchange == null) {
            return newExchange;
        }

        List<Greeting> greetingsOld = oldExchange.getIn().getBody(List.class);
        List<Greeting> greetingsNew = newExchange.getIn().getBody(List.class);

        List<Greeting> all = new ArrayList<>();
        all.addAll(greetingsOld);
        all.addAll(greetingsNew);
        oldExchange.getIn().setBody(all);
        return oldExchange;
    }
}
