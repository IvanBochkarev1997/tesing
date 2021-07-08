import com.google.errorprone.annotations.Var;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;

import java.nio.file.WatchEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class PageSearch {
    protected WebDriver driver;
    private String [] price;
    private String bPr;

    private By field_search = By.id("store_nav_search_term");
    private By search_link = By.xpath("//a[@id='store_search_link']");
    private By searchRowsGames = By.xpath("//div[@id='search_resultsRows']");



    public PageSearch(WebDriver driver){
        this.driver = driver;
    }

    public boolean openIsPageResults(String game){
        Reporter.log("//////PageSearchTest/////",true);
        driver.findElement(field_search).sendKeys(game);
        try{
            driver.findElement(search_link).submit();
            Reporter.log("Opened page with results of search",true);

            try  {
                driver.findElement(searchRowsGames);
                Reporter.log("List of games not null",true);
            }
            catch (NoSuchElementException ex){
                ex.printStackTrace();
            }
        }
        catch (NoSuchWindowException e){
            e.printStackTrace();
            return false;
        }
        return true;
    }
    public boolean validFilter(String game, String count) throws InterruptedException {

        driver.findElement(field_search).sendKeys(game);
        driver.findElement(search_link).submit();

        Actions actions = new Actions(driver);
        WebElement menu = driver.findElement(By.xpath("//div[@id='sort_by_dselect_container']"));
        actions.moveToElement(menu);
        actions.click().build().perform();
        actions.moveToElement(driver.findElement(By.xpath("//a[@id='Price_DESC']")));
        actions.click().build().perform();
        Thread.sleep(5000);
        try {

            List<WebElement> webElements = driver.findElements(By.xpath("//div[@data-price-final]"));
            List<Integer> sortedList=UtilitesFeatures.sortData(webElements,Integer.parseInt(count));
            List<Integer> basicList = UtilitesFeatures.convrtToI(webElements,Integer.parseInt(count));
            Assert.assertEquals(basicList, sortedList);
            return true;
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
        return false;

    }
}
