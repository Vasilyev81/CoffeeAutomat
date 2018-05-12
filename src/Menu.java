import org.jetbrains.annotations.Contract;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.util.HashMap;

public final class Menu extends HashMap<String, Integer> {


    public Menu() {
        readData();
    }


    //считываем меню из файла и сохраняем в HashMap
    private void readData() {
        StringBuilder stringBuilder = new StringBuilder();
        JSONArray menuArr = null;
        JSONObject resultJSON;
        try (BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream("src/menu.json")))) {
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                stringBuilder.append(inputLine);
            }
        } catch (FileNotFoundException e) {
            System.err.println("Ошибка: файл меню не найден");
        } catch (IOException e) {
            System.err.println("Ошибка в процессе чтения файла меню");
        }
        try {
            menuArr = (JSONArray) JSONValue.parseWithException(stringBuilder.toString());
        } catch (ParseException e) {
            System.err.println("Ошибка в процессе парсинга файла меню");
        }
        if (!(menuArr.size() == 0)) {
            for (Object aMenuArr : menuArr) {
                resultJSON = (JSONObject) aMenuArr;
                this.put((String) resultJSON.get("drinkName"), Integer.valueOf((String) resultJSON.get("price")));
            }
        }
    }

    @Contract(pure = true)
    public HashMap<String, Integer> getMenu() {
        return this;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        this.forEach((key, value) -> {
            result.append(key);
            result.append(" - ");
            result.append(value);
            result.append("; ");
        });
        return result.toString();
    }
}
