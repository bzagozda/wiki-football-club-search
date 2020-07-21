package pl.bzagozda.wikifootball;

import pl.bzagozda.wikifootball.data.WikiPage;
import pl.bzagozda.wikifootball.data.WikiQuery;
import pl.bzagozda.wikifootball.data.WikiSearchData;

import java.util.List;

public class WikiPageTestHelper {

    static WikiPage dummyWikiPage() {
        return new WikiPage(
                new WikiQuery(
                        List.of(
                                new WikiSearchData(
                                        "Bobby Charlton",
                                        "also won the Ballon d'Or. He played almost all of his club " +
                                                "football at <span class=\\\"searchmatch\\\">Manchester</span> " +
                                                "United, where he became renowned for his attacking " +
                                                "instincts, his passing"),

                                new WikiSearchData(
                                        "Manchester United F.C.",
                                        "<span class=\\\"searchmatch\\\">Manchester</span> " +
                                                "United Football Club is a professional football club" +
                                                " based in Old Trafford, Greater <span class=\\\"searchmatch\\\">" +
                                                "Manchester</span>, England, that competes in the Premier League"),

                                new WikiSearchData(
                                        "Manchester City F.C.",
                                        "<span class=\\\"searchmatch\\\">Manchester</span> City " +
                                                "Football Club is an English football club based in " +
                                                "<span class=\\\"searchmatch\\\">Manchester</span> that competes " +
                                                "in the Premier League, the top flight of English football")
                        ))
        );
    }

    static WikiPage dummyWikiPageWithoutFC() {
        return new WikiPage(
                new WikiQuery(
                        List.of(
                                new WikiSearchData(
                                        "Pancake",
                                        "varieties include bacon. In Poland, thin crêpe-style pancakes " +
                                                "are called <span class=\\\"searchmatch\\\">naleśniki</span> " +
                                                "(pronounced naleshniki). They are usually rolled and served " +
                                                "with a variety"),

                                new WikiSearchData(
                                        "List of Polish dishes",
                                        "particularly at Christmas Eve and Easter, but also at other winter " +
                                                "holidays <span class=\\\"searchmatch\\\">Naleśniki</span> - crepes " +
                                                "which are either folded into triangles or rolled into a tube")
                        ))
        );
    }
}
