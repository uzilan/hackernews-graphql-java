package com.howtographql.hackernews.models;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Link {
    private final String id;
    private final String url;
    private final String description;
    private final String userId;

    public Link(String url, String description, String userId) {
        this(null, url, description, userId);
    }
}
