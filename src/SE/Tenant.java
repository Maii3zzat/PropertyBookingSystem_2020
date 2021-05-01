package SE;

import SE.interfaces.TenantReadOnly;
import SE.interfaces.UserReadOnly;
import java.sql.ResultSet;
import java.util.ArrayList;

public class Tenant extends User {

    int creditCardNo;
    //  Booking booking;
    BankAccount bankAcc;
    private TenantReadOnly TRO;

    public Tenant(TenantReadOnly TRO, UserReadOnly URO) {
        super(URO);
        this.TRO = TRO;
    }

    void notifyFlatmate() {

    }

    public ResultSet searchProperty(String location) {
        return TRO.searchProperty(location);
    }

    public void viewProperty(int s) {
        TRO.viewProperty(s);
    }

    public void rentProperty(String name, Booking b) {
        TRO.rentProperty(name, b);
    }

    public void CheckAvailableProperty(String name, String checkIn, String checkOut) {
        TRO.CheckAvailableProperty(name, checkIn, checkOut);
    }

    public void shareProperty(String name, Booking b) {
        TRO.shareProperty(name, b);
    }

    public Property chooseProperty(int pid) {
        return TRO.chooseProperty(pid);
    }

    public void addReview(String d, double r) {
        TRO.addReview(d, r);
    }
}
