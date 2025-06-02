package models;

import java.util.ArrayList;
import java.util.List;

public class RegularUser extends User {

    private List<String> sentRequests;
    private List<String> receivedRequests;

    public RegularUser(String userId, String name, String email, String password) {
        super(userId, name, email, password);
        this.sentRequests = new ArrayList<>();
        this.receivedRequests = new ArrayList<>();
    }

    public List<String> getSentRequests() {
        return sentRequests;
    }

    public List<String> getReceivedRequests() {
        return receivedRequests;
    }

    public void sendRequest(String request) {
        sentRequests.add(request);
    }

    public void receiveRequest(String request) {
        receivedRequests.add(request);
    }


    @Override
    public void viewDashboard() {
        System.out.println("\n📊 Regular User Dashboard for: " + getName());
        System.out.println("🕒 Time Credits: " + getTimeCredits());
        System.out.println("🛠️ Services: " + getServices());
        System.out.println("📨 Sent Requests: " + sentRequests);
        System.out.println("📥 Received Requests: " + receivedRequests + "\n");
    }


    @Override
    public String toString() {
        return "RegularUser{" +
                "userId='" + userId + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", timeCredits=" + timeCredits +
                ", services=" + services +
                ", sentRequests=" + sentRequests +
                ", receivedRequests=" + receivedRequests +
                '}';
    }
}
