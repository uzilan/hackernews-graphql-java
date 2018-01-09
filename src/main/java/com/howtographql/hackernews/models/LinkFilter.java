package com.howtographql.hackernews.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class LinkFilter {

    @JsonProperty("description_contains") //the name must match the schema
    private String descriptionContains;

    @JsonProperty("url_contains")
    private String urlContains;
}
