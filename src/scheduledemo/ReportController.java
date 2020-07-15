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
import javafx.scene.control.ChoiceBox;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;


public class ReportController implements Initializable {

    /**
     * Fields
     */
    double x, y;
    
    DBConnect connect = new DBConnect();

    @FXML
    private ChoiceBox<String> userChoiceBox;
   
     @FXML
    private ChoiceBox<String> customerChoiceBox;
    
    @FXML
    private ChoiceBox<String> monthChoiceBox;

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
     * This will get the report of the schedule of a consultant.
     */
    public void changeScheduleButtonPushed(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("ConsultantReport.fxml"));
        
             
                
        loader.load();
         ConsultantReportController consultantController = loader.getController();
        consultantController.choiceSelected(userChoiceBox.getValue());
        
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Parent scene = loader.getRoot();
        stage.setScene(new Scene(scene));
        stage.show();
    }
    
    /**
     * This will get the report of the number of appointments in a month.
     */
    public void changeNumberButtonPushed(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("TypeReport.fxml"));
        
            
         connect.getType(monthChoiceBox.getValue());      

        loader.load();
     
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Parent scene = loader.getRoot();
        stage.setScene(new Scene(scene));
        stage.show();
    }
    
    /**
     * This will get the report of a detailed customer.
     */
    public void changeDetailButtonPushed(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("DetailedReport.fxml"));
        connect.getDetailCustomer(customerChoiceBox.getValue());
            
            

        loader.load();
        
         DetailedReportController detailController = loader.getController();
         detailController.setDetailedCustomer(Database.getDetailedCustomer());
        
         
        
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
        
        //The next lines of code will populate the choiceboxes fields.
        for (int i = 0; i < connect.getUserCount(); i++) {
            userChoiceBox.getItems().add(Database.getAllConsultantName(i));
        }
        
        for (int i = 0; i < connect.getCount(); i++) {
            customerChoiceBox.getItems().add(Database.getAllCustomerName(i));
        }
        
        monthChoiceBox.getItems().add("January");
        monthChoiceBox.getItems().add("February");
        monthChoiceBox.getItems().add("March");
        monthChoiceBox.getItems().add("April");
        monthChoiceBox.getItems().add("May");
        monthChoiceBox.getItems().add("June");
        monthChoiceBox.getItems().add("July");
        monthChoiceBox.getItems().add("August");
        monthChoiceBox.getItems().add("September");
        monthChoiceBox.getItems().add("October");
        monthChoiceBox.getItems().add("November");
        monthChoiceBox.getItems().add("December");
        
    }

}
