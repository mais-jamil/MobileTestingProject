import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;

public class MyTestCases {

	DesiredCapabilities caps = new DesiredCapabilities();
	String AppiumURL = "http://127.0.0.1:4723/wd/hub";
	AndroidDriver driver;

	@BeforeTest
	public void mySetUp() {

		caps.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
		caps.setCapability(MobileCapabilityType.DEVICE_NAME, "testphone1");
		File myApplication = new File("src/application/calculator.apk");
		caps.setCapability(MobileCapabilityType.APP, myApplication.getAbsolutePath());

	}

	@Test(enabled = false)
	public void AddTowNum() throws MalformedURLException {

		driver = new AndroidDriver(new URL(AppiumURL), caps);
		driver.findElement(By.id("com.google.android.calculator:id/digit_7")).click();
		driver.findElement(By.id("com.google.android.calculator:id/op_add")).click();
		driver.findElement(By.id("com.google.android.calculator:id/digit_8")).click();

		String ActualResult = driver.findElement(By.id("com.google.android.calculator:id/result_preview")).getText();
		String ExpectedResult = "15";

		org.testng.Assert.assertEquals(ActualResult, ExpectedResult);

	}

	@Test(enabled = false)
	public void clickNumbers() throws MalformedURLException, InterruptedException {

		driver = new AndroidDriver(new URL(AppiumURL), caps);

		List<WebElement> AllButtons = driver.findElements(By.className("android.widget.ImageButton"));

		for (int i = 0; i < AllButtons.size(); i++) {
			if (AllButtons.get(i).getAttribute("resource-id").contains("digit")) {
				AllButtons.get(i).click();
			}
		}
		String Actual = driver.findElement(By.id("com.google.android.calculator:id/formula")).getText();
		String Expected = "7894561230";

		Thread.sleep(2000);
		org.testng.Assert.assertEquals(Actual, Expected);
	}

	@Test
	public void clickEvenNum() throws MalformedURLException {
		driver = new AndroidDriver(new URL(AppiumURL), caps);
		List<WebElement> AllButtons = driver.findElements(By.className("android.widget.ImageButton"));

		for (int i = 0; i < AllButtons.size(); i++) {
			if (AllButtons.get(i).getAttribute("resource-id").contains("digit")) {
				String theNum = AllButtons.get(i).getAttribute("resource-id").replace("com.google.android.calculator:id/digit_", "");
				int IntNum = Integer.parseInt(theNum);
				if(IntNum %2 ==0) {
					AllButtons.get(i).click();
				}
			}
			
			String Actual = driver.findElement(By.id("com.google.android.calculator:id/formula")).getText();
			String Expected = "84620";

		}
	}
}