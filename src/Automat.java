import javafx.beans.property.*;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;

import java.util.HashMap;


public final class Automat extends Task<ObservableList<Long>> {
    private final IntegerProperty account = new SimpleIntegerProperty(this, "accountValue", 0);
    private final StringProperty message = new SimpleStringProperty(this, "textMessage", "");
    private final DoubleProperty progress = new SimpleDoubleProperty(this, "progressValue", 0.00);
    private final Menu menu;
    private int minPrice;
    private boolean enoughMoney;
    private Choice choice;

    private STATES State;


    enum STATES {
        STANDBY, COOK, FINISH
    }


    public Automat(Menu menu) {
        this.account.set(0);
        this.menu = menu;
        this.minPrice = findMinPrice();
        this.enoughMoney = false;
        this.message.set("");
        this.State = STATES.STANDBY;
        this.progress.setValue(0.00);
        this.choice = new Choice();
    }


    /*Domain specific business rules*/
    //@Override
    public void run() {
        while (true) {
            switch (State) {
                case STANDBY: {
                    setMessage("Выберите напиток");
                    break;
                }
                case COOK: {
                    if (check(choice)) {
                        cook(choice);
                        setState(STATES.FINISH);
                    } else setState(STATES.STANDBY);
                    break;
                }
                case FINISH: {


                }
            }
        }
    }


    @Override
    protected ObservableList<Long> call() throws Exception {
        return null;
    }


    public void setState(STATES State) {
        this.State = State;
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
        account.set(account.get() + cash);
        setMessage("Денежные средства: " + account.get());
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
    public void cancel() {
        setMessage("Отмена сеанса обслуживания, возврат денежных средств: " + account.get());
        account.set(0);
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

