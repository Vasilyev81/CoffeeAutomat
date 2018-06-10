import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;


public class Automat extends Task<ObservableList<Long>> {

    private final Menu menu;
    private int account;
    private int minPrice;
    private boolean enoughMoney;
    private Choice choice;
    //private STATES State;
    //TODO: Migrate to the Tasks states



    //enum STATES {WAIT, COOK, FINISH, CANCEL};




    public Automat(Menu menu) {
        account = 0;
        this.menu = menu;
        minPrice = findMinPrice();
        enoughMoney = false;
        choice = new Choice();
        //State = STATES.WAIT;

    }

    //занесение денег на счёт пользователем
    public void depositAccount(int cash) {
        account+=cash;
        setMessage("Денежные средства: " + account);
    }




    @Override
    protected ObservableList<Long> call() throws Exception {
        //represent the results and state
        final ObservableList<Long> result = FXCollections.observableArrayList();

        //

        return result;
    }

    // Searching smallest price
    private int findMinPrice() {
        menu.forEach((key, value) -> {
            int p = value;
            if (p < minPrice) minPrice = p;
        });
        return minPrice;
    }



    //Работаем с дисплеем

    // set Choice
    public void setChoice(String drinkName, Integer price) {
        choice.setName(drinkName);
        choice.setPrice(price);
        System.out.println(choice.toString());
    }

    //проверка наличия необходимой суммы
    public boolean check(Choice choice) {
        if (choice.price < account.get())
            return true;

        else {
            setMessage("Стоимость выбранного напитка " + choice.price + "p.");
            choice.nullifyChoice();
            setState(STATES.STANDBY);
            return false;
        }
    }

    //отмена сеанса обслуживания пользователем
    public boolean cancel() {
        this.updateMessage("Отмена сеанса обслуживания, возврат денежных средств: " + account);
        account.set(0);
        return true;
    }

    //имитация процесса приготовления напитка
    public void cook(Choice choice) {

        int cookingTime = choice.price * 10;
        setMessage("Идёт приготовление напитка!");
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



    /*accountValue property*/
    public final int getAccontValue() {
        return account.get();
    }

    public final IntegerProperty accountProperty() {
        return account;
    }

    /*progresValue property*/
}

