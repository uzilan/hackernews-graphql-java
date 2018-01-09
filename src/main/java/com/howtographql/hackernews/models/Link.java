package com.howtographql.hackernews.models;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Link {
    private final String id;
    private final String url;
    private final String description;


    public Link(String url, String description) {
        this(null, url, description);
    }
}
