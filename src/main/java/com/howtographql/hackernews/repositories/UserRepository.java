package com.howtographql.hackernews.repositories;

import com.howtographql.hackernews.models.User;
import com.mongodb.client.MongoCollection;
import lombok.Data;
import lombok.val;
import org.bson.Document;
import org.bson.types.ObjectId;

import static com.mongodb.client.model.Filters.eq;

@Data
public class UserRepository {

    private final MongoCollection<Document> users;

    public User findByEmail(String email) {
        val doc = users.find(eq("email", email)).first();
        return user(doc);
    }

    public User findById(String id) {
        val doc = users.find(eq("_id", new ObjectId(id))).first();
        return user(doc);
    }

    public User saveUser(User user) {
        val doc = new Document();
        doc.append("name", user.getName());
        doc.append("email", user.getEmail());
        doc.append("password", user.getPassword());
        users.insertOne(doc);
        return new User(
                doc.get("_id").toString(),
                user.getName(),
                user.getEmail(),
                user.getPassword());
    }

    private User user(Document doc) {
        return new User(
                doc.get("_id").toString(),
                doc.getString("name"),
                doc.getString("email"),
                doc.getString("password"));
    }
}
