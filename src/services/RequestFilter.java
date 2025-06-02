package services;

import java.util.*;
import models.*;

public class RequestFilter {
    public static List<ServiceRequest> filterByStatus(List<ServiceRequest> requests, RequestStatus status) {
        List<ServiceRequest> filtered = new ArrayList<>();
        for (ServiceRequest r : requests) {
            if (r.getStatus() == status) {
                filtered.add(r);
            }
        }
        return filtered;
    }
}