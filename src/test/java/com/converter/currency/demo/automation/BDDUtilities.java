package com.converter.currency.demo.automation;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


public class BDDUtilities {
	private static String OS = System.getProperty("os.name").toLowerCase();

	public static WebDriver getDriver() {
		if(isWindows()){
			System.setProperty("webdriver.chrome.driver", "driver/chromedriver.exe");
		}else {
			System.setProperty("webdriver.chrome.driver", "driver/chromedriver");
		}
		return new ChromeDriver();
	}

	public static boolean isWindows() {
		return (OS.indexOf("win") >= 0);
	}

	public static boolean isUnix() {
		return (OS.indexOf("nix") >= 0 || OS.indexOf("nux") >= 0 || OS.indexOf("aix") > 0 );
	}
}
