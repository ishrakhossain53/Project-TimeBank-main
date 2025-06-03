package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import models.User;
import services.AuthService;
import services.ServiceManager;
import services.RequestManager;
import utils.InitializableWithServices;
import utils.Router;

public class LoginController implements InitializableWithServices {
    @FXML private TextField emailField;
    @FXML private PasswordField passwordField;
    @FXML private Label messageLabel;

    private AuthService authService;
    private ServiceManager serviceManager;
    private RequestManager requestManager;

    // Dependency injection from Router
    @Override
    public void initializeServices(AuthService auth, ServiceManager service, RequestManager request) {
        this.authService = auth;
        this.serviceManager = service;
        this.requestManager = request;
    }

    @FXML
    private void handleLogin() {
        String email = emailField.getText();
        String password = passwordField.getText();

        if (email.isEmpty() || password.isEmpty()) {
            messageLabel.setText("❗ Please fill in both fields.");
            messageLabel.setStyle("-fx-text-fill: red;");
            return;
        }

        User user = authService.login(email, password);

        if (user != null) {
            messageLabel.setText("✅ Login successful!");
            messageLabel.setStyle("-fx-text-fill: green;");

            // Navigate to dashboard (example)
            try {
                Router.goTo("views/dashboard.fxml", "Dashboard");
            } catch (Exception e) {
                messageLabel.setText("⚠️ Failed to load dashboard.");
                e.printStackTrace();
            }

        } else {
            messageLabel.setText("❌ Invalid email/password!");
            messageLabel.setStyle("-fx-text-fill: red;");
        }
    }
}
