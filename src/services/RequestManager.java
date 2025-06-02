package services;

import models.Service;
import models.ServiceRequest;
import models.RequestStatus;

import java.util.*;

public class RequestManager {
    private final Map<String, ServiceRequest> requests = new HashMap<>();

    public void sendRequest(ServiceRequest request) {
        requests.put(request.getRequestId(), request);
    }

    public void updateRequestStatus(String requestId, RequestStatus status) {
        ServiceRequest request = requests.get(requestId);
        if (request != null) {
            request.setStatus(status);
        }
    }

    public List<ServiceRequest> getRequestsByUser(String requesterUserId) {
        List<ServiceRequest> userRequests = new ArrayList<>();
        for (ServiceRequest req : requests.values()) {
            if (req.getRequesterUserId().equals(requesterUserId)) {
                userRequests.add(req);
            }
        }
        return userRequests;
    }

    public Collection<ServiceRequest> getAllRequests() {
        return requests.values();
    }

    public ServiceRequest getRequest(String requestId) {
        return requests.get(requestId);
    }


    public List<ServiceRequest> getIncomingRequests(String providerUserId, Collection<Service> allServices) {
        List<ServiceRequest> incoming = new ArrayList<>();
        Set<String> myServiceIds = new HashSet<>();

        // First, collect all service IDs that belong to this provider
        for (Service s : allServices) {
            if (s.getProviderUserId().equals(providerUserId)) {
                myServiceIds.add(s.getId());
            }
        }

        // Then, find all requests for those services
        for (ServiceRequest req : requests.values()) {
            if (myServiceIds.contains(req.getServiceId())) {
                incoming.add(req);
            }
        }

        return incoming;
    }
}