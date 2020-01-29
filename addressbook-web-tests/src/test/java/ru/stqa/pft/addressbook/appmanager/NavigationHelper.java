package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

public class NavigationHelper extends HelperBase {

  public NavigationHelper(WebDriver wd) {
    super(wd);
  }

  public void groupPage(String groups) {
    if (isElementPresent(By.tagName("h1"))
            && wd.findElement(By.tagName("h1")).getText().equals("Groups")
            && isElementPresent(By.name("new"))) {
      return;
    }
    click(By.linkText(groups));
  }

  public void homePage() {
    Select select = new Select(wd.findElement(By.xpath("//*[@id=\"right\"]/select")));
    System.out.println(select.getFirstSelectedOption().getText());
    if (isElementPresent(By.id("maintable"))
            && select.getFirstSelectedOption().getText().equals("[all]")) {
      return;
    }
    click(By.linkText("home"));
  }
}

