package data;

import models.User;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class UserDataStore {

    private static final String FILE_NAME = "users.dat";  // ডাটা ফাইলের নাম

    // ইউজারদের লিস্ট ফাইলে সেভ করার ফাংশন
    public void saveUsers(List<User> users) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(users);
            System.out.println("✅ User data saved successfully.");
        } catch (IOException e) {
            System.out.println("❌ Error saving user data: " + e.getMessage());
        }
    }

    // ফাইল থেকে ইউজারদের লিস্ট লোড করার ফাংশন
    public List<User> loadUsers() {
        File file = new File(FILE_NAME);
        if (!file.exists()) {
            return new ArrayList<>();  // যদি ফাইল না থাকে, খালি লিস্ট রিটার্ন করো
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            return (List<User>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("❌ Error loading user data: " + e.getMessage());
            return new ArrayList<>();
        }
    }
}
