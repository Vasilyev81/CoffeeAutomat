import javafx.scene.control.Button;

class ChoiseButton extends Button {
    String drinkName;
    int price;

    public ChoiseButton(String d, int p) {
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