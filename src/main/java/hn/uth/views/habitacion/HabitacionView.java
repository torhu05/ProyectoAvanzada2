package hn.uth.views.habitacion;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.NativeLabel;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.Notification.Position;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.splitlayout.SplitLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.upload.Upload;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.data.converter.StringToIntegerConverter;
import com.vaadin.flow.data.renderer.LitRenderer;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.StreamResource;
import com.vaadin.flow.spring.data.VaadinSpringDataHelpers;

import hn.uth.controller.InteractorHabitacion;
import hn.uth.controller.InteractorImplHabitacion;
import hn.uth.controller.InteractorImplReserva;
import hn.uth.controller.InteractorReserva;
import hn.uth.data.Reserva;
import hn.uth.data.Cliente;
import hn.uth.data.Habitacion;
import hn.uth.views.MainLayout;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.PageRequest;
import org.springframework.orm.ObjectOptimisticLockingFailureException;

@PageTitle("Habitacion")
@Route(value = "Habitacion/:sampleBookID?/:action?(edit)", layout = MainLayout.class)
public class HabitacionView extends Div implements BeforeEnterObserver, ViewModelHabitacion {

    private final String SAMPLEBOOK_ID = "sampleBookID";
    private final String SAMPLEBOOK_EDIT_ROUTE_TEMPLATE = "Habitacion/%s/edit";

    private final Grid<Habitacion> grid = new Grid<>(Habitacion.class, false);

    private TextField numerohabitacion;
    private ComboBox<String> ocupacion;
    private TextField precio;
    private ComboBox<String> tipohabitacion;


    private final Button cancel = new Button("Cancelar");
    private final Button save = new Button("Guardar",new Icon(VaadinIcon.CHECK_CIRCLE));
    private final Button delete = new Button("Eliminar",new Icon(VaadinIcon.CLOSE_CIRCLE));
    


    private Habitacion HabitacionS;
    
    private List<Habitacion> elementos;
    private InteractorHabitacion controlador;


    public HabitacionView() {
        addClassNames("habitacion-view");

        
        controlador = new InteractorImplHabitacion(this);
        elementos = new ArrayList<>();
        // Create UI
        SplitLayout splitLayout = new SplitLayout();

        createGridLayout(splitLayout);
        createEditorLayout(splitLayout);

        add(splitLayout);

        grid.addColumn("numerohabitacion").setAutoWidth(true);
        grid.addColumn("ocupacion").setAutoWidth(true);
        grid.addColumn("precio").setAutoWidth(true);
        grid.addColumn("tipohabitacion").setAutoWidth(true);

        grid.addThemeVariants(GridVariant.LUMO_NO_BORDER);

        // when a row is selected or deselected, populate form
        grid.asSingleSelect().addValueChangeListener(event -> {
            if (event.getValue() != null) {
                UI.getCurrent().navigate(String.format(SAMPLEBOOK_EDIT_ROUTE_TEMPLATE, event.getValue().getNumerohabitacion()));
            } else {
                clearForm();
                UI.getCurrent().navigate(HabitacionView.class);
            }
        });
        
        controlador.consultarHabitacion();

        cancel.addClickListener(e -> {
            clearForm();
            refreshGrid();
        });

        save.addClickListener(e -> {
            try {
                if (this.HabitacionS == null) {
		            this.HabitacionS = new Habitacion();
		            this.HabitacionS.setNumerohabitacion(numerohabitacion.getValue());
		            this.HabitacionS.setOcupacion(ocupacion.getValue());
		            this.HabitacionS.setPrecio(Double.parseDouble(precio.getValue()));
		            this.HabitacionS.setTipohabitacion(tipohabitacion.getValue());
		            
		            this.controlador.CrearHabitacion(HabitacionS);
		            
                } else {
                	
                	this.HabitacionS.setNumerohabitacion(numerohabitacion.getValue());
		            this.HabitacionS.setOcupacion(ocupacion.getValue());
		            this.HabitacionS.setPrecio(Double.parseDouble(precio.getValue()));
		            this.HabitacionS.setTipohabitacion(tipohabitacion.getValue());
		            
		            this.controlador.ActualizarHabitacion(HabitacionS);
                	
                	
                }
                clearForm();
                refreshGrid();
                //Notification.show("Data updated");
                UI.getCurrent().navigate(HabitacionView.class);
            } catch (ObjectOptimisticLockingFailureException exception) {
                Notification n = Notification.show(
                        "Error updating the data. Somebody else has updated the record while you were making changes.");
                n.setPosition(Position.MIDDLE);
                n.addThemeVariants(NotificationVariant.LUMO_ERROR);
            } 
        });
    }

