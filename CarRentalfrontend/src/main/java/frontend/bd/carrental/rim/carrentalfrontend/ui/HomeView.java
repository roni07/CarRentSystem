package frontend.bd.carrental.rim.carrentalfrontend.ui;

import com.vaadin.navigator.View;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

public class HomeView extends VerticalLayout implements View {

    public HomeView() {
        this.setSizeFull();
        this.setWidth(100,Unit.PERCENTAGE);
        this.setHeight(100,Unit.PERCENTAGE);

        this.addComponent(new Label("Welcome"));
      //  MenuBar barmenu = new MenuBar();
//        MenuBar.MenuItem vehicle = barmenu.addItem("Vehicle");
//        MenuBar.MenuItem toyota = vehicle.addItem("ToYoTa");
//
//        MenuBar.MenuItem kia = vehicle.addItem("KIA");
//        MenuBar.MenuItem kiaCadenza = kia.addItem("KIA Caenza");
//        MenuBar.MenuItem exterior = kia.addItem("EXTERIOR");
//        MenuBar.MenuItem sedans = kia.addItem("SEDANS");
//
//        vehicle.setStyleName(ValoTheme.MENU_ITEM);
//
//        MenuBar.MenuItem nissan = vehicle.addItem("NiSSaN");
//        MenuBar.MenuItem mitsubishi = vehicle.addItem("Mitsubishi");
//
//
//
//// Another top-level item
//        MenuBar.MenuItem snacks = barmenu.addItem("About");
//
//        snacks.setStyleName(ValoTheme.MENU_ITEM);
//
//// Yet another top-level item
//        MenuBar.MenuItem servs = barmenu.addItem("Services", null, null);
//        servs.setStyleName(ValoTheme.MENU_ITEM);

    }


}
