package es.aketzagonzalez.aeropuertosD;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Persona;

/**
 * Clase tablaPersonasController.
 */
public class tablaPersonasController {

    /** El boton aniadir. */
    @FXML
    private Button btnAniadir;
    
    /** El btn eliminar. */
    @FXML
    private Button btnEliminar;

    /** El btn modificar. */
    @FXML
    private Button btnModificar;

    /** El id tabla apellido. */
    @FXML
    private TableColumn<Persona, String> idTablaApellido;

    /** El id tabla edad. */
    @FXML
    private TableColumn<Persona, Integer> idTablaEdad;

    /** El id tabla nombre. */
    @FXML
    private TableColumn<Persona, String> idTablaNombre;

    /** La tabla personas. */
    @FXML
    private TableView<Persona> tablaPersonas;

    /** El txt apellidos. */
    @FXML
    private TextField txtApellidos;

    /** El txt edad. */
    @FXML
    private TextField txtEdad;

    /** El txt nombre. */
    @FXML
    private TextField txtNombre;

    /**
     * Aniadir persona validando que todos los campos sean validos y mostrando 
     * el mensaje adecuado en cada caso.
     *
     * @param event El evento
     */
    @FXML
    void aniadirPersona(ActionEvent event) {
    	String error="";
    	String nombre=txtNombre.getText();
    	String apellidos=txtApellidos.getText();
    	int edad=-1;
    	boolean existe=false;
    	if(txtNombre.getText().isEmpty()) {
    		error+="El campo nombre es obligatorio\n";
    	}
    	if(txtApellidos.getText().isEmpty()) {
    		error+="El campo apellidos es obligatorio\n";
    	}
    	if(txtEdad.getText().isEmpty()) {
    		error+="El campo edad es obligatoiro\n";
    	}else {
	    	try {
	    		edad=Integer.parseInt(txtEdad.getText());
	    		if(edad<=0) {
	    			throw new Exception();
	    		}
	    	}catch(NumberFormatException e) {
	    		error+="La edad debe ser un numero entero\n";
	    	}catch(Exception e) {
	    		error+="La edad debe ser superior a 0\n";
	    	}
    	}
    	Persona p=new Persona(nombre, apellidos, edad);
    	for(Persona per:tablaPersonas.getItems()){
    		if(per.equals(p)) {
    			existe=true;
    		}
    	}
    	Alert al=new Alert(AlertType.INFORMATION);
    	al.setHeaderText(null);
    	if(!existe&&error.equals("")) {
    		tablaPersonas.getItems().add(p);
    	}
    	
    	if(error.equals("")&&!existe) {
    		al.setContentText("Persona añadida correctamente");
    	}else {
    		if(error.equals("")){
    			al.setAlertType(AlertType.WARNING);
    			error="La persona ya estaba en la lista";
    		}else {
    			al.setAlertType(AlertType.ERROR);
    		}
    		al.setContentText(error);
    	}
    	al.showAndWait();
    	txtApellidos.setText("");
    	txtNombre.setText("");
    	txtEdad.setText("");
    	tablaPersonas.getSelectionModel().clearSelection();
    }
    
    /**
     * Eliminar persona de la tabla.
     *
     * @param event El evento
     */
    @FXML
    void eliminarPersona(ActionEvent event) {
    	Alert al=new Alert(AlertType.CONFIRMATION);
    	al.setHeaderText(null);
    	if(tablaPersonas.getSelectionModel().getSelectedItem()!=null) {
	    	al.setContentText("Estas seguro de que deseas borrar a: "+tablaPersonas.
	    			getSelectionModel().getSelectedItem());
	    	al.showAndWait();
	    	if(al.getResult().getButtonData().name().equals("OK_DONE")) {
	    		tablaPersonas.getItems().remove(tablaPersonas.getSelectionModel().
	    				getSelectedItem());
	    	}
    	}else {
    		al.setAlertType(AlertType.ERROR);
    		al.setContentText("No hay nadie seleccionado, asi que no se puede seleccionar nadie");
        	al.showAndWait();
    	}
    	txtApellidos.setText("");
    	txtNombre.setText("");
    	txtEdad.setText("");
    	tablaPersonas.getSelectionModel().clearSelection();
    }

    /**
     * Modificar persona dentro de la tabla con sus respectivas validaciones y mensajes.
     *
     * @param event El evento
     */
    @FXML
    void modificarPersona(ActionEvent event) {
    	String error="";
    	String nombre=txtNombre.getText();
    	String apellidos=txtApellidos.getText();
    	int edad=-1;
    	boolean existe=false;
    	if(txtNombre.getText().isEmpty()) {
    		error+="El campo nombre es obligatorio\n";
    	}
    	if(txtApellidos.getText().isEmpty()) {
    		error+="El campo apellidos es obligatorio\n";
    	}
    	if(txtEdad.getText().isEmpty()) {
    		error+="El campo edad es obligatoiro\n";
    	}else {
	    	try {
	    		edad=Integer.parseInt(txtEdad.getText());
	    		if(edad<=0) {
	    			throw new Exception();
	    		}
	    	}catch(NumberFormatException e) {
	    		error+="La edad debe ser un numero entero\n";
	    	}catch(Exception e) {
	    		error+="La edad debe ser superior a 0\n";
	    	}
    	}
    	Persona p=new Persona(nombre, apellidos, edad);
    	for(Persona per:tablaPersonas.getItems()){
    		if(per.equals(p)) {
    			existe=true;
    		}
    	}
    	Alert al=new Alert(AlertType.INFORMATION);
    	al.setHeaderText(null);
    	if(tablaPersonas.getSelectionModel().getSelectedItem()!=null) {
    		if(error.equals("")&&!existe) {
	    		tablaPersonas.getItems().set(tablaPersonas.getSelectionModel().getSelectedIndex(), p);
	    		al.setContentText("Persona modificada correctamente");
	    	}else {
	    		if(error.equals("")){
	    			al.setAlertType(AlertType.WARNING);
	    			error="La persona ya estaba en la lista";
	    		}else {
	    			al.setAlertType(AlertType.ERROR);
	    		}
	    		al.setContentText(error);
	    	}
    	}else {
    		al.setAlertType(AlertType.ERROR);
    		al.setContentText("No hay nadie seleccionado, asi que no se puede seleccionar nadie");
    	}
    	al.showAndWait();
    	txtApellidos.setText("");
    	txtNombre.setText("");
    	txtEdad.setText("");
    	tablaPersonas.getSelectionModel().clearSelection();
    }
    
    /**
     * Inicializa el valor de las celdas.
     */
    @FXML
    private void initialize() {
    	idTablaNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
    	idTablaApellido.setCellValueFactory(new PropertyValueFactory<>("apellidos"));
    	idTablaEdad.setCellValueFactory(new PropertyValueFactory<>("edad"));
    }

}
