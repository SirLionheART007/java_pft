package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactCreationTest extends TestBase {


  @Test
  public void testContactCreation() throws Exception {
    app.getNavigationHelper().gotoContactCreation();
    app.getContactHelper().fillContactForm(new ContactData("Test", "Tester", "Tester", "LionheART", "1", "2", "3", "4", "5"));
    app.getContactHelper().submitContactCreation();
    app.logout();
  }

}
