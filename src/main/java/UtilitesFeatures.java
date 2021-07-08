import org.openqa.selenium.WebElement;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Properties;

public class UtilitesFeatures {
    public static Properties readPropertiesFile(String fileName){
        Properties prop = new Properties();
        try (InputStream input = UtilitesFeatures.class.getClassLoader().getResourceAsStream(fileName)) {

            //load a properties file from class path, inside static method
            prop.load(input);

            //get the property value and print it out
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return prop;
    }

    public static List<Integer> sortData(List<WebElement> wlist, int count){
        List<Integer> testList = new ArrayList<>();
        for(int i = 0; i < count; i++){
            testList.add(Integer.valueOf(wlist.get(i).getAttribute("data-price-final")));
        }
        testList.sort(Collections.reverseOrder());
        return testList;
    }
    public static List<Integer> convrtToI(List<WebElement> wlist, int count){
        List<Integer> cw = new ArrayList<>();
        for(int i = 0; i < count; i++){
            cw.add(Integer.valueOf(wlist.get(i).getAttribute("data-price-final")));
        }

        return cw;
    }

}