package com.howtographql.hackernews.operations.resolvers;

import com.coxautodev.graphql.tools.GraphQLResolver;
import com.howtographql.hackernews.models.SigninPayload;
import com.howtographql.hackernews.models.User;

public class SigninResolver implements GraphQLResolver<SigninPayload> {
    public User user(SigninPayload payload) {
        return payload.getUser();
    }
}
