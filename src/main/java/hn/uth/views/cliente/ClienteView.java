package hn.uth.views.cliente;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.dependency.Uses;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.Notification.Position;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.splitlayout.SplitLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.data.renderer.LitRenderer;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;
import com.vaadin.flow.spring.data.VaadinSpringDataHelpers;
import com.vaadin.flow.theme.lumo.LumoIcon;

import hn.uth.data.SamplePerson;
import hn.uth.views.MainLayout;
import java.util.Optional;
import org.springframework.data.domain.PageRequest;
import org.springframework.orm.ObjectOptimisticLockingFailureException;

@PageTitle("Cliente")
@Route(value = "Cliente/:samplePersonID?/:action?(edit)", layout = MainLayout.class)
@RouteAlias(value = "", layout = MainLayout.class)
@Uses(Icon.class)
public class ClienteView extends Div implements BeforeEnterObserver {

    private final String SAMPLEPERSON_ID = "samplePersonID";
    private final String SAMPLEPERSON_EDIT_ROUTE_TEMPLATE = "Cliente/%s/edit";

    private final Grid<SamplePerson> grid = new Grid<>(SamplePerson.class, false);

    private TextField identidad;
    private TextField nombre; 
    private TextField apellido;
    private TextField correo;
    private TextField telefono;
    private DatePicker fechaCumpleaños;
    private ComboBox<String> sexo;
    private TextField nacionalidad;
    private TextField lugarProcedencia;

    private final Button cancel = new Button("Cancelar");
    private final Button save = new Button("Guardar", new Icon(VaadinIcon.CHECK_CIRCLE));
    private final Button eliminar = new Button("Eliminar", new Icon(VaadinIcon.CLOSE_CIRCLE));
    
    private SamplePerson ClienteSeleccionado;

    public ClienteView() {
        addClassNames("cliente-view");

        // Create UI
        SplitLayout splitLayout = new SplitLayout();

        createGridLayout(splitLayout);
        createEditorLayout(splitLayout);

        add(splitLayout);

        // Configure Grid
        grid.addColumn("identidad").setAutoWidth(true);
        grid.addColumn("nombre").setAutoWidth(true);
        grid.addColumn("apellido").setAutoWidth(true);
        grid.addColumn("correo").setAutoWidth(true);
        grid.addColumn("telefono").setAutoWidth(true);
        grid.addColumn("fechaCumpleaños").setAutoWidth(true);
        grid.addColumn("sexo").setAutoWidth(true);
        grid.addColumn("nacionalidad").setAutoWidth(true);
        grid.addColumn("lugarProcedencia").setAutoWidth(true);
        
        grid.addThemeVariants(GridVariant.LUMO_NO_BORDER);

        // when a row is selected or deselected, populate form
        grid.asSingleSelect().addValueChangeListener(event -> {
            if (event.getValue() != null) {
                UI.getCurrent().navigate(String.format(SAMPLEPERSON_EDIT_ROUTE_TEMPLATE, event.getValue().getId()));
            } else {
                clearForm();
                UI.getCurrent().navigate(ClienteView.class);
            }
        });

   
        cancel.addClickListener(e -> {
            clearForm();
            refreshGrid();
        });

        save.addClickListener(e -> {
            try {
                if (this.ClienteSeleccionado == null) {
                    this.ClienteSeleccionado = new SamplePerson();
                }
               
                clearForm();
                refreshGrid();
                Notification.show("Data updated");
                UI.getCurrent().navigate(ClienteView.class);
            } catch (ObjectOptimisticLockingFailureException exception) {
                Notification n = Notification.show(
                        "Error updating the data. Somebody else has updated the record while you were making changes.");
                n.setPosition(Position.MIDDLE);
                n.addThemeVariants(NotificationVariant.LUMO_ERROR);
           
            }
        });
       eliminar.addClickListener ( e-> {
       Notification n = Notification.show( "Botor eliminar seleccionado, aun no hay nada que eliminar");
       n.setPosition(Position.MIDDLE);
       n.addThemeVariants(NotificationVariant.LUMO_WARNING);
    });
    } 
    @Override
    public void beforeEnter(BeforeEnterEvent event) {
        Optional<Long> samplePersonId = event.getRouteParameters().get(SAMPLEPERSON_ID).map(Long::parseLong);
        if (samplePersonId.isPresent()) {
           /* Optional<SamplePerson> samplePersonFromBackend = samplePersonService.get(samplePersonId.get());
            if (samplePersonFromBackend.isPresent()) {
                populateForm(samplePersonFromBackend.get());
            } else {
                Notification.show(
                        String.format("The requested samplePerson was not found, ID = %s", samplePersonId.get()), 3000,
                        Notification.Position.BOTTOM_START);
                // when a row is selected but the data is no longer available,
                // refresh grid
                refreshGrid();
                event.forwardTo(ClienteView.class);
            }*/
        }
    }

