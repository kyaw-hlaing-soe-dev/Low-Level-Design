package org.example.classrelationships;

import java.util.ArrayList;
import java.util.List;

class Message {
    private User author;
    private String content;
    private String timestamp;

    public Message(User author, String content, String timestamp) {
        this.author = author;
        this.content = content;
        this.timestamp = timestamp;
    }

    public User getAuthor() { return author; }
    public String getContent() { return content; }
    public String getTimestamp() { return timestamp; }
}

class User {
    private String name;
    private List<User> followers = new ArrayList<>();
    private List<User> following = new ArrayList<>();
    private List<Message> messages = new ArrayList<>();

    public User(String name) {
        this.name = name;
    }

    public void follow(User user) {
        // TODO: Add user to following, add this to user's followers
        // Guard against: self-follows, duplicates
        if(user==this) return;
        if(following.contains(user)) return;
        following.add(user);
        user.followers.add(this);
    }

    public void sendMessage(String content, String timestamp) {
        Message message = new Message(this, content, timestamp);
        messages.add(message);
    }

    public String getName() { return name; }
    public List<User> getFollowers() { return followers; }
    public List<User> getFollowing() { return following; }
    public List<Message> getMessages() { return messages; }
}


public class SocialNetwork {
}
