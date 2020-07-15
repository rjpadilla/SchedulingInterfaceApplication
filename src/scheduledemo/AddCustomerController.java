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
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.scene.control.TextField;


public class AddCustomerController implements Initializable {

    /**
     * Fields
     */
    double x, y;

    @FXML
    private TextField nameTextField;

    @FXML
    private TextField phoneTextField;

    @FXML
    private TextField addressTextField;

    @FXML
    private TextField cityTextField;

    @FXML
    private TextField codeTextField;

    @FXML
    private ChoiceBox<String> countryChoiceBox;

    /**
     * This will add a customer to the app database and MySQL database.
     */
    public void changeAddButtonPushed(ActionEvent event) throws IOException {
        DBConnect connect = new DBConnect();
        try {
            for (int i = 1; i < 6; i++) {
                if (countryChoiceBox.getValue().contains(connect.getCountry(i))) {
                    connect.putCity(cityTextField.getText(), connect.getCountryId(connect.getCountry(i)));
                    connect.putAddress(addressTextField.getText(), connect.getCityId(cityTextField.getText()), phoneTextField.getText(), codeTextField.getText());
                    connect.putName(nameTextField.getText(), connect.getAddressId(addressTextField.getText()));
                    Database.addCustomer(new Customer(nameTextField.getText(),addressTextField.getText(),phoneTextField.getText()));
                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(getClass().getResource("Customer.fxml"));
                    loader.load();
                    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    Parent scene = loader.getRoot();
                    stage.setScene(new Scene(scene));
                    stage.show();
                }

            }
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText("Please enter valid input values into the text field.");
            alert.setContentText("Error: " + e);
            alert.showAndWait();
            throw e;
        }

    }

    /**
     * This will return to the customer form.
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

        countryChoiceBox.getItems().add("USA");
        countryChoiceBox.getItems().add("China");
        countryChoiceBox.getItems().add("Russia");
        countryChoiceBox.getItems().add("France");
        countryChoiceBox.getItems().add("Britain");

    }

}
