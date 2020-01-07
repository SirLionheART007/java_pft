package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactModificationTests extends TestBase {

  @Test

  public void testContactModification() {
    app.getContactHelper().initContactModification();
    app.getContactHelper().fillContactForm(new ContactData("123", "Tester", "Tester", "LionheART", "1", "2", "3", "4", "5", null), false);
    app.getContactHelper().submitContactModification();

  }
}
