package frontend.bd.carrental.rim.carrentalfrontend.ui;


import com.vaadin.annotations.Theme;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.PushStateNavigation;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;

@Theme("valo")
@SpringUI
@PushStateNavigation
public class MainUI extends UI {


    @Override
    protected void init(VaadinRequest vaadinRequest) {

        Label menuLabel = new Label("Menu");
        menuLabel.addStyleName(ValoTheme.MENU_TITLE);

        Button homeButton = new Button("Home", e -> getNavigator().
                navigateTo("Home"));
        homeButton.addStyleNames(ValoTheme.BUTTON_LINK, ValoTheme.MENU_ITEM);


        Button registrationView = new Button("login", e -> getNavigator().
                navigateTo("Registration"));
        registrationView.addStyleNames(ValoTheme.BUTTON_LINK, ValoTheme.MENU_ITEM);


        CssLayout menu = new CssLayout(menuLabel, homeButton, registrationView);
        menu.addStyleName(ValoTheme.MENU_ROOT);
        menu.setSizeFull();

        VerticalLayout viewContainer = new VerticalLayout();
        viewContainer.setMargin(false);


        HorizontalLayout mainLayout = new HorizontalLayout();
        mainLayout.addComponent(menu);
        mainLayout.addComponentsAndExpand(viewContainer);

        mainLayout.setExpandRatio(menu, .1f);

        setContent(mainLayout);

        LoginView loginView = new LoginView();
        HomeView homeView = new HomeView();

        Navigator navigator = new Navigator(this, viewContainer);

        navigator.addView("", homeView);
        navigator.setErrorView(homeView);

        navigator.addView("Registration", loginView);


    }


}
