package ru.stqa.pft.addressbook.tests;


import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;


import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class ContactModificationTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    if (app.contact().all().size() == 0) {
      app.contact().create(new ContactData().withFirstname("tester").withLastname("testerov").withGroup("test1"));
    }
  }



  @Test
  public void testContactModification() {
    Contacts before = app.contact().all();
    int index = before.size() - 1;
    ContactData modifiedContact = before.iterator().next();
    ContactData contact = new ContactData().
            withId(modifiedContact.getId()).withFirstname("Test").withLastname("Tester").withCompany("testovaya");
    app.contact().modify(index, contact);
    Contacts after = app.contact().all();
    assertEquals(after.size(), before.size());
    System.out.println(after);
    System.out.println(before.without(modifiedContact).withAdded(contact));
    assertThat(after, equalTo(before.without(modifiedContact).withAdded(contact)));

  }


}
