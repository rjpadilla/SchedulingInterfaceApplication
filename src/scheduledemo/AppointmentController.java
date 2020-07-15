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
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;


public class AppointmentController implements Initializable {

    /**
     * Fields
     */
    
    @FXML
    private TableView<Appointments> appointmentTable;

    @FXML
    private TableColumn<Appointments, String> startColumn;

    @FXML
    private TableColumn<Appointments, String> endColumn;

    @FXML
    private TableColumn<Appointments, String> dateColumn;

    @FXML
    private TableColumn<Appointments, String> titleColumn;
    
    
    double x, y;
    static boolean entered;
    
    DBConnect connect = new DBConnect();

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
     * This will change the scene to the AddAppointment page.
     */
    public void changeAddButtonPushed(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("AddAppointment.fxml"));
        loader.load();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Parent scene = loader.getRoot();
        stage.setScene(new Scene(scene));
        stage.show();
    }
    
    /*
    *This will change to the UpdateAppointment page.
     */
    public void changeUpdateButtonPushed(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("UpdateAppointment.fxml"));
        loader.load();
        
        UpdateAppointmentController updateController = loader.getController();
        updateController.setAppointment(appointmentTable.getSelectionModel().getSelectedItem());
        
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Parent scene = loader.getRoot();
        stage.setScene(new Scene(scene));
        stage.show();
    }
    
    /*
    *This will delete the selected appointment.
     */
    public void changeDeleteButtonPushed(ActionEvent event) throws IOException {
        Database.deleteSchedule(appointmentTable.getSelectionModel().getSelectedItem().getTitle());
        Database.deleteWeek(appointmentTable.getSelectionModel().getSelectedItem().getTitle());
        connect.deleteAppointment((appointmentTable.getSelectionModel().getSelectedItem().getTitle()));
        
        connect.getSchedule();
        connect.getWeek(null);
    }

    /**
     * This will change the scene to the home page.
     */
    public void changeHomeButtonPushed(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("MainPage.fxml"));
        loader.load();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Parent scene = loader.getRoot();
        stage.setScene(new Scene(scene));
        stage.show();
    }

    public void changeCustomerButtonPushed(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("Customer.fxml"));
        loader.load();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Parent scene = loader.getRoot();
        stage.setScene(new Scene(scene));
        stage.show();
    }

    public void changeAppointmentButtonPushed(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("Appointment.fxml"));
        loader.load();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Parent scene = loader.getRoot();
        stage.setScene(new Scene(scene));
        stage.show();
    }

    public void changeCalendarButtonPushed(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("Calendar.fxml"));
        loader.load();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Parent scene = loader.getRoot();
        stage.setScene(new Scene(scene));
        stage.show();
    }

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
        

        
        
        startColumn.setCellValueFactory(new PropertyValueFactory<Appointments, String>("start"));        
        endColumn.setCellValueFactory(new PropertyValueFactory<Appointments, String>("end"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<Appointments, String>("date"));
        titleColumn.setCellValueFactory(new PropertyValueFactory<Appointments, String>("title"));

        appointmentTable.setItems(Database.getAllAppointments());
    }

}
