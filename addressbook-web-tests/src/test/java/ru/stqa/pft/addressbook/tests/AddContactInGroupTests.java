package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.Groups;

public class AddContactInGroupTests extends TestBase{



  @Test
  public void testAddContactInGroup(ContactData contact) {
    Groups groups = app.db().groups();
    Contacts before = app.db().contacts();
    app.contact().addInGroup(contact);
  }

}
