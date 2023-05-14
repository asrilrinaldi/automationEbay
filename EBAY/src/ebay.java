import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class ebay {
    public static void main(String[] args) throws Exception {

        scenario1();  //Access a Product via category after applying multiple filters
        scenario2(); //Access a Product via Search

    }

    public static void scenario1() throws Exception {
        WebDriver webDriver = null;
        boolean isLaunch = false;

        /*
         *launch for chrome browser
         */
        while (!isLaunch) {
            try {
                System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+"\\chromedriver113.exe"); //get chrome driver in directory
                WebDriver driver = new ChromeDriver(); //try open chrome
                driver.manage().window().maximize();   //Maximize window chrome
                driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS); //time wait
                webDriver = driver; //put driver
                isLaunch = true; //set true for not looping
            } catch (Exception e) {

            }
        }

        /*
        *Go to url ebay
         */
        webDriver.get("https://www.ebay.com/");

        /*
        *Wait element cateogry present after page loads
         */
        waitElement(webDriver,"//button[text()=' Shop by category']");

        /*
         *click button navigate shop by category
         */
        webDriver.findElement(By.xpath("//button[text()=' Shop by category']")).click();

        /*
         *wait element in category present after page loads
         */
        waitElement(webDriver,"//div[@id='gh-sbc-o' and @style='display: block;']//a[text()='Cell phones & accessories']");

        /*
         *click navigate menu Cell phones & accessories
         */
        webDriver.findElement(By.xpath("//div[@id='gh-sbc-o' and @style='display: block;']//a[text()='Cell phones & accessories']")).click();

        /*
         *wait element side navigation present after page loads
         */
        waitElement(webDriver,"//a[text()='Cell Phones & Smartphones']");

        /*
         *click element side menu navigation Cell Phones & Smartphones
         */
        webDriver.findElement(By.xpath("//a[text()='Cell Phones & Smartphones']")).click();

        /*
         *wait element button filters present after loads
         */
        waitElement(webDriver, "//button[@class='brm__all-filters fake-link']");

        /*
         *click button filters
         */
        webDriver.findElement(By.xpath("//button[@class='brm__all-filters fake-link']")).click();

        /*
         *wait element menu screen size in popup filter
         */
        waitElement(webDriver, "//div[@role='tab']//span[text()='Screen Size']");

        /*
         *set string value for screen size
         */
        String strScreenSize = "4.0 - 4.4 in";

        /*
         *click menu screen size in popup filter
         */
        webDriver.findElement(By.xpath("//div[@role='tab']//span[text()='Screen Size']")).click();

        /*
         *wait element in menu screen size with string value
         */
        waitElement(webDriver, "//label//span[text()='"+strScreenSize+"']//parent::label//preceding-sibling::span//input");
        /*
         *click element in menu screen size with string value
         */
        webDriver.findElement(By.xpath("//label//span[text()='"+strScreenSize+"']//parent::label//preceding-sibling::span//input")).click();

        /*
         *set string value for price menu
         */
        String strStartPrice = "10,000";
        String strEndPrice = "100,000";

        /*
         *click price menu in popup filter
         */
        webDriver.findElement(By.xpath("//div[@role='tab']//span[text()='Price']")).click();

        /*
         *wait element in menu price
         */
        waitElement(webDriver, "//input[@class='x-textrange__input x-textrange__input--from']");

        /*
         *send value price filter
         */
        webDriver.findElement(By.xpath("//input[@class='x-textrange__input x-textrange__input--from']")).sendKeys(strStartPrice);
        webDriver.findElement(By.xpath("//input[@class='x-textrange__input x-textrange__input--to']")).sendKeys(strEndPrice);

        /*
         *set string value item location
         */
        String strLocation = "Asia";

        /*
         *click menu item location in popup filters
         */
        webDriver.findElement(By.xpath("//div[@role='tab']//span[text()='Item Location']")).click();

        /*
         *wait element in menu item location
         */
        waitElement(webDriver, "//input[@role='radio' and @value='"+strLocation+"']");

        /*
         *click checkbox element in menu item location with string value
         */
        webDriver.findElement(By.xpath("//input[@role='radio' and @value='"+strLocation+"']")).click();

        /*
         *click button apply filters
         */
        webDriver.findElement(By.xpath("//button[@class='x-overlay-footer__apply-btn btn btn--primary']")).click();

        /*
         *wait element result after page loads
         */
        waitElement(webDriver, "//span[@class='b-pageheader__text']");

        /*
         *get value result after apply filters
         */
        String strFilters = webDriver.findElement(By.xpath("//span[@class='b-pageheader__text']")).getText();

        /*
         *validate string value filters with value result apply filters
         *if not match error with exception
         */
        if(!strFilters.toLowerCase().contains(strScreenSize) &&
                !strFilters.toLowerCase().contains(strStartPrice)&&
                !strFilters.toLowerCase().contains(strEndPrice)&&
                !strFilters.toLowerCase().contains(strLocation)){
            throw new Exception("Validate Error :" +strFilters);
        }else{
            System.out.println("Validate success");
        }
    }

    public static void scenario2() throws Exception {
        WebDriver webDriver = null;
        boolean isLaunch = false;

        /*
         *launch for chrome browser
         */
        while (!isLaunch) {
            try {
                System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+"\\chromedriver113.exe"); //get chrome driver in directory
                WebDriver driver = new ChromeDriver(); //try open chrome
                driver.manage().window().maximize();   //Maximize window chrome
                driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS); //time wait
                webDriver = driver; //put driver
                isLaunch = true; //set true for not looping
            } catch (Exception e) {

            }
        }

        /*
         *go to url ebay after launch chrome
         */
        webDriver.get("https://www.ebay.com/");

        /*
         *wait element search present after page loads
         */
        waitElement(webDriver,"//input[@role='combobox']");

        /*
         *set string value for search
         */
        String strSearch = "MacBook";

        /*
         *send value with before string value
         */
        webDriver.findElement(By.xpath("//input[@role='combobox']")).sendKeys(strSearch);

        /*
         *select with dropwdown
         */
        dropdown(webDriver,"//select[@name='_sacat']", "Computers/Tablets & Networking");

        /*
         *click button search ebay
         */
        webDriver.findElement(By.xpath("//input[@type='submit' and @value='Search']")).click();

        /*
         *with element result search after page loads
         */
        waitElement(webDriver, "//h1[@class='srp-controls__count-heading']//span[2]");

        /*
         *get value to string for result search
         */
        String strResultSearch  = webDriver.findElement(By.xpath("//h1[@class='srp-controls__count-heading']//span[2]")).getText();

        /*
         *validate string value with result value
         *if not match error with exception
         */
        if(!strResultSearch.equalsIgnoreCase(strSearch))
            throw new Exception("result not match");
    }

    /*
     *wait element present after page loads
     */
    public static void waitElement(WebDriver webDriver, String strXpath){
        WebDriverWait wait = new WebDriverWait(webDriver, 3);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(strXpath)));
    }

    /*
     *for element tag select
     */
    public static void dropdown(WebDriver webDriver, String strXpath,String strValue){
        String valueDropdown = "";
        Select dropdown = new Select(webDriver.findElement(By.xpath(strXpath)));
        List<WebElement> listDropdownCategories = dropdown.getOptions();

        for(int i = 0;  i<listDropdownCategories.size(); i++){
            valueDropdown = listDropdownCategories.get(i).getText();

            if(valueDropdown.equalsIgnoreCase(strValue)){ //waiting match with value
                dropdown.selectByIndex(i);
                break;
            }
        }
    }

}
