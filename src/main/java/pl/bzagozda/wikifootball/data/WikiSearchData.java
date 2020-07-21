package pl.bzagozda.wikifootball.data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
public class WikiSearchData {
    private String title;
    private String snippet;

    public WikiSearchData() { }

    public WikiSearchData(String title, String snippet) {
        this.title = title;
        this.snippet = snippet;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSnippet() {
        return snippet;
    }

    public void setSnippet(String snippet) {
        this.snippet = snippet;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WikiSearchData that = (WikiSearchData) o;
        return Objects.equals(title, that.title) &&
                Objects.equals(snippet, that.snippet);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, snippet);
    }
}
