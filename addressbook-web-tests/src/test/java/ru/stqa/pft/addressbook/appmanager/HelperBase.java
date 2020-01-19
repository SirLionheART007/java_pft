package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;

import java.io.File;

public class HelperBase {
  protected WebDriver wd;

  public HelperBase(WebDriver wd) {
    this.wd = wd;
  }

  protected void click(By lokator) {
    wd.findElement(lokator).click();
  }

  protected void type(By lokator, String text) {
    click(lokator);
    if (text != null) {
      wd.findElement(lokator).clear();
      wd.findElement(lokator).sendKeys(text);
    }
  }

  protected void attach(By lokator, File file) {
    if (file != null) {
      wd.findElement(lokator).sendKeys(file.getAbsolutePath());
    }
  }

  public boolean isElementPresent(By locator) {
    try {
      wd.findElement(locator);
      return true;
    } catch (NoSuchElementException e) {
      return false;
    }
  }

  public boolean isAlertPresent() {
    try {
      wd.switchTo().alert();
      return true;
    } catch (NoAlertPresentException e) {
      return false;
    }
  }
}
