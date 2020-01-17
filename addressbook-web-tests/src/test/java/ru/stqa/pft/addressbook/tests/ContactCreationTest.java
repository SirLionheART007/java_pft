package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;

public class ContactCreationTest extends TestBase {


  @Test
  public void testContactCreation() throws Exception {

    List<ContactData> before = app.contact().list();
    System.out.println(before);
    ContactData contact = new ContactData("Test", null, "Tester", null, null, null, null, null, null, "test1");
    app.contact().create(contact, true);
    List<ContactData> after = app.contact().list();
    System.out.println(after);
    Assert.assertEquals(after.size(), before.size() + 1);

    contact.setId(after.stream().max((o1, o2) -> Integer.compare(o1.getId(), o2.getId())).get().getId());
    before.add(contact);
    Comparator<? super ContactData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());;
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(after, before);
  }

}