package com.howtographql.hackernews.repositories;

import com.howtographql.hackernews.models.Scalars;
import com.howtographql.hackernews.models.Vote;
import com.mongodb.client.MongoCollection;
import lombok.Data;
import lombok.val;
import org.bson.Document;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

import static com.mongodb.client.model.Filters.eq;

@Data
public class VoteRepository {

    private final MongoCollection<Document> votes;


    public List<Vote> findByUserId(String userId) {
        val list = new ArrayList<Vote>();
        for (Document doc : votes.find(eq("userId", userId))) {
            list.add(vote(doc));
        }
        return list;
    }

    public List<Vote> findByLinkId(String linkId) {
        val list = new ArrayList<Vote>();
        for (Document doc : votes.find(eq("linkId", linkId))) {
            list.add(vote(doc));
        }
        return list;
    }

    public Vote saveVote(Vote vote) {
        val doc = new Document();
        doc.append("userId", vote.getUserId());
        doc.append("linkId", vote.getLinkId());
        doc.append("createdAt", Scalars.dateTime.getCoercing().serialize(vote.getCreatedAt()));
        votes.insertOne(doc);
        return new Vote(
                doc.get("_id").toString(),
                vote.getCreatedAt(),
                vote.getUserId(),
                vote.getLinkId());
    }

    private Vote vote(Document doc) {
        return new Vote(
                doc.get("_id").toString(),
                ZonedDateTime.parse(doc.getString("createdAt")),
                doc.getString("userId"),
                doc.getString("linkId")
        );
    }
}
