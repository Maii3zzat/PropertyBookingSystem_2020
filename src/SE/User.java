package SE;

import SE.interfaces.ReviewReadOnly;
import SE.interfaces.UserReadOnly;

public class User implements ReviewReadOnly {

    Account user_acc = new Account();
    DB db = new DB();
    Notification notf = new Notification();

    private UserReadOnly URO;
    
    public User(UserReadOnly URO) {
        this.URO = URO;
    }

    public User() {

    }
    public int searchUser(int id) {
        user_acc.acc = db.retrieveAccounts();
        int index = -1;
        for (int i = 0; i < user_acc.acc.size(); i++) {
            if (user_acc.acc.get(i).getId() == id) {
                index = i;
            }
        }

        if (index == -1) {
            System.out.println("not found");
            return 0;
        } else {
            System.out.println("ID: " + user_acc.acc.get(index).getId());
            System.out.println("Name: " + user_acc.acc.get(index).getName());
            System.out.println("Email: " + user_acc.acc.get(index).getLoginMail());
            System.out.println("Password: " + user_acc.acc.get(index).getLoginPW());
            System.out.println("type: " + user_acc.acc.get(index).getType());
            return index;
        }

    }

    
    void editAccount() {
        URO.editAccount();
    }

    void createAccount(String loginMail, String loginPW, String type, String name,
            String creditCardNo, String phone, double balance) {
        URO.createAccount(loginMail, loginPW, type, name, creditCardNo, phone, balance);
    }

    void viewAccount() {
        URO.viewAccount();
        
    }

    boolean login(String loginMail, String loginPW) {
        return URO.login(loginMail, loginPW);
    }

    void sendVerificationCode() {
        URO.sendVerificationCode();
    }

    boolean enterVerificationCode(int code) {
        return URO.enterVerificationCode(code);
    }

    @Override
    public void viewReviews() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void rate(float rate, String msg) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
