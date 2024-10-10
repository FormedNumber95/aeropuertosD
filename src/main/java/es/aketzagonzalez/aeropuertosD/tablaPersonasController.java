package es.aketzagonzalez.aeropuertosD;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import model.Persona;

public class tablaPersonasController {

	private static Stage s;
	
    @FXML
    private Button btnAniadir;

    @FXML
    private static TableColumn<Persona, String> idTablaApellido;

    @FXML
    private static TableColumn<Persona, Integer> idTablaEdad;

    @FXML
    private static TableColumn<Persona, String> idTablaNombre;

    @FXML
    private static TableView<Persona> tablaPersonas=new TableView<Persona>();

    @FXML
    void aniadirPersona(ActionEvent event) {
    	s=new Stage();
    	Scene scene;
		try {
			scene = new Scene(new FXMLLoader(MainApp.class.getResource("/fxml/aniadirPersona.fxml")).load());
			s.setTitle("Nueva Persona");
			s.setScene(scene);
		} catch (IOException e) {
			e.printStackTrace();
		}
        s.setResizable(false);
        s.initOwner(MainApp.getStage());
        s.initModality(javafx.stage.Modality.WINDOW_MODAL);
        s.show();
    }

	public static Stage getS() {
		return s;
	}

	public static TableView<Persona> getTablaPersonas() {
		return tablaPersonas;
	}
    
}
