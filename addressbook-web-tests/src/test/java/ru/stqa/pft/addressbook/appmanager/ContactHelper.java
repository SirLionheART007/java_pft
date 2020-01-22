package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
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
    click(By.xpath("//table[@id='maintable']/tbody/tr[2]/td[8]/a/img"));
  }

  public void initContactModificationById(int id) {
    WebElement checkbox = wd.findElement(By.cssSelector("input[value='" + id + "']"));
    WebElement row = checkbox.findElement(By.xpath("./../.."));
    row.findElement(By.cssSelector(("img[title = 'Edit']"))).click();
  }

  public void submitContactModification() {
    click(By.name("update"));
  }

  public void selectContactById(int id) {
    wd.findElement(By.cssSelector("input[value='" + id + "']")).click();
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

  public void create(ContactData contact) {
    gotoContactCreation();
    fillContactForm(contact, true);
    submitContactCreation();
    returnToContactPage();
  }

  public void modify(ContactData contact) {
    initContactModificationById(contact.getId());
    fillContactForm(contact, false);
    submitContactModification();
    returnToContactPage();
  }


  public void delete(ContactData contact) {
    selectContactById(contact.getId());
    deleteSelectedContact();
    acceptAlert();
  }

  public boolean isThereAContact() {
    return isElementPresent(By.name("selected[]"));
  }



  public Contacts all() {
    Contacts contacts = new Contacts();
    List<WebElement> elements = wd.findElements(By.name("entry"));
    for (WebElement element : elements) {
      List<WebElement> El = element.findElements(By.tagName("td"));
      String firstname = El.get(2).getText();
      String lastname = El.get(1).getText();
      int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
      contacts.add(new ContactData().withId(id).withFirstname(firstname).withLastname(lastname));
    }
    return contacts;
  }


}
