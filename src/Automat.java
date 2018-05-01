import javafx.beans.property.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.util.HashMap;
import java.util.Map;


public final class Automat implements Runnable {
    private final IntegerProperty account = new SimpleIntegerProperty(this, "accountValue", 0);
    private final StringProperty message = new SimpleStringProperty(this, "textMessage", "");
    private final DoubleProperty progress = new SimpleDoubleProperty(this, "progressValue", 0.00);
    private final Menu menu;
    private int minPrice;
    private Choise choise;

    private STATES State;


    enum STATES {
        LOW_MONEY, ACCEPT, CHECK, COOK
    }


    public Automat(Menu menu) {
        this.account.set(0);
        this.menu = menu;
        this.minPrice = findMinPrice();
        this.message.set("");
        this.State = STATES.LOW_MONEY;
        this.progress.setValue(0.00);
        this.choise = new Choise();
    }


    /*Domain specific business rules*/
    @Override
    public void run() {
        while (true) {
            switch (State) {
                case LOW_MONEY: {
                    while (account.get() < minPrice) {
                        setMessage("Внесите денежные средства");
                    }
                    setMessage("Выберите напиток");
                }
                case ACCEPT: {
                    setMessage("\nВыбрать напиток(1), добавить денежные средства(2) или отменить сеанс(3)?");
                    //String temp = reader.readLine();
//                    if (temp.equals("1"))
//                    {
//                        automat.prinMenu();
//                        automat.choice();
//                        automat.State = Automata.STATES.CHECK;
//                        break;
//                    } else if (temp.equals("2"))
//                    {
//                        automat.depositAccount(0);
//                        break;
//                    } else if (temp.equals("3"))
//                    {
//                        automat.cancel();
//                        automat.State = Automata.STATES.WAIT;
//                        break;
//                    }
                }
                case CHECK: {
                    /*if (automat.check(btn.getDrinkName()) == false)
                    {
                        automat.sendToDisplay("\nНедостаточно денежных средств. Внести дополнительные денежные средства(1) или отменить сеанс(2)?");
                        String temp = reader.readLine();
                        if (temp.equals("1"))
                        {
                            automat.depositAccount(0);
                            break;
                        } else if (temp.equals("2"))
                        {
                            automat.cancel();
                            automat.State = Automata.STATES.WAIT;
                            break;
                        }
                    } else if (automat.check(btn.getDrinkName()))
                    {
                        automat.State = Automata.STATES.COOK;
                        break;
                    }*/
                }
                case COOK: {
                    try {
                        check(choise.price);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    finish();
                    //automat.State = Automata.STATES.WAIT;
                    break;
                }
            }
        }
    }


    public STATES getState() {
        return State;
    }


    public HashMap<String, Integer> getMenu() {
        return this.menu;
    }

    // Searching smallest price
    private int findMinPrice() {
        menu.forEach((key, value) -> {
            int p = value;
            if (p < minPrice) minPrice = p;
        });
        return minPrice;
    }

    //занесение денег на счёт пользователем
    public void depositAccount(int cash) {
        setMessage("Введите наличные");
        account.set(account.get() + cash);
        setMessage("Денежные средства: " + account.get());
        State = STATES.ACCEPT;
    }

    //Работаем с дисплеем
    public String getMessage() {
        return message.toString();
    }

    // set Choice
    public void setChoise(String drinkName, Integer price) {
        choise.setDrinkName(drinkName);
        choise.setPrice(price);
        System.out.println(choise.toString());
    }

    //проверка наличия необходимой суммы
    public void check(int price) throws InterruptedException {
        if (account.get() < price) setMessage("Недостаточно денег для оплаты выбранного напитка");
        else cook(price);
    }

    //отмена сеанса обслуживания пользователем
    public void cancel() {
        setMessage("Отмена сеанса обслуживания, возврат денежных средств: " + account.get());
        account.set(0);
    }

    //имитация процесса приготовления напитка
    public void cook(int price) throws InterruptedException {
        setMessage("Идёт приготовление напитка!");
        account.set(account.get() - price);
        latency(400);
        double timer;
        for (int i = 0; i < 1000; i++) {
            latency(5);
            timer = +0.001;
            setProgress(timer);
        }
        setMessage("Ваш напиток готов!");
        latency(400);
        setProgress(0.00);
    }

    //завершение обслуживания пользователя
    public void finish() {
        if (account.get() > 0) {
            setMessage("Ваша сдача: " + account + "Приятного аппетита!");
            account.set(0);
        } else setMessage("Приятного аппетита!");
    }

    /*Progress*/
    private void setProgress(double value) {
        progress.setValue(value);
    }


    /*waiting*/
    private final void latency(long mills) {
        try {
            Thread.sleep(mills);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /*message property*/
    public final String getMesage() {
        return message.get();
    }

    public final void setMessage(String message) {
        messageProperty().set(message);
    }

    public final StringProperty messageProperty() {
        return message;
    }


    /*accountValue property*/
    public final int getAccontValue() {
        return account.get();
    }

    public final IntegerProperty accountProperty() {
        return account;
    }

    /*progresValue property*/

    public final DoubleProperty progressProperty() {
        return progress;
    }

}

