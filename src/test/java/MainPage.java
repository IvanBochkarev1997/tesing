import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Reporter;

public class MainPage {
    protected WebDriver driver;

    public MainPage(WebDriver driver){
        this.driver = driver;
    }

    public boolean openIsMainPage(String url){
        if (driver.findElement(By.xpath("//body[contains(@class,'v6 infinite_scrolling sale_summer2021 no_home_gutter responsive_page')]")) != null){
            Reporter.log("/////MainPageTest//////",true);
            Reporter.log("MainPage of website opened",true);
            return true;
        }
        driver.close();
        return false;

    }
}
