package pl.bzagozda.wikifootball.data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;
import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
public class WikiQuery {
    private List<WikiSearchData> search;

    public WikiQuery() { }

    public WikiQuery(List<WikiSearchData> search) {
        this.search = search;
    }

    public List<WikiSearchData> getSearch() {
        return search;
    }

    public void setSearch(List<WikiSearchData> search) {
        this.search = search;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WikiQuery wikiQuery = (WikiQuery) o;
        return Objects.equals(search, wikiQuery.search);
    }

    @Override
    public int hashCode() {
        return Objects.hash(search);
    }
}
