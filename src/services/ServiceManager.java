package services;

import models.Service;

import java.util.*;

public class ServiceManager {
    private final Map<String, Service> services = new HashMap<>();

    public void addService(Service service) {
        services.put(service.getId(), service);
    }

    public void updateService(String serviceId, String newTitle, String newDescription, int newHours) {
        Service service = services.get(serviceId);
        if (service != null) {
            service.setTitle(newTitle);
            service.setDescription(newDescription);
            service.setHours(newHours);
        }
    }

    public void deleteService(String serviceId) {
        services.remove(serviceId);
    }

    public Service getService(String serviceId) {
        return services.get(serviceId);
    }

    public List<Service> getServicesByUser(String providerUserId) {
        List<Service> userServices = new ArrayList<>();
        for (Service s : services.values()) {
            if (s.getProviderUserId().equals(providerUserId)) {
                userServices.add(s);
            }
        }
        return userServices;
    }

    public List<Service> getAllServices() {
        return new ArrayList<>(services.values());
    }

}