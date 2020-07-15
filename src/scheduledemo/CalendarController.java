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
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;


public class CalendarController implements Initializable {

    /**
     * Fields
     */
    double x, y;

    private ToggleGroup radioToggleGroup;

    @FXML
    private RadioButton monthlyRadioButton;

    @FXML
    private RadioButton weeklyRadioButton;

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
    
    

    /**
     * Toggles between each radio button.
     */
    public void radioButtonPushed(ActionEvent event) throws IOException {
        
        
        
        if (this.radioToggleGroup.getSelectedToggle().equals(this.monthlyRadioButton)) {
            appointmentTable.setItems(Database.getAllSchedules());
        }
        if (this.radioToggleGroup.getSelectedToggle().equals(this.weeklyRadioButton)) {
            
            appointmentTable.setItems(Database.getWeeklySchedules());
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

    /**
     * This will change the scene to the customer page.
     */
    public void changeCustomerButtonPushed(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("Customer.fxml"));
        loader.load();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Parent scene = loader.getRoot();
        stage.setScene(new Scene(scene));
        stage.show();
    }

    /**
     * This will change the scene to the Appointment page.
     */
    public void changeAppointmentButtonPushed(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("Appointment.fxml"));
        loader.load();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Parent scene = loader.getRoot();
        stage.setScene(new Scene(scene));
        stage.show();
    }

    /**
     * This will change the scene to the calendar page.
     */
    public void changeCalendarButtonPushed(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("Calendar.fxml"));
        loader.load();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Parent scene = loader.getRoot();
        stage.setScene(new Scene(scene));
        stage.show();
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


        
        
        //This will enable the radio button.
        radioToggleGroup = new ToggleGroup();
        this.monthlyRadioButton.setToggleGroup(radioToggleGroup);
        this.weeklyRadioButton.setToggleGroup(radioToggleGroup);

        //This will populate the calendar table view.
        userColumn.setCellValueFactory(new PropertyValueFactory<Schedule, String>("user"));
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<Schedule, String>("description"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<Schedule, String>("date"));
        titleColumn.setCellValueFactory(new PropertyValueFactory<Schedule, String>("title"));
        locationColumn.setCellValueFactory(new PropertyValueFactory<Schedule, String>("location"));

        
    }

}
