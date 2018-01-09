package com.howtographql.hackernews.opreations;

import com.coxautodev.graphql.tools.GraphQLRootResolver;
import com.howtographql.hackernews.models.Link;
import com.howtographql.hackernews.repositories.LinkRepository;
import lombok.Data;
import lombok.val;

@Data
public class Mutation implements GraphQLRootResolver {

    private final LinkRepository linkRepository;

    public Link createLink(String url, String description) {
        val newLink = new Link(url, description);
        linkRepository.saveLink(newLink);
        return newLink;
    }
}