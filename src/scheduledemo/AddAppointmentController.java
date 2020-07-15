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
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;


public class AddAppointmentController implements Initializable {

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

    DBConnect connect = new DBConnect();

    /*
    *This will add an appointment to the app database and MySQL database.
    */
    public void changeAddButtonPushed(ActionEvent event) throws IOException, NullPointerException {

        try {

            int hour = hourSpinner.getValue();
            int minute = minuteSpinner.getValue();

            assert hour > 7 : "Business hours are between 8:00 AM and 5:00 PM!";
            assert hour < 17 : "Business hours are between 8:00 AM and 5:00 PM!";

            String date = String.valueOf(dateDatePicker.getValue());
            assert Database.getDateAppointment(connect.getDate(date), hour, minute) == true : "Cant overlap";
            String start = date + " " + hour + ":" + minute + ":00";
            String end = date + " " + (hour + 1) + ":" + minute + ":00";

            connect.putAppointment(connect.getCustomerID(customerChoiceBox.getValue()), connect.getUserID(Database.getUser()), titleTextField.getText(), descriptionTextField.getText(), locationChoiceBox.getValue(), contactChoiceBox.getValue(), urlTextField.getText(), start, end);

            Database.addAppointment(new Appointments(connect.getStart(hour, minute), connect.getEnd(hour, minute), connect.getDate(date), titleTextField.getText()));
            Database.addSchedule(new Schedule(Database.getUser(), titleTextField.getText(), descriptionTextField.getText(), locationChoiceBox.getValue(), start));

            connect.getWeek(titleTextField.getText());

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("Appointment.fxml"));
            loader.load();
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Parent scene = loader.getRoot();
            stage.setScene(new Scene(scene));
            stage.show();
        } catch (AssertionError | Exception e) {
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

        /*
        *The next few blocks of code will fill out the choiceboxes in the form.
        */
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

        SpinnerValueFactory<Integer> hourValueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 23, 8);
        this.hourSpinner.setValueFactory(hourValueFactory);

        SpinnerValueFactory<Integer> minuteValueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(00, 59, 30);
        this.minuteSpinner.setValueFactory(minuteValueFactory);

    }

}
