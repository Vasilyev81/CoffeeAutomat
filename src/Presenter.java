public class Presenter implements Runnable {
    private final Automat model;
    private final View view;

    public Presenter(Automat automat, View view) {
        this.model = automat;
        this.view = view;
        attachEvents();
    }

    private void attachEvents() {
        view.rub1.setOnAction(e -> model.depositAccount(1));
        view.rub2.setOnAction(e -> model.depositAccount(2));
        view.rub5.setOnAction(e -> model.depositAccount(5));
        view.rub10.setOnAction(e -> model.depositAccount(10));
        for (DrinkButton choiceBtn : view.drinksButtonsArr) {
            choiceBtn.setOnAction(event -> {
                model.setChoice(choiceBtn.getDrinkName(), choiceBtn.getPrice());
                model.setState(Automat.STATES.COOK);
            });
        }
        view.cancelButton.setOnAction(e -> model.cancel());
    }

    @Override
    public void run() {
    }
}


