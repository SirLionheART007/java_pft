package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

public class ContactCreationTest extends TestBase {


  @Test(enabled = false)
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
    Comparator<? super ContactData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());;
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(after, before);
  }

}