package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactModificationTests extends TestBase {

  @Test

  public void testContactModification() {
    if (! app.getContactHelper().isThereAContact()) {
      app.getContactHelper().createContact(new ContactData("Test", "Tester", "Tester", "LionheART", "1", "2", "3", "4", "5", "test2"), true);
    }
    app.getContactHelper().initContactModification();
    app.getContactHelper().fillContactForm(new ContactData("123", "Tester", "Tester", "LionheART", "1", "2", "3", "4", "5", null), false);
    app.getContactHelper().submitContactModification();

  }
}
