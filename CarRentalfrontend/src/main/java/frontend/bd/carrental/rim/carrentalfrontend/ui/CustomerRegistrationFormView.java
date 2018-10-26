package frontend.bd.carrental.rim.carrentalfrontend.ui;

import com.vaadin.data.Binder;
import com.vaadin.navigator.View;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import frontend.bd.carrental.rim.carrentalfrontend.model.Customer;
import frontend.bd.carrental.rim.carrentalfrontend.service.CustomerService;

import java.util.Optional;


public class CustomerRegistrationFormView extends FormLayout implements View {

    final CustomerService customerService = new CustomerService();

    private Binder<Customer> customerBinder = new Binder<>();

    public Optional<Customer> getCustomer() {
        Customer customer = new Customer();
        if (customerBinder.writeBeanIfValid(customer))
            return Optional.of(customer);
        else return Optional.empty();
    }

    private TextField firstNameField;
    private TextField lastNameField;
    private TextField emailField;
    private TextField userNameField;
    private PasswordField passwordField;
    private TextField streetNumberFiled;
    private TextField streetNameField;
    private TextField cityField;
    private TextField postalCodeField;

    public CustomerRegistrationFormView() {

        firstNameField = new TextField("First Name");
        lastNameField = new TextField("Last Name");
        emailField = new TextField("Email");
        userNameField = new TextField("User Name");
        passwordField = new PasswordField("Password");
        streetNumberFiled = new TextField("Street Number");
        streetNameField = new TextField("Street Name");
        cityField = new TextField("City");
        postalCodeField = new TextField("Postal Code");


        Button registrationButton = new Button("+SignUp");
        registrationButton.setStyleName(ValoTheme.BUTTON_PRIMARY);

        registrationButton.addClickListener(clickEvent -> {
            Optional<Customer> optionalCustomer = this.getCustomer();
            if (optionalCustomer.isPresent()) {
                Customer customer = null;
                try {
                    customer = this.customerService.customerRegistration(optionalCustomer.get());
                    Notification.show("Customer Registered " + customer.getFirstName());
                } catch (Exception e) {
                    Notification.show(e.getMessage(), Notification.TYPE_ERROR_MESSAGE);
                    e.printStackTrace();
                }

            } else {
                Notification.show("Error int the form entry", Notification.TYPE_WARNING_MESSAGE);
            }
            customerBinder.readBean(null);
        });

        //first name binder
        customerBinder
                .forField(firstNameField)
                .asRequired()
                .bind(Customer::getFirstName, Customer::setFirstName);

        //last name binder
        customerBinder
                .forField(lastNameField)
                .asRequired()
                .bind(Customer::getLastName, Customer::setLastName);

        //email binder
        customerBinder
                .forField(emailField)
                .asRequired()
                .bind(Customer::getEmail, Customer::setEmail);

        //user name binder
        customerBinder
                .forField(userNameField)
                .asRequired()
                .bind(Customer::getUserName, Customer::setUserName);

        //password binder
        customerBinder
                .forField(passwordField)
                .asRequired()
                .bind(Customer::getPassword, Customer::setPassword);

        //street number binder
        customerBinder
                .forField(streetNumberFiled)
                .asRequired()
                .bind(customer -> customer.getCustomerAddress().getStreetNumber(),
                        (customer, c) -> customer.getCustomerAddress().setStreetNumber(c));

        //street name binder
        customerBinder
                .forField(streetNameField)
                .asRequired()
                .bind(customer -> customer.getCustomerAddress().getStreetName(),
                        ((customer, c) -> customer.getCustomerAddress().setStreetName(c)));

        //
        customerBinder
                .forField(cityField)
                .asRequired()
                .bind(customer -> customer.getCustomerAddress().getCity(),
                        ((customer, c) -> customer.getCustomerAddress().setCity(c)));

        customerBinder
                .forField(postalCodeField)
                .asRequired()
                .bind(customer -> customer.getCustomerAddress().getPostalCode(),
                        ((customer, c) -> customer.getCustomerAddress().setPostalCode(c)));


        // FormLayout formLayout = new FormLayout();
        //formLayout.setSizeUndefined();

        this.addComponents(firstNameField, lastNameField, emailField, userNameField, passwordField,
                streetNumberFiled, streetNameField, cityField, postalCodeField, registrationButton);

        this.setSizeUndefined();

        this.setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);
    }

    public void clearAllField() {
        firstNameField.clear();
        lastNameField.clear();
        emailField.clear();
        userNameField.clear();
        passwordField.clear();
        streetNameField.clear();
        streetNumberFiled.clear();
        cityField.clear();
        postalCodeField.clear();
    }
}
