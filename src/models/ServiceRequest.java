package models;

import java.io.Serializable;

public class ServiceRequest implements Serializable {
    private String requestId;
    private String serviceId;
    private String requesterUserId; // Match User.userId
    private RequestStatus status;

    public ServiceRequest(String requestId, String serviceId, String requesterUserId) {
        this.requestId = requestId;
        this.serviceId = serviceId;
        this.requesterUserId = requesterUserId;
        this.status = RequestStatus.PENDING;
    }

    public String getRequestId() { return requestId; }
    public String getServiceId() { return serviceId; }
    public String getRequesterUserId() { return requesterUserId; }
    public RequestStatus getStatus() { return status; }

    public void setStatus(RequestStatus status) { this.status = status; }
}