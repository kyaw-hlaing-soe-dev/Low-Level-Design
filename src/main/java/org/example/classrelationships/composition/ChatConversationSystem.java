package org.example.classrelationships.composition;

import java.util.ArrayList;
import java.util.List;

class Message {
    private String sender;
    private String text;
    private long timestamp;

    public Message(String sender, String text) {
        this.sender = sender;
        this.text = text;
        this.timestamp = System.currentTimeMillis();
    }

    public void display() {
        System.out.println("Sender: " + sender);
        System.out.println("Text: " + text);
        System.out.println("Timestamp: " + timestamp);
    }

    public String getSender() { return sender; }
    public String getText() { return text; }
}

class Conversation {
    private String title;
    private List<Message> messages;

    public Conversation(String title) {
        this.title = title;
        this.messages = new ArrayList<>();
    }

    public void sendMessage(String sender, String text) {
        messages.add(new Message(sender, text));
    }

    public void printHistory() {
        System.out.println("Conversation: " + title);
        for (Message message : messages) {
            message.display();
        }
    }

    public void delete() {
        messages.clear();
    }

    public int getMessageCount() { return messages.size(); }
    public String getTitle() { return title; }

    public void forwardMessage(Conversation target, int messageIndex) {
        if (messageIndex >= 0 && messageIndex < messages.size()) {
            Message original = messages.get(messageIndex);
            target.sendMessage(original.getSender(), original.getText());
        }
    }
}

public class ChatConversationSystem {
    public static void main(String[] args) {
        Conversation teamChat = new Conversation("Team Discussion");
        Conversation projectChat = new Conversation("Project Alpha");

        teamChat.sendMessage("Alice", "Hey team, standup in 5 minutes");
        teamChat.sendMessage("Bob", "Got it, joining now");
        teamChat.sendMessage("Alice", "Don't forget to update your tasks");

        projectChat.sendMessage("Charlie", "Deployment is scheduled for Friday");

        System.out.println("Before deletion:");
        teamChat.printHistory();
        System.out.println("Project chat has " + projectChat.getMessageCount() + " messages\n");

        // Challenge: forward a message
        teamChat.forwardMessage(projectChat, 2);
        System.out.println("After forwarding:");
        projectChat.printHistory();

        // Delete team chat - all its messages are destroyed
        teamChat.delete();
        System.out.println("\nAfter deleting team chat:");
        System.out.println("Team chat has " + teamChat.getMessageCount() + " messages");
        System.out.println("Project chat still has " + projectChat.getMessageCount() + " messages");
    }
}