/*
*Raul Padilla
*C195 Software II
 */
package scheduledemo;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableColumn.SortType;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import java.sql.*;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;


public class CustomerController implements Initializable {

    /**
     * Fields
     */
    double x, y;

    @FXML
    private TableView<Customer> customerTable;

    @FXML
    private TableColumn<Customer, String> addressColumn;

    @FXML
    private TableColumn<Customer, String> nameColumn;

    @FXML
    private TableColumn<Customer, String> phoneColumn;

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
     * This will change the scene to the AddCustomer page.
     */
    public void changAddButtonPushed(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("AddCustomer.fxml"));
        loader.load();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Parent scene = loader.getRoot();
        stage.setScene(new Scene(scene));
        stage.show();
    }

    /*
    *This will change to the update customer page.
     */
    public void changUpdateButtonPushed(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("UpdateCustomer.fxml"));
        loader.load();

        UpdateCustomerController updateController = loader.getController();
        updateController.setCustomer(customerTable.getSelectionModel().getSelectedItem());

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Parent scene = loader.getRoot();
        stage.setScene(new Scene(scene));
        stage.show();
    }

    /*
    *This will delete the selected customer.
     */
    public void changeDeleteButtonPushed(ActionEvent event) throws IOException {
        String address=customerTable.getSelectionModel().getSelectedItem().getAddress();
        int city = connect.getCityId(customerTable.getSelectionModel().getSelectedItem().getAddress());
        connect.deleteCustomer(customerTable.getSelectionModel().getSelectedItem().getName());
        connect.deleteAddress(address);
        connect.deleteCity(city);

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



        nameColumn.setCellValueFactory(new PropertyValueFactory<Customer, String>("name"));
        phoneColumn.setCellValueFactory(new PropertyValueFactory<Customer, String>("phone"));
        addressColumn.setCellValueFactory(new PropertyValueFactory<Customer, String>("address"));

        customerTable.setItems(Database.getAllCustomer());

    }

}
