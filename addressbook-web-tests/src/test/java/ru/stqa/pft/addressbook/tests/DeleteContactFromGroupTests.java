package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;

import static org.hamcrest.MatcherAssert.assertThat;

public class DeleteContactFromGroupTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    Groups groups = app.db().groups();
    Contacts contacts = app.db().contacts();
    ContactData contact = app.db().contacts().iterator().next();
    GroupData group = groups.iterator().next();
    if (app.db().groups().size() == 0) {
      app.goTo().groupPage("groups");
      app.group().create(new GroupData().withName("test 1"));
    }
    if (app.db().contacts().size() == 0) {
      app.contact().create(new ContactData().withFirstname("tester").withLastname("testerov").inGroup(groups.iterator().next()));
    }
    int i = 0;
    for (ContactData c : contacts) {
      if (c.getGroups().size() == 0) {
        i++;
      }
    }
    if (i == contacts.size()) {
      app.contact().addInGroup(contact, group);
      app.goTo().homePage();
    }
  }

  @Test
  public void testDeleteContactFromGroup() {
    Contacts contacts = app.db().contacts();
    for (ContactData contact : contacts) {
      int id = contact.getId();
      Groups beforeGroups = contact.getGroups();
      for (GroupData group : beforeGroups) {
        app.contact().deleteFromGroup(contact, group);
        app.goTo().homePage();
        contact = app.db().contacts().iterator().next();
        Groups afterGroups = contact.getGroups();
        assertThat(afterGroups, equalTo((beforeGroups).without(group)));
      }
    }
  }
}
