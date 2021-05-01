package SE;

import SE.interfaces.LandlordReadOnly;
import SE.interfaces.UserReadOnly;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        DB db = new DB();
//        
//        Admin admin = new Admin(1);
//        Admin admi2n = new Admin(2);
//        DB db = new DB();

        int id;
        id = 1;
        VisitTimes v1 = new VisitTimes("Thursday", "10am-2pm");
        VisitTimes v2 = new VisitTimes("Firday", "1pm-4pm");
        ArrayList<VisitTimes> times = new ArrayList();
        times.add(v1);
        times.add(v2);
        db.addProperty(new Property(1, "Mai", "very cool as well", "Buy", "Alexandria", 2000, 0, "NA", "NA", times));
        db.addProperty(new Property(2, "Evee", "very cute", "Rent", "Cairo", 1200, 4, "10-10-2020", "30-10-2020", times));
        db.addProperty(new Property(1, "Hoda", "very cool", "Share", "Alexandria", 3000, 5, "10-02-2020", "20-03-2020", times));
//        
//        db.rentProperty("Evee", new Booking(20, "21-10-2020", "25-10-2020", 2, 4));
//        db.buyProperty("mai", 15);
//        db.CheckAvailableProperty("Evee", "20-10-2020", "22-10-2020");

        Account acc = new Account();
        Payment pay = new Payment();
        Property prop = new Property();
        // try {
        
        Property i = new Property();
        Review r = new Review();
        Account a = new Account();
        User u = new User();
        
        User user = new User(a);
        
        Landlord lord = new Landlord(i, a);
        Tenant tenantR = new Tenant(r, a);
        Tenant tenantP = new Tenant(i, a);
        Buyer buyer = new Buyer(i, a);

        Admin adminP = new Admin(i);
        Admin adminA = new Admin(a);

        lord.addProperty(new Property(1, "Mai", "very cool as well", "Buy", "Alexandria", 2000, 0, "NA", "NA", times));
        
        System.out.println("Enter email: ");
        String email = in.nextLine();
        System.out.println("Enter password: ");
        String password = in.nextLine();
        acc.login(email, password);
        user.login(email, password);
        
        db.buyProperty("Mai", Account.Login_ID);

        /*} catch (Exception e) {
            System.out.println(e);
        }*/
//        notf.showNotifications();
//        notf.clearAll();
//        acc.createAccount("omar1@ko.com", "1346_omar", "admin", "omar","213123","0100",5000);
//        acc.createAccount("omar2@ko.com", "1346_omar", "admin", "omar","213123","0100",2500);
//        acc.createAccount("omar3@ko.com", "1346_omar", "admin", "omar","213123","0100",1560);
//        acc.createAccount("omar4@ko.com", "1346_omar", "admin", "omar","213123","0100",4510);
//        acc.createAccount("omar5@ko.com", "1346_omar", "admin", "omar","213123","0100",1056);
//        acc.createAccount("omar6@ko.com", "1346_omar", "admin", "omar","213123","0100",123456);
//        acc.createAccount("omar7@ko.com", "1346_omar", "admin", "omar","213123","0100",123456);
//        acc.createAccount("omar8@ko.com", "1346_omar", "admin", "omar","213123","0100",123456);
//        acc.createAccount("omar9@ko.com", "1346_omar", "admin", "omar","213123","0100",123456);
//        acc.createAccount("omar10@ko.com", "1346_omar", "admin", "omar","213123","0100",123456);
//        acc.createAccount("omar11@ko.com", "1346_omar", "admin", "omar","213123","0100",123456);
//        acc.editAccount("omar2@asd.com");
//        acc.viewAccount("omar2@asd.com");
//        acc.banAccount("omar1@asd.com");
    }
}
