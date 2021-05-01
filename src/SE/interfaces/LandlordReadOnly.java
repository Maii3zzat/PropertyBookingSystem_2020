package SE.interfaces;

import SE.Property;
import java.sql.ResultSet;
import java.util.ArrayList;

public interface LandlordReadOnly {


    public void addProperty(Property p);

    public void viewProperty(int s);

    public void viewOwnedProperties(int s);

    public void deleteProperty(int pid);

    public void editProperty(int id, String str, String n);

    public ResultSet searchProperty(String location);

}
