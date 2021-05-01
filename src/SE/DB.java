package SE;

import SE.interfaces.LandlordReadOnly;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

public class DB {

    private final String userName = "root";
    private final String password = "";
    private final String dbName = "se_database";
    private LandlordReadOnly LRO;

    private Scanner in = new Scanner(System.in);
    private Connection con;

    public DB() {
        try {
            //Loading the jdbc driver
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            //Get a connection to database
            con = DriverManager.getConnection("jdbc:mysql://localhost:3308/" + dbName, userName, password);
        } catch (Exception e) {
            System.err.println("DATABASE CONNECTION ERROR: " + e.toString());
        }
    }

    /*------------ Account S database ------------*/
    public ArrayList<Account> retrieveAccounts() {                  // retrieve all the accounts from the database
        ArrayList<Account> result = new ArrayList();
        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select * from account");
            while (rs.next()) {
                //Account(int id, String name, String loginMail, String loginPW, String type)
                result.add(new Account(rs.getInt("user_ID"), rs.getString("name"), rs.getString("loginMail"),
                        rs.getString("loginPW"), rs.getString("type"), rs.getString("creditCardNo"), rs.getString("phone")));
            }
        } catch (Exception e) {
            System.err.println("DATABASE QUERY ERROR: " + e.toString());
        }
        return result;

    }

    public ArrayList<BankAccount> retrieveBankAccounts() {                  // retrieve all the accounts from the database
        ArrayList<BankAccount> result = new ArrayList();
        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select * from bankaccount");
            while (rs.next()) {
                result.add(new BankAccount(rs.getString("loginMail"), rs.getDouble("balance"), rs.getInt("b_ID"), rs.getInt("u_ID")));
            }
        } catch (Exception e) {
            System.err.println("DATABASE QUERY ERROR: " + e.toString());
        }
        return result;

    }

    public void insertNewAccount(ArrayList<Account> acc) {              // create new account in the database 
        try {

            Statement sql = con.createStatement();
            sql.executeUpdate("insert into account(loginMail,loginPW,type,name,creditCardNo,phone) "
                    + "values('" + acc.get(acc.size() - 1).getLoginMail() + "', '" + acc.get(acc.size() - 1).getLoginPW()
                    + "','" + acc.get(acc.size() - 1).getType() + "','" + acc.get(acc.size() - 1).getName() + "','"
                    + acc.get(acc.size() - 1).getCreditCardNo() + "','" + acc.get(acc.size() - 1).getPhone() + "')");
        } catch (Exception e) {
            System.err.println("DATABASE INSERTION ERROR: " + e.toString());
        }
    }

    public void insertNewBankAccount(ArrayList<Account> acc, ArrayList<BankAccount> BanAcc) {
        try {
            acc = retrieveAccounts();

            int ID;
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select * from account where loginMail = '" + acc.get(acc.size() - 1).getLoginMail() + "'");
            if (rs.first()) {
                ID = rs.getInt("user_ID");
                System.out.println(ID);
                Statement sql = con.createStatement();
                sql.executeUpdate("insert into bankaccount(loginMail,balance,u_ID) "
                        + "values('" + BanAcc.get(BanAcc.size() - 1).getMail() + "'," + BanAcc.get(BanAcc.size() - 1).getBalance() + "," + ID + ")");
                System.out.println("account created");
            }

        } catch (Exception e) {
            System.err.println("DATABASE INSERTION ERROR: " + e.toString());
        }

    }

    public void updateAccount(int id, String newRecord, int edit) {   // edit account name and password 
        try {
            Statement stmt = con.createStatement();
            if (edit == 1) {
                stmt.executeUpdate("update account set name = '" + newRecord + "' where id =  " + id + " ");
            } else if (edit == 2) {
                stmt.executeUpdate("update account set loginPW = '" + newRecord + "' where id =  " + id + " ");
            }
            System.out.println("Acccount updated");
        } catch (Exception e) {
            System.err.println("DATABASE UPDATE ERROR: " + e.toString());
        }
    }

    public ArrayList<Account> deleteAccount(int id) {
        ArrayList<Account> result = new ArrayList();
        try {
            Statement stmt = con.createStatement();
            stmt.executeUpdate("delete from account where id = " + id + "");
            result = retrieveAccounts();
            System.out.println("Account deleted");
        } catch (Exception e) {
            System.err.println("DATABASE QUERY ERROR: " + e.toString());
        }
        return result;
    }

    /*------------ Account E database ------------*/

 /*------------ Notification S database ------------*/
    public ArrayList<Notification> getNotifications() {
        ArrayList<Notification> result = new ArrayList();

        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select * from notification where user_ID = " + Account.Login_ID + " ");
            while (rs.next()) {
                result.add(new Notification(rs.getInt("notID"), rs.getString("type"), rs.getString("notDesc")));
            }
        } catch (Exception e) {
            System.err.println("DATABASE QUERY ERROR: " + e.toString());
        }
        return result;
    }

    public ArrayList<Notification> deleteNotifications(ArrayList<Notification> not) {
        try {
            Statement stmt = con.createStatement();
            stmt.executeUpdate("delete from notification where user_ID = " + Account.Login_ID + "");
            System.out.println("Notifications deleted");
            not.clear();
        } catch (Exception e) {
            System.err.println("DATABASE DELETION ERROR: " + e.toString());
        }
        return not;
    }

    public void createNotification(int prop_ID, int user_ID, String type, String desc) {
        try {
            Statement sql = con.createStatement();
            sql.executeUpdate("insert into notification(reciever_ID,p_ID,sender_ID,type,notDesc) "
                    + "values(" + user_ID + "," + prop_ID + "," + Account.Login_ID + ",'" + type + "','" + desc + "')");
        } catch (Exception e) {
            System.err.println("DATABASE INSERTION ERROR: " + e.toString());
        }
    }

    /*------------ Notification E database ------------*/

 /*------------ Property S database ------------*/
    public ArrayList<Property> retrieveProperty() {
        ArrayList<Property> result = new ArrayList();
        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select * from property");
            while (rs.next()) {
                //int propID, int userId, String name, String description, String type, String location, double price, int maxGuests, String PavailableFrom, String PavailableTo, String state
                result.add(new Property(rs.getInt("propID"), rs.getInt("user_ID"), rs.getString("pname"),
                        rs.getString("pdesc"), rs.getString("type"), rs.getString("location"), rs.getDouble("price"),
                        rs.getInt("maxGuests"), rs.getString("PavailableFrom"), rs.getString("PavailableTo"), rs.getString("state")));
            }
        } catch (Exception e) {
            System.err.println("DATABASE QUERY ERROR: " + e.toString());
        }
        return result;

    }

    public void addProperty(Property p) {
        try {
            Statement stmt = con.createStatement();

            String q1 = ("insert into property(user_ID, pname, pdesc, type, location,price,maxGuests,PavailableFrom,PavailableTo,visitTimes) "
                    + "values('" + Account.Login_ID + "'  , '" + p.getName() + "'  , '" + p.getDescription() + "'  , '" + p.getType() + "'  , '" + p.getLocation() + "'  , '"
                    + p.getPrice() + "'  ,'" + p.getMaxGuests() + "'  , '" + p.getPavailableFrom() + "'  ,'" + p.getPavailableTo() + "'  ,'" + p.getVisits() + "')");

            stmt.executeUpdate(q1);
            System.out.println("property added to pending list!");
        } catch (Exception e) {
            System.err.println("DATABASE INSERTION ERROR: " + e.toString());
        }
    }

    public ResultSet viewProperty(int id) {
        Property.viewedProp = id;
        ResultSet rs = null;
        try {
            Statement stmt = con.createStatement();
            rs = stmt.executeQuery("Select propID AS 'ID',pname AS 'Property Name',pdesc AS 'Description',type,location,price,maxGuests AS 'Max guests',PavailableFrom,PavailableTo,visitTimes from property where propID = '" + id + "'");
        } catch (Exception e) {
            System.err.println("DATABASE QUERY ERROR: " + e.toString());
        }
        return rs;
    }

    public void viewOwnedProperties(int id) {
        try {
            Statement stmt = con.createStatement();
            String q1 = "Select* from property where user_ID = '" + id + "' ";
            ResultSet rs = stmt.executeQuery(q1);
            if (rs.next()) {
                System.out.println("Owner ID: " + rs.getInt(1));
                System.out.println("Property ID: " + rs.getInt(2));
                System.out.println("Name: " + rs.getString(3));
                System.out.println("Description: " + rs.getString(4));
                System.out.println("Type: " + rs.getString(5));
                System.out.println("Location: " + rs.getString(6));
                System.out.println("Rent per day or Buy price: " + rs.getDouble(7));
                System.out.println("Max Guests: " + rs.getInt(8));
                System.out.println("Availability: " + rs.getString(9));

            } else {
                System.out.println("There are no owned properties!");
            }
        } catch (Exception e) {
            System.err.println("DATABASE INSERTION ERROR: " + e.toString());
        }
    }

    public boolean CheckAvailableProperty(String name, String checkIn, String checkOut) {
        try {
            Statement stmt = con.createStatement();
            String q1 = "Select * from property where  pname = '" + name + "'";
            ResultSet r = stmt.executeQuery(q1);

            if (r.next()) {
                String datef = r.getString(11);
                String dateto = r.getString(12);

                char[] cin = checkIn.toCharArray();
                char[] cout = checkOut.toCharArray();
                char[] df = datef.toCharArray();
                char[] dto = dateto.toCharArray();

                String id1 = Character.toString(cin[0]);
                String id2 = Character.toString(cin[1]);
                String im1 = Character.toString(cin[3]);
                String im2 = Character.toString(cin[4]);
                String iy1 = Character.toString(cin[6]);
                String iy2 = Character.toString(cin[7]);
                String iy3 = Character.toString(cin[8]);
                String iy4 = Character.toString(cin[9]);

                String od1 = Character.toString(cout[0]);
                String od2 = Character.toString(cout[1]);
                String om1 = Character.toString(cout[3]);
                String om2 = Character.toString(cout[4]);
                String oy1 = Character.toString(cout[6]);
                String oy2 = Character.toString(cout[7]);
                String oy3 = Character.toString(cout[8]);
                String oy4 = Character.toString(cout[9]);

                String df1 = Character.toString(df[0]);
                String df2 = Character.toString(df[1]);
                String mf1 = Character.toString(df[3]);
                String mf2 = Character.toString(df[4]);
                String yf1 = Character.toString(df[6]);
                String yf2 = Character.toString(df[7]);
                String yf3 = Character.toString(df[8]);
                String yf4 = Character.toString(df[9]);

                String dt1 = Character.toString(dto[0]);
                String dt2 = Character.toString(dto[1]);
                String mt1 = Character.toString(dto[3]);
                String mt2 = Character.toString(dto[4]);
                String yt1 = Character.toString(dto[6]);
                String yt2 = Character.toString(dto[7]);
                String yt3 = Character.toString(dto[8]);
                String yt4 = Character.toString(dto[9]);

                String iday = id1 + id2;
                String imonth = im1 + im2;
                String iyear = iy1 + iy2 + iy3 + iy4;

                String oday = od1 + od2;
                String omonth = om1 + om2;
                String oyear = oy1 + oy2 + oy3 + oy4;

                String dayf = df1 + df2;
                String monthf = mf1 + mf2;
                String yearf = yf1 + yf2 + yf3 + yf4;

                String dayt = dt1 + dt2;
                String montht = mt1 + mt2;
                String yeart = yt1 + yt2 + yt3 + yt4;

                int iday1 = Integer.parseInt(iday);
                int imonth1 = Integer.parseInt(imonth);
                int iyear1 = Integer.parseInt(iyear);
                int oday1 = Integer.parseInt(oday);
                int omonth1 = Integer.parseInt(omonth);
                int oyear1 = Integer.parseInt(oyear);
                int dayf1 = Integer.parseInt(dayf);
                int monthf1 = Integer.parseInt(monthf);
                int yearf1 = Integer.parseInt(yearf);
                int dayt1 = Integer.parseInt(dayt);
                int montht1 = Integer.parseInt(montht);
                int yeart1 = Integer.parseInt(yeart);

                if (yearf1 <= iyear1 && yeart1 >= oyear1) {
                    if (monthf1 <= imonth1 && montht1 >= omonth1) {
                        if (dayf1 < iday1 && dayt1 >= oday1) {
                            System.out.println("This property '" + r.getString(3) + "' is Available!");
                            return true;
                        } else {
                            System.out.println("unavailable days");
                            return false;
                        }
                    } else {
                        System.out.println("unavailable months");
                        return false;
                    }
                } else {
                    System.out.println("unavailable year");
                    return false;
                }
            } else {
                System.out.println("This property does not exist!");
                return false;
            }

        } catch (Exception e) {
            System.err.println("DATABASE INSERTION ERROR: " + e.toString());
        }
        return false;
    }

    public void buyProperty(String name, int userID) {
        try {

            Statement stmt = con.createStatement();
            String q = "select propID,type,price,PavailableFrom from property where pname ='" + name + "' ";
            ResultSet r = stmt.executeQuery(q);

            Landlord landlord = new Landlord();
            Payment pay = new Payment();

            if (r.next()) {
                int id = r.getInt(1);
                String tt = "Buy";
                String ptype = r.getString(2);
                double price = r.getDouble(3);
                String av = r.getString(4);

                String q1 = "insert into Booking(user_ID, propID, type) values('" + userID + "', '" + id + "', '" + tt + "')";
                String q3 = "select * from property where type = '" + tt + "' AND type = '" + ptype + "'";
                //get userid from users table when done to make sure user exixsts. 
                ResultSet rs = stmt.executeQuery(q3);
                if (rs.next()) {
                    if (av.equalsIgnoreCase("NA")) {
                        System.out.println("Property not available for buying!!");
                        JOptionPane.showMessageDialog(null, "Property isn't available for Buyin!");
                    } else {
                        stmt.executeUpdate(q1);
                        System.out.println("Please proceed to check out, Current total = " + price + "!! ");
                        JOptionPane.showMessageDialog(null, "Please continue to checkout!");
                        System.out.print("choose 1 for cash or 2 for online payment: ");
                        while (true) {
                            int method = in.nextInt();
                            if (method == 1) {
                                pay.makePayment(price, false);
                                landlord.notifyLandlord(id, Account.Login_ID, "buy");
                                break;
                            } else if (method == 2) {
                                pay.makePayment(price, true);
                                landlord.notifyLandlord(id, Account.Login_ID, "rent");
                                break;
                            } else {
                                System.out.println("try again");
                            }
                        }
                        updateAvailability(id, "NA", "NA");
                    }
                } else {
                    System.out.println("Incorrect Data!");
                }
            } else {
                System.out.println("This property does not exist!");
            }
        } catch (Exception e) {
            System.err.println("DATABASE INSERTION ERROR: " + e.toString());
        }
    }

    public void rentProperty(String name, Booking b) {
        try {
            Payment pay = new Payment();
            Landlord landlord = new Landlord();

            Statement stmt = con.createStatement();
            String q = "select propID,maxGuests,PavailableFrom,PavailableTo,type,price from property where pname ='" + name + "' ";
            ResultSet r = stmt.executeQuery(q);
            String tt = "Rent";

            if (r.next()) {
                int id = r.getInt(1);
                int maxG = r.getInt(2);
                String datef = r.getString(3);
                String dateto = r.getString(4);
                String ptype = r.getString(5);
                double price = r.getDouble(6);

                char[] df = datef.toCharArray();
                char[] dto = dateto.toCharArray();
                char[] cin = b.getCheckIn().toCharArray();
                char[] cout = b.getCheckOut().toCharArray();

                String id1 = Character.toString(cin[0]);
                String id2 = Character.toString(cin[1]);
                String im1 = Character.toString(cin[3]);
                String im2 = Character.toString(cin[4]);
                String iy1 = Character.toString(cin[6]);
                String iy2 = Character.toString(cin[7]);
                String iy3 = Character.toString(cin[8]);
                String iy4 = Character.toString(cin[9]);

                String od1 = Character.toString(cout[0]);
                String od2 = Character.toString(cout[1]);
                String om1 = Character.toString(cout[3]);
                String om2 = Character.toString(cout[4]);
                String oy1 = Character.toString(cout[6]);
                String oy2 = Character.toString(cout[7]);
                String oy3 = Character.toString(cout[8]);
                String oy4 = Character.toString(cout[9]);

                String df1 = Character.toString(df[0]);
                String df2 = Character.toString(df[1]);
                String mf1 = Character.toString(df[3]);
                String mf2 = Character.toString(df[4]);
                String yf1 = Character.toString(df[6]);
                String yf2 = Character.toString(df[7]);
                String yf3 = Character.toString(df[8]);
                String yf4 = Character.toString(df[9]);

                String dt1 = Character.toString(dto[0]);
                String dt2 = Character.toString(dto[1]);
                String mt1 = Character.toString(dto[3]);
                String mt2 = Character.toString(dto[4]);
                String yt1 = Character.toString(dto[6]);
                String yt2 = Character.toString(dto[7]);
                String yt3 = Character.toString(dto[8]);
                String yt4 = Character.toString(dto[9]);

                String iday = id1 + id2;
                String imonth = im1 + im2;
                String iyear = iy1 + iy2 + iy3 + iy4;

                String oday = od1 + od2;
                String omonth = om1 + om2;
                String oyear = oy1 + oy2 + oy3 + oy4;

                String dayf = df1 + df2;
                String monthf = mf1 + mf2;
                String yearf = yf1 + yf2 + yf3 + yf4;

                String dayt = dt1 + dt2;
                String montht = mt1 + mt2;
                String yeart = yt1 + yt2 + yt3 + yt4;

                int iday1 = Integer.parseInt(iday);
                int imonth1 = Integer.parseInt(imonth);
                int iyear1 = Integer.parseInt(iyear);
                int oday1 = Integer.parseInt(oday);
                int omonth1 = Integer.parseInt(omonth);
                int oyear1 = Integer.parseInt(oyear);
                int dayf1 = Integer.parseInt(dayf);
                int monthf1 = Integer.parseInt(monthf);
                int yearf1 = Integer.parseInt(yearf);
                int dayt1 = Integer.parseInt(dayt);
                int montht1 = Integer.parseInt(montht);
                int yeart1 = Integer.parseInt(yeart);

                if (yearf1 <= iyear1 && yeart1 >= oyear1) {
                    if (monthf1 <= imonth1 && montht1 >= omonth1) {
                        if (dayf1 < iday1 && dayt1 >= oday1) {

                            String q1 = "insert into Booking(user_ID, propID, checkIn, checkOut, type, guestNo, duration) values('" + b.getUserID() + "', '" + id + "',"
                                    + " '" + b.getCheckIn() + "', '" + b.getCheckOut() + "'," + " '" + tt + "','" + b.getGuestsNo() + "', '" + b.getDuration() + "')";
                            String q3 = "select * from property where '" + b.getGuestsNo() + "' <= '" + maxG + "'AND  type = '" + tt + "' AND type = '" + ptype + "'";
                            ResultSet rs = stmt.executeQuery(q3);

                            double total = b.getDuration() * price;

                            if (rs.next()) {
                                stmt.executeUpdate(q1);
                                System.out.println("Please proceed to checkout, Current total = " + total + "!!");
                                while (true) {
                                    int method = in.nextInt();
                                    if (method == 1) {
                                        pay.makePayment(total, false);
                                        landlord.notifyLandlord(id, Account.Login_ID, "rent");
                                        break;
                                    } else if (method == 2) {
                                        pay.makePayment(total, true);
                                        landlord.notifyLandlord(id, Account.Login_ID, "rent");
                                        break;
                                    } else {
                                        System.out.println("try again");
                                    }
                                }
                                updateAvailability(id, b.getCheckOut(), "31-12-2020");
                            } else {
                                System.out.println("Incorrect Data!");
                            }
                        } else {
                            System.out.println("unavailable days");
                        }
                    } else {
                        System.out.println("unavailable months");
                    }
                } else {
                    System.out.println("unavailable year");
                }
            } else {
                System.out.println("This property does not exist!");
            }
        } catch (Exception e) {
            System.err.println("DATABASE INSERTION ERROR: " + e.toString());
        }
    }

    public void shareProperty(String name, Booking b) {
        try {
            Statement stmt = con.createStatement();
            String q = "select propID,maxGuests,PavailableFrom, PavailableTo,type,price from property where pname ='" + name + "' ";
            ResultSet r = stmt.executeQuery(q);
            String tt = "Share";

            if (r.next()) {
                int id = r.getInt(1);
                int maxG = r.getInt(2);
                String datef = r.getString(3);
                String dateto = r.getString(4);
                String ptype = r.getString(5);
                double price = r.getDouble(6);

                char[] df = datef.toCharArray();
                char[] dto = dateto.toCharArray();
                char[] cin = b.getCheckIn().toCharArray();
                char[] cout = b.getCheckOut().toCharArray();

                String id1 = Character.toString(cin[0]);
                String id2 = Character.toString(cin[1]);
                String im1 = Character.toString(cin[3]);
                String im2 = Character.toString(cin[4]);
                String iy1 = Character.toString(cin[6]);
                String iy2 = Character.toString(cin[7]);
                String iy3 = Character.toString(cin[8]);
                String iy4 = Character.toString(cin[9]);

                String od1 = Character.toString(cout[0]);
                String od2 = Character.toString(cout[1]);
                String om1 = Character.toString(cout[3]);
                String om2 = Character.toString(cout[4]);
                String oy1 = Character.toString(cout[6]);
                String oy2 = Character.toString(cout[7]);
                String oy3 = Character.toString(cout[8]);
                String oy4 = Character.toString(cout[9]);

                String df1 = Character.toString(df[0]);
                String df2 = Character.toString(df[1]);
                String mf1 = Character.toString(df[3]);
                String mf2 = Character.toString(df[4]);
                String yf1 = Character.toString(df[6]);
                String yf2 = Character.toString(df[7]);
                String yf3 = Character.toString(df[8]);
                String yf4 = Character.toString(df[9]);

                String dt1 = Character.toString(dto[0]);
                String dt2 = Character.toString(dto[1]);
                String mt1 = Character.toString(dto[3]);
                String mt2 = Character.toString(dto[4]);
                String yt1 = Character.toString(dto[6]);
                String yt2 = Character.toString(dto[7]);
                String yt3 = Character.toString(dto[8]);
                String yt4 = Character.toString(dto[9]);

                String iday = id1 + id2;
                String imonth = im1 + im2;
                String iyear = iy1 + iy2 + iy3 + iy4;

                String oday = od1 + od2;
                String omonth = om1 + om2;
                String oyear = oy1 + oy2 + oy3 + oy4;

                String dayf = df1 + df2;
                String monthf = mf1 + mf2;
                String yearf = yf1 + yf2 + yf3 + yf4;

                String dayt = dt1 + dt2;
                String montht = mt1 + mt2;
                String yeart = yt1 + yt2 + yt3 + yt4;

                int iday1 = Integer.parseInt(iday);
                int imonth1 = Integer.parseInt(imonth);
                int iyear1 = Integer.parseInt(iyear);
                int oday1 = Integer.parseInt(oday);
                int omonth1 = Integer.parseInt(omonth);
                int oyear1 = Integer.parseInt(oyear);
                int dayf1 = Integer.parseInt(dayf);
                int monthf1 = Integer.parseInt(monthf);
                int yearf1 = Integer.parseInt(yearf);
                int dayt1 = Integer.parseInt(dayt);
                int montht1 = Integer.parseInt(montht);
                int yeart1 = Integer.parseInt(yeart);

                if (yearf1 <= iyear1 && yeart1 >= oyear1) {
                    if (monthf1 <= imonth1 && montht1 >= omonth1) {
                        if (dayf1 < iday1 && dayt1 >= oday1) {

                            String q1 = "insert into Booking(user_ID, propID, checkIn, checkOut, type, guestNo, duration) values('" + b.getUserID() + "', '" + id + "',"
                                    + " '" + b.getCheckIn() + "', '" + b.getCheckOut() + "'," + " '" + tt + "','" + b.getGuestsNo() + "', '" + b.getDuration() + "')";
                            String q3 = "select * from property where '" + b.getGuestsNo() + "' <= '" + maxG + "'AND  type = '" + tt + "' AND type = '" + ptype + "'";
                            ResultSet rs = stmt.executeQuery(q3);

                            double total = b.getDuration() * price;
                            if (rs.next()) {
                                stmt.executeUpdate(q1);
                                System.out.println("Please proceed to checkout, Current total = " + total + "!!");
                                updateAvailability(id, b.getCheckOut(), "31-12-2020");
                            } else {
                                System.out.println("Incorrect Data!");
                            }
                        } else {
                            System.out.println("unavailable days");
                        }
                    } else {
                        System.out.println("unavailable months");
                    }
                } else {
                    System.out.println("unavailable year");
                }
            } else {
                System.out.println("This property does not exist!");

            }
        } catch (Exception e) {
            System.err.println("DATABASE INSERTION ERROR: " + e.toString());
        }
    }

    public void deleteProperty(int pid) {
        try {
            Statement stmt = con.createStatement();
            stmt.executeUpdate("delete from property where propID = '" + pid + "'");
            System.out.println("property deleted");
        } catch (Exception e) {
            System.err.println("DATABASE DELETION ERROR: " + e.toString());
        }
    }

    public void editProperty(int id, String str, String n) {
        try {
            Statement stmt = con.createStatement();
            if (str.equalsIgnoreCase("name")) {
                stmt.executeUpdate("update property set pname = '" + n + "'" + "where propID = " + id);
            } else if (str.equalsIgnoreCase("description")) {
                stmt.executeUpdate("update property set pdesc = '" + n + "'" + "where propID = " + id);
            } else if (str.equalsIgnoreCase("type")) {
                stmt.executeUpdate("update property set type = '" + n + "'" + "where propID = " + id);
            } else if (str.equalsIgnoreCase("location")) {
                stmt.executeUpdate("update property set location = '" + n + "'" + "where propID = " + id);
            } else if (str.equalsIgnoreCase("price")) {
                float f = Float.parseFloat(n);
                stmt.executeUpdate("update property set price = " + f + "where propID = " + id);
            } else if (str.equalsIgnoreCase("maxGuests")) {
                float x = Float.parseFloat(n);
                stmt.executeUpdate("update property set maxGuests = " + x + "where propID = " + id);
            } else if (str.equalsIgnoreCase("PavialbleFrom")) {
                stmt.executeUpdate("update property set PavailableFrom = '" + n + "'" + "where propID = " + id);
            } else if (str.equalsIgnoreCase("PavialbleTo")) {
                stmt.executeUpdate("update property set PavailableTo = '" + n + "'" + "where propID = " + id);
            } else {
                System.out.print("no such variable");
            }
        } catch (Exception e) {
            System.err.println("DATABASE UPDATE ERROR: " + e.toString());
        }
    }

    public ResultSet viewPendingPropList() {
        ResultSet rs = null;
        try {
            Statement stmt = con.createStatement();
            stmt.executeQuery("select * from property where state = 'Pending'");
            //add aliaces
        } catch (Exception e) {
            System.err.println("DATABASE QUERY ERROR: " + e.toString());
        }
        return rs;
    }

    public ResultSet searchProperty(String location) {
        ResultSet rs = null;
        try {
            Statement stmt = con.createStatement();
            rs = stmt.executeQuery("select user_ID AS 'Owner',propID AS'ID',pname AS 'Property Name',pdesc AS 'Description',type,location,price,maxGuests,PavailableFrom AS 'Available from',PavailableTo AS 'Available to',visitTimes from property where location = '" + location + "'");
        } catch (Exception e) {
            System.err.println("DATABASE QUERY ERROR: " + e.toString());
        }
        return rs;
    }

    public Property chooseProperty(int pid) {
        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select * from property where propID = " + pid);
            if (rs.first()) {
                return new Property(rs.getInt("propID"), rs.getInt("user_ID"), rs.getString("pname"), rs.getString("pdesc"),
                        rs.getString("type"), rs.getString("location"), rs.getFloat("price"), rs.getInt("maxGuests"), rs.getString("state"),
                        rs.getString("PavailableFrom"), rs.getString("PavailableTo"));
            }
        } catch (Exception e) {
            System.err.println("DATABASE QUERY ERROR: " + e.toString());
        }
        return null;
    }

    public void approveProperty(String type, int Pid) {
        try {
            Statement stmt = con.createStatement();
            if (type.equalsIgnoreCase("Admin")) {
                stmt.executeUpdate("update property set state = 'Available' " + "where propID = " + Pid);
            }
        } catch (Exception e) {
            System.err.println("DATABASE UPDATE ERROR: " + e.toString());
        }
    }

    public void disapproveProperty(String type, int Pid) {
        try {
            if (type.equalsIgnoreCase("Admin")) {
                deleteProperty(Pid);
                System.out.println("Property is disapproved, hence deleted.");

            }
        } catch (Exception e) {
            System.err.println("DATABASE UPDATE ERROR: " + e.toString());
        }
    }

    public void updateAvailability(int Pid, String fromDate, String toDate) {
        try {
            Statement stmt = con.createStatement();
            stmt.executeUpdate("update property set PavailableFrom ='" + fromDate + "', PavailableTo = '" + toDate + "' " + "where propID = " + Pid);

        } catch (Exception e) {
            System.err.println("DATABASE UPDATE ERROR: " + e.toString());
        }
    }

    /*------------ Property E database ------------*/

 /*------------ Booking S database ------------*/
    public Booking getBookingInfo(int Bid) {
        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select * from booking where Bid = " + Bid);
            if (rs.first()) {
                return new Booking(rs.getInt("Bid"), rs.getInt("user_ID"), rs.getInt("propID"), rs.getString("checkIn"), rs.getString("checkOut"), rs.getString("type"), rs.getInt("guestNo"), rs.getString("state"), rs.getInt("duration"));
            }
        } catch (Exception e) {
            System.err.println("DATABASE QUERY ERROR: " + e.toString());
        }
        return null;
    }

    public ResultSet viewRentHistory(int userID) {
        ResultSet rs = null;
        try {
            Statement stmt = con.createStatement();
            rs = stmt.executeQuery("select * from booking where user_ID = " + userID);

        } catch (Exception e) {
            System.err.println("DATABASE QUERY ERROR: " + e.toString());
        }
        return rs;
    }

    public void editRent(int Bid, String Cin, String Cout, int guestsNo) {
        try {
            Statement stmt = con.createStatement();

            char[] cin = Cin.toCharArray();
            char[] cout = Cout.toCharArray();

            String id1 = Character.toString(cin[0]);
            String id2 = Character.toString(cin[1]);
            String im1 = Character.toString(cin[3]);
            String im2 = Character.toString(cin[4]);
            String iy1 = Character.toString(cin[6]);
            String iy2 = Character.toString(cin[7]);
            String iy3 = Character.toString(cin[8]);
            String iy4 = Character.toString(cin[9]);

            String od1 = Character.toString(cout[0]);
            String od2 = Character.toString(cout[1]);
            String om1 = Character.toString(cout[3]);
            String om2 = Character.toString(cout[4]);
            String oy1 = Character.toString(cout[6]);
            String oy2 = Character.toString(cout[7]);
            String oy3 = Character.toString(cout[8]);
            String oy4 = Character.toString(cout[9]);

            String iday = id1 + id2;
            String imonth = im1 + im2;
            String iyear = iy1 + iy2 + iy3 + iy4;

            String oday = od1 + od2;
            String omonth = om1 + om2;
            String oyear = oy1 + oy2 + oy3 + oy4;

            int iday1 = Integer.parseInt(iday);
            int imonth1 = Integer.parseInt(imonth);
            int iyear1 = Integer.parseInt(iyear);
            int oday1 = Integer.parseInt(oday);
            int omonth1 = Integer.parseInt(omonth);
            int oyear1 = Integer.parseInt(oyear);

            if (oyear1 >= iyear1) {
                if (omonth1 >= imonth1) {
                    if (oday1 > iday1) {

                        stmt.executeUpdate("update booking set checkIn = '" + Cin + "', checkOut = '" + Cout + "', guestNo = '" + guestsNo + "' where Bid = '" + Bid + "'");
                        System.out.println("Booking has been updated Successfully!");
                    } else {
                        System.out.println("your check out day must be after your check in day");
                    }
                } else {
                    System.out.println("your check out month must be after your check in month");
                }
            } else {
                System.out.println("your check out year must be after your check in year");
            }

        } catch (Exception e) {
            System.err.println("DATABASE UPDATE ERROR: " + e.toString());
        }

    }

    /*------------ Booking E database ------------*/

 /*------------ Payment & bankaccount S database ------------*/
    public void updateBalance(double balance) {

        try {
            Statement stmt = con.createStatement();
            stmt.executeUpdate("update bankaccount set balance = " + balance + " where u_ID =  " + (Account.Login_ID) + " ");

            System.out.println("Bank account updated");
        } catch (Exception e) {
            System.err.println("DATABASE UPDATE ERROR: " + e.toString());
        }
    }

    public void savePayment(double amount, boolean method) {
        try {
            DateFormat df = new SimpleDateFormat("dd/MM/yy");
            Date dateobj = new Date();
            if (method == true) {
                String type = "online payment";
                Statement sql = con.createStatement();
                sql.executeUpdate("insert into payment(user_ID,bankacc_ID,date,amount,method) "
                        + "values(" + Account.Login_ID + "," + Account.Login_ID + ",'" + df.format(dateobj) + "'," + amount + ",'" + type + "')");
            } else if (method == false) {
                String type = "cash";
                Statement sql = con.createStatement();
                sql.executeUpdate("insert into payment(user_ID,date,amount,method) "
                        + "values(" + Account.Login_ID + ",'" + df.format(dateobj) + "'," + amount + ",'" + type + "')");
            }
        } catch (Exception e) {
            System.err.println("DATABASE INSERTION ERROR: " + e.toString());
        }
    }

    /*------------ Payment & bankaccount E database ------------*/

 /*------------ Review S database ------------*/
    public void giveReview(ArrayList<Review> r) {
        try {
            Statement stmt = con.createStatement();
            for (int i = 0; i < r.size(); i++) {
                stmt.executeUpdate("insert into review values(" + r.get(i).id + ", " + Account.Login_ID + ", " + Property.viewedProp + ", '" + r.get(i).description + "', " + r.get(i).rating + ")");
                System.out.println("Review added");
            }
        } catch (Exception e) {
            System.err.println("DATABASE INSERTION ERROR: " + e.toString());
        }
    }

    public ArrayList<Review> getReviews() {
        ArrayList<Review> result = new ArrayList();
        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select * from review");
            while (rs.next()) {
                result.add(new Review(rs.getInt("reviewID"), rs.getString("reviewDesc"), rs.getDouble("rating")));
            }
        } catch (Exception e) {
            System.err.println("DATABASE QUERY ERROR: " + e.toString());
        }
        return result;
    }

    /*------------ Review E database ------------*/

 /*------------ Report S database ------------*/
    public void makeReport(ArrayList<Report> r) {
        try {
            Statement stmt = con.createStatement();
            for (int i = 0; i < r.size(); i++) {
                stmt.executeUpdate("insert into report values(" + r.get(i).id + ", " + Account.Login_ID + ", '" + r.get(i).description + "', '" + r.get(i).title + "', " + r.get(i).date + ")");
                System.out.println("Report added");
            }
        } catch (Exception e) {
            System.err.println("DATABASE INSERTION ERROR: " + e.toString());
        }
    }
    /*------------ Report E database ------------*/

}
