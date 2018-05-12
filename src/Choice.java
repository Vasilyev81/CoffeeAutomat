public final class Choice {
    public String drinkName;
    public Integer price;

    public Choice() {
        this.drinkName = "";
        this.price = 0;
    }

    public Choice(String drinkName, Integer price) {
        this.drinkName = drinkName;
        this.price = price;
    }

    public void setName(String drinkName) {
        this.drinkName = drinkName;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public void nullifyChoice(){
        this.drinkName = "";
        this.price = 0;
    }

    @Override
    public String toString() {
        return "Choice{" +
                "drinkName='" + drinkName + '\'' +
                ", price=" + price +
                '}';
    }
}
