/*
*Raul Padilla
*C195 Software II
 */
package scheduledemo;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;

public class Database {

    private static ObservableList<Customer> allCustomer = FXCollections.observableArrayList();
    private static ObservableList<Appointments> allAppointments = FXCollections.observableArrayList();
    private static ObservableList<Schedule> allSchedule = FXCollections.observableArrayList();
    private static ObservableList<Schedule> weeklySchedule = FXCollections.observableArrayList();
    private static ObservableList<Schedule> consultantSchedule = FXCollections.observableArrayList();
    private static ObservableList<Type> allType = FXCollections.observableArrayList();
    private static List<String> allUser = new ArrayList<>();
    private static List<String> allPass = new ArrayList<>();
    private static List<String> customerName = new ArrayList<>();
    private static DetailedCustomer allDetailedCustomer;
    private static String loginUser;
    private static String loginPass;

    public static void addUser(String user) {
        allUser.add(user);
    }

    public static void addPassword(String pass) {
        allPass.add(pass);
    }

    //This will a customer to the database
    public static void addCustomer(Customer customer) {
        allCustomer.add(customer);

    }

    //This will add a customer to the database
    public static void addAppointment(Appointments appointment) {
        allAppointments.add(appointment);
    }

    //This will add a scheduled appointment to the database
    public static void addSchedule(Schedule schedule) {
        allSchedule.add(schedule);
    }

    //This will add a weekly scheduled appointment to the database
    public static void addWeek(String title) {
        for (int i = 0; i < allSchedule.size(); i++) {
            if (allSchedule.get(i).getTitle().equals(title)) {
                weeklySchedule.add(allSchedule.get(i));
            }
        }
    }

    //This will add a consultant scheduled appointment to the database
    public static void addConsultant(String user) {
        for (int i = 0; i < allSchedule.size(); i++) {
            if (allSchedule.get(i).getUser().equals(user)) {
                consultantSchedule.add(allSchedule.get(i));
            }
        }
    }

    //This will add a type of appointment to the database
    public static void addType(Type type) {
        allType.add(type);
    }

    //This will add a detailed customer to the database
    public static void addDetailedCustomer(DetailedCustomer detail) {
        allDetailedCustomer = detail;
    }

    //THis will delete a schedule in the database.
    public static void deleteAppointment(String appointmentTitle) {
        for (int i = 0; i < allAppointments.size(); i++) {
            if (allAppointments.get(i).getTitle().equals(appointmentTitle)) {
                allAppointments.remove(i);
            }
        }
    }

    //THis will delete a customer in the database.
    public static void deleteSchedule(String scheduleTitle) {
        for (int i = 0; i < allSchedule.size(); i++) {
            if (allSchedule.get(i).getTitle().equals(scheduleTitle)) {
                allSchedule.remove(i);
            }
        }
    }

    //This will update an appointment in the database
    public static void updateAppointment(String title) {

        for (int i = 0; i < allAppointments.size(); i++) {
            if (allAppointments.get(i).getTitle().equals(title)) {

                allAppointments.remove(i);
            }
        }

    }

    //This will get an appointment's date in the database
    public static boolean getDateAppointment(String date, int hour, int minute) {

        for (int i = 0; i < allAppointments.size(); i++) {
            if (allAppointments.get(i).getDate().equals(date)) {

                LocalTime start = LocalTime.parse(allAppointments.get(i).getStart().substring(0, 8));
                LocalTime end = LocalTime.parse(allAppointments.get(i).getEnd().substring(0, 8));
                LocalTime overStart = LocalTime.of(hour, minute);
                LocalTime overEnd = LocalTime.of(hour + 1, minute);
                if (overStart.isAfter(start) || overEnd.isBefore(end)) {
                    return false;
                }

            }
        }
        return true;

    }

    //This will alert the user if there is an appointment withn 15 minutes.
    public static void getAlertAppointment(String date) {
        
        for (int i = 0; i < allAppointments.size(); i++) {
            if (allAppointments.get(i).getDate().equals(date)) {

                LocalTime start = LocalTime.parse(allAppointments.get(i).getStart().substring(0, 8));
                LocalTime end = LocalTime.parse(allAppointments.get(i).getEnd().substring(0, 8));
                LocalTime timeNow = LocalTime.now();
                if (timeNow.isAfter(start.minusMinutes(15)) && timeNow.isBefore(start)) {
                    Alert warning = new Alert(Alert.AlertType.WARNING);
                    warning.setTitle("Attention!");
                    warning.setHeaderText("You have an appointment in less than 15 minutes");
                    warning.setContentText("Your appointment with " + allAppointments.get(i).getTitle() + " is at " + allAppointments.get(i).getStart());
                    warning.showAndWait();
                }
            }
        }
    }

    //This will update the week in the database
    public static void updateWeek(String title) {

        for (int i = 0; i < weeklySchedule.size(); i++) {
            if (weeklySchedule.get(i).getTitle().equals(title)) {

                weeklySchedule.remove(i);
            }
        }

    }

    //This will delete the week in the database
    public static void deleteWeek(String title) {

        for (int i = 0; i < weeklySchedule.size(); i++) {
            if (weeklySchedule.get(i).getTitle().equals(title)) {

                weeklySchedule.remove(i);
            }
        }

    }

    //This will update a customer in the database
    public static void updateCustomer(String name) {

        for (int i = 0; i < allCustomer.size(); i++) {
            if (allCustomer.get(i).getName().equals(name)) {
                allCustomer.remove(i);
            }
        }

    }

    //THis will delete a customer in the database.
    public static void deleteCustomer(String customer) {
        for (int i = 0; i < allCustomer.size(); i++) {
            if (allCustomer.get(i).getName().equals(customer)) {
                allCustomer.remove(i);
            }
        }
    }

    /*
    *This will add all the customers to the Customer tableview
     */
    public static ObservableList<Customer> getAllCustomer() {
        return allCustomer;
    }

    public static String getAllCustomerName(int i) {

        return allCustomer.get(i).getName();

    }

    public static String getAllConsultantName(int i) {

        return allUser.get(i);

    }

    public static ObservableList<Appointments> getAllAppointments() {
        return allAppointments;
    }

    public static ObservableList<Schedule> getAllSchedules() {
        return allSchedule;
    }

    public static ObservableList<Schedule> getWeeklySchedules() {
        return weeklySchedule;
    }

    public static ObservableList<Schedule> getConsultantSchedules() {
        return consultantSchedule;
    }

    public static ObservableList<Type> getTypeAppointments() {
        return allType;
    }

    public static DetailedCustomer getDetailedCustomer() {
        return allDetailedCustomer;
    }

    public static void loginUser(String login) {
        for (int i = 0; i < allUser.size(); i++) {
            if (login.equals(allUser.get(i))) {
                loginUser = allUser.get(i);
            }
        }

    }

    public static void loginPass(String login) {
        for (int i = 0; i < allPass.size(); i++) {
            if (login.equals(allPass.get(i))) {
                loginPass = allPass.get(i);
            }
        }

    }

    /*
        Get logged in user.
     */
    public static String getUser() {
        return loginUser;
    }

    /*
        Get logged in user's password.
     */
    public static String getPass() {
        return loginPass;
    }

}
