package com.howtographql.hackernews.repositories;

import com.howtographql.hackernews.models.Link;
import com.mongodb.client.MongoCollection;
import lombok.Data;
import lombok.val;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.List;

import static com.mongodb.client.model.Filters.eq;

@Data
public class LinkRepository {

    private final MongoCollection<Document> links;

    public Link findById(String id) {
        val doc = links.find(eq("_id", new ObjectId(id))).first();
        return link(doc);
    }

    public List<Link> getAllLinks() {
        val allLinks = new ArrayList<com.howtographql.hackernews.models.Link>();
        for (Document doc : links.find()) {
            Link link = new Link(
                    doc.get("_id").toString(),
                    doc.getString("url"),
                    doc.getString("description"),
                    doc.getString("postedBy")
            );
            allLinks.add(link);
        }
        return allLinks;
    }

    public void saveLink(Link link) {
        Document doc = new Document();
        doc.append("url", link.getUrl());
        doc.append("description", link.getDescription());
        doc.append("postedBy", link.getUserId());
        links.insertOne(doc);
    }

    private Link link(Document doc) {
        return new Link(
                doc.get("_id").toString(),
                doc.getString("url"),
                doc.getString("description"));
    }
}
