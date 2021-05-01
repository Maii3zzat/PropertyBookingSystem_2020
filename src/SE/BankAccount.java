package SE;

public class BankAccount {

    String mail;
    double balance;
    int id;
    int user_id;
    DB db = new DB();

    Account acc = new Account();

    public BankAccount(String mail, double balance, int id, int user_id) {
        this.mail = mail;
        this.balance = balance;
        this.id = id;
        this.user_id = user_id;
    }

    public BankAccount() {
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void CheckCCInfo(String mail, double amount, int index) {
        acc.acc = db.retrieveAccounts();
        acc.banAcc = db.retrieveBankAccounts();

        CheckAccountBalance(amount, index);
    }

    public void UpdateBalance(double amount, int index) {
        
        acc.acc = db.retrieveAccounts();
        acc.banAcc = db.retrieveBankAccounts();

        double newBal = acc.banAcc.get(index).getBalance() - amount;
        db.updateBalance(newBal);
        acc.banAcc.get(index).setBalance(newBal);

//        System.out.println(acc.banAcc.get(index).getBalance());
//        System.out.println(amount);
//        System.out.println(newBal);
        db.savePayment(amount, true);
        System.out.println("payment complete");

    }

    public void CheckAccountBalance(double amount, int index) {
        if (amount <= acc.banAcc.get(index).getBalance()) {
            UpdateBalance(amount, index);
        } else {
            System.out.println("Not enough balance");
            return;
        }

    }
}
