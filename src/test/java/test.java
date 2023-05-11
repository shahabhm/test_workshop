import com.google.gson.Gson;
import net.sf.oval.constraint.Assert;
import org.example.controller.Controller;
import org.example.model.Account;
import org.example.model.DataBase;
import org.example.model.exception.AlreadySignedUpException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class test {


    @Test
    public void firstTest() {
        Account a = new Account("shahab", "hoseini", "041239841023", 0);
        Account b = new Account("shahab", "hoseini", "041239841023", 0);
        System.out.println(a.equals(b));
        Assertions.assertEquals(a, b);
    }

    @Test
    public void testAssertEquals() throws Exception {
        Controller controller = new Controller();
        controller.createBook("booke", "SCI-FI", 3);
        controller.createAccount("shahab", "shahabi", "0899889585");
        controller.borrowBook("0899889585", "booke");
        Account account = DataBase.getInstance().findAccountBySSN("0899889585");
        Assertions.assertEquals(1, account.getBorrowedBooks().size());
        controller.returnBook("0899889585", "booke");
        Assertions.assertEquals(0, account.getBorrowedBooks().size());
    }

    @Test
    public void testAssertNull() {
        String ssn = "nonExistingSSN";
        Account account = DataBase.getInstance().findAccountBySSN(ssn);
        Assertions.assertNull(account);
    }

    @Test
    public void testAssertSame() {
        String ssn = "0393999399";
        Account a = new Account("shahab", "shahabi", ssn, 0);
        try {
            a.save();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        Account b = DataBase.getInstance().findAccountBySSN(ssn);
        Assertions.assertSame(a, b);
    }

    @Test
    public void testAssertNotSame() {
        Account account = new Account("shahab", "shahabi", "0903432222", 0);
        String accountString = new Gson().toJson(account);
        Account accountFromString = new Gson().fromJson(accountString, Account.class);
        Assertions.assertEquals(account, accountFromString);
        Assertions.assertNotSame(account, accountFromString);
    }

    @Test
    public void testAssertThrow() {
        Executable codeToThrowException = new Executable() {
            @Override
            public void execute() throws Throwable {
                new Account("", "", "", 0).save();
            }
        };
        Assertions.assertThrows(Exception.class, codeToThrowException);
    }

    @Test
    public void testAssertThrowWithLambda() {
        Assertions.assertThrows(Exception.class, () -> {
            new Account("", "", "", 0).save();
        });
    }
    @Test
    public void testingWithMock() throws Exception {
        Controller c = Mockito.mock(Controller.class);
        final String firstName = "foe";
        final String lastName = "bar";
        final String ssn = "0681231234";
        c.createAccount(firstName, lastName, ssn);
        Account notActuallySavedAccount = DataBase.getInstance().findAccountBySSN(ssn);
        Assertions.assertNull(notActuallySavedAccount);
    }


    @Test
    public void testMockVerify() throws Exception {
        Controller c = Mockito.mock(Controller.class);
        final String firstName = "foe";
        final String lastName = "bar";
        final String ssn = "0681231234";
        c.createAccount(firstName, lastName, ssn);
    }

    @Test
    public void testWhenThen() throws Exception {
        Controller controller = Mockito.mock(Controller.class);
        Mockito.when(controller.createAccount("j", "k", "m")).thenReturn("success");
        String answer = controller.createAccount("j", "k", "m");
        Assertions.assertEquals("success", answer);
    }

    @Test
    public void testAnswer() throws Exception {
        Controller controller = Mockito.mock(Controller.class, (invocationOnMock) -> {
            if (invocationOnMock.getArgument(1) == "k") return "success";
            throw new AlreadySignedUpException();
        });
        Mockito.when(controller.createAccount("j", "k", "m")).thenReturn("success");
        String answer = controller.createAccount("j", "k", "m");
        Assertions.assertEquals("success", answer);
    }

    @Test
    public void test2() {
        DataBase mockedDatabase = Mockito.mock(DataBase.class);
        final String firstName = "a";
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

    @Test
    public void testSpy() throws Exception {
        Controller c = Mockito.spy(Controller.class);
        final String firstName = "foe";
        final String lastName = "bar";
        final String ssn = "0681231234";
        c.createAccount(firstName, lastName, ssn);
        Account actuallyCreatedAccount = DataBase.getInstance().findAccountBySSN(ssn);
        Assertions.assertNotNull(actuallyCreatedAccount);
        Mockito.verify(c).createAccount(firstName, lastName, ssn);
    }

    @Mock
    DataBase db;

    @InjectMocks
    Controller c;

    @Test
    public void testInject() throws Exception {
        c.createAccount("aaadweqd", "aff", "093840128");
    }

    @Test
    public void testInjectWithReturnValue() throws Exception{
        String existingSsn = "14231324";
        Mockito.when(db.findAccountBySSN(existingSsn)).thenReturn(new Account("ali", "saberi", existingSsn, 0));
        Assertions.assertNotNull(db.findAccountBySSN(existingSsn));
        Assertions.assertThrows(AlreadySignedUpException.class, () -> {
           c.createAccount("sina", "salehi", existingSsn);
        });
    }


    /*
    inject:
    @Mock
    Map<String, String> wordMap;

    @InjectMocks
    MyDictionary dic = new MyDictionary();

    @Test
    public void whenUseInjectMocksAnnotation_thenCorrect() {
        Mockito.when(wordMap.get("aWord")).thenReturn("aMeaning");

        assertEquals("aMeaning", dic.getMeaning("aWord"));
}
     */
}
