/*
*Raul Padilla
*C195 Software II
 */
package scheduledemo;



public class Customer {
    private String name;
    private String phone;
    private String address;
    
    

    public Customer(String name, String address, String phone) {
        
        this.name = name;
        this.phone = phone;
        this.address = address;
        
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
    


}
