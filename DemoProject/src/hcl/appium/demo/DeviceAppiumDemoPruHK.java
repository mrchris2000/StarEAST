package hcl.appium.demo;

import io.appium.java_client.android.Activity;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;

public class DeviceAppiumDemoPruHK  {
    private AndroidDriver<WebElement> driver;
    private final String SEARCH_ACTIVITY = ".app.SearchInvoke";
    private final String ALERT_DIALOG_ACTIVITY = ".app.AlertDialogSamples";
    private final String PACKAGE = "io.appium.android.apis";

	static {
		System.setProperty("com.ibm.jsse2.overrideDefaultTLS","true");
	}
    

    public static void main(String[] args) throws IOException {
    	new DeviceAppiumDemoPruHK();
    }
    
    public DeviceAppiumDemoPruHK() throws IOException{
        File classpathRoot = new File(System.getProperty("user.dir"));
        File appDir = new File("c:\\temp\\");
        File app = new File(appDir.getCanonicalPath(), "ApiDemos-debug.apk");
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("deviceName", "Android_Accelerated_x86_Oreo");
        capabilities.setCapability("app", app.getAbsolutePath());
        driver = new AndroidDriver<WebElement>(capabilities);


        myTest();

        testOpensAlert();
        
        driver.quit();
    }
    
    public void myTest(){
        driver.startActivity(new Activity(PACKAGE, SEARCH_ACTIVITY));
        AndroidElement searchBoxEl = (AndroidElement) driver.findElementById("txt_query_prefill");
        searchBoxEl.sendKeys("Hello world!");
        AndroidElement onSearchRequestedBtn = (AndroidElement) driver.findElementById("btn_start_search");
        onSearchRequestedBtn.click();
        AndroidElement searchText = (AndroidElement) new WebDriverWait(driver, 30)
                .until(ExpectedConditions.visibilityOfElementLocated(By.id("android:id/search_src_text")));
        String searchTextValue = searchText.getText();

    }
    



    public void testOpensAlert() {
        // Open the "Alert Dialog" activity of the android app
        driver.startActivity(new Activity(PACKAGE, ALERT_DIALOG_ACTIVITY));

        // Click button that opens a dialog
        AndroidElement openDialogButton = (AndroidElement) driver.findElementById("io.appium.android.apis:id/two_buttons");
        openDialogButton.click();

        // Check that the dialog is there
        AndroidElement alertElement = (AndroidElement) driver.findElementById("android:id/alertTitle");
        String alertText = alertElement.getText();
     //   Assert.assertEquals(alertText, "Lorem ipsum dolor sit aie consectetur adipiscing\nPlloaso mako nuto siwuf cakso dodtos anr koop.");
        AndroidElement closeDialogButton = (AndroidElement) driver.findElementById("android:id/button1");

        // Close the dialog
        closeDialogButton.click();
    }
}