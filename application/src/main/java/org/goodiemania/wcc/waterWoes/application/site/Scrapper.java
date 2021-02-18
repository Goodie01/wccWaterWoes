package org.goodiemania.wcc.waterWoes.application.site;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Scrapper {
    private final String startingUrl;
    private final DriverSupplier driverSupplier;
    private final List<String> foundUrls = new ArrayList<>();

    public Scrapper(final String startingUrl,
                    final DriverSupplier DriverSupplier) {
        this.startingUrl = startingUrl;
        this.driverSupplier = DriverSupplier;
    }

    public List<String> start() throws InterruptedException {
        WebDriver driver = driverSupplier.get();

        WebDriverWait webDriverWait = new WebDriverWait(driver, 60);

        driver.navigate().to(startingUrl);

        while (true) {
            webDriverWait.until(givenWebDriver ->
                ((JavascriptExecutor) givenWebDriver).executeScript("return document.readyState").equals("complete"));

            try {
                String attribute = driver.findElement(By.className("download__container"))
                    .findElement(By.tagName("a"))
                    .getAttribute("href");

                if (attribute != null) {
                    addUrl(attribute);
                }
            } catch (NoSuchElementException e) {
                //This means that the page (as
            }

            WebElement previousMeetingLink = driver.findElement(By.id("content_0_lnkPrevMeeting"));

            if (StringUtils.equals(previousMeetingLink.getAttribute("href"), driver.getCurrentUrl())) {
                System.out.println("Reached the end!");
                System.out.println(driver.getCurrentUrl());
                System.out.println(driver.getTitle());

                driver.close();
                break;
            }

            previousMeetingLink.click();
        }

        return foundUrls;
    }

    private void addUrl(final String attribute) {
        foundUrls.add(attribute);
    }

}