    private void createEditorLayout(SplitLayout splitLayout) {
        Div editorLayoutDiv = new Div();
        editorLayoutDiv.setClassName("editor-layout");

        Div editorDiv = new Div();
        editorDiv.setClassName("editor");
        editorLayoutDiv.add(editorDiv);

        FormLayout formLayout = new FormLayout();
        identidad = new TextField("identidad");
        identidad.setPrefixComponent(VaadinIcon.USER_CHECK.create());        
        nombre = new TextField("nombre");
        nombre.setPrefixComponent(VaadinIcon.USER_CARD.create());
        apellido = new TextField("Apellido");
        apellido.setPrefixComponent(VaadinIcon.USER_CARD.create());
        correo = new TextField("Correo");
        correo.setPrefixComponent(VaadinIcon.AT.create());
        telefono = new TextField("Telefono");
        telefono.setPrefixComponent(VaadinIcon.PHONE.create());
        fechaCumpleaños = new DatePicker("Fecha Cumpleaños");
        fechaCumpleaños.setPrefixComponent(VaadinIcon.DATE_INPUT.create());
        sexo = new ComboBox<>("Sexo");
        sexo.setPrefixComponent(VaadinIcon.FAMILY.create());
        sexo.setAllowCustomValue(true);
        
        nacionalidad = new TextField("Nacionalidad");
        nacionalidad.setPrefixComponent(VaadinIcon.MAP_MARKER.create());
        lugarProcedencia = new TextField("Lugar de Procedencia");
        lugarProcedencia.setPrefixComponent(VaadinIcon.AIRPLANE.create());
        formLayout.add(identidad, nombre, apellido, correo, telefono, fechaCumpleaños, sexo, nacionalidad, lugarProcedencia);

        editorDiv.add(formLayout);
        createButtonLayout(editorLayoutDiv);

        splitLayout.addToSecondary(editorLayoutDiv);
    }

    private void createButtonLayout(Div editorLayoutDiv) {
        HorizontalLayout buttonLayout = new HorizontalLayout();
        buttonLayout.setClassName("button-layout");
        cancel.addThemeVariants(ButtonVariant.LUMO_TERTIARY);
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        eliminar.addThemeVariants(ButtonVariant.LUMO_PRIMARY, ButtonVariant.LUMO_ERROR);
        
        buttonLayout.add(save, eliminar, cancel);
        editorLayoutDiv.add(buttonLayout);
    }

    private void createGridLayout(SplitLayout splitLayout) {
        Div wrapper = new Div();
        wrapper.setClassName("grid-wrapper");
        splitLayout.addToPrimary(wrapper);
        wrapper.add(grid);
    }

    private void refreshGrid() {
        grid.select(null);
        grid.getDataProvider().refreshAll();
    }

    private void clearForm() {
        populateForm(null);
    }

    private void populateForm(SamplePerson value) {
        this.ClienteSeleccionado = value;
       if(value != null) {
    	   identidad.setValue(value.getIdentidad());
    	   nombre.setValue(value.getNombre());
    	   apellido.setValue(value.getApellido());
    	   correo.setValue(value.getCorreo());
    	   telefono.setValue(value.getTelefono());
    	   fechaCumpleaños.setValue(value.getFechaCumpleaños());
    	   sexo.setValue(value.getSexo());
    	   nacionalidad.setValue(value.getNacionalidad());
    	   lugarProcedencia.setValue(value.getLugarProcedencia());   
       }     
    }
}
