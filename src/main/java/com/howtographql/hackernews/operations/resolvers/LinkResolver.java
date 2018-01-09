package com.howtographql.hackernews.operations.resolvers;

import com.coxautodev.graphql.tools.GraphQLResolver;
import com.howtographql.hackernews.models.Link;
import com.howtographql.hackernews.models.User;
import com.howtographql.hackernews.repositories.UserRepository;
import lombok.Data;

@Data
public class LinkResolver implements GraphQLResolver<Link> {

    private final UserRepository userRepository;

    public User postedBy(Link link) {
        if (link.getUserId() == null) {
            return null;
        }
        return userRepository.findById(link.getUserId());
    }
}