package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

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
    Contacts contacts = app.db().contacts();
    for (ContactData contact : contacts) {
      int id = contact.getId();
      Groups beforeGroups = contact.getGroups();
      for (GroupData group : groups) {
        if (contact.getGroups().size() == 0) {
          app.contact().addInGroup(contact, group);
          app.goTo().homePage();
          assertThat(app.contact().count(), equalTo(contacts.size()));
          assertThat(contact.withId(id), equalTo(contact.inGroup(group)));
          Contacts after = app.db().contacts();
          Groups afterGroups = contact.getGroups();
          assertThat(afterGroups, equalTo((beforeGroups).withAdded(group)));
        }
        group = groups.iterator().next();
      }
    contact = contacts.iterator().next();
  }
}


}
