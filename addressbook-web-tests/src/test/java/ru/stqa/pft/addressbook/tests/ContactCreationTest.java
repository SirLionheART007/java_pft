package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;


import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTest extends TestBase {


  @DataProvider
  public Iterator<Object[]> validContacts() {
    List<Object[]> list = new ArrayList<Object[]>();
    list.add(new Object[] {new ContactData().withFirstname("tester").withLastname("testerov1").withGroup("test1")
            .withHome("111").withMobile("222").withWork("333")
            .withEmail("artem@artem.ru").withEmail2("java@java.ru").withEmail3("java1@java.ru")});
    list.add(new Object[] {new ContactData().withFirstname("tester2").withLastname("testerov2").withGroup("test1")
            .withHome("111").withMobile("222").withWork("333")
            .withEmail("artem@artem.ru").withEmail2("java@java.ru").withEmail3("java1@java.ru")});
    list.add(new Object[] {new ContactData().withFirstname("tester3").withLastname("testerov3").withGroup("test1")
            .withHome("111").withMobile("222").withWork("333")
            .withEmail("artem@artem.ru").withEmail2("java@java.ru").withEmail3("java1@java.ru")});
    return list.iterator();
  }

  @Test(dataProvider = "validContacts")
  public void testContactCreation(ContactData contact) throws Exception {

    Contacts before = app.contact().all();
    File photo = new File("src/test/resources/IMG_3708.JPG");
    app.contact().create(contact);
    assertThat(app.contact().count(), equalTo(before.size() + 1));
    Contacts after = app.contact().all();
    assertThat(after.size(), equalTo(before.size() + 1));
    assertThat(after, equalTo(
            before.withAdded(contact.withId(after.stream().mapToInt(ContactData::getId).max().getAsInt()))));
  }

}