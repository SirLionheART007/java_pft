package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;


import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class ContactCreationTest extends TestBase {


  @Test
  public void testContactCreation() throws Exception {

    Contacts before = app.contact().all();
    ContactData contact = new ContactData()
            .withFirstname("Test").withLastname("Tester")
            .withGroup("[none]");
    app.contact().create(contact);
    Contacts after = app.contact().all();
    assertEquals(after.size(), (before.size() + 1));

    assertThat(after, equalTo(before.withAdded(contact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt()))));
  }
}