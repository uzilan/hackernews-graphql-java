package com.howtographql.hackernews.models;

import lombok.Data;

@Data
public class SigninPayload {
    private final String token;
    private final User user;
}
