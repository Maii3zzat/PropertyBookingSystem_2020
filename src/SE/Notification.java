package SE;

import java.util.ArrayList;


public class Notification {
    int notifId;
    String notifType;
    String description;
    
    DB x=new DB();
    ArrayList<Notification> nots =new ArrayList<Notification>();

    public Notification() {
    }
    public Notification(String notifType, String description) {
        this.notifType = notifType;
        this.description = description;
    }
    public Notification(int notifId, String notifType, String description) {
        this.notifId = notifId;
        this.notifType = notifType;
        this.description = description;
    }

    public int getNotifId() {
        return notifId;
    }
    public void setNotifId(int notifId) {
        this.notifId = notifId;
    }
    public String getNotifType() {
        return notifType;
    }
    public void setNotifType(String notifType) {
        this.notifType = notifType;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    
    public void showNotifications()
    {
        nots=x.getNotifications();
        for(int i=0;i<nots.size();i++)
            System.out.println(nots.get(i).notifId+ " , " + nots.get(i).notifType + " , " + nots.get(i).description);
    }
    public void clearAll()
    {
        nots=x.deleteNotifications(nots);
    }
}
