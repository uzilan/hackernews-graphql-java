package com.howtographql.hackernews;

import com.coxautodev.graphql.tools.SchemaParser;
import com.howtographql.hackernews.opreations.Mutation;
import com.howtographql.hackernews.opreations.Query;
import com.howtographql.hackernews.repositories.LinkRepository;
import com.mongodb.MongoClient;
import graphql.schema.GraphQLSchema;
import graphql.servlet.SimpleGraphQLServlet;
import lombok.val;

import javax.servlet.annotation.WebServlet;

@WebServlet(urlPatterns = "/graphql")
public class GraphQLEndpoint extends SimpleGraphQLServlet {

    private static final LinkRepository linkRepository;

    static {
        //Change to `new MongoClient("mongodb://<host>:<port>/hackernews")`
        //if you don't have Mongo running locally on port 27017
        val mongo = new MongoClient().getDatabase("hackernews");
        linkRepository = new LinkRepository(mongo.getCollection("links"));
    }

    public GraphQLEndpoint() {
        super(buildSchema());
    }

    private static GraphQLSchema buildSchema() {
        return SchemaParser.newParser()
                .file("schema.graphqls")
                .resolvers(new Query(linkRepository), new Mutation(linkRepository))
                .build()
                .makeExecutableSchema();
    }
}