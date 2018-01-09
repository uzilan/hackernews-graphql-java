package com.howtographql.hackernews.operations;

import com.coxautodev.graphql.tools.GraphQLRootResolver;
import com.howtographql.hackernews.models.Link;
import com.howtographql.hackernews.models.LinkFilter;
import com.howtographql.hackernews.repositories.LinkRepository;
import lombok.Data;

import java.util.List;

@Data
public class Query implements GraphQLRootResolver {

    private final LinkRepository linkRepository;

    public List<Link> allLinks(LinkFilter filter) {
        return linkRepository.getAllLinks(filter);
    }
}