package SE;

import SE.interfaces.AdminReadOnly;
import SE.interfaces.UserReadOnly;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Account implements AdminReadOnly, UserReadOnly {

    Scanner input = new Scanner(System.in);

    int id;
    String name;
    String loginMail;
    String loginPW;
    String type;
    String creditCardNo;
    String phone;

    static int Login_ID;
    static String access_type;

    public static int verificationCode;
    static int one_admin = 1;

    ArrayList<Account> acc = new ArrayList<>();
    ArrayList<BankAccount> banAcc = new ArrayList<>();

    DB db = new DB();

    public Account() {
    }

    public Account(int id, String name, String loginMail, String loginPW, String type, String creditCardNo, String phone) {
        this.id = id;
        this.name = name;
        this.loginMail = loginMail;
        this.loginPW = loginPW;
        this.type = type;
        this.creditCardNo = creditCardNo;
        this.phone = phone;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLoginMail() {
        return loginMail;
    }

    public void setLoginMail(String loginMail) {
        this.loginMail = loginMail;
    }

    public String getLoginPW() {
        return loginPW;
    }

    public void setLoginPW(String loginPW) {
        this.loginPW = loginPW;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCreditCardNo() {
        return creditCardNo;
    }

    public void setCreditCardNo(String creditCardNo) {
        this.creditCardNo = creditCardNo;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public ArrayList<Account> getAcc() {
        return acc;
    }

    /*  */
    public void setAcc(ArrayList<Account> acc) {
        this.acc = acc;
    }

    @Override
    public void editAccount() {
        acc = db.retrieveAccounts();
        int choose = -1;
        int index = -1;
        String newRecord = "";
        for (int i = 0; i < acc.size(); i++) {
            if (acc.get(i).getId() == Login_ID) {
                index = i;
            }
        }
        if (index == -1) {
            System.out.println("not found");
        } else {
            System.out.println("enter 1 to change name or 2 to change password");
            System.out.println("Name: " + acc.get(index).getName());
            System.out.println("Password: " + acc.get(index).getLoginPW());
            choose = input.nextInt();
            if (choose == 1) {
                input.nextLine();
                System.out.println("Enter new name");
                newRecord = input.nextLine();
                db.updateAccount(acc.get(index).id, newRecord, 1);
            } else if (choose == 2) {
                input.nextLine();
                System.out.println("Enter new password");
                newRecord = input.nextLine();
                db.updateAccount(acc.get(index).id, newRecord, 2);

            }
        }
    }

    @Override
    public void createAccount(String loginMail, String loginPW, String type, String name, String creditCardNo, String phone, double balance) {

        acc = db.retrieveAccounts();

        int code = 0;
        int numOfAttempts = 3;

        Account new_acc = new Account();
        BankAccount new_BanAcc = new BankAccount();

        for (int i = 0; i < acc.size(); i++) {
            if (acc.get(i).type.equals("Admin")) {
                System.err.println("only 1 admin can be created!");
                return;
            }
        }
        
        boolean Unique = false;
        try {
            if (!acc.isEmpty()) {
                for (int i = 0; i < acc.size(); i++) {
                    if (acc.get(i).loginMail.equals(loginMail)) {
                        Unique = false;
                        break;
                    } else {
                        Unique = true;
                    }
                }

                if (Unique) {

                    new_acc.setLoginMail(loginMail);
                    new_acc.setLoginPW(loginPW);
                    new_acc.setType(type);
                    new_acc.setName(name);
                    new_acc.setCreditCardNo(creditCardNo);
                    new_acc.setPhone(phone);

                    new_BanAcc.setMail(loginMail);
                    new_BanAcc.setBalance(balance);

                    if (type.equals("Admin")) {
                        one_admin = 1;
                    }

                    sendVerificationCode();
                    System.out.print("Enter your verification code: ");
                    while (numOfAttempts > 0) {
                        code = input.nextInt();
                        if (true) {
                            acc.add(new_acc);
                            banAcc.add(new_BanAcc);
                            db.insertNewAccount(acc);
                            db.insertNewBankAccount(acc, banAcc);
                            break;
                        } else {
                            numOfAttempts--;
                            System.out.println("please try again!");
                        }
                    }
                } else if (Unique == false) {
                    System.err.println("this email is already registered");
                }
            } else if (acc.isEmpty()) {

                new_acc.setLoginMail(loginMail);
                new_acc.setLoginPW(loginPW);
                new_acc.setType(type);
                new_acc.setName(name);
                new_acc.setCreditCardNo(creditCardNo);
                new_acc.setPhone(phone);

                if (type.equals("Admin")) {
                    one_admin = 1;
                }

                new_BanAcc.setMail(loginMail);
                new_BanAcc.setBalance(balance);

                sendVerificationCode();
                System.out.print("Enter your verification code: ");
                while (numOfAttempts > 0) {
                    code = input.nextInt();
                    if (true) {

                        acc.add(new_acc);
                        banAcc.add(new_BanAcc);

                        db.insertNewAccount(acc);
                        db.insertNewBankAccount(acc, banAcc);
                        break;

                    } else {
                        numOfAttempts--;
                        System.out.println("pls try again!");
                    }
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    @Override
    public void viewAccount() {
        acc = db.retrieveAccounts();
        int index = -1;
        for (int i = 0; i < acc.size(); i++) {
            if (acc.get(i).getId() == Login_ID) {
                index = i;
            }
        }

        if (index == -1) {
            System.out.println("not found");
        } else {
            System.out.println("ID: " + acc.get(index).getId());
            System.out.println("Name: " + acc.get(index).getName());
            System.out.println("Email: " + acc.get(index).getLoginMail());
            System.out.println("Password: " + acc.get(index).getLoginPW());
            System.out.println("type: " + acc.get(index).getType());

        }
    }

    @Override
    public boolean login(String loginMail, String loginPW) {
        acc = db.retrieveAccounts();
        int index = -1;
        for (int i = 0; i < acc.size(); i++) {
            if (acc.get(i).getLoginMail().equals(loginMail)) {
                index = i;
            }
        }
        if (index == -1) {
            System.out.println("Error Mail");
        } else {
            if (acc.get(index).getLoginPW().equals(loginPW)) {
                Login_ID = acc.get(index).getId();
                access_type = acc.get(index).getType();
                return true;
            } else {
                System.out.println("Error PW");
                return false;
            }
        }
        return false;
    }

    @Override
    public void banAccount(String loginMail) {
        acc = db.retrieveAccounts();
        int index = -1;
        for (int i = 0; i < acc.size(); i++) {
            if (acc.get(i).loginMail.equals(loginMail)) {
                index = i;
            }
        }
        if (index == -1) {
            System.out.println("account not found");
        } else {
            db.deleteAccount(acc.get(index).getId());
        }

    }

    @Override
    public void sendVerificationCode() {
        Random rand = new Random();
        String id = String.format("%04d", rand.nextInt(10000));
        verificationCode = Integer.parseInt(id);
        System.out.println("your verification code is: " + verificationCode);
    }

    @Override
    public boolean enterVerificationCode(int code) {
        if (verificationCode == code) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void viewProperty(int s) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void approveProperty(String type, int Pid) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void disapproveProperty(String type, int Pid) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ResultSet viewPendingPropList() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
