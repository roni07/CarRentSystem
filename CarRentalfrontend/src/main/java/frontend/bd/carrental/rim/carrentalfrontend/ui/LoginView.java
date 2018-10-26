package frontend.bd.carrental.rim.carrentalfrontend.ui;

import com.vaadin.navigator.View;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import frontend.bd.carrental.rim.carrentalfrontend.ui.admin.AdminView;


public class LoginView extends HorizontalLayout implements View {


    FormLayout loginForm = new FormLayout();

    public LoginView() {

        TextField userNameField = new TextField("User Name");
        PasswordField passField = new PasswordField("Password");


        Button loginButton = new Button("Login");
        loginButton.addStyleName(ValoTheme.BUTTON_PRIMARY);

        Button signUpButton = new Button("+SignUp");
        signUpButton.setStyleName(ValoTheme.BUTTON_PRIMARY);


        loginButton.addClickListener(e -> getUI().getNavigator().addView( "AdminView",
                AdminView.class));


    /*    loginButton.addClickListener(clickEvent -> {
            if (userNameField.getValue().equals("a")
                    &&
                    passField.getValue().equals("a")) {
                getUI().getNavigator().navigateTo("AdminView");
                userNameField.clear();
                passField.clear();
            }
        });*/


        loginButton.addClickListener(e -> getUI()
                .getNavigator()
                .navigateTo("AdminView")
        );


        signUpButton.addClickListener(e -> getUI().getNavigator().addView(
                "CustomerRegistrationFormView",
                CustomerRegistrationFormView.class));

        signUpButton.addClickListener(e -> getUI()
                .getNavigator()
                .navigateTo("CustomerRegistrationFormView")
        );


        HorizontalLayout buttons = new HorizontalLayout();
        buttons.addComponents(loginButton, signUpButton);

        loginForm.addComponents(userNameField, passField, buttons);


        this.addComponents(loginForm);

  /*        back button property
        loginButton.addClickListener(e -> UI.getCurrent().getNavigator().
                navigateTo("reg"));
*/

    }

}
