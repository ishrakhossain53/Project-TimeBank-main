package models;

import java.io.Serializable;

public class Service implements Serializable {
    private String id;
    private String title;
    private String description;
    private String providerUserId; // Match User.userId
    private int hours;

    public Service(String id, String title, String description, String providerUserId, int hours) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.providerUserId = providerUserId;
        this.hours = hours;
    }

    public String getId() { return id; }
    public String getTitle() { return title; }
    public String getDescription() { return description; }
    public String getProviderUserId() { return providerUserId; }
    public int getHours() { return hours; }

    public void setTitle(String title) { this.title = title; }
    public void setDescription(String description) { this.description = description; }
    public void setHours(int hours) { this.hours = hours; }
}