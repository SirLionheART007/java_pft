package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

public class ContactCreationTest extends TestBase {


  @Test
  public void testContactCreation() throws Exception {

    List<ContactData> before = app.getContactHelper().getContactList();
    System.out.println(before);
    ContactData contact = new ContactData("Test", null, "Tester", null, null, null, null, null, null, "test1");
    app.getContactHelper().createContact(contact, true);
    List<ContactData> after = app.getContactHelper().getContactList();
    System.out.println(after);
    Assert.assertEquals(after.size(), before.size() + 1);

    contact.setId(after.stream().max((o1, o2) -> Integer.compare(o1.getId(), o2.getId())).get().getId());
    before.add(contact);
    Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));
  }

}