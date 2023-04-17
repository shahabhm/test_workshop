import com.google.gson.Gson;
import org.example.controller.Controller;
import org.example.model.Account;
import org.example.model.Book;
import org.junit.Assert;
import org.junit.Test;

/*
        Assert.assertEquals();
        Assert.assertNotEquals();
        Assert.assertArrayEquals();
        Assert.assertTrue();
        Assert.assertFalse();
        Assert.fail(); // ?
        Assert.assertNull();
        Assert.assertNotNull();
        Assert.assertSame();
        Assert.assertNotSame();
        Assert.assertThrows();
 */

public class test {

    @Test
    public void test1(){
        Controller c = new Controller();
        final String firstName = "foe";
        final String lastName = "bar";
        final String ssn = "0681231234";
        String ans = c.createAccount(firstName, lastName, ssn);
        Assert.assertEquals(ans, "success");

    }

    @Test
    public void test2() {
        final String firstName = "";
        final String lastName = "bar";
        final String ssn = "0681231234";
        Account a = new Account(firstName, lastName, ssn, 0);
        System.out.println(a);
        System.out.println(new Gson().toJson(a));
        try {
            a.save();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
