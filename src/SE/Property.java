package SE;

import SE.interfaces.AdminReadOnly;
import SE.interfaces.BuyerReadOnly;
import SE.interfaces.LandlordReadOnly;
import SE.interfaces.TenantReadOnly;
import java.sql.ResultSet;
import java.util.ArrayList;

public class Property implements BuyerReadOnly, LandlordReadOnly, TenantReadOnly, AdminReadOnly {

    private int propID;
    private String name;
    private String description;
    private String type;
    private String location;
    private double price;
    private int maxGuests;
    private String PavailableFrom;
    private String PavailableTo;
    private int userId;
    private String state;
    private ArrayList<VisitTimes> visits = new ArrayList();

    public static int viewedProp;

    DB db = new DB();
    Payment pay = new Payment();

    public Property() {
    }

    public Property(int propID, int userId, String name, String description, String type, String location, double price, int maxGuests,
            String PavailableFrom, String PavailableTo, String state) {
        this.propID = propID;
        this.name = name;
        this.description = description;
        this.type = type;
        this.location = location;
        this.price = price;
        this.maxGuests = maxGuests;
        this.PavailableFrom = PavailableFrom;
        this.PavailableTo = PavailableTo;
        this.userId = userId;
        this.state = state;
    }

    public Property(int userId, String name, String description, String type, String location, double price, int maxGuests, String PavailableFrom, String PavailableTo) {
        this.name = name;
        this.description = description;
        this.type = type;
        this.location = location;
        this.price = price;
        this.maxGuests = maxGuests;
        this.PavailableFrom = PavailableFrom;
        this.PavailableTo = PavailableTo;
        this.userId = userId;
    }

    public Property(int userId, String name, String description, String type, String location, double price, int maxGuests,
            String PavailableFrom, String PavailableTo, ArrayList<VisitTimes> visitTimes) {
        this.name = name;
        this.description = description;
        this.type = type;
        this.location = location;
        this.price = price;
        this.maxGuests = maxGuests;
        this.PavailableFrom = PavailableFrom;
        this.PavailableTo = PavailableTo;
        this.userId = userId;
        this.visits = visitTimes;

    }

    public ArrayList<VisitTimes> getVisits() {
        return visits;
    }

    public void setVisits(ArrayList<VisitTimes> visits) {
        this.visits = visits;
    }

    public String getPavailableFrom() {
        return PavailableFrom;
    }

    public void setPavailableFrom(String PavailableFrom) {
        this.PavailableFrom = PavailableFrom;
    }

    public String getPavailableTo() {
        return PavailableTo;
    }

    public void setPavailableTo(String PavailableTo) {
        this.PavailableTo = PavailableTo;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int ownerId) {
        this.userId = ownerId;
    }

    public int getPropID() {
        return propID;
    }

    public void setPropID(int propID) {
        this.propID = propID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getMaxGuests() {
        return maxGuests;
    }

    public void setMaxGuests(int maxGuests) {
        this.maxGuests = maxGuests;
    }

    @Override
    public void addProperty(Property p) {
        db.addProperty(p);
    }
//
//    @Override
//    public ResultSet viewProperty(int s) {
//        ResultSet r = db.viewProperty(s);
//        retrun r;
//    }

    @Override
    public void viewOwnedProperties(int s) {
        db.viewOwnedProperties(s);
    }

    @Override
    public void CheckAvailableProperty(String name, String checkIn, String checkOut) {
        db.CheckAvailableProperty(name, checkIn, checkOut);
    }

    @Override
    public void buyProperty(String name, int userID) {
        db.buyProperty(name, userID);
    }

    @Override
    public void rentProperty(String name, Booking b) {
        db.rentProperty(name, b);
    }

    @Override
    public void shareProperty(String name, Booking b) {
        db.shareProperty(name, b);
    }

    @Override
    public void deleteProperty(int pid) {
        db.deleteProperty(pid);
    }

    @Override
    public void editProperty(int id, String str, String n) {
        db.editProperty(id, str, n);
    }

    @Override
    public ResultSet viewPendingPropList() {
        ResultSet result = null;
        result = db.viewPendingPropList();
        return result;
    }

    @Override
    public ResultSet searchProperty(String location) {
        ResultSet result = null;
        result = db.searchProperty(location);
        return result;
    }

    @Override
    public Property chooseProperty(int pid) {
        Property prop = new Property();
        db.chooseProperty(pid);
        return prop;
    }

    @Override
    public void approveProperty(String type, int Pid) {
        db.approveProperty(type, Pid);
    }

    @Override
    public void disapproveProperty(String type, int Pid) {
        db.disapproveProperty(type, Pid);
    }

    
    public void updateAvailability(int Pid, String fromDate, String toDate) {
        db.updateAvailability(Pid, fromDate, toDate);
    }

    //in class diagram fe 'date' in parameters of addproperty
    //add max guests in parameters in class diagrram (id too?)
    //add av ti mes
    @Override
    public void addReview(String d, double r) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void viewAccount() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean login(String loginMail, String loginPW) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void banAccount(String loginMail) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void viewProperty(int s) {
       db.viewProperty(s);
    }
}
