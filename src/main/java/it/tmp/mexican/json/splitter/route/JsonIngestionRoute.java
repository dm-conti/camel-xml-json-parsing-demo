package it.tmp.mexican.json.splitter.route;

import org.apache.camel.builder.PredicateBuilder;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.builder.xml.XPathBuilder;
import org.apache.camel.model.language.JsonPathExpression;

/**
 * Created by uzumaki on 01/11/14.
 */

public class JsonIngestionRoute extends RouteBuilder{
    private final String ROUTE_ID = "JsonIngestionRoute";

    @Override
    public void configure() throws Exception {


        from("file:src/data/json/?noop=true")
                .routeId(ROUTE_ID)
                .choice()
                    .when().jsonpath("$.people[?(@.city == 'London')]")
                        .log("UK message")
                        .to("file:src/data/outjson/messages/uk")
                    .otherwise()
                        .log("Other message")
                        .to("file:src/data/outjson/messages/others")
                .endChoice()
                .end()
                .stop();
    }
}
