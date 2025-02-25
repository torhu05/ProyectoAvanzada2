package hn.uth.views.reserva;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.Notification.Position;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.splitlayout.SplitLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.spring.data.VaadinSpringDataHelpers;

import hn.uth.controller.InteractorCliente;
import hn.uth.controller.InteractorImplCliente;
import hn.uth.controller.InteractorImplReserva;
import hn.uth.controller.InteractorReserva;
import hn.uth.data.Reserva;
import hn.uth.data.Habitacion;
import hn.uth.data.Cliente;
import hn.uth.views.MainLayout;
import hn.uth.views.habitacion.HabitacionView;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import org.springframework.data.domain.PageRequest;
import org.springframework.orm.ObjectOptimisticLockingFailureException;

@PageTitle("Reserva")
@Route(value = "Reserva/:sampleAddressID?/:action?(edit)", layout = MainLayout.class)
public class ReservaView extends Div implements BeforeEnterObserver, ViewModelReserva {

    private final String SAMPLEADDRESS_ID = "sampleAddressID";
    private final String SAMPLEADDRESS_EDIT_ROUTE_TEMPLATE = "Reserva/%s/edit";

    private final Grid<Reserva> grid = new Grid<>(Reserva.class, false);

    private TextField ticket;
    private TextField precioTotal;
    private TextField idHabitacion;
    private TextField idCliente;
    private DatePicker fechaInicio;
    private DatePicker fechaFinal;

    
    private final Button cancel = new Button("Cancelar");
    private final Button save = new Button("Guardar");
    private final Button eliminar = new Button("Eliminar");


    private Reserva ReservaS;


    private List<Reserva> elementos;
    private InteractorReserva controlador;
  


	public ReservaView() {

		addClassNames("reserva-view");

		controlador = new InteractorImplReserva(this);
		elementos = new ArrayList<>();

		// Create UI
		SplitLayout splitLayout = new SplitLayout();

		createGridLayout(splitLayout);
		createEditorLayout(splitLayout);

		add(splitLayout);

		// Configure Grid

		grid.addColumn("ticket").setAutoWidth(true).setHeader("Ticket");
		grid.addColumn("preciototal").setAutoWidth(true).setHeader("Precio Total");
		grid.addColumn("idhabitacion").setAutoWidth(true).setHeader("Numero Habitacion");
		grid.addColumn("idcliente").setAutoWidth(true).setHeader("Id Cliente");
		grid.addColumn("fechainicio").setAutoWidth(true).setHeader("Fecha de Inicio");
		grid.addColumn("fechafinal").setAutoWidth(true).setHeader("Fecha Final");

		grid.addThemeVariants(GridVariant.LUMO_NO_BORDER);

		// when a row is selected or deselected, populate form
		grid.asSingleSelect().addValueChangeListener(event -> {
			if (event.getValue() != null) {
				UI.getCurrent()
						.navigate(String.format(SAMPLEADDRESS_EDIT_ROUTE_TEMPLATE, event.getValue().getTicket()));
			} else {
				clearForm();
				UI.getCurrent().navigate(ReservaView.class);
			}
		});

		controlador.consultarReserva();

		cancel.addClickListener(e -> {
			clearForm();
			refreshGrid();
		});

		save.addClickListener(e -> {
			try {
				if (this.ReservaS == null) {
					this.ReservaS = new Reserva();
					this.ReservaS.setTicket(ticket.getValue());
					this.ReservaS.setPreciototal(Double.parseDouble(precioTotal.getValue()));
					this.ReservaS.setIdhabitacion(idHabitacion.getValue());
					this.ReservaS.setIdcliente(idCliente.getValue());
					this.ReservaS.setFechainicio(fechaInicio.getValue().toString());
					this.ReservaS.setFechafinal(fechaFinal.getValue().toString());

					this.controlador.CrearReserva(ReservaS);

				} else {

					this.ReservaS.setTicket(ticket.getValue());
					this.ReservaS.setPreciototal(Double.parseDouble(precioTotal.getValue()));
					this.ReservaS.setIdhabitacion(idHabitacion.getValue());
					this.ReservaS.setIdcliente(idCliente.getValue());
					this.ReservaS.setFechainicio(fechaInicio.getValue().toString());
					this.ReservaS.setFechafinal(fechaFinal.getValue().toString());

					this.controlador.ActualizarReserva(ReservaS);
				}

				clearForm();
				refreshGrid();
				// Notification.show("Data updated");
				UI.getCurrent().navigate(ReservaView.class);
			} catch (ObjectOptimisticLockingFailureException exception) {
				Notification n = Notification.show(
						"Error updating the data. Somebody else has updated the record while you were making changes.");
				n.setPosition(Position.MIDDLE);
				n.addThemeVariants(NotificationVariant.LUMO_PRIMARY);
			}
		});

		eliminar.addClickListener(e -> {
			if (this.ReservaS == null) {
				mostrarMensajeError("Seleccione una reserva para poder eliminar");
			} else {
				this.controlador.EliminarReserva(ReservaS.getTicket());
				clearForm();
				refreshGrid();
				UI.getCurrent().navigate(ReservaView.class);
			}

		});
	}


