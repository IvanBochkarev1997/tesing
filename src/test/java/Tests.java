import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import io.github.bonigarcia.wdm.WebDriverManager;
 import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.json.TypeToken;
import org.testng.ITestContext;
import org.testng.Reporter;
import org.testng.annotations.*;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

@Test
public class Tests {
    protected WebDriver driver;
    private String url;
    private String locale;
    private PageSearch pageSearch;

    @BeforeClass
    public void setupClass() {
        Properties prop = UtilitesFeatures.readPropertiesFile("webdriver.properties");
        url = prop.getProperty("url");
        locale = prop.getProperty("locale");
        WebDriverManager
                .chromedriver()
                   .setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--lang="+locale);
        driver = new ChromeDriver(options);


        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
        driver.get(url);
        pageSearch = new PageSearch(driver);


    }
    @Test
    public void mainPageTest() {
        MainPage mainPage = new MainPage(driver);
        mainPage.openIsMainPage(url);
    }

    @Test(dataProvider = "getData")
    public void pageSearchTest(TestData data) {
       pageSearch.openIsPageResults(data.getGame());

    }
    @Test(dataProvider = "getData")
    public void sortGamesRight(TestData data) throws InterruptedException {
      if (pageSearch.validFilter(data.getGame(),data.getN())){
          Reporter.log("Test-sortGamesRight is true, with data:"+data,true );
      }
    }

    /*@AfterClass
    public void closeTest(){

        driver.quit();
    }*/

    @DataProvider
    public Object[][] getData() throws FileNotFoundException {
        JsonElement jsonData = new JsonParser().parse(new FileReader("D:\\Work\\i.bochkarev\\src\\main\\resources\\in.json"));
        JsonElement dataSet = jsonData.getAsJsonObject().get("dataSet");
        List<TestData> testData = new Gson().fromJson(dataSet, new TypeToken<List<TestData>>() {
        }.getType());
        Object[][] returnValue = new Object[testData.size()][1];
        int index = 0;
        for (Object[] each : returnValue) {
            each[0] = testData.get(index++);
        }
        return returnValue;
    }
}

