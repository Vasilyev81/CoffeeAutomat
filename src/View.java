import javafx.concurrent.Service;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;

public class View extends Group {

    private Menu menu;
    DrinksBtnArray drinksButtonsArr;
    private Service model;



    HBox hBox = new HBox(5);

    Button rub1 = new Button();
    Button rub2 = new Button();
    Button rub5 = new Button();
    Button rub10 = new Button();

    ImageView ru1v = new ImageView(new Image("./1ru.jpg"));
    ImageView ru2v = new ImageView(new Image("./2ru.jpg"));
    ImageView ru5v = new ImageView(new Image("./5ru.jpg"));
    ImageView ru10v = new ImageView(new Image("./10ru.jpg"));

    TextArea moneyMessage = new TextArea();
    TextArea textMessage = new TextArea();

    ProgressBar progressBar = new ProgressBar();

    GridPane drinksPane = new GridPane();

    Button progressButton = new Button();
    Button cancelButton = new Button();


    public View(Menu menu, Service service) {
        this.menu = menu;
        drinksButtonsArr = new DrinksBtnArray(menu);
        model = service;
        layoutForm();
        bindFieldsToModel();
    }


    private void layoutForm() {
        rub1.setGraphic(ru1v);
        rub1.setCursor(Cursor.HAND);
        rub2.setGraphic(ru2v);
        rub2.setCursor(Cursor.HAND);
        rub5.setGraphic(ru5v);
        rub5.setCursor(Cursor.HAND);
        rub10.setGraphic(ru10v);
        rub10.setCursor(Cursor.HAND);

        hBox.getChildren().add(rub1);
        hBox.getChildren().add(rub2);
        hBox.getChildren().add(rub5);
        hBox.getChildren().add(rub10);

        moneyMessage.setLayoutX(5);
        moneyMessage.setLayoutY(120);
        moneyMessage.setPrefSize(120, 20);
        moneyMessage.setFont(Font.font(15));
        moneyMessage.setWrapText(false);
        moneyMessage.setPrefRowCount(1);
        moneyMessage.setCursor(Cursor.WAIT);
        moneyMessage.editableProperty().setValue(false);

        textMessage.setLayoutX(120);
        textMessage.setLayoutY(120);
        textMessage.setPrefSize(360, 40);
        textMessage.setFont(Font.font(15));
        textMessage.setCursor(Cursor.WAIT);
        textMessage.editableProperty().setValue(false);

        progressBar.setLayoutX(5);
        progressBar.setLayoutY(175);
        progressBar.setPrefSize(480, 25);
        progressBar.setCursor(Cursor.WAIT);
        progressBar.setVisible(true);
        

        drinksPane.setLayoutX(5);
        drinksPane.setLayoutY(205);

        {
            int column = 0;
            int row = 0;
            for (DrinkButton btn : drinksButtonsArr) {
                btn.setPrefSize(160, 50);
                btn.setCursor(Cursor.HAND);
                btn.setText(btn.getDrinkName() + "\n" + btn.getPrice());
                drinksPane.add(btn, column, row);
                column++;
                if (column == 3) {
                    column = 0;
                    row++;
                }
            }

            progressButton.setPrefHeight(50);
            progressButton.setPrefWidth(160);
            progressButton.setFont(Font.font(10));
            progressButton.setCursor(Cursor.HAND);
            progressButton.setText("Progress");
            drinksPane.add(progressButton, 2, 1);

            cancelButton.setPrefHeight(50);
            cancelButton.setPrefWidth(480);
            cancelButton.setFont(Font.font(15));
            cancelButton.setCursor(Cursor.HAND);
            cancelButton.setText("Завершение");
            drinksPane.add(cancelButton, 0, row + 1, 3, 1);
        }



        this.getChildren().add(hBox);
        this.getChildren().add(moneyMessage);
        this.getChildren().add(textMessage);
        this.getChildren().add(progressBar);
        this.getChildren().add(drinksPane);
    }

    private void bindFieldsToModel() {
        textMessage.textProperty().bind(model.messageProperty());
        moneyMessage.textProperty().bind(model.valueProperty().asString());
        //maybe unbinding is not necessary
        progressBar.progressProperty().unbind();
        progressBar.progressProperty().bind(model.progressProperty());
    }


}


