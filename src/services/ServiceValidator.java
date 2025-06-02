package services;

import models.Service;

public class ServiceValidator {

    public static boolean isValid(Service service) {
        if (service == null) return false;
        if (service.getTitle() == null || service.getTitle().isEmpty()) return false;
        if (service.getDescription() == null || service.getDescription().isEmpty()) return false;
        if (service.getProviderUserId() == null || service.getProviderUserId().isEmpty()) return false;
        if (service.getHours() <= 0) return false;
        return true;
    }
}