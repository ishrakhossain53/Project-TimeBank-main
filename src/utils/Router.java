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
        if (primaryStage == null) {
            throw new IllegalStateException("Primary stage not set. Call Router.setPrimaryStage() first.");
        }

        if (Router.class.getClassLoader().getResource(fxmlPath) == null) {
            throw new IllegalArgumentException("FXML file not found: " + fxmlPath);
        }

        FXMLLoader loader = new FXMLLoader(Router.class.getClassLoader().getResource(fxmlPath));
        Scene scene = new Scene(loader.load());  // or add default size if needed

        Object controller = loader.getController();
        if (controller instanceof InitializableWithServices) {
            ((InitializableWithServices) controller).initializeServices(authService, serviceManager, requestManager);
        }

        primaryStage.setTitle(title);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
