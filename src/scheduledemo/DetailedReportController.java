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
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;


public class DetailedReportController implements Initializable {

    /**
     * Fields
     */
    double x, y;

    @FXML
    private Label nameTextField;

    @FXML
    private Label descriptionTextField;

    @FXML
    private Label phoneTextField;

    @FXML
    private Label dateTextField;

    @FXML
    private Label addressTextField;

    @FXML
    private Label titleTextField;

    @FXML
    private Label consultantTextField;

    @FXML
    private Label locationTextField;

    @FXML
    private Label typeTextField;

    DBConnect connect = new DBConnect();

    /**
     * This will populate the field with the customer's information.
     */
    public void setDetailedCustomer(DetailedCustomer detail) {

        nameTextField.setText(String.valueOf(detail.getName()));
        descriptionTextField.setText(String.valueOf(detail.getDescription()));
        phoneTextField.setText(String.valueOf(detail.getPhone()));
        addressTextField.setText(String.valueOf(detail.getAddress()));
        consultantTextField.setText(String.valueOf(detail.getUser()));
        locationTextField.setText(String.valueOf(detail.getLocation()));
        dateTextField.setText(String.valueOf(detail.getDate()));
        typeTextField.setText(String.valueOf(detail.getType()));
        titleTextField.setText(String.valueOf(detail.getTitle()));

    }

    /**
     * This will change the scene to the Report page.
     */
    public void changeReportButtonPushed(ActionEvent event) throws IOException {
        Database.getTypeAppointments().clear();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("Report.fxml"));
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

    }

}
