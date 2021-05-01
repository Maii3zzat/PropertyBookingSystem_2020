package SE.interfaces;

import SE.Property;
import java.sql.ResultSet;
import java.util.ArrayList;

public interface BuyerReadOnly {

    public ResultSet searchProperty(String location);

    public void buyProperty(String name, int userID);

    public void viewProperty(int s);

    public void CheckAvailableProperty(String name, String checkIn, String checkOut);

    
}
