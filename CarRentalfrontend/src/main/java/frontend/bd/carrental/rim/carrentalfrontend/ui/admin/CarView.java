package frontend.bd.carrental.rim.carrentalfrontend.ui.admin;

import com.vaadin.ui.ComboBox;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import frontend.bd.carrental.rim.carrentalfrontend.model.CarClass;

public class CarView extends VerticalLayout{

    public CarView() {
        TextField carIdField = new TextField("Car Id");
        TextField carModelField = new TextField("Model");

        ComboBox<CarClass> carClass = new ComboBox<>("Class");

        carClass.setItems(CarClass.Subcompacts);

        TextField carColorField = new TextField("Color");
        TextField carNumberPlateField = new TextField("Number Plate");
        TextField bookedField = new TextField("Booked");
        TextField perDayChargeField = new TextField("Day/Charge");

        FormLayout carLayout = new FormLayout();
        carLayout.addComponents(carIdField, carModelField, carClass, carColorField,
                carNumberPlateField, bookedField, perDayChargeField);

        this.addComponent(carLayout);
    }
}
