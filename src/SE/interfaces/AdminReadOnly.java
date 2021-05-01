package SE.interfaces;

import java.sql.ResultSet;

public interface AdminReadOnly {

    public void viewProperty(int s);

    public void approveProperty(String type, int Pid);

    public void disapproveProperty(String type, int Pid);

    void viewAccount();

    boolean login(String loginMail, String loginPW);

    void banAccount(String loginMail);
    
    public ResultSet viewPendingPropList();
}
