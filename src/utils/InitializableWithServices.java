package utils;

import services.AuthService;
import services.ServiceManager;
import services.RequestManager;

public interface InitializableWithServices {
    void initializeServices(AuthService auth, ServiceManager service, RequestManager request);
}