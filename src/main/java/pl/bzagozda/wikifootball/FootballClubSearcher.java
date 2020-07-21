package pl.bzagozda.wikifootball;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.bzagozda.wikifootball.data.WikiPage;

import java.util.Optional;

/**
 * Write a code that would look for a football club name
 * (as an input parameter) on the first ten pages of
 * Wikipedia API (https://en.wikipedia.org/w/api.php).
 * For each page try to find one result that would display
 * information about a given club. Return the URL to this wiki page.
 * Sample API query: https://en.wikipedia.org/w/api.phpaction=query&list=search&format=json&srsearch=%22Liverpool%22&srlimit=10
 * Input: Liverpool
 * Output: https://en.wikipedia.org/wiki/Liverpool_F.C
 */
public class FootballClubSearcher {

    private static final String URI = "https://en.wikipedia.org/w/api.php";

    private static final String TAG_REGEX = "(?i).*F\\.C\\..*";

    private static final Logger LOG = LoggerFactory.getLogger(FootballClubSearcher.class);

    private final Searcher<WikiPage> wikiPageSearcher;

    public FootballClubSearcher(Searcher<WikiPage> wikiPageSearcher) {
        this.wikiPageSearcher = wikiPageSearcher;
    }

    public Optional<String> findLink(String clubName) {
        LOG.debug("Looking for wiki page for club " + clubName);
        return wikiPageSearcher.find(clubName)
                .flatMap(wikiPage -> wikiPage
                        .getQuery()
                        .getSearch()
                        .stream()
                        .filter(wikiSearchData -> wikiSearchData.getTitle().matches(TAG_REGEX))
                        .findFirst())
                .map(data -> buildWikiPage(data.getTitle()));
    }

    protected static String buildWikiPage(String pageTitle) {
        return String.format("https://en.wikipedia.org/wiki/%s", pageTitle.replace(" ", "_"));
    }
}
