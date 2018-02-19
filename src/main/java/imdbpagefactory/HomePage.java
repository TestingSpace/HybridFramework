package imdbpagefactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
	//Initialize objects in the Page
	public HomePage(WebDriver driver){
		PageFactory.initElements(driver, this);
	}
	public String strWebURL = "http://www.imdb.com";
	public String strHomePageTitle = "IMDb - Movies, TV and Celebrities - IMDb";
	 
	@FindBy(xpath = "//*[@id='navTitleMenu']/span")
	public WebElement btnChartDropDown;

//	@FindBy(id = "navTitleMenu")
//	public WebElement btnDropDown;
	
	@FindBy(xpath = "//*[@id='navMenu1']/div[2]/ul[2]/li[4]/a")
	public WebElement linkTop250;
	
//	@FindBy(linkText = "Top Rated Movies")
//	public WebElement linkTop250;
	
}
