package SE;

import java.util.ArrayList;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class Report {
    String date;
    int id;
    String description;
    String title;
    //User user_id;
    
    DB db = new DB();
    
    ArrayList<Report> reps =new ArrayList<Report>();

    public Report() {
    }
    public Report(String date, int id, String description, String title) {
        this.date = date;
        this.id = id;
        this.description = description;
        this.title = title;
    }
    public Report(String date, String description, String title) {
        this.date = date;
        this.description = description;
        this.title = title;
    }
    

    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    
    public void reportContent(String d, String t)
    {
        DateFormat df = new SimpleDateFormat("dd/MM/yy");
        Date dateobj = new Date();
        System.out.println(df.format(dateobj));
        Report rep = new Report(df.format(dateobj),d,t);
        reps.add(rep);
        db.makeReport(reps);
    }
    
}