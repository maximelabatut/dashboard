package fr.main.data.dao.utils;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;

public class QueryWithParameters {

    private String query;
    private MapSqlParameterSource parameters;

    public QueryWithParameters() {
        this.query = "";
        this.parameters = new MapSqlParameterSource();
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public MapSqlParameterSource getParameters() {
        return parameters;
    }

    public void setParameters(MapSqlParameterSource parameters) {
        this.parameters = parameters;
    }

}
