package SE;

import java.util.Scanner;

public class Payment {

    int paymentID;
    String date;
    float amount;
    boolean method;

    DB db = new DB();
    BankAccount ba = new BankAccount();

    public Payment() {
    }

    public Payment(int paymentID, String date, float amount, boolean method) {
        this.paymentID = paymentID;
        this.date = date;
        this.amount = amount;
        this.method = method;
    }

    public int getPaymentID() {
        return paymentID;
    }

    public void setPaymentID(int paymentID) {
        this.paymentID = paymentID;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public boolean getMethod() {
        return method;
    }

    public void setMethod(boolean method) {
        this.method = method;
    }

    public void makePayment(double amount, boolean method) {
        Account acc = new Account();

        acc.banAcc = db.retrieveBankAccounts();
        acc.acc = db.retrieveAccounts();

        int idn = -1;
        if (method == true) {
            for (int i = 0; i < acc.acc.size(); i++) {
                if (acc.acc.get(i).getId() == Account.Login_ID) {
                    idn = i;
                }
            }
            ba.CheckCCInfo(acc.acc.get(idn).getCreditCardNo(), amount, idn);
        }else {
            db.savePayment(amount,false);
            System.out.println("payment complete");
        }
    }
}
