package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.ArrayList;
import java.util.List;


public class ContactHelper extends HelperBase {

  public ContactHelper(WebDriver wd) {

    super(wd);
  }

  public void submitContactCreation() {

    click(By.xpath("(//input[@name='submit'])[2]"));
  }

  public void fillContactForm(ContactData contactData, boolean creation) {
    type(By.name("firstname"), contactData.getFirstname());
    type(By.name("middlename"), contactData.getMiddlename());
    type(By.name("lastname"), contactData.getLastname());
    type(By.name("nickname"), contactData.getNickname());
    type(By.name("title"), contactData.getTitle());
    type(By.name("company"), contactData.getCompany());
    type(By.name("address"), contactData.getAddress());
    type(By.name("home"), contactData.getHome());
    type(By.name("email"), contactData.getEmail());

    if (creation) {
      new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup());
    } else {
      Assert.assertFalse(isElementPresent(By.name("new_group")));
    }
  }

  public void initContactModification() {
    click(By.xpath("//img[@alt='Edit']"));
  }

  public void submitContactModification() {
    click(By.name("update"));
  }

  public void selectContact() {
    click(By.name("selected[]"));
  }

  public void deleteSelectedContact() {
    click(By.xpath("//input[@value='Delete']"));
  }

  public void gotoContactCreation() {

    click(By.linkText("add new"));
  }

  public void returnToContactPage() {
    click(By.linkText("home page"));
  }

  public void acceptAlert() {
    wd.switchTo().alert().accept();
  }

  public void createContact(ContactData contact, boolean creation) {
    gotoContactCreation();
    fillContactForm(contact, true);
    submitContactCreation();
    returnToContactPage();
  }

  public boolean isThereAContact() {
    return isElementPresent(By.name("selected[]"));
  }

  public List<ContactData> getContactList() {
    List<ContactData> contacts = new ArrayList<ContactData>();
    List<WebElement> elements =wd.findElements(By.cssSelector("span.group"));
    for (WebElement element : elements) {
      String firstname = element.getText();
      int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
      ContactData contact = new ContactData("Test", "Tester", "Tester", "LionheART", "1", "2", "3", "4", "5", "test1"), true);
      contacts.add(contact);
    }
    return contacts;  }
}
