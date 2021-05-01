package SE.interfaces;

public interface UserReadOnly {

    void editAccount();

    void createAccount(String loginMail, String loginPW, String type, String name, String creditCardNo, String phone, double balance);

    void viewAccount();

    boolean login(String loginMail, String loginPW);

    void sendVerificationCode();

    boolean enterVerificationCode(int code);
}
