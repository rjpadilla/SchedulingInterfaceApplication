/*
*Raul Padilla
*C195 Software II
 */
package scheduledemo;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.TimeZone;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;


public class LoginController implements Initializable {

    /**
     * Fields
     */
    double x, y;

    @FXML
    private TextField passwordTextField;

    @FXML
    private TextField usernameTextField;

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
     * This will change the scene to the main home page if the login is a success.
     */
    public void changeLoginButtonPushed(ActionEvent event) throws IOException {
        Database.loginUser(usernameTextField.getText());
        Database.loginPass(passwordTextField.getText());
        try {
            if ((usernameTextField.getText().contains(Database.getUser())) && (passwordTextField.getText().contains(Database.getPass()))) {
                DBConnect connect = new DBConnect();
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("MainPage.fxml"));
                loader.load();
                
                String date = LocalDate.now().toString();
                Database.getAlertAppointment(connect.getDate(date));
                
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                Parent scene = loader.getRoot();
                stage.setScene(new Scene(scene));
                stage.show();
            }
        } catch (Exception ex) {

            ResourceBundle rb = ResourceBundle.getBundle("language_files/rb");
            Alert warning = new Alert(Alert.AlertType.WARNING);
            warning.setTitle(rb.getString("warning"));
            warning.setHeaderText(rb.getString("warning"));
            warning.setContentText(rb.getString("wrong"));
            warning.showAndWait();
        }

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
        
        //Next lines of code will set the locale to mx if the timezone selected is America/Mexico City
        TimeZone tz = TimeZone.getDefault();

        if (tz.getID().equals("America/Mexico_City")) {
            Locale locale = new Locale("mx");
            locale.setDefault(locale);
        }

        DBConnect connect = new DBConnect();
        
        //This will grab the user's username and password
        connect.setUser();
        connect.setPass();
    }

}
