import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.scene.Scene;
import javafx.stage.Stage;




public class CoffeeMachineApp extends Application {

    Menu menu = new Menu();

    //create a service
    Service<ObservableList<Long>> service = new Service<ObservableList<Long>>() {
        @Override
        protected Task<ObservableList<Long>> createTask() {
            return new Automat(menu);
        }
    };



    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage stage) {

        //Menu menu = new Menu();
        //Automat model = new Automat(menu);

/*
        Thread modelTrd = new Thread(model);
        modelTrd.start();
*/

        View view = new View(menu, service);
        view.setStyle("-fx-padding: 10;" +
                "-fx-border-style: solid inside;" +
                "-fx-border-width: 2;" +
                "-fx-border-insets: 5;" +
                "-fx-border-radius: 5;" +
                "-fx-border-color: blue;");
        Scene scene = new Scene(view);
        Presenter presenter = new Presenter(service, view);

/*
        Thread presenterTrd = new Thread(presenter);
        presenterTrd.start();
*/

        stage.setScene(scene);
        stage.setTitle("Coffee Machine");
        stage.show();
    }
}


