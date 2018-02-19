package TopIMDB250;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.DriverManager;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import base.ExtentManager;
import base.Practice;
import database.Connect;
import imdbpagefactory.HomePage;
import imdbpagefactory.Top250;

public class Top250Test {

	public WebDriver driver;
	HomePage objHomePage = new HomePage(driver);
	Top250 objTop250 = new Top250(driver);
	Connect DBConnectOBJ = new Connect();
	Practice sqliteOBJ = new Practice();
	ExtentReports report = ExtentManager.getInstance();
	ExtentTest test ;
	@BeforeTest
	public void droptable() throws SQLException{
		test = report.startTest("Test 1: Drop table"); 
		test.log(LogStatus.INFO, "Starting the test");
		sqliteOBJ.strQuery = "DELETE FROM IMDB_Top250";
		test.log(LogStatus.INFO, "Deleting database contents");
		DBConnectOBJ.Connect();
		DBConnectOBJ.exeQuery(sqliteOBJ.strQuery);
		System.out.println("Records Deleted");
		test.log(LogStatus.INFO, "setting system property");
		//System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"/driver/chromedriver.exe");//OS: windows , Browser :Google Chrome
		System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir")+"/driver/geckodriver.exe"); //OS: Windows , Browser :Firefox
		//System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir")+"/driver-linux/geckodriver"); //OS: Linux , Browser :Firefox
		test.log(LogStatus.PASS, "database contents deleted");
		report.endTest(test);
		report.flush();
	}
	
	//Opens the IMDB Web site and checks for the title of the page
	@Test(priority =1)
	public void GetBrowser() {
		test = report.startTest("Test 2: GetBrowser"); 
		test.log(LogStatus.INFO, "Starting the test");	
	//driver = new ChromeDriver(); // Browser : chrome
	driver  = new FirefoxDriver(); // Browser : firefox
	driver.manage().window().maximize(); 
	test.log(LogStatus.INFO, "Maximizing window");	
	test.log(LogStatus.PASS, "browser is open");
	report.endTest(test);
	report.flush();
	}
	
	// Navigate to Top 250 Page
	@Test(priority = 2)
	public void NavigateToIMDB(){
		test = report.startTest("Test 3: Navigate To IMDB"); 
		test.log(LogStatus.INFO, "Starting the test");
		test.log(LogStatus.INFO, "Navigating to Top250 Movies page");	
	System.out.println("Navigating to Top250 Movies page");
	driver.get("http://www.imdb.com/chart/top?ref_=nv_mv_250_6");
	test.log(LogStatus.PASS, "Test case navigate To IMDB successful");
	report.endTest(test);
	report.flush();
	}
	
	// Method for creating database connection and downloading data from the Web Page
	@Test(priority =3)
	public void FetchData() throws SQLException{
		test = report.startTest("Test 4 : Fetch Data from website"); 
		test.log(LogStatus.INFO, "Starting the test");
		DBConnectOBJ.Connect();
		System.out.println("Connecting Database  ..... ");
		test.log(LogStatus.INFO, "Connecting Database  ..... ");
			
	      for (int i=1;i<=250;i++){
	    	    // Get Movie Name
	    	  	objTop250.movieName=driver.findElement(By.xpath("//*[@id=\"main\"]/div/span/div/div/div[3]/table/tbody/tr["+i+"]/td[2]/a")).getText();
	    	  	If(objTop250.movieName.contains("'"));{
	    	  		objTop250.movieName = objTop250.movieName.replaceAll("'", "''");
	    	  	}
	    	  	
	    	  	// Get Movie Year and remove the bracket
	    	  	objTop250.movieYear=driver.findElement(By.xpath("//*[@id=\"main\"]/div/span/div/div/div[3]/table/tbody/tr["+i+"]/td[2]/span")).getText();
	    	  	objTop250.movieYear=objTop250.movieYear.substring(1, 5);
	    	  	 
	    	  	// Get movie rating
	    	  	objTop250.movieRating = driver.findElement(By.xpath("//*[@id=\"main\"]/div/span/div/div/div[3]/table/tbody/tr["+i+"]/td[3]/strong")).getText();
	    	  	System.out.println(i+" "+objTop250.movieName+"  "+objTop250.movieYear+"  "+objTop250.movieRating);
	    	  	// Update the SQLite database
				sqliteOBJ.strQuery = "INSERT INTO IMDB_Top250 (Sr_No,Movie_Name,Movie_Year,Movie_Rating) " +
		                             "VALUES ("+(i)+",'"+objTop250.movieName+"','"+objTop250.movieYear+"','"+objTop250.movieRating+"')"; 
	    		
				DBConnectOBJ.exeQuery(sqliteOBJ.strQuery);

					 
				}

			test.log(LogStatus.INFO, "List updated");
			test.log(LogStatus.PASS, "Test case FetchData from website successful");
			report.endTest(test);
			report.flush();
			}
			private void If(boolean contains) {
				// TODO Auto-generated method stub
				
			}
			
	//Create File by getting all the data from database
	@Test(priority =4)
	public void CreateFile() throws SQLException{
		test = report.startTest("Test 5 : Create File"); 
		test.log(LogStatus.INFO, "Starting the test");

		test.log(LogStatus.INFO,"Creating File by getting all the data from database");
		try {
			sqliteOBJ.strQuery = "select * from IMDB_Top250";
			System.out.println("Database Created Successfully");
			FileWriter objwriter = new FileWriter(sqliteOBJ.strFilePath);
			BufferedWriter objbw = new BufferedWriter(objwriter);
			DBConnectOBJ.Connect();
			sqliteOBJ.conn = DriverManager.getConnection(sqliteOBJ.strDBPath);
			sqliteOBJ.stmt = sqliteOBJ.conn.createStatement();
			sqliteOBJ.rs = sqliteOBJ.stmt.executeQuery(sqliteOBJ.strQuery);
			ResultSetMetaData col = sqliteOBJ.rs.getMetaData();
			objbw.write(col.getColumnName(1)+","+col.getColumnName(2)+","+col.getColumnName(3)+","+col.getColumnName(4));
			objbw.newLine();
			while (sqliteOBJ.rs.next()){
				objbw.write(sqliteOBJ.rs.getInt("Sr_No")+","+sqliteOBJ.rs.getString("Movie_Name")+","+sqliteOBJ.rs.getString("Movie_Year")+","+sqliteOBJ.rs.getString("Movie_Rating"));
				objbw.newLine();
			}
			objbw.close();
			sqliteOBJ.rs.close();
			sqliteOBJ.stmt.close();
			sqliteOBJ.conn.close();
			System.out.println("File location is : "+sqliteOBJ.strFilePath);
		} catch (IOException e) {
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
		    System.exit(0);
		}	
		test.log(LogStatus.PASS, "Test case CreateFile successful");
		report.endTest(test);
		report.flush();
	}

}
