package services;

import models.*;
import data.UserDataStore;
import java.util.List;

public class AuthService {

    private List<User> users;
    private UserDataStore userDataStore;
    private User currentUser; // ✅ Add this field

    public AuthService() {
        this.userDataStore = new UserDataStore();
        this.users = userDataStore.loadUsers();

        if (users.isEmpty()) {
            users.add(new Admin("admin01", "Super Admin", "admin@example.com", "adminpass"));
            userDataStore.saveUsers(users);
        }
    }

    public boolean register(User user) {
        for (User u : users) {
            if (u.getEmail().equalsIgnoreCase(user.getEmail())) {
                System.out.println("❌ Email already registered: " + user.getEmail());
                return false;
            }
        }
        users.add(user);
        userDataStore.saveUsers(users);
        System.out.println("✅ User registered successfully: " + user.getName());
        return true;
    }

    public User login(String email, String password) {
        for (User user : users) {
            if (user.getEmail().equalsIgnoreCase(email) && user.getPassword().equals(password)) {
                currentUser = user; // ✅ Save the logged-in user
                System.out.println("✅ Login successful: " + user.getName());
                return user;
            }
        }
        System.out.println("❌ Login failed. Invalid credentials.");
        return null;
    }

    public User getCurrentUser() {
        return currentUser; // ✅ Provide access to the logged-in user
    }

    public List<User> getAllUsers() {
        return users;
    }
}
