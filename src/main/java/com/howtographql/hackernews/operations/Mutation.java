package com.howtographql.hackernews.operations;

import com.coxautodev.graphql.tools.GraphQLRootResolver;
import com.howtographql.hackernews.AuthContext;
import com.howtographql.hackernews.models.AuthData;
import com.howtographql.hackernews.models.Link;
import com.howtographql.hackernews.models.SigninPayload;
import com.howtographql.hackernews.models.User;
import com.howtographql.hackernews.repositories.LinkRepository;
import com.howtographql.hackernews.repositories.UserRepository;
import graphql.GraphQLException;
import graphql.schema.DataFetchingEnvironment;
import lombok.Data;
import lombok.val;

@Data
public class Mutation implements GraphQLRootResolver {

    private final LinkRepository linkRepository;
    private final UserRepository userRepository;

    //The way to inject the context is via DataFetchingEnvironment
    public Link createLink(String url, String description, DataFetchingEnvironment env) {
        AuthContext context = env.getContext();
        Link newLink = new Link(url, description, context.getUser().getId());
        linkRepository.saveLink(newLink);
        return newLink;
    }

    public User createUser(String name, AuthData auth) {
        val newUser = new User(name, auth.getEmail(), auth.getPassword());
        return userRepository.saveUser(newUser);
    }

    public SigninPayload signinUser(AuthData auth) throws IllegalAccessException {
        val user = userRepository.findByEmail(auth.getEmail());
        if (user.getPassword().equals(auth.getPassword())) {
            return new SigninPayload(user.getId(), user);
        }
        throw new GraphQLException("Invalid credentials");
    }
}
