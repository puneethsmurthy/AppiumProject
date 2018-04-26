package com.komoot.library;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.appium.java_client.android.AndroidDriver;

public class Generic {
	
	public AndroidDriver driver;
	private String name = "Puneeth";
	private String invalidEmail = "invalidEmail";
	private String validEmail = "puneeth.shanthamurthy@gmail.com";
	private String password = "12345678";
	private String expectedErrorMessage = "The email you entered is incorrect. Please re-enter it and try again.";
	private String expectedErrorMessageNameField = "Please enter your name and try again.";
	private String expectedErrorMessageNameEmail = "Please enter your email and try again.";
	private String expectedErrorMessageNamePassword = "Please enter your password and try again.";
	private String expectedWelcomeMessage = "Puneeth, Ready For Your First Adventure?";
	
	
	@BeforeMethod
	public void setUp() throws MalformedURLException{
		
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability("platformName", "Android");
		capabilities.setCapability("deviceName", "Nexus 6 API 24");
		
		driver = new AndroidDriver(new URL("http://0.0.0.0:4723/wd/hub"), capabilities);
	}
	
	@AfterMethod
	public void tearDown(){
		
		driver.quit();
	}
	
	@Test(description="Verify validation for Invalid Email ID", priority=0)
	public void testWithInvalidEmailId() throws Exception{	
		
		Thread.sleep(5000);
		
		driver.findElement(By.id("de.komoot.android:id/button_mail_register")).click();
		
		WebElement nameTextField = (new WebDriverWait(driver, 60))
				.until(ExpectedConditions.presenceOfElementLocated(By.id("de.komoot.android:id/edittext_display_name")));
		nameTextField.sendKeys(name);
																		
		WebElement emailTextField = driver.findElement(By.id("de.komoot.android:id/edittext_email"));
		emailTextField.sendKeys(invalidEmail);		
		
		WebElement passwordTextField = driver.findElement(By.id("de.komoot.android:id/edittext_password"));
		passwordTextField.sendKeys(password);
		
		driver.navigate().back();
		
		WebElement loginButton = driver.findElement(By.id("de.komoot.android:id/button_register"));
		loginButton.click();
		
		WebElement errorMessage = (new WebDriverWait(driver, 60))
				.until(ExpectedConditions.presenceOfElementLocated(By.id("android:id/message")));
		
		String actualErrorMessage = errorMessage.getText();
		
		Assert.assertEquals(actualErrorMessage, expectedErrorMessage, "FAIL: Invalid Email test does not work");
		
		Thread.sleep(5000);
	}
	
	@Test(description="Verify Mandatory field validation for Name field", priority=1)
	public void testForMandatoryFieldName() throws Exception{
		
		Thread.sleep(5000);
		
		driver.findElement(By.id("de.komoot.android:id/button_mail_register")).click();
		
		WebElement nameTextField = (new WebDriverWait(driver, 60))
				.until(ExpectedConditions.presenceOfElementLocated(By.id("de.komoot.android:id/edittext_display_name")));
		nameTextField.click();
		
		driver.navigate().back();
		
		WebElement loginButton = driver.findElement(By.id("de.komoot.android:id/button_register"));
		loginButton.click();
		
		WebElement errorMessage = (new WebDriverWait(driver, 60))
				.until(ExpectedConditions.presenceOfElementLocated(By.id("android:id/message")));
		
		String actualErrorMessage = errorMessage.getText();
		
		Assert.assertEquals(actualErrorMessage, expectedErrorMessageNameField, "FAIL: Mandatory test for Name Field does not work");
		
		Thread.sleep(5000);
	}
	
	
	@Test(description="Verify Mandatory field validation for Email field",priority=2)
	public void testForMandatoryFieldEmail() throws Exception{
		
		Thread.sleep(5000);
		
		driver.findElement(By.id("de.komoot.android:id/button_mail_register")).click();
		
		WebElement nameTextField = (new WebDriverWait(driver, 60))
				.until(ExpectedConditions.presenceOfElementLocated(By.id("de.komoot.android:id/edittext_display_name")));
		nameTextField.sendKeys(name);
		
		driver.navigate().back();
		
		WebElement loginButton = driver.findElement(By.id("de.komoot.android:id/button_register"));
		loginButton.click();
		
		WebElement errorMessage = (new WebDriverWait(driver, 60))
				.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/android.widget.TextView[1]")));
		
		String actualErrorMessage = errorMessage.getText();
		System.out.println("Actual Error Message: " +actualErrorMessage);
		
		Assert.assertEquals(actualErrorMessage, expectedErrorMessageNameEmail, "FAIL: Mandatory test for Email Field does not work");
		
		Thread.sleep(5000);
	}
	
	@Test(description="Verify Mandatory field validation for Password field",priority=3)
	public void testForMandatoryFieldPassword() throws Exception{
		
		Thread.sleep(5000);
		
		driver.findElement(By.id("de.komoot.android:id/button_mail_register")).click();
		
		WebElement nameTextField = (new WebDriverWait(driver, 60))
				.until(ExpectedConditions.presenceOfElementLocated(By.id("de.komoot.android:id/edittext_display_name")));
		nameTextField.sendKeys(name);
		
		WebElement emailTextField = driver.findElement(By.id("de.komoot.android:id/edittext_email"));
		emailTextField.sendKeys(validEmail);	
		
		driver.navigate().back();
		
		WebElement loginButton = driver.findElement(By.id("de.komoot.android:id/button_register"));
		loginButton.click();
		
		WebElement errorMessage = (new WebDriverWait(driver, 60))
				.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.ScrollView[1]/android.widget.LinearLayout[1]/android.widget.TextView[1]")));
		
		String actualErrorMessage = errorMessage.getText();
		
		Assert.assertEquals(actualErrorMessage, expectedErrorMessageNamePassword, "FAIL: Mandatory test for Password Field does not work");
		
		Thread.sleep(5000);
	}
	
	@Test(description="Verify Sign up functionality by providing valid values", priority=4)
	public void testForValidSignUp() throws Exception{
		
		driver.findElement(By.id("de.komoot.android:id/button_mail_register")).click();
		
		WebElement nameTextField = (new WebDriverWait(driver, 60))
				.until(ExpectedConditions.presenceOfElementLocated(By.id("de.komoot.android:id/edittext_display_name")));
		nameTextField.sendKeys(name);
																		
		WebElement emailTextField = driver.findElement(By.id("de.komoot.android:id/edittext_email"));
		emailTextField.sendKeys(validEmail);		
		
		WebElement passwordTextField = driver.findElement(By.id("de.komoot.android:id/edittext_password"));
		passwordTextField.sendKeys(password);
		
		driver.navigate().back();
		
		WebElement loginButton = driver.findElement(By.id("de.komoot.android:id/button_register"));
		loginButton.click();
		
		WebElement welcomeTextField = (new WebDriverWait(driver, 60))
				.until(ExpectedConditions.presenceOfElementLocated(By.id("de.komoot.android:id/oia_title")));
		String actualWelcomeMessage = welcomeTextField.getText();
		
		Assert.assertEquals(actualWelcomeMessage, expectedWelcomeMessage, "Fail: Welcome Message did not appear");
		
	}
	
}
