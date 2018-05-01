import java.util.ArrayList;
import java.util.Map;

public class DrinksBtnArray extends ArrayList<DrinkButton> {

    public DrinksBtnArray(Menu menu) {
        createDrinksBtnArray(menu);
    }

    private void createDrinksBtnArray(Menu menu) {
        for (Map.Entry<String, Integer> entry : menu.entrySet()) {
            this.add(new DrinkButton(entry.getKey(), entry.getValue()));
        }
    }
}
