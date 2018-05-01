import javafx.scene.control.Button;

import java.util.function.BiConsumer;

class DrinkButton extends Button {
    String drinkName;
    int price;

    public DrinkButton(String d, int p) {
        super();
        this.drinkName = d;
        this.price = p;
    }

    public String getDrinkName() {
        return drinkName;
    }

    public int getPrice() {
        return price;
    }
}