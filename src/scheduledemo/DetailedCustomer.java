/*
*Raul Padilla
*C195 Software II
 */
package scheduledemo;


public class DetailedCustomer {

    private String name;
    private String phone;
    private String address;
    private String user;
    private String title;
    private String description;
    private String location;
    private String date;
    private String type;

    public DetailedCustomer(String name, String address, String phone, String user, String title, String description, String location, String date, String type) {

        this.name = name;
        this.phone = phone;
        this.address = address;
        this.user = user;
        this.title = title;
        this.date = date;
        this.location = location;
        this.description = description;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getAddress() {
        return address;
    }

    public String getUser() {
        return user;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getLocation() {
        return location;
    }

    public String getDate() {
        return date;
    }
    
    public String getType() {
        return type;
    }
}
