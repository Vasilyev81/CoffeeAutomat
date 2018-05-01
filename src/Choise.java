public class Choise {
    public String drinkName;
    public Integer price;

    public Choise() {
        this.drinkName = "";
        this.price = 0;
    }

    public Choise(String drinkName, Integer price) {
        this.drinkName = drinkName;
        this.price = price;
    }

    public void setDrinkName(String drinkName) {
        this.drinkName = drinkName;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Choise{" +
                "drinkName='" + drinkName + '\'' +
                ", price=" + price +
                '}';
    }
}
