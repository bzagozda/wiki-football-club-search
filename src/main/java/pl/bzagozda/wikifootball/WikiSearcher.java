package pl.bzagozda.wikifootball;

import jakarta.ws.rs.ProcessingException;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.core.MediaType;
import org.glassfish.jersey.client.ClientProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.bzagozda.wikifootball.data.WikiPage;

import java.util.Optional;

public class WikiSearcher implements Searcher<WikiPage> {

    private final Client CLIENT;

    private final String URI;

    private static final Logger LOG = LoggerFactory.getLogger(WikiSearcher.class);

    public WikiSearcher(String uri) {
        this(ClientBuilder.newClient(), uri);
    }

    public WikiSearcher(Client client, String uri) {
        this.CLIENT = client;
        this.URI = uri;
    }

    @Override
    public Optional<WikiPage> find(String text) {
        if(text == null) {
            return Optional.empty();
        }

        try {
            return Optional.of(CLIENT.target(URI)
                    .queryParam("action", "query")
                    .queryParam("list", "search")
                    .queryParam("format", "json")
                    .queryParam("srsearch", text)
                    .queryParam("srlimit", 10)
                    .request(MediaType.APPLICATION_JSON)
                    .property(ClientProperties.CONNECT_TIMEOUT, 1000)
                    .property(ClientProperties.READ_TIMEOUT, 1000)
                    .get(WikiPage.class));
        } catch (ProcessingException e) {
            LOG.error("There was problem with processing request ", e);
        } catch (WebApplicationException e) {
            LOG.error("Service responded with " + e.getResponse().getStatus(), e);
        }

        return Optional.empty();
    }
}
