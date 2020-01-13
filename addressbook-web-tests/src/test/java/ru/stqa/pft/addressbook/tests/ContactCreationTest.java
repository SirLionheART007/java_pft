package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.List;

public class ContactCreationTest extends TestBase {


  @Test
  public void testContactCreation() throws Exception {
    app.getContactHelper().createContact(new ContactData("Test", "Tester", "Tester", "LionheART", "1", "2", "3", "4", "5", "test1"), true);
    List<ContactData> before = app.getContactHelper().getContactList();
    List<ContactData> after = app.getContactHelper().getContactList();
  }

}