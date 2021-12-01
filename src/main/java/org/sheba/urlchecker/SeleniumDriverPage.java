/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.sheba.urlchecker;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 *
 * @author kabir
 */
public class SeleniumDriverPage {

    private WebDriver driver;
    private static SeleniumDriverPage seleniumDriverPage;

    private SeleniumDriverPage() {
        System.setProperty("webdriver.chrome.driver", "/usr/bin/chromedriver");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    public static SeleniumDriverPage getInstance() {
        if (seleniumDriverPage == null) {
            seleniumDriverPage = new SeleniumDriverPage();
        }
        return seleniumDriverPage;
    }

    public void close(){
        driver.close();
    }
    public boolean isValidURL(String url) {
        try {
            driver.get(url);
            return driver.getCurrentUrl().equals(url);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }

}
