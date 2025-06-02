package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import models.*;
import services.*;
import utils.InitializableWithServices;

import java.util.*;

public class ServiceRequestController implements InitializableWithServices {
    @FXML private ListView<Service> serviceListView;
    @FXML private TextField selectedServiceIdField;
    @FXML private ListView<ServiceRequest> incomingRequestsView;
    @FXML private TextField requestIdField;

    private AuthService authService;
    private ServiceManager serviceManager;
    private RequestManager requestManager;

    @Override
    public void initializeServices(AuthService auth, ServiceManager service, RequestManager request) {
        this.authService = auth;
        this.serviceManager = service;
        this.requestManager = request;

        refreshViews();  // Optional: Load data into ListViews
    }

    private void refreshViews() {
        String currentUserId = authService.getCurrentUser().getUserId();

        serviceListView.getItems().setAll(serviceManager.getAllServices());

        incomingRequestsView.getItems().setAll(
                requestManager.getIncomingRequests(currentUserId, serviceManager.getAllServices())
        );
    }

    // 🛠️ FXML Action Methods

    @FXML
    private void handleSendRequest() {
        String serviceId = selectedServiceIdField.getText();
        if (serviceId == null || serviceId.isEmpty()) {
            showAlert("Please enter a service ID.");
            return;
        }

        String requestId = UUID.randomUUID().toString();
        String userId = authService.getCurrentUser().getUserId();
        ServiceRequest request = new ServiceRequest(requestId, serviceId, userId);
        requestManager.sendRequest(request);
        showAlert("Request sent successfully!");
    }

    @FXML
    private void handleAcceptRequest() {
        String requestId = requestIdField.getText();
        if (requestId == null || requestId.isEmpty()) {
            showAlert("Please enter a request ID.");
            return;
        }

        requestManager.updateRequestStatus(requestId, RequestStatus.ACCEPTED);
        showAlert("Request accepted.");
    }

    @FXML
    private void handleRejectRequest() {
        String requestId = requestIdField.getText();
        if (requestId == null || requestId.isEmpty()) {
            showAlert("Please enter a request ID.");
            return;
        }

        requestManager.updateRequestStatus(requestId, RequestStatus.REJECTED);
        showAlert("Request rejected.");
    }

    private void showAlert(String msg) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText(msg);
        alert.show();
    }
}
