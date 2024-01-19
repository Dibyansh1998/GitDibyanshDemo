import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Jdbcconnecting {

	public static void main(String[] args) throws SQLException {
		
		WebDriverManager.chromedriver().setup();
		WebDriver driver= new ChromeDriver();
		driver.manage().window().maximize();

		String host = "localhost";
		String port = "3306";
		// Below command will connect the SQL Server to Selenium
		Connection con = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/qajdbct", "root", "root");
		Statement s = con.createStatement();
		ResultSet rs = s.executeQuery("select * from credentials where scenarios='rewardscard'");

		while (rs.next()) {
			
			driver.get("https://login.salesforce.com/");
			driver.findElement(By.xpath("//input[@id='username']")).sendKeys(rs.getString("username"));
			driver.findElement(By.cssSelector("#password")).sendKeys(rs.getString("password"));
		}

	}

}
