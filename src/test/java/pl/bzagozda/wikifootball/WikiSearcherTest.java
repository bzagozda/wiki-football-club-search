package pl.bzagozda.wikifootball;

import jakarta.ws.rs.ProcessingException;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.Invocation;
import jakarta.ws.rs.client.WebTarget;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import pl.bzagozda.wikifootball.data.WikiPage;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class WikiSearcherTest {

    @Mock Client client;
    @Mock WebTarget webTarget;
    @Mock Invocation.Builder builder;

    private WikiSearcher wikiSearcher;
    private String uri = "https://en.wikipedia.org/w/api.php";

    private WikiPage wikiPageDummy = WikiPageTestHelper.dummyWikiPage();

    @Before
    public void setUp() {
        wikiSearcher = new WikiSearcher(client, uri);
        when(client.target(uri)).thenReturn(webTarget);
        when(webTarget.queryParam(anyString(), any())).thenReturn(webTarget);
        when(webTarget.request(anyString())).thenReturn(builder);
        when(builder.property(anyString(), any())).thenReturn(builder);
        when(builder.get(WikiPage.class)).thenReturn(wikiPageDummy);
    }

    @Test
    public void findingText_shouldReturnWikiPage_whenTextIsProvided() {
        Optional<WikiPage> wikiPage = wikiSearcher.find("Some text");

        Assert.assertTrue(wikiPage.isPresent());
        Assert.assertEquals(wikiPageDummy, wikiPage.get());
    }

    @Test
    public void findingText_shouldReturnNothing_whenTextIsNull() {
        Optional<WikiPage> wikiPage = wikiSearcher.find(null);

        Assert.assertFalse(wikiPage.isPresent());
    }

    @Test
    public void findingText_shouldReturnNothing_whenProcessingExceptionIsThrown() {
        when(builder.get(WikiPage.class)).thenThrow(new ProcessingException("Processing problem"));
        Optional<WikiPage> wikiPage = wikiSearcher.find("Some text");

        Assert.assertFalse(wikiPage.isPresent());
    }

    @Test
    public void findingText_shouldReturnNothing_whenWebApplicationExceptionIsThrown() {
        when(builder.get(WikiPage.class)).thenThrow(new WebApplicationException("Bad request"));
        Optional<WikiPage> wikiPage = wikiSearcher.find("Some text");

        Assert.assertFalse(wikiPage.isPresent());
    }
}
