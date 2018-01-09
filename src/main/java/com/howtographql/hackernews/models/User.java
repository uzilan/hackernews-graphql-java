package com.howtographql.hackernews.models;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class User {
    private final String id;
    private final String name;
    private final String email;
    private final String password;

    public User(String name, String email, String password) {
        this(null, name, email, password);
    }
}
