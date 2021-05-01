package SE;

import SE.Booking;
import SE.DB;
import SE.Property;
import java.sql.ResultSet;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class DBTest {

    DB db = new DB();

    public DBTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testAddProperty() {
        System.out.println("addProperty");
        Property p = new Property(5, "The Hippo", "it has a seaview", "rent", "Alexandria", 1000, 3, "10-08-2020", "30-08-2020");
        DB instance = new DB();
        instance.addProperty(p);

    }

    @Test
    public void testViewOwnedProperties() {
        System.out.println("viewOwnedProperties");
        int id = 5;
        DB instance = new DB();
        instance.viewOwnedProperties(id);

    }

    @Test
    public void testCheckAvailableProperty() {
        System.out.println("CheckAvailableProperty");
        String name = "The Hippo";
        String checkIn = "18-08-2020";
        String checkOut = "25-08-2020";
        DB instance = new DB();
        Boolean result = instance.CheckAvailableProperty(name, checkIn, checkOut);
        assertEquals(true, result);

    }

    @Test
    public void testBuyProperty() {
        System.out.println("buyProperty");
        String name = "Silverbell Realty";
        int userID = 4;
        DB instance = new DB();
        instance.buyProperty(name, userID);

    }

    @Test
    public void testRentProperty() {
        System.out.println("rentProperty");
        String name = "Pagoda Partners Realty";
        Booking b = new Booking(5, "05-05-2020", "07-05-2020", 2, 2);
        DB instance = new DB();
        instance.rentProperty(name, b);

    }

    @Test
    public void testShareProperty() {
        System.out.println("shareProperty");
        String name = "LampLighters Realty";
        Booking b = new Booking(5, "22-02-2020", "23-02-2020", 1, 1);
        DB instance = new DB();
        instance.shareProperty(name, b);

    }

    //insert an pid of value 3 to delete the property
    @Test
    public void testDeleteProperty() {
        System.out.println("deleteProperty");
        int pid = 0;
        DB instance = new DB();
        instance.deleteProperty(pid);

    }

    @Test
    public void testEditProperty() {
        System.out.println("editProperty");
        int id = 1;
        String str = "name";
        String n = "Goldenbell Realty";
        DB instance = new DB();
        instance.editProperty(id, str, n);

    }

    @Test
    public void testChooseProperty() {
        System.out.println("chooseProperty");
        int pid = 1;
        DB instance = new DB();
        Property result = instance.chooseProperty(pid);
        assertEquals("Property{propID=2, name=Pagoda Partners Realty, description=Great view of the mountains. , type=Rent, location=El Gouna, price=2000.0, maxGuests=3, PavailableFrom=02-05-2020, PavailableTo=02-09-2020, userId=4, state=Pending}", result.toString());

    }

    @Test
    public void testApproveProperty() {
        System.out.println("approveProperty");
        String type = "admin";
        int Pid = 2;
        DB instance = new DB();
        instance.approveProperty(type, Pid);

    }

    //insert a pid of value 3 to disapprove the property 
    @Test
    public void testDisapproveProperty() {
        System.out.println("disapproveProperty");
        String type = "admin";
        int Pid = 95;
        DB instance = new DB();
        instance.disapproveProperty(type, Pid);

    }

    @Test
    public void testUpdateAvailability() {
        System.out.println("updateAvailability");
        int Pid = 4;
        String fromDate = "10-08-2020";
        String toDate = "30-08-2020";
        DB instance = new DB();
        instance.updateAvailability(Pid, fromDate, toDate);

    }

    @Test
    public void testGetBookingInfo() {
        System.out.println("getBookingInfo");
        int Bid = 1;
        DB instance = new DB();
        Booking result = instance.getBookingInfo(Bid);
        assertEquals("Booking{id=1, userID=3, Pid=1, checkIn=11-08-2020, checkOut=30-08-2020, type=Rent, guestsNo=3, state=unpaid, duration=1}", result.toString());

    }

    @Test
    public void testEditRent() {
        System.out.println("editRent");
        int Bid = 2;
        String Cin = "11-08-2020";
        String Cout = "30-08-2020";
        int guestsNo = 3;
        DB instance = new DB();
        instance.editRent(Bid, Cin, Cout, guestsNo);

    }

    @Test
    public void testLogin() {
        Account c = new Account();
        boolean result = c.login("admin@admin.com", "admin");
        assertEquals(true, result);
    }

    @Test
    public void testVerificationCode() {
        Account c = new Account();
        boolean result = c.enterVerificationCode(Account.verificationCode);
        assertEquals(true, result);
    }
}
