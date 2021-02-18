package org.goodiemania.wcc.waterWoes.application.site;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;
import java.util.Optional;
import org.apache.commons.io.FileUtils;
import org.goodiemania.wcc.waterWoes.application.Main;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxDriverLogLevel;
import org.openqa.selenium.firefox.FirefoxOptions;

public class DriverSupplier {
    private static String extractDriverAndWait() {
        Optional<String> newDriverLocation;
        while ((newDriverLocation = attemptToExtractDriver()).isEmpty()) {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                //suggestion from sonar
                Thread.currentThread().interrupt();
            }
        }
        return newDriverLocation.get();
    }

    private static Optional<String> attemptToExtractDriver() {
        File targetFile = new File("geckodriver.exe");

        if (!targetFile.exists()) {
            InputStream resourceAsStream = Main.class.getClassLoader().getResourceAsStream("drivers/geckodriver.exe");
            Objects.requireNonNull(resourceAsStream);
            try {
                FileUtils.copyInputStreamToFile(resourceAsStream, targetFile);
            } catch (IOException e) {
                return Optional.empty();
            }
        }

        return Optional.of(targetFile.getAbsolutePath());
    }

    public WebDriver get() {
        String driverLocation = extractDriverAndWait();
        System.setProperty("webdriver.gecko.driver", driverLocation);
        FirefoxOptions options = new FirefoxOptions();
        options.setLogLevel(FirefoxDriverLogLevel.FATAL);
        options.setHeadless(true);

        options.setBinary("C:\\Program Files\\Firefox Nightly\\firefox.exe");

        return new FirefoxDriver(options);
    }
}
