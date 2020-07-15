/*
*Raul Padilla
*C195 Software II
 */
package scheduledemo;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;


public class UpdateAppointmentController implements Initializable {

    /**
     * Fields
     */
    double x, y;

    @FXML
    private TextField titleTextField;

    @FXML
    private TextField descriptionTextField;

    @FXML
    private DatePicker dateDatePicker;

    @FXML
    private TextField urlTextField;

    @FXML
    private ChoiceBox<String> customerChoiceBox;

    @FXML
    private ChoiceBox<String> locationChoiceBox;

    @FXML
    private ChoiceBox<String> contactChoiceBox;

    @FXML
    private Spinner<Integer> hourSpinner;

    @FXML
    private Spinner<Integer> minuteSpinner;

   
    
    private static Appointments updatedAppointment = null;
    
    DBConnect connect = new DBConnect();
    
    
    /**
     * This method will grab the selected appointment data and populate fields.
     */
    public void setAppointment(Appointments appointment) {
        
        updatedAppointment = appointment;
        
       titleTextField.setText(String.valueOf(appointment.getTitle()));
       descriptionTextField.setText(connect.getDescription(appointment.getTitle()));
       urlTextField.setText(connect.getURL(appointment.getTitle()));
      
       
       
       
       Database.updateAppointment(appointment.getTitle());
    }

    /**
     * This will confirm and update the appointment.
     */
    public void changeUpdateButtonPushed(ActionEvent event) throws IOException {
        
        try {
            
            connect.deleteAppointment(updatedAppointment.getTitle());
            Database.deleteSchedule(updatedAppointment.getTitle());
            
            String title = titleTextField.getText();
            String description = descriptionTextField.getText();
            String url = urlTextField.getText();
            String customerName = customerChoiceBox.getValue();
            String location = locationChoiceBox.getValue();
            String contact = contactChoiceBox.getValue();
            int hour = hourSpinner.getValue();
            int minute = minuteSpinner.getValue();
            
            String date = String.valueOf(dateDatePicker.getValue());
            String start =  date  + " "+  hour + ":" + minute + ":00";
            String end =  date  + " "+  (hour+1) + ":" + minute + ":00";
            
           
            connect.putAppointment(connect.getCustomerID(customerName), connect.getUserID(Database.getUser()), title, description, location, contact, url, start, end);
            Database.addAppointment(new Appointments(connect.getStart(hour, minute),connect.getEnd(hour, minute),connect.getDate(date),title));
            Database.addSchedule(new Schedule(Database.getUser(),title,description,location,start));
            
            connect.getWeek(title);
            
            
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("Appointment.fxml"));
            loader.load();
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Parent scene = loader.getRoot();
            stage.setScene(new Scene(scene));
            stage.show();
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText("Please enter valid input values into the text field.");
            alert.setContentText("Error: " + e);
            alert.showAndWait();
        }

    }

    /**
     * This will return to the appointment form.
     */
    public void changeCancelButtonPushed(ActionEvent event) throws IOException {
        Database.getAllAppointments().add(updatedAppointment);
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("Appointment.fxml"));
        loader.load();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Parent scene = loader.getRoot();
        stage.setScene(new Scene(scene));
        stage.show();
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
        
        
        for (int i = 0; i < connect.getCount(); i++) {
            customerChoiceBox.getItems().add(Database.getAllCustomerName(i));
        }

              
        

        contactChoiceBox.getItems().add("Phone");
        contactChoiceBox.getItems().add("Live-Video");
        contactChoiceBox.getItems().add("In-Person");

        locationChoiceBox.getItems().add("Remote");
        locationChoiceBox.getItems().add("Phoenix, Arizona");
        locationChoiceBox.getItems().add("New York, New York");
        locationChoiceBox.getItems().add("London, England");

        SpinnerValueFactory<Integer> hourValueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 23,8);
        this.hourSpinner.setValueFactory(hourValueFactory);
        

        SpinnerValueFactory<Integer> minuteValueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(00, 59,30);
        this.minuteSpinner.setValueFactory(minuteValueFactory);
        
    }
}
