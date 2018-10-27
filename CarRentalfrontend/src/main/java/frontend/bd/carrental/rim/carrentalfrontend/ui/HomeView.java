package frontend.bd.carrental.rim.carrentalfrontend.ui;

import com.vaadin.navigator.View;
import com.vaadin.ui.Grid;
import com.vaadin.ui.VerticalLayout;

public class HomeView extends VerticalLayout implements View {

    public HomeView() {
        Grid<Object> grid1 = new Grid<>();
        Grid<Object> grid2 = new Grid<>();
        this.addComponents(grid1, grid2);
    }


}
