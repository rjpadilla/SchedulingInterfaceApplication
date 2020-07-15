/*
*Raul Padilla
*C195 Software II
 */

package scheduledemo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.TimeZone;
import javafx.scene.control.Alert;

public class DBConnect {

    private Connection conn;
    private Statement st;
    private ResultSet rs;
    private static String name;
    private static String address;
    private static String phone;
    private static String time;
    private static String date;
    private static String code;
    private static String description;
    private static String url;
    private static String title;

    private String detailName, detailTitle, detailDescription, detailLocation, detailDate, detailType, detailUser, detailAddress, detailPhone;

    private static int id;

    /**
     * This will connect the application to the MySQL database.
     */
    public DBConnect() {
        try {
            Class.forName("com.mysql.jdbc.Driver");

            conn = DriverManager.getConnection("jdbc:mysql://3.227.166.251/U06hUe", "U06hUe", "53688769683");
            st = conn.createStatement();
        } catch (Exception ex) {
            e1.error(ex);
        }
    }

    /**
     * This will grab the name from the database.
     */
    public String getCustomerName() {
        try {
            String query = "select customerName from customer";
            rs = st.executeQuery(query);
            while (rs.next()) {
                name = rs.getString("customerName");

            }
        } catch (Exception ex) {
            e1.error(ex);
        }
        return name;
    }

    /**
     * This will query the data from the SQL database to add to the Customer
     * list for the Customer TableView.
     */
    public void getCustomer() {
        try {
            String query = "select c.customerName, a.address, a.phone from customer c inner join address a on (c.addressId = a.addressId);";
            rs = st.executeQuery(query);
            while (rs.next()) {
                String customerName = rs.getString(1);
                String customerAddress = rs.getString(2);
                String customerPhone = rs.getString(3);
                Database.addCustomer(new Customer(customerName, customerAddress, customerPhone));
            }
        } catch (Exception ex) {
            e1.error(ex);
        }

    }

    /**
     * This will update the customer from the database
     */
    public void updateCustomer(String updatedName, String name) {
        try {
            String query = "update customer set customerName  = '" + updatedName + "' where customerName = '" + name + "';";
            st.executeUpdate(query);
            Database.deleteCustomer(name);
        } catch (Exception ex) {
            d1.alert();
        }

    }

    /**
     * This will delete the customer from the app database and SQL database.
     */
    public void deleteCustomer(String name) {
        try {
            String query = "delete from customer where customerName = '" + name + "';";
            st.executeUpdate(query);
            Database.deleteCustomer(name);
        } catch (Exception ex) {
            d1.alert();
        }

    }

    /**
     * This will update the customer from the SQL database
     */
    public void updateCity(String updatedCity, String city) {
        try {
            String query = "update city set city  = '" + updatedCity + "' where city = '" + city + "';";
            st.executeUpdate(query);

        } catch (Exception ex) {
            d1.alert();
        }

    }

    /**
     * This will delete the city from the SQL database
     */
    public void deleteCity(int cityId) {
        try {
            String query = "delete from city where cityID = " + cityId + ";";
            st.executeUpdate(query);

        } catch (Exception ex) {
            d1.alert();
        }

    }

    /**
     * This will update the customer from the SQL database
     */
    public void updateAddress(String updatedAddress, String address, String updatedPhone, String updatedCode) {
        try {
            String phoneQuery = "update address set phone  = '" + updatedPhone + "' where address = '" + address + "';";
            String codeQuery = "update address set postalCode  = '" + updatedCode + "' where address = '" + address + "';";
            String addressQuery = "update address set address  = '" + updatedAddress + "' where address = '" + address + "';";

            st.executeUpdate(phoneQuery);
            st.executeUpdate(codeQuery);
            st.executeUpdate(addressQuery);

        } catch (Exception ex) {
            d1.alert();
        }

    }

    /**
     * This will delete the address from the SQL database
     */
    public void deleteAddress(String name)  {
       try{
            String query = "delete from address where address = '" + name + "';";
            st.executeUpdate(query);    
       }catch(Exception ex) {
           //Left this empty on purpose...
       }
              

    }

    /**
     * This will delete the customer from the database
     */
    public void deleteAppointment(String title) {
        try {
            String query = "delete from appointment where title = '" + title + "';";
            st.executeUpdate(query);
            Database.deleteAppointment(title);
        } catch (Exception ex) {
            e1.error(ex);
        }

    }

    /**
     * This will grab the id from the SQL database.
     */
    public int getCustomerID(String name) {
        try {
            String query = "select customerId from customer where customerName = '" + name + "';";
            rs = st.executeQuery(query);
            while (rs.next()) {
                id = rs.getInt("customerId");

            }
        } catch (Exception ex) {
            e1.error(ex);
        }
        return id;
    }

