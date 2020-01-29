package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

public class AddContactInGroupTests extends TestBase{

  @BeforeMethod
  public void ensurePreconditions() {
    if (app.db().contacts().size() == 0) {
      app.contact().create(new ContactData().withFirstname("tester").withLastname("testerov"));
    }
    if (app.db().groups().size() == 0) {
      app.goTo().groupPage("groups");
      app.group().create(new GroupData().withName("test 1"));
    }
  }

  @Test
  public void testAddContactInGroup() {
    app.goTo().homePage();
    Groups groups = app.db().groups();
    Contacts before = app.db().contacts();
    ContactData contact = before.iterator().next();
    app.contact().addInGroup(contact);
  }

}
