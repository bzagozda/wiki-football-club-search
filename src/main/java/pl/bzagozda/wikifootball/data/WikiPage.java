package pl.bzagozda.wikifootball.data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
public class WikiPage {
    private WikiQuery query;

    public WikiPage() {}

    public WikiPage(WikiQuery query) {
        this.query = query;
    }

    public WikiQuery getQuery() {
        return query;
    }

    public void setQuery(WikiQuery query) {
        this.query = query;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WikiPage wikiPage = (WikiPage) o;
        return Objects.equals(query, wikiPage.query);
    }

    @Override
    public int hashCode() {
        return Objects.hash(query);
    }
}
