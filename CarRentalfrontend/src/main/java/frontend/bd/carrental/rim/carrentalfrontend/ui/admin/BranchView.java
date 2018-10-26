package frontend.bd.carrental.rim.carrentalfrontend.ui.admin;

import com.vaadin.data.Binder;
import com.vaadin.data.converter.StringToIntegerConverter;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.ui.*;
import com.vaadin.ui.renderers.ComponentRenderer;
import com.vaadin.ui.themes.ValoTheme;
import frontend.bd.carrental.rim.carrentalfrontend.model.Branch;
import frontend.bd.carrental.rim.carrentalfrontend.service.BranchService;


public class BranchView extends HorizontalLayout{

    private BranchService branchService = new BranchService();

    Binder<Branch> branchBinder = new Binder<>();


    public BranchView() {
        this.setSizeFull();
        Grid<Branch> branchGrid = new Grid<>();
        branchGrid.setSizeFull();
        branchGrid.setCaption("Branch List");
        branchGrid.setItems(branchService.getBranch());
        branchGrid.addColumn(Branch::getBranchId).setCaption("Branch Id");
        branchGrid.addColumn(Branch::getBranchName).setCaption("Branch Name");
        branchGrid.addColumn(branch -> branch.getBranchAddress().getStreetNumber())
                .setCaption("Street Number");
        branchGrid.addColumn(branch -> branch.getBranchAddress().getStreetName())
                .setCaption("Street Name");
        branchGrid.addColumn(branch -> branch.getBranchAddress().getCity()).setCaption("City");
        branchGrid.addColumn(branch -> branch.getBranchAddress().getPostalCode()).
                setCaption("Postal Code");

        branchGrid.addColumn(branch -> {
                    Button editButton = new Button("Edit");
                    editButton.setIcon(VaadinIcons.EDIT);
                    return editButton;
                }).setRenderer(new ComponentRenderer()).setCaption("Action");


        TextField branchIdField = new TextField("Id");
        TextField branchName = new TextField("Branch Name");
        TextField streetNumberField = new TextField("Street Number");
        TextField streetNameField = new TextField("Street Name");
        TextField cityField = new TextField("City");
        TextField postalCodeField = new TextField("Postal Code");

        Button addButton = new Button("+Add");

        addButton.setStyleName(ValoTheme.BUTTON_PRIMARY);


        FormLayout branchForm = new FormLayout(branchIdField, branchName, streetNameField,
                streetNumberField, cityField, postalCodeField, addButton);

        branchForm.setCaption("Add New Branch");

        this.addComponents(branchGrid, branchForm);
        this.setSizeFull();
        this.setHeightUndefined();

        branchBinder.forField(branchIdField)
                .asRequired()
                .withConverter(new StringToIntegerConverter("Id Must Be Number"))
                .bind(Branch::getBranchId, Branch::setBranchId);

    }
}
