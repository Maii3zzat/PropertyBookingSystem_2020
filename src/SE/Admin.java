package SE;

import SE.interfaces.AdminReadOnly;
import java.sql.ResultSet;

public class Admin {

    Account account = new Account();
    Property property;
    User user;

    AdminReadOnly ARO;

    public Admin(AdminReadOnly ARO) {
        this.ARO = ARO;
    }

    public Admin() {
    }

    public Admin(int i) {
        account.createAccount("admin@admin.com", "admin", "Admin", "admin", "", "", 0);
    }

    public void viewProperty(int s) {
        ARO.viewProperty(s);
    }

    public void approveProperty(String type, int Pid) {
        ARO.approveProperty(type, Pid);
    }

    public void disapproveProperty(String type, int Pid) {
        ARO.disapproveProperty(type, Pid);
    }

    void viewAccount(){
        ARO.viewAccount();
    }

    boolean login(String loginMail, String loginPW){
        return ARO.login(loginMail, loginPW);
    }

    void banAccount(String loginMail){
        ARO.banAccount(loginMail);
    }
     public ResultSet viewPendingPropList() {
        return ARO.viewPendingPropList();
    }
}
