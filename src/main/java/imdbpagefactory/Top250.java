package imdbpagefactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Top250 {

	// Top 250 
	public Top250(WebDriver driver){
		PageFactory.initElements(driver, this);
	}
	public String strTop250PageTitle = "IMDb Top 250 - IMDb";
	public String movieName,movieYear,movieRating;
	
	
	@FindBy(xpath = "//*[@id='main']/div")
	public WebElement tblMovieList;	
	
	 
}
