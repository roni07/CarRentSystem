package frontend.bd.carrental.rim.carrentalfrontend.ui.admin;

import com.vaadin.navigator.View;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TabSheet;

public class AdminView extends HorizontalLayout implements View {

    private BranchView branchView;
    private CarView carView;
    public AdminView() {
        branchView = new BranchView();
        carView = new CarView();

        TabSheet tabSheet = new TabSheet();
        tabSheet.setSizeFull();

        tabSheet.addTab(branchView, "Branch");
        tabSheet.addTab(carView, "Car");


        this.addComponent(tabSheet);

    }
}