    public int getUserID(String name) {
        try {
            String query = "select userId from user where userName = '" + name + "';";
            rs = st.executeQuery(query);
            while (rs.next()) {
                id = rs.getInt("userId");

            }
        } catch (Exception ex) {
            e1.error(ex);
        }
        return id;
    }

    /**
     * This will query the data from the SQL database to add to the Appointment
     * list for the Appointment TableView.
     */
    public void getAppointments() {
        try {
            LocalDateTime dt = LocalDateTime.now();
            TimeZone tz = TimeZone.getDefault();

            ZoneId zone = ZoneId.of(tz.getID());
            ZonedDateTime zdt = dt.atZone(zone);
            ZoneOffset offset = zdt.getOffset();

            String query = "SELECT TIME_FORMAT(CONVERT_TZ(start,'-04:00','" + offset + "'),'%r'), TIME_FORMAT(CONVERT_TZ(end,'-04:00','" + offset + "'),'%r'), DATE_FORMAT(start,\"%M %d %Y\"), title from appointment;";
            rs = st.executeQuery(query);
            while (rs.next()) {
                String start = rs.getString(1);
                String end = rs.getString(2);
                String date = rs.getString(3);
                String title = rs.getString(4);
                Database.addAppointment(new Appointments(start, end, date, title));
            }

        } catch (Exception ex) {
            e1.error(ex);
        }

    }
    
    /**
     * This will grab the schedule from the SQL database and plug it into the app database.
     */
    public void getSchedule() {
        try {
            if (Database.getAllSchedules().isEmpty()) {
                String query = "select u.userName, a.title, a.description, a.location, a.start from appointment a inner join user u on (a.userId = u.userId);";
                rs = st.executeQuery(query);
                while (rs.next()) {
                    String user = rs.getString(1);
                    String title = rs.getString(2);
                    String desc = rs.getString(3);
                    String location = rs.getString(4);
                    String start = rs.getString(5);
                    Database.addSchedule(new Schedule(user, title, desc, location, start));
                }
            }

        } catch (Exception ex) {
            e1.error(ex);
        }
    }

    /**
     * This will return the date from the string input.
     */
    public String getDate(String input) {
        try {
            String query = "select DATE_FORMAT('" + input + "','%M %d %Y') FROM DUAL";
            rs = st.executeQuery(query);
            while (rs.next()) {
                date = rs.getString(1);
            }

        } catch (Exception ex) {
            e1.error(ex);
        }
        return date;
    }

    /**
     * This will grab the user from the SQL database and plug it into the user
     * list.
     */
    public void setUser() {
        try {

            String query = "select userName from user";
            rs = st.executeQuery(query);
            while (rs.next()) {
                String userName = rs.getString(1);
                Database.addUser(userName);
            }
        } catch (Exception ex) {
            e1.error(ex);
        }
    }

    /**
     * This will grab the password from the SQL database and plug it into the
     * password list.
     */
    public void setPass() {
        try {

            String query = "select password from user";
            rs = st.executeQuery(query);
            while (rs.next()) {
                String password = rs.getString(1);
                Database.addPassword(password);
            }
        } catch (Exception ex) {
            e1.error(ex);
        }
    }

    /**
     * This will grab the customer count from the SQL database.
     */
    public int getCount() {
        try {

            String query = "select COUNT(*) from customer";
            rs = st.executeQuery(query);

            while (rs.next()) {
                int count = rs.getInt("COUNT(*)");
                return count;

            }

        } catch (Exception ex) {
            e1.error(ex);
        }
        return 0;
    }

    /**
     * This will grab the user count from the SLQ database.
     */
    public int getUserCount() {
        try {

            String query = "select COUNT(*) from user";
            rs = st.executeQuery(query);

            while (rs.next()) {
                int count = rs.getInt("COUNT(*)");
                return count;

            }

        } catch (Exception ex) {
            e1.error(ex);
        }
        return 0;
    }

    /**
     * This will grab the address from the SQL database.
     */
    public String getAddress() {
        try {
            String query = "select address from address";
            rs = st.executeQuery(query);
            while (rs.next()) {
                address = rs.getString("address");
                rs.next();
            }
        } catch (Exception ex) {
            e1.error(ex);
        }
        return address;
    }

    /**
     * This will grab the phone number from the SQL database.
     */
    public String getPhone() {
        try {
            String query = "select phone from address";
            rs = st.executeQuery(query);
            while (rs.next()) {
                phone = rs.getString("phone");
                rs.next();
            }
        } catch (Exception ex) {
            e1.error(ex);
        }
        return phone;
    }

