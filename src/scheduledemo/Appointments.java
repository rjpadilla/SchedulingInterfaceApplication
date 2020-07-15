/*
*Raul Padilla
*C195 Software II
 */
package scheduledemo;


public class Appointments {
    private String start;
    private String end;
    private String date;
    private String title;
    

    public Appointments(String start, String end, String date, String title) {
        
        this.start = start;
        this.end = end;
        this.date = date;
        this.title =title;
        
    }

    public String getStart() {
        return start;
    }

    public String getEnd() {
        return end;
    }

    public String getDate() {
        return date;
    }
    
     public String getTitle() {
        return title;
    }
    

}