    @Override
    public void beforeEnter(BeforeEnterEvent event) {
        Optional<String> sampleAddressId = event.getRouteParameters().get(SAMPLEADDRESS_ID);
        if (sampleAddressId.isPresent()) {
        	Reserva reservaObtenida = obtenerReserva(sampleAddressId.get());
            if (reservaObtenida != null) {
                populateForm(reservaObtenida);
            } else {
                Notification.show(
                        String.format("Reserva con ticket %s no existe", sampleAddressId.get()), 3000,
                        Notification.Position.BOTTOM_START);
                // when a row is selected but the data is no longer available,
                // refresh grid
                refreshGrid();

                event.forwardTo(ReservaView.class);
            }
        }
        }

    

    private Reserva obtenerReserva(String ticket) {
		// TODO Auto-generated method stub
    
    	Reserva encontrado = null;
    	for(Reserva res: elementos) {
    		if(res.getTicket().equals(ticket)) {
    			encontrado = res;
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
        ticket = new TextField("Ticket");
        ticket.setPrefixComponent(VaadinIcon.TICKET.create());
        precioTotal = new TextField("Precio Total");
        precioTotal.setPrefixComponent(VaadinIcon.MONEY.create());
        idHabitacion = new TextField("Numero Habitacion");
        idHabitacion.setPrefixComponent(VaadinIcon.HOME.create());
        idCliente = new TextField("Id Cliente");
        idCliente.setPrefixComponent(VaadinIcon.USERS.create());
        fechaInicio = new DatePicker("Fecha Inicio");
        fechaInicio.setPrefixComponent(VaadinIcon.CALENDAR.create());
        fechaFinal = new DatePicker("Fecha Final");
        fechaFinal.setPrefixComponent(VaadinIcon.CALENDAR.create());
        formLayout.add(ticket, precioTotal, idHabitacion, idCliente, fechaInicio, fechaFinal);

        editorDiv.add(formLayout);
        createButtonLayout(editorLayoutDiv);

        splitLayout.addToSecondary(editorLayoutDiv);
    }

    private void createButtonLayout(Div editorLayoutDiv) {
        HorizontalLayout buttonLayout = new HorizontalLayout();
        buttonLayout.setClassName("button-layout");
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        eliminar.addThemeVariants(ButtonVariant.LUMO_PRIMARY, ButtonVariant.LUMO_ERROR);
        cancel.addThemeVariants(ButtonVariant.LUMO_TERTIARY);
        
        
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

        this.controlador.consultarReserva();
    }

    private void clearForm() {
        populateForm(null);
    }

    private void populateForm(Reserva value) {
        this.ReservaS = value;

        if(value != null) {
        	ticket.setValue(value.getTicket());
        	precioTotal.setValue(Double.toString(value.getPreciototal()));
        	idHabitacion.setValue(value.getIdhabitacion());
        	idCliente.setValue(value.getIdcliente());        	
        	
        }else {
        	ticket.setValue("");
        	precioTotal.setValue("");
        	idHabitacion.setValue("");
        	idCliente.setValue("");  
        }
    }


	@Override
	public void mostrarReservaEnGrid(List<Reserva> items) {
		// TODO Auto-generated method stub
		
		Collection<Reserva> itemsCollection = items;
		grid.setItems(itemsCollection);
		this.elementos = items;
		
	}


	@Override
	public void mostrarMensajeError(String mensaje) {
		Notification.show(mensaje);	
		
	}
	
	@Override
	public void mostrarMensajeExito(String mensaje) {
		Notification.show(mensaje);	
		
	}
}