    /**
     * This will grab the country from the SQL database.
     */
    public String getCountry(int i) {
        try {

            String query = "select country from country where countryId =" + i;
            rs = st.executeQuery(query);
            while (rs.next()) {
                String name = rs.getString("country");
                return name;
            }

        } catch (Exception ex) {
            e1.error(ex);
        }
        return null;
    }

    /**
     * This will grab the country from the SQL database.
     */
    public int getCountryId(String name) {
        try {

            String query = "select countryId from country where country ='" + name + "'";
            rs = st.executeQuery(query);

            while (rs.next()) {
                int id = rs.getInt("countryId");
                return id;

            }

        } catch (Exception ex) {
            e1.error(ex);
        }
        return 0;
    }

    /**
     * This will grab the description from the SQL database.
     */
    public String getDescription(String title) {
        try {
            String query = "select description from appointment where title = '" + title + "'";
            rs = st.executeQuery(query);
            while (rs.next()) {
                description = rs.getString(1);
                rs.next();
            }
        } catch (Exception ex) {
            e1.error(ex);
        }
        return description;
    }

    /**
     * This will grab the url from the SQL database.
     */
    public String getURL(String title) {
        try {
            String query = "select url from appointment where title = '" + title + "'";
            rs = st.executeQuery(query);
            while (rs.next()) {
                url = rs.getString(1);

            }
        } catch (Exception ex) {
            e1.error(ex);
        }
        return url;
    }

    /**
     * This will put the appointment into the SQL database.
     */
    public void putAppointment(int customerId, int userId, String title, String description, String location, String contact, String url, String start, String end) {
        try {
            String user = Database.getUser();//Testing

            String query = "INSERT INTO appointment(appointmentId, customerId, userId,title, description, location, contact, url, start, end,createDate,createdBy,lastUpdate,lastUpdateBy) "
                    + "VALUE(appointmentId," + customerId + "," + userId + ",'" + title + "','" + description + "','" + location
                    + "','" + contact + "','" + url + "','" + start + "','" + end + "',sysdate(), '" + user + "', sysdate(),'" + user + "')";
            st.executeUpdate(query);

        } catch (Exception ex) {
            e1.error(ex);
        }

    }

    /*
    *This will convert the time of the app for the start time.
     */
    public String getStart(int hour, int minute) {
        try {
            String query = "select TIME_FORMAT('" + hour + ":" + minute + "','%r') AS TIME FROM DUAL";
            rs = st.executeQuery(query);
            while (rs.next()) {
                time = rs.getString("TIME");
            }
        } catch (Exception ex) {
            e1.error(ex);
        }
        return time;

    }

    /*
    *This will convert the time of the app for the end time.
     */
    public String getEnd(int hour, int minute) {
        try {
            hour++;
            String query = "select TIME_FORMAT('" + hour + ":" + minute + "','%r') AS TIME FROM DUAL";
            rs = st.executeQuery(query);
            while (rs.next()) {
                time = rs.getString("TIME");
            }
        } catch (Exception ex) {
            e1.error(ex);
        }
        return time;

    }

    /**
     * This will put the name into the database.
     */
    public void putName(String name, int id) {
        try {
            String user = Database.getUser();//Testing

            String query = "INSERT INTO customer(customerName,addressId,active,createDate,createdBy,lastUpdate,lastUpdateBy) VALUES ('" + name + "'," + id + "," + 1 + ",sysdate(), '" + user + "', sysdate(),'" + user + "')";
            st.executeUpdate(query);
        } catch (Exception ex) {
            e1.error(ex);
        }

    }

    /**
     * This will grab the city id from the SQL database.
     */
    public int getCityId(String name) {
        try {

            String query = "select cityId from city where city ='" + name + "'";
            rs = st.executeQuery(query);

            while (rs.next()) {
                int id = rs.getInt("cityId");
                return id;

            }

        } catch (Exception ex) {
            e1.error(ex);
        }
        return 0;
    }

    /**
     * This will grab the city name from the database joined by address.
     */
    public String getCityName(int addressId) {
        try {

            String query = "select c.city from city c inner join address a on (c.cityId = a.cityId) where a.addressId = " + addressId;
            rs = st.executeQuery(query);

            while (rs.next()) {
                String city = rs.getString(1);
                return city;

            }

        } catch (Exception ex) {
            e1.error(ex);
        }
        return null;
    }

    /**
     * This will grab the zipcode from the SQL database.
     */
    public String getZipcode(int addressId) {
        try {

            String query = "select postalCode from address where addressId = " + addressId;
            rs = st.executeQuery(query);

            while (rs.next()) {
                code = rs.getString(1);

            }

        } catch (Exception ex) {
            e1.error(ex);
        }
        return code;
    }

