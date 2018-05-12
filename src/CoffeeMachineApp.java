import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.security.acl.Group;


public class CoffeeMachineApp extends Application {

    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage stage) {

        Menu menu = new Menu();
        Automat model = new Automat(menu);

/*
        Thread modelTrd = new Thread(model);
        modelTrd.start();
*/

        View view = new View(menu, model);
        view.setStyle("-fx-padding: 10;" +
                "-fx-border-style: solid inside;" +
                "-fx-border-width: 2;" +
                "-fx-border-insets: 5;" +
                "-fx-border-radius: 5;" +
                "-fx-border-color: blue;");
        Scene scene = new Scene(view);
        Presenter presenter = new Presenter(model, view);

/*
        Thread presenterTrd = new Thread(presenter);
        presenterTrd.start();
*/

        stage.setScene(scene);
        stage.setTitle("Coffee Machine");
        stage.show();
    }
}


