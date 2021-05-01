package test;

import SE.Account;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class JUnitTesting {

    
    @Test
    public void testLogin() {
        Account c = new Account();
        boolean result = c.login("admin@admin.com", "admin");
        assertEquals(true, result);
    }

    @Test
    public void testCreateAccont() {
        Account c = new Account();
        boolean result = c.enterVerificationCode(Account.verificationCode);
        assertEquals(true, result);
    }
    
    
}

