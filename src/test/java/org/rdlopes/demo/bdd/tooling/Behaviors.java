package org.rdlopes.demo.bdd.tooling;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.io.IOException;

import static org.rdlopes.demo.bdd.tooling.FrameworkLogger.*;


public class Behaviors extends WebDriverConfigurations {
    public static enum GetElementBy{ID, XPath}

    // não utilizar diretamente
    private static WebElement GetElement(GetElementBy by, String webElement) throws IOException {

        WebElement element = null;

        if(by == GetElementBy.ID){
            element = driver.findElement(By.id(webElement));
        }

        if(by == GetElementBy.XPath){
            element = driver.findElement(By.xpath(webElement));
        }

        if (!element.isDisplayed() || !element.isEnabled()) {
            LogElement_NotFound(by, webElement);
            return null;
        }

        //LogElement_Found(by, webElement);
        return element;
    }

    public static void ClickElementById(String elementId) throws IOException {

        WebElement element = GetElement(GetElementBy.ID, elementId);

        if(element == null){
            element.click();
            LogElement_Click(GetElementBy.ID, elementId);
        }
    }

    public static void ClickElementByXpath(String elementXpath) throws IOException {

        WebElement element = GetElement(GetElementBy.XPath, elementXpath);

        if(element != null){
            element.click();
            LogElement_Click(GetElementBy.XPath, elementXpath);
        }
    }

    public static void InputTextById(String text, String elementId) throws IOException {
        WebElement element = GetElement(GetElementBy.ID, elementId);

        if(element != null){
            element.sendKeys(text);
            LogElement_Input(text, elementId);
            FrameworkLogger.LogAttach(text, elementId);
        }
    }

    public static void InputTextByXpath(String text, String elementXpath) throws IOException {
        WebElement element = GetElement(GetElementBy.XPath, elementXpath);

        if(element != null){
            element.sendKeys(text);
            LogElement_Input(text, elementXpath);
        }
    }

    public static WebElement GetElementByXPath(String elementXpath) throws IOException {
        WebElement element = GetElement(GetElementBy.XPath, elementXpath);

        if(element != null){
            return element;
        }
        return null;
    }
}
