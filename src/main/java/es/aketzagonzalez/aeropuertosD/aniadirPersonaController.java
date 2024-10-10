package es.aketzagonzalez.aeropuertosD;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import model.Persona;

public class aniadirPersonaController {

    @FXML
    private Button btnCancelar;

    @FXML
    private Button btnGuardar;

    @FXML
    private TextField txtEdad;

    @FXML
    private TextField txtApellidos;

    @FXML
    private TextField txtNombre;

    @FXML
    void accionAniadir(ActionEvent event) {
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
    	for(Persona per:tablaPersonasController.getTablaPersonas().getItems()){
    		if(per.equals(p)) {
    			existe=true;
    		}
    	}
    	Alert al=new Alert(AlertType.INFORMATION);
    	al.setHeaderText(null);
    	if(!existe&&error.equals("")) {
    		tablaPersonasController.getTablaPersonas().getItems().add(p);
    		tablaPersonasController.getTablaPersonas().refresh();
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
    	tablaPersonasController.getTablaPersonas().getSelectionModel().clearSelection();
    	tablaPersonasController.getS().close();
    }

    @FXML
    void accionCancelar(ActionEvent event) {
    	tablaPersonasController.getS().close();
    }

}
