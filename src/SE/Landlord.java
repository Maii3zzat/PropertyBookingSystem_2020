package SE;

import SE.interfaces.LandlordReadOnly;
import SE.interfaces.UserReadOnly;
import java.sql.ResultSet;
import java.util.ArrayList;

public class Landlord extends User {

    ArrayList<Property> properties = new ArrayList<>();
    DB db = new DB();

    User landlord = new User();
    private LandlordReadOnly LRO;
 
    public Landlord() {
    }

    public Landlord(LandlordReadOnly LRO, UserReadOnly URO) {
        super(URO);
        this.LRO = LRO;
    }



    public void notifyLandlord(int prop_ID, int user_ID, String type) {
        properties = db.retrieveProperty();
        landlord.user_acc.acc = db.retrieveAccounts();
        int index = -1;
        for (int i = 0; i < properties.size(); i++) {
            if (properties.get(i).getPropID() == prop_ID) {
                index = i;
            }
        }
        if (index == -1) {
            System.out.println("error");
        } else {
            int reciever_ID = properties.get(index).getUserId();
            int sender_ID = Account.Login_ID;
            int p_ID = prop_ID;
            String desc = landlord.user_acc.acc.get(sender_ID - 1).getName() + " has requested to " + type + " " + properties.get(index).getName();
            db.createNotification(p_ID, reciever_ID, type, desc);
        }
    }

    public void addProperty(Property p) {
        LRO.addProperty(p);
    }

    public void viewProperty(int s) {
        LRO.viewProperty(s);
    }

    public void viewOwnedProperties(int s) {
        LRO.viewOwnedProperties(s);
    }

    public void deleteProperty(int pid) {
        LRO.deleteProperty(pid);
    }

    public void editProperty(int id, String str, String n) {
        LRO.editProperty(id, str, n);
    }

    public ResultSet searchProperty(String location) {
        return LRO.searchProperty(location);
    }

}
