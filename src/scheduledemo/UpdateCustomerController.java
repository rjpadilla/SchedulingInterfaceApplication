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
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;


public class UpdateCustomerController implements Initializable {

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
    
    private static Customer updatedCustomer = null;

    DBConnect connect = new DBConnect();
    
    /**
     * This method will grab the selected customer data and populate fields.
     */
    public void setCustomer(Customer customer) {
        
        updatedCustomer = customer;
        
        nameTextField.setText(String.valueOf(customer.getName()));
        phoneTextField.setText(String.valueOf(customer.getPhone()));
        addressTextField.setText(String.valueOf(customer.getAddress()));
        
        cityTextField.setText(String.valueOf(connect.getCityName(connect.getAddressId(customer.getAddress()))));
        codeTextField.setText(String.valueOf(connect.getZipcode(connect.getAddressId(customer.getAddress()))));
       
       Database.updateCustomer(customer.getName());
    }

    /**
     * This will save the form data.
     */
    public void changeUpdateButtonPushed(ActionEvent event) throws IOException {
        
        try {
            
            for (int i = 1; i < 6; i++) {
                if (countryChoiceBox.getValue().contains(connect.getCountry(i))) {
                    
                    connect.updateCustomer(nameTextField.getText(),updatedCustomer.getName());
                    connect.updateAddress(addressTextField.getText(), updatedCustomer.getAddress(),phoneTextField.getText(),codeTextField.getText());
                    connect.updateCity(cityTextField.getText(),connect.getCityName(connect.getAddressId(updatedCustomer.getAddress())));
                                       
                   
                    Database.addCustomer(new Customer(nameTextField.getText(), addressTextField.getText(), phoneTextField.getText()));
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
        }

    }

    /**
     * This will return to the customer form.
     */
    public void changeCustomerButtonPushed(ActionEvent event) throws IOException {
        
        Database.getAllCustomer().add(updatedCustomer);
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
        
        //Next lines of code will populate the choicebox fields.
        countryChoiceBox.getItems().add("USA");
        countryChoiceBox.getItems().add("China");
        countryChoiceBox.getItems().add("Russia");
        countryChoiceBox.getItems().add("France");
        countryChoiceBox.getItems().add("Britain");
        
    }

}
