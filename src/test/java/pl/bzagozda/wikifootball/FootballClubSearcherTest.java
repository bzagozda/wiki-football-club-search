package pl.bzagozda.wikifootball;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import pl.bzagozda.wikifootball.data.WikiPage;

import java.util.Optional;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class FootballClubSearcherTest {

    @Mock Searcher<WikiPage> searcher;

    FootballClubSearcher footballClubSearcher;

    @Before
    public void setUp() {
        when(searcher.find("Manchester")).thenReturn(Optional.of(WikiPageTestHelper.dummyWikiPage()));
        when(searcher.find("Not Existing Club")).thenReturn(Optional.of(WikiPageTestHelper.dummyWikiPageWithoutFC()));
        when(searcher.find(null)).thenReturn(Optional.empty());

        footballClubSearcher = new FootballClubSearcher(searcher);
    }

    @Test
    public void findingLink_shouldReturnLinkToFootballClub_whenLookingForFootballClub() {
        Optional<String> manchesterClubWiki = footballClubSearcher.findLink("Manchester");

        Assert.assertTrue(manchesterClubWiki.isPresent());
        Assert.assertEquals("https://en.wikipedia.org/wiki/Manchester_United_F.C.", manchesterClubWiki.get());
    }

    @Test
    public void findingLink_shouldReturnNothing_whenLookingForNotExistingClub() {
        Optional<String> missingClub = footballClubSearcher.findLink("Not Existing Club");

        Assert.assertFalse(missingClub.isPresent());
    }

    @Test
    public void findingLink_shouldReturnNothing_whenNoClubNameWasGiven() {
        Optional<String> missingClub = footballClubSearcher.findLink(null);

        Assert.assertFalse(missingClub.isPresent());
    }
}
