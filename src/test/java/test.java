import org.example.controller.Controller;
import org.junit.Assert;
import org.junit.Test;

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
}