    /**
     * This will grab the address Id from the SQL database.
     */
    public int getAddressId(String name) {
        try {

            String query = "select addressId from address where address ='" + name + "'";
            rs = st.executeQuery(query);

            while (rs.next()) {
                int id = rs.getInt("addressId");
                return id;

            }

        } catch (Exception ex) {
            e1.error(ex);
        }
        return 0;
    }

    /*
    *This will get the weeks in a month from the sql database and add it to the database.
     */
    public void getWeek(String newWeek) {
        try {
            String query = "select title from appointment where MONTH(start) = MONTH(CURDATE());";
            rs = st.executeQuery(query);

            if (Database.getWeeklySchedules().isEmpty()) {
                while (rs.next()) {
                    title = rs.getString(1);
                    Database.addWeek(title);
                }
            } else {
                if (title.equals(newWeek)) {
                    Database.updateWeek(title);
                    Database.addWeek(newWeek);
                } else {
                    Database.updateWeek(newWeek);
                }
            }

        } catch (Exception ex) {
            e1.error(ex);
        }

    }

    /**
     * This will grab the "type" from the SQL database.
     */
    public void getType(String monthChoice) {
        try {

            String query = "select Count(*), contact, MONTHNAME(start) from appointment where MONTHNAME(start) = '" + monthChoice + "' group  by contact;";
            rs = st.executeQuery(query);
            while (rs.next()) {

                String count = String.valueOf(rs.getInt(1));
                String type = rs.getString(2);
                String month = rs.getString(3);
                Database.addType(new Type(count, type, month));

            }

        } catch (Exception ex) {
            e1.error(ex);
        }
    }

    /**
     * This will use inner joins to get a detailed customer report.
     */
    public void getDetailCustomer(String detailChoice) {
        try {

            String query = "select c.customerName, a.address, a.phone, at.title , at.description , at.location, at.start, at.contact,at.lastUpdateBy\n"
                    + "from customer c \n"
                    + "inner join address a on (c.addressId = a.addressId)\n"
                    + " inner join appointment at on (c.customerId = at.customerId)\n"
                    + " where customerName = '" + detailChoice + "';";

            rs = st.executeQuery(query);

            while (rs.next()) {

                detailName = rs.getString(1);
                detailAddress = rs.getString(2);
                detailPhone = rs.getString(3);
                detailTitle = rs.getString(4);
                detailDescription = rs.getString(5);
                detailLocation = rs.getString(6);
                detailDate = rs.getString(7);
                detailType = rs.getString(8);
                detailUser = rs.getString(9);

                Database.addDetailedCustomer(new DetailedCustomer(detailName, detailAddress, detailPhone, detailUser, detailTitle, detailDescription, detailLocation, detailDate, detailType));
            }

        } catch (Exception ex) {
            e1.error(ex);
        }
    }

    /**
     * This will put an address into the SQL database.
     */
    public void putAddress(String address, int cityId, String phone, String code) {
        try {
            String user = Database.getUser();//Testing

            String query = "INSERT INTO address(addressId,address,cityId,postalCode,phone,createDate,createdBy,lastUpdate,lastUpdateBy) VALUES (addressId,'" + address + "'," + cityId + ",'" + code + "','" + phone + "',sysdate(), '" + user + "', sysdate(),'" + user + "')";            
            st.executeUpdate(query);

        } catch (Exception ex) {
            e1.error(ex);
        }

    }

    /**
     * This will put a city into the SQL database.
     */
    public void putCity(String city, int countryId) {
        try {
            String user = Database.getUser();

            String query = "INSERT INTO city(cityId,city,countryId,createDate,createdBy,lastUpdate,lastUpdateBy) VALUES (cityId,'" + city + "'," + countryId + ", sysdate(), '" + user + "', sysdate(),'" + user + "')";
            st.executeUpdate(query);

        } catch (Exception ex) {
            e1.error(ex);
        }

    }

    /*
    The next block of codes use lambda expressions. Theses lambda expression help with code redundancy.
    */
    interface Error {

        public void error(Exception ex);
    }

    interface DeleteReference {

        public void alert();
    }

    Error e1 = (ex) -> {
        System.out.println("Error " + ex);
    };

    DeleteReference d1 = () -> {
        Alert warning = new Alert(Alert.AlertType.WARNING);
        warning.setTitle("Warning Message");
        warning.setHeaderText("The selected customer cannot be deleted since it is referenced in an appointment.");
        warning.setContentText("Delete the appointment first before deleting the customer.");
        warning.showAndWait();
    };

}
