/*
*Raul Padilla
*C195 Software II
 */
package scheduledemo;


public class Schedule {
    private String user;
    private String title;
    private String description;
    private String location;
    private String date;
    
    

    public Schedule(String user, String title, String description, String location, String date) {
        
        this.user = user;
        this.title = title;
        this.date = date;
        this.location =location;
        this.description =description;
        
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
    

}