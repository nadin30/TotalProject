package wiki;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import wiki.BaseSteps;
import cucumber.api.PendingException;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;

import org.openqa.selenium.chrome.ChromeDriver;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;


import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;


public class MyStepdefsIUA extends BaseSteps {

    @Before
    public void before() {
        startWebDriver();
    }

    @After
    public void after() {
        stopWebDriver();
    }

//    protected WebDriver driver;





    @Given("^Enter login \"([^\"]*)\"$")
    public void enterLogin(String login) throws Throwable {
        WebElement loginField = driver.findElement(By.name("login"));
        loginField.sendKeys(login);

    }


    @And("^Enter passworg \"([^\"]*)\"$")
    public void enterPassworg(String pass) throws Throwable {
        WebElement passField = driver.findElement(By.name("pass"));
        Thread.sleep(2000);
        passField.sendKeys(pass);

    }

    @When("^I click login button$")
    public void iClickLoginButton() throws Throwable {
        WebElement logButton = driver.findElement(By.cssSelector("div.content.clear > form > p > input[type=\"submit\"]"));
        logButton.click();
    }

    @Then("^i check that user name is \"([^\"]*)\"$")
    public void iCheckThatUserNameIs(String UserNAme) throws Throwable {
        WebElement trueUserName = driver.findElement(By.className("sn_menu_title"));
        Thread.sleep(5000);
        assertEquals(UserNAme, trueUserName.getText());
    }

    @When("^I click quit button$")
    public void iClickQuitButton() throws Throwable {
        WebElement quitButton = driver.findElement(By.cssSelector("li:nth-child(3) > span"));
        quitButton.click();
        Thread.sleep(2000);
        WebElement LogOut = driver.findElement(By.cssSelector("#accountSettingsPopup > ul > li:nth-child(7) > a"));
        LogOut.click();

    }

    @Then("^I check that I quit$")
    public void iCheckThatIQuit() throws Throwable {
        WebDriverWait wait = new WebDriverWait(driver,20);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.content.clear > form > p > input[type=\"submit\"]")));

    }

    @Given("^Enter not vali login \"([^\"]*)\"$")
    public void enterNotValiLogin(String nvlogin) throws Throwable {
        WebElement loginField = driver.findElement(By.name("login"));
        loginField.sendKeys(nvlogin);
    }

    @And("^Enter not valid password \"([^\"]*)\"$")
    public void enterNotValidPassword(String nvpass) throws Throwable {
        WebElement passField = driver.findElement(By.name("pass"));
        Thread.sleep(3000);
        passField.sendKeys(nvpass);
    }

    @Then("^I check that I don't autirize$")
    public void iCheckThatIDonTAutirize() throws Throwable {
        WebDriverWait wait = new WebDriverWait(driver,20);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("lform_errCtrl")));
    }

    @And("^I click create mess button$")
    public void iClickCreateMessButton() throws Throwable {
        WebElement create_btn = driver.findElement(By.cssSelector("div.Left > p > a"));
        create_btn.click();
    }

    @And("^I enter addressee$")
    public void iEnterAddressee() throws Throwable {
        WebElement addressee_field = driver.findElement(By.id("to"));
        addressee_field.sendKeys("miroshka_n@i.ua");
    }

    @And("^I enter letter subject$")
    public void iEnterLetterSubject() throws Throwable {
        WebElement subject_field = driver.findElement(By.name("subject"));
        subject_field.sendKeys("New Letter");
    }

    @And("^I enter letter text$")
    public void iEnterLetterText() throws Throwable {
        WebElement text_field = driver.findElement(By.id("text"));
        text_field.sendKeys("Letter text");
    }

    @When("^I click sent button$")
    public void iClickSentButton() throws Throwable {
        WebElement sent_btn = driver.findElement(By.name("send"));
        sent_btn.click();
    }

    @Then("^I see OK message$")
    public void iSeeOKMessage() throws Throwable {
        WebDriverWait wait = new WebDriverWait(driver,20);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("block_confirmation")));
    }

    @And("^I click inbox letters button$")
    public void iClickInboxLettersButton() throws Throwable {
        WebElement inbox = driver.findElement(By.cssSelector("li.current.new > a"));
        inbox.click();
    }

    @And("^I click on checkbox$")
    public void iClickOnCheckbox() throws Throwable {
        WebElement checkbox = driver.findElement(By.cssSelector("div:nth-child(20) > span > input[type=\"checkbox"));
        checkbox.click();
    }

    @When("^I click delete button$")
    public void iClickDeleteButton() throws Throwable {
        WebElement delete_let_btn = driver.findElement(By.cssSelector("fieldset:nth-child(3) > span"));
        delete_let_btn.click();
    }

    @Then("^I deleted letter$")
    public void iDeletedLetter()  {
        WebDriverWait wait = new WebDriverWait(driver, 2);
        wait.until(ExpectedConditions.alertIsPresent());
        Alert alert = driver.switchTo().alert();
        alert.accept();

    }

    @Then("^I check status code$")
    public void iCheckStatusCode() throws IOException, InterruptedException {

        URL url = new URL("https://www.i.ua/");
        HttpURLConnection connection = (HttpURLConnection)url.openConnection();
        connection.setRequestMethod("POST");
        connection.connect();
        Thread.sleep(1000);
        int code = connection.getResponseCode();

        Assert.assertEquals(200, code);
    }

    @Then("^I check autorization$")
    public void iCheckAutorization()  throws IOException {
        URL url = new URL("https://passport.i.ua/login/");
        HttpURLConnection connection = (HttpURLConnection)url.openConnection();
        connection.setRequestMethod("POST");
//        Map<String, String> parameters = new HashMap<String, String>();
//        parameters.put("login", "miroshka_n@i.ua");
//        parameters.put("pass", "nadin123");
        int code = connection.getResponseCode();
        Assert.assertEquals(302, code);
    }

    @And("^lkmhjltyghiykt$")
    public void lkmhjltyghiykt() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @Then("^I sent request to the site www.i.ua$")
    public void iSentRequestToTheSiteWwwIUa() throws Throwable {
        io.restassured.response.Response resp = RestAssured.get("https://www.i.ua/");
        int code = resp.getStatusCode();
        Assert.assertEquals(code,200);
        System.out.println("Time of responce  = "+resp.getTime());
    }

    @Then("^I check data from of the site$")
    public void iCheckDataFromOfTheSite() throws Throwable {
        io.restassured.response.Response resp = RestAssured.get("https://www.i.ua/");
        String data = resp.asString();
        System.out.println("Data is "+data);
        System.out.println("Time of responce  = "+resp.getTime());
    }

    @Then("^I check that I login$")
    public void iCheckThatILogin() throws Throwable {
        io.restassured.response.Response resp = given().body("   {\"login\": \"miroshka_n\"," +
                ""+" \"pass\": \"nadin123\"")

                .when().contentType(ContentType.JSON)
                .post("https://www.i.ua/");

        System.out.println(resp.asString());
    }
}
