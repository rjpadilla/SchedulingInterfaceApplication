/*
*Raul Padilla
*C195 Software II
 */
package scheduledemo;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;


public class ConsultantReportController implements Initializable {

    /**
     * Fields
     */
    double x, y;

    @FXML
    private TableView<Schedule> appointmentTable;

    @FXML
    private TableColumn<Schedule, String> userColumn;

    @FXML
    private TableColumn<Schedule, String> descriptionColumn;

    @FXML
    private TableColumn<Schedule, String> dateColumn;

    @FXML
    private TableColumn<Schedule, String> titleColumn;

    @FXML
    private TableColumn<Schedule, String> locationColumn;

    

    //DBConnect connect = new DBConnect();

    /**
     * This will fill the table with the consultants appointments.
     */
    public void choiceSelected(String consultant)  {
        
        if (!(Database.getConsultantSchedules().isEmpty())) {
            Database.getConsultantSchedules().clear();
            Database.addConsultant(consultant);
            appointmentTable.setItems(Database.getConsultantSchedules());
        } else {
            
            Database.addConsultant(consultant);
            appointmentTable.setItems(Database.getConsultantSchedules());
        }

    }
    


    /**
     * This will exit the program.
     */
    public void changeExitButtonPushed(ActionEvent event) throws IOException {
        Platform.exit();
    }

    /**
     * This will minimize the program.
     */
    public void changeMinimizeButtonPushed(ActionEvent event) throws IOException {
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        window.setIconified(true);
    }

    /**
     * This will change the scene to the Report page.
     */
    public void changeReportButtonPushed(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("Report.fxml"));
        loader.load();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Parent scene = loader.getRoot();
        stage.setScene(new Scene(scene));
        stage.show();
    }

    /**
     * This will get the current coordinate when you press on the mouse.
     */
    @FXML
    public void pressCurrentCordinate(MouseEvent event) {
        x = event.getSceneX();
        y = event.getSceneY();
    }

    /**
     * Moves the program window around
     */
    @FXML
    public void draggedWindowBar(MouseEvent event) {
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setX(event.getScreenX() - x);
        window.setY(event.getScreenY() - y);
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        userColumn.setCellValueFactory(new PropertyValueFactory<Schedule, String>("user"));
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<Schedule, String>("description"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<Schedule, String>("date"));
        titleColumn.setCellValueFactory(new PropertyValueFactory<Schedule, String>("title"));
        locationColumn.setCellValueFactory(new PropertyValueFactory<Schedule, String>("location"));
        appointmentTable.setItems(Database.getConsultantSchedules());
    }

}
