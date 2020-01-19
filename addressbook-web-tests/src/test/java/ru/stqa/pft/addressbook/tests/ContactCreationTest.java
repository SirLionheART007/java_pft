package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;


import java.io.File;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTest extends TestBase {


  @Test
  public void testContactCreation() throws Exception {

    Contacts before = app.contact().all();
    File photo = new File("src/test/resources/IMG_3708.JPG");
    ContactData contact = new ContactData().withFirstname("tester").withLastname("testerov").withGroup("test1")
            .withHome("111").withMobile("222").withWork("333")
            .withEmail("artem@artem.ru").withEmail2("java@java.ru").withEmail3("java1@java.ru").withPhoto(photo);
    app.contact().create(contact);
    assertThat(app.contact().count(), equalTo(before.size() + 1));
    Contacts after = app.contact().all();
    assertThat(after.size(), equalTo(before.size() + 1));
    assertThat(after, equalTo(
            before.withAdded(contact.withId(after.stream().mapToInt(ContactData::getId).max().getAsInt()))));
  }

}