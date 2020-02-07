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
    ContactData contact = app.db().contacts().iterator().next();
    GroupData group = groups.iterator().next();
    if (app.db().groups().size() == 0) {
      app.goTo().groupPage("groups");
      app.group().create(new GroupData().withName("test 1"));
    }
    if (app.db().contacts().size() == 0) {
      app.contact().create(new ContactData().withFirstname("tester").withLastname("testerov").inGroup(groups.iterator().next()));
    }
    if (contact.getGroups().size() == 0) {
      app.contact().addInGroup(contact, group);
      app.goTo().homePage();
    }
  }

  @Test
  public void testDeleteContactFromGroup() {
    Contacts before = app.db().contacts();
    ContactData contact = before.iterator().next();
    app.contact().deleteFromGroup(contact);
    app.goTo().homePage();
    assertThat(app.contact().count(), equalTo(before.size()));
    Contacts after = app.db().contacts();
    assertThat(after, equalTo(before));
  }
}
