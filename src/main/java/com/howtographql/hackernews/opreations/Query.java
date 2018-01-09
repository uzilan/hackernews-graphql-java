package com.howtographql.hackernews.opreations;

import com.coxautodev.graphql.tools.GraphQLRootResolver;
import com.howtographql.hackernews.models.Link;
import com.howtographql.hackernews.repositories.LinkRepository;
import lombok.Data;

import java.util.List;

@Data
public class Query implements GraphQLRootResolver {

    private final LinkRepository linkRepository;

    public List<Link> allLinks() {
        return linkRepository.getAllLinks();
    }
}
