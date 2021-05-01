/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SE.interfaces;

import SE.Booking;
import SE.Property;
import java.sql.ResultSet;
import java.util.ArrayList;

public interface TenantReadOnly {

    public ResultSet searchProperty(String location);

    public void viewProperty(int s);

    public void rentProperty(String name, Booking b);

    public void CheckAvailableProperty(String name, String checkIn, String checkOut);

    public void shareProperty(String name, Booking b);

    public Property chooseProperty(int pid);

    public void addReview(String d, double r);
}
