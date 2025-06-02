package utils;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import services.AuthService;
import services.ServiceManager;
import services.RequestManager;

public class Router {

    private static Stage primaryStage;
    private static AuthService authService;
    private static ServiceManager serviceManager;
    private static RequestManager requestManager;

    public static void setPrimaryStage(Stage stage) {
        primaryStage = stage;
    }

    public static void setServices(AuthService auth, ServiceManager service, RequestManager request) {
        authService = auth;
        serviceManager = service;
        requestManager = request;
    }

    public static void goTo(String fxmlPath, String title) throws Exception {
        FXMLLoader loader = new FXMLLoader(Router.class.getClassLoader().getResource(fxmlPath));
        Scene scene = new Scene(loader.load());

        // Inject services if controller implements a setup method
        Object controller = loader.getController();
        if (controller instanceof InitializableWithServices) {
            ((InitializableWithServices) controller).initializeServices(authService, serviceManager, requestManager);
        }

        primaryStage.setTitle(title);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}