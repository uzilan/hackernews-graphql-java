package com.howtographql.hackernews.models;

import lombok.Data;

@Data
public class Link {
    private final String url;
    private final String description;
}
