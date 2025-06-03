package TimeBank;

import javafx.application.Application;
import javafx.stage.Stage;
import services.AuthService;
import services.ServiceManager;
import services.RequestManager;
import utils.Router;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) {
        try {
            AuthService authService = new AuthService();
            ServiceManager serviceManager = new ServiceManager();
            RequestManager requestManager = new RequestManager();

            Router.setPrimaryStage(primaryStage);
            Router.setServices(authService, serviceManager, requestManager);

            Router.goTo("views/login.fxml", "Time Bank - Login");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
