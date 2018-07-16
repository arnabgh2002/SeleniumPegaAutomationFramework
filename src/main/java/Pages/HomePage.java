package Pages;

import org.openqa.selenium.support.PageFactory;

import Base.Base;

public class HomePage extends Base{
	
	public HomePage(){
		PageFactory.initElements(driver, this);
	}

}
