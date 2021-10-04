package utilities;

import org.openqa.selenium.WebElement;

import java.util.List;

public class ListsMethods {
    //prints list text
    public static void printListText(List<WebElement> list){
        System.out.println("");
        for (WebElement element:list) {
            System.out.println(element.getText());
        }
    }
}