public class Presenter implements Runnable
{
    private final Automat model;
    private final View view;

    public Presenter(Automat automat, View view)
    {
        this.model = automat;
        this.view = view;
        attachEvents();
    }

    private void attachEvents(){
        view.rub1.setOnAction(e -> model.coin(1));
        view.rub2.setOnAction(e -> model.coin(2));
        view.rub5.setOnAction(e -> model.coin(5));
        view.rub10.setOnAction(e -> model.coin(10));
        view.cancelButton.setOnAction(e -> model.cancel());
    }

    @Override
    public void run() {

    }
}


