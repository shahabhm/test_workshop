<div dir = "rtl">

# Maven

[//]: # (lambda functions)

[//]: # (equals)

[//]: # (annotations)

# یونیت تست

## تعریف یونیت تست

در یونیت تست، یک قسمت کوچک از کد که از نظر منطقی مستقل است را تست می کنیم. توجه داشته باشید که یونیت تست نباید به سیستم های دیگر دسترسی داشته باشد. مثلا نباید به شبکه نیاز داشته باشد، یا فایل ها را دستکاری کند.

## چرا از یونیت تست استفاده کنیم؟

- توصیه می شود که بعد از اضافه کردن کد به سیستم، تعدادی تست مربوط به آن کد را هم بنویسید. این کار باعث می شود که بعدا، هر گاه خودتان یا شخص دیگری کد را تغییر داد، خرابی ها خودشان را بلافاصله نشان دهند. نه این که برنامه در حال اجرا ناگهان خراب شود یا رفتار غیرمنطقی داشته باشد. همچنین در صورت وجود تست، می توانید تصور نسبتا خوبی از این که خرابی در کجا شکل گرفته است داشته باشید.

- یونیت تست ها میتوانند به داکیومنتیشن کد شما هم کمک کنند. چون در یونیت تست، شما توابع کدتان را با ورودی های واقعی صدا می کنید و در انتها هم خروجی مورد انتظار خودتان را در تست نوشته اید. در نتیجه با خواندن تست ها میتوان به درک بهتری از برنامه رسید. همچنین کامنت ها ممکن است قدیمی شوند اما تست ها را باید به روز نگه دارید، در نتیجه آنها به منبع خوبی برای کسب اطلاعات از سیستم تبدیل می شوند.

## شروع کار با JUnit 

junit یک فریمورک برای تست پروزه های جاواست و ما قصد داریم که از این فریمورک استفاده کنیم. پس از این که پروژه maven خود را ساختید،
تگ زیر را به فایل 
`pom.xml`
اضافه کنید.

<div dir = "ltr">

```xml
<dependency>
    <groupId>org.junit.jupiter</groupId>
    <artifactId>junit-jupiter</artifactId>
    <version>RELEASE</version>
    <scope>test</scope>
</dependency>

```

</div>

حال می توانیم تست نوشتن را شروع کنیم.

ابتدا یک کلاس برای نوشتن تست ها بسازید. 
این کلاس می تواند در محلی مثل 
`test/java/Test.java`
 باشد.

<div dir = "ltr">

```java
import org.example.model.Account;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class Test {

 @Test
 public void firstTest() {
  Account a = new Account("shahab", "hoseini", "041239841023", 0);
  Account b = new Account("shahab", "hoseini", "041239841023", 0);
  System.out.println(a.equals(b));
  Assertions.assertEquals(a, b);
 }
}
```

</div>

توضیحات

-`@Test`:
باید قبل از هر تست این annotation را قرار دهید.
توابع تست باید public و غیر static باشند.

-`Assertions`: این کلاس توابعی را در اختیار ما قرار می دهد که با استفاده از آنها می توانیم
خروجی کد را با نتیجه ای که از آن انتظار داریم مقایسه کنیم.

-`Assertions.assertEquals`:
این تابع، دو مقدار (که یکی مقدار مورد انتظار ما و دیگری مقداری است که کد تولید کرده)
را می گیرد و در صورتی که برابر باشند، تست پاس می شود و اگر برابر نباشند، تست fail شده و خطایی مبنی بر برابر نبودن مقادیر نشان داده می شود.
همچنین این تابع overload شده و می توانید هر نوع data type و یا objectی را به آن بدهید.

توجه داشته باشید که در صورتی که دو object به این تابع داده باشید، باید متد equals را برای کلاسشان تعریف کرده باشید.
در غیر این صورت، تابع به درستی کار نخواهد کرد و برای مقادیر برابر هم حطای نابرابری چاپ خواهد کرد.
اگر میخواهید برابر بودن رفرنس دو شی را بررسی کنید، از 
`Assertions.assertSame`
استفاده کنید.

(می توانید متد equals کلاس Account را یکبار کامنت کنید و دوباره این تست را اجرا کنید)

- `testAssertEquals`

به خاطر داشته باشید که در یک تابع تست، می توانید به تعداد دلخواه از Assertions ها استفاده کنید.
همچنین می توانید موارد مختلفی را در یک تابع تست کنید. اما توصیه می شود که تست هایتان را بیش از حد شلوغ نکنید
و فقط یک بخش از کدتان را تست کنید.
(قرار است یک `unit` را تست کنید.)

سایر توابع کلاس Assertions:

`assertNotSame` و `assertNotEquals`
برعکس 
`assertSame` و `AssertEquals` 
هستند.

توابع `assertNull` و `assertNotNull`
هم null بودن یا نبودن شی را بررسی می کنند.

توابع `assertTrue` و `assertFalse` هم می توانند برای boolean استفاده شوند.

همچنین توجه داشته باشید که در بررسی مقادیر double و float، می توانید یک مقدار به عنوان دقت محاسبه به 
`assertEquals` 
بدهید تا محاسبه برابری را با دقت مشخص شده انجام دهد و از نتایج پیش بینی نشده جلوگیری کند.

<div dir = "ltr">

```java 
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
```
</div>



فرض کنید که میخواهیم تست کنیم که آیا تابعی از کد که قرار است exception پرتاب کند به درستی کار می کند یا خیر؟
برای این کار می توانیم از 
`Assertions.assertThrows`
استفاده کنیم. این تابع یک کلاس و یک آبجکت که از اینترفیس `Executable` پیروی میکند را ورودی میگیرد و در صورتی که  
تابع `execute` آن آبجکت، چیزی از کلاس مشخص شده پرتاب کند، تست پاس می شود.

البته از آنجا که این اینترفیس فقط یک تابع دارد، برای راحتی می توانید از 
`lambda function`
هم استفاده کنید. این کد و کد قبلی معادل هم هستند.
<div dir = "ltr">

```java 
    @Test
    public void testAssertThrowWithLambda() {
        Assertions.assertThrows(Exception.class, () -> {
            new Account("", "", "", 0).save();
        });
    }
```
</div>

### mock کردن

mock
 کردن به جایگذاری یک شی قلابی به جای object
ی که قرار است در کد داشته باشیم گفته می شود.

مثلا فرض کنید که در تست قرار است به آبجکتی از یک کلاس نیاز داشته باشید که کد آن به طور کامل پیاده سازی نشده.
 یا فرض کنید که بخشی از کدتان به شبکه یا فایل سیستم دسترسی دارد و هر بار اجرای تست نیاز به خواندن از دیتابیس
یا اتصال به اینترنت داشته باشد.

در این شرایط می توانیم با استفاده از ماک کردن، این وابستگی ها را از بین ببریم.

<div dir = "ltr">

```java
@ExtendWith(MockitoExtension.class)
public class test {
```

</div>
 
در مثال زیر، آبجکت c، یک نمونه ماک شده از کلاس `Controller` است.
از آنجا که این آبحکت ماک شده است، تابع `createAccount` کاری انجام نمی دهد.
<div dir = "ltr">

```java
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
```
</div>
















</div>