    @Override
    public void beforeEnter(BeforeEnterEvent event) {
        Optional<String> sampleBookId = event.getRouteParameters().get(SAMPLEBOOK_ID);
        if (sampleBookId.isPresent()) {
            Habitacion habitacionObtenida = obtenerHabitacion(sampleBookId.get());
            if (habitacionObtenida!=null) {
                populateForm(habitacionObtenida);
            } else {
                Notification.show(String.format("El empleado con identidad = %s no existe", sampleBookId.get()),
                        3000, Notification.Position.BOTTOM_START);
                // when a row is selected but the data is no longer available,
                // refresh grid
                refreshGrid();
                event.forwardTo(HabitacionView.class);
            }
        }
    }

    private Habitacion obtenerHabitacion(String identidad) {
		// TODO Auto-generated method stub
    	Habitacion encontrado = null;
    	for(Habitacion hab: elementos) {
    		if(hab.getNumerohabitacion().equals(identidad)) {
    			encontrado = hab;
    			break;
    		}
    	}
		return encontrado;
	}

	private void createEditorLayout(SplitLayout splitLayout) {
        Div editorLayoutDiv = new Div();
        editorLayoutDiv.setClassName("editor-layout");

        Div editorDiv = new Div();
        editorDiv.setClassName("editor");
        editorLayoutDiv.add(editorDiv);

        FormLayout formLayout = new FormLayout();
        numerohabitacion = new TextField("Numero habitacion");
        numerohabitacion.setPrefixComponent(VaadinIcon.TICKET.create());
        numerohabitacion.setId("numerohabitacion");
        
        ocupacion = new ComboBox<>("Ocupacion");
        ocupacion.setPrefixComponent(VaadinIcon.BED.create());
        ocupacion.setId("ocupacion");
        ocupacion.setItems("Cama 1","Cama 2","Cama 3");
        
        precio = new TextField("Precio");
        precio.setPrefixComponent(VaadinIcon.MONEY.create());
        
        tipohabitacion = new ComboBox<>("Tipo Habitacion");
        tipohabitacion.setPrefixComponent(VaadinIcon.STORAGE.create());
        tipohabitacion.setId("tipohabitacion");
        tipohabitacion.setItems("Individual","Doble","Triple");
        
        formLayout.add(numerohabitacion,ocupacion,precio,tipohabitacion);



        editorDiv.add(formLayout);
        createButtonLayout(editorLayoutDiv);

        splitLayout.addToSecondary(editorLayoutDiv);
    }

    private void createButtonLayout(Div editorLayoutDiv) {
        HorizontalLayout buttonLayout = new HorizontalLayout();
        buttonLayout.setClassName("button-layout");
        cancel.addThemeVariants(ButtonVariant.LUMO_TERTIARY);
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        delete.addThemeVariants(ButtonVariant.LUMO_PRIMARY,ButtonVariant.LUMO_ERROR);	
        buttonLayout.add(save,delete,cancel);

        editorLayoutDiv.add(buttonLayout);
    }

    private void createGridLayout(SplitLayout splitLayout) {
        Div wrapper = new Div();
        wrapper.setClassName("grid-wrapper");
        splitLayout.addToPrimary(wrapper);
        wrapper.add(grid);
    }

    private void attachImageUpload(Upload upload, Image preview) {
        ByteArrayOutputStream uploadBuffer = new ByteArrayOutputStream();
        upload.setAcceptedFileTypes("image/*");
        upload.setReceiver((fileName, mimeType) -> {
            uploadBuffer.reset();
            return uploadBuffer;
        });
        upload.addSucceededListener(e -> {
            StreamResource resource = new StreamResource(e.getFileName(),
                    () -> new ByteArrayInputStream(uploadBuffer.toByteArray()));
            preview.setSrc(resource);
            preview.setVisible(true);
            if (this.HabitacionS == null) {
                this.HabitacionS = new Habitacion();
            }
          //  this.sampleBook.setImage(uploadBuffer.toByteArray());
        });
        preview.setVisible(false);
    }

    private void refreshGrid() {
        grid.select(null);
        grid.getDataProvider().refreshAll();
        this.controlador.consultarHabitacion();
    }

    private void clearForm() {
        populateForm(null);
    }

    private void populateForm(Habitacion value) {
        this.HabitacionS = value;
        if(value != null) {
        	numerohabitacion.setValue(value.getNumerohabitacion());
        	ocupacion.setValue(value.getOcupacion());
        	precio.setValue(Double.toString(value.getPrecio()));
        	tipohabitacion.setValue(value.getTipohabitacion());
        }
       
    }

	@Override
	public void mostrarHabitacionEnGrid(List<Habitacion> items) {
		// TODO Auto-generated method stub
		Collection<Habitacion> itemsCollection = items;
		grid.setItems(itemsCollection);
		this.elementos = items;
		
	}

	@Override
	public void mostrarMensajeError(String mensaje) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void mostrarMensajeExito(String mensaje) {
		Notification.show(mensaje);	
		
	}

    }


