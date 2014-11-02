package it.tmp.mexican.json.splitter.route;

import org.apache.camel.builder.PredicateBuilder;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.builder.xml.XPathBuilder;

/**
 * Created by uzumaki on 01/11/14.
 */

public class XMLIngestionRoute extends RouteBuilder{
    private final String ROUTE_ID = "XMLIngestionRoute";

    @Override
    public void configure() throws Exception {

        /*
        * La rotta mostra due modalita' differenti di utilizzo
        * di xpath.
        * */
        from("file:src/data/xml/?noop=true")
                .routeId(ROUTE_ID)
                .choice()
                    .when( PredicateBuilder.toPredicate(XPathBuilder.xpath("/person/city = 'London'")) )
                        .log("UK message")
                        .to("file:src/data/outxml/messages/uk")
                    .when().xpath("/person/city = 'Tampa'")
                        .log("Other message")
                        .to("file:src/data/outxml/messages/others")
                .endChoice()
                .stop();
    }
}
