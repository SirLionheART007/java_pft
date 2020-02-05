package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.By;


public class AdminHelper extends HelperBase {

  public AdminHelper(ApplicationManager app) {
    super(app);
  }

  public void goToManageUsers() {
    click(By.linkText("Управление"));
    click(By.linkText("Управление пользователями"));
  }


  public void selectUser(String username) {
    click(By.linkText(username));
  }

  public void resetPassword() {
    click(By.xpath("//form[@id='manage-user-reset-form']/fieldset/span/input"));
  }

  public void login() {
    type(By.name("username"), "administrator");
    click(By.cssSelector("input[type='submit']"));
    type(By.name("password"), "root");
    click(By.cssSelector("input[type='submit']"));
  }
}
