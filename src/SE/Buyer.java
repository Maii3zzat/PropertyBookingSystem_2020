package SE;

import SE.interfaces.BuyerReadOnly;
import SE.interfaces.UserReadOnly;
import java.sql.ResultSet;
import java.util.ArrayList;

public class Buyer extends User {

    Booking booking = new Booking();
    BankAccount bankAcc = new BankAccount();

    private final BuyerReadOnly BRO;

    public Buyer(BuyerReadOnly BRO, UserReadOnly URO) {
        super(URO);
        this.BRO = BRO;
    }

  

    public ResultSet searchProperty(String location) {
        return BRO.searchProperty(location);
    }

    public void buyProperty(String name, int userID) {
        BRO.buyProperty(name, userID);
    }

    public void viewProperty(int s) {
        BRO.viewProperty(s);
    }

    public void CheckAvailableProperty(String name, String checkIn, String checkOut) {
        BRO.CheckAvailableProperty(name, checkIn, checkOut);
    }
}
