package es.aketzagonzalez.aeropuertosD;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Persona;

/**
 * Clase tablaPersonasController.
 */
public class tablaPersonasController {

	/** El stage. */
	private static Stage s;
	
    /** El btn aniadir. */
    @FXML
    private Button btnAniadir;

    /** El id tabla apellido. */
    @FXML
    private  TableColumn<Persona, String> idTablaApellido;

    /** El id tabla edad. */
    @FXML
    private TableColumn<Persona, Integer> idTablaEdad;

    /** El id tabla nombre. */
    @FXML
    private TableColumn<Persona, String> idTablaNombre;

    /** El tabla personas. */
    @FXML
    private TableView<Persona> tablaPersonas=new TableView<Persona>();

    /**
     * Aniadir persona a la tabla llamando a una ventana modal.
     *
     * @param event El evento
     */
    @FXML
    void aniadirPersona(ActionEvent event) {
    	s=new Stage();
    	Scene scene;
		try {
			 FXMLLoader controlador = new FXMLLoader(MainApp.class.getResource("/fxml/aniadirPersona.fxml"));
			scene = new Scene(controlador.load());
			s.setTitle("Nueva Persona");
			s.setScene(scene);
			aniadirPersonaController controller = controlador.getController();
			controller.setTablaPersonas(tablaPersonas);
		} catch (IOException e) {
			e.printStackTrace();
		}
        s.setResizable(false);
        s.initOwner(MainApp.getStage());
        s.initModality(javafx.stage.Modality.WINDOW_MODAL);
        s.show();
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

	/**
	 * Getter del stage.
	 *
	 * @return El stage
	 */
	public static Stage getS() {
		return s;
	}
    
}
