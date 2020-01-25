package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactInfoTest extends TestBase {

  @Test
  public void testContactInfo() {
    ContactData contact = app.contact().all().iterator().next();
    ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);
    assertThat(contact.getFullInfo(), equalTo(mergeInfo(contactInfoFromEditForm)));

  }

  private String mergeInfo(ContactData contact) {
    return Arrays.asList(contact.getFirstname()+ "\n", contact.getLastname()+"\n", contact.getAddress()+"\n",
            contact.getHome() + "\n",contact.getMobile() + "\n", contact.getWork()+"\n",
            "\n" + contact.getEmail()+"\n", contact.getEmail2()+"\n", contact.getEmail3())
            .stream().filter((s) -> ! s.equals("")).map(ContactInfoTest::cleaned).collect(Collectors.joining());
  }

  public static String cleaned(String phone) {
    return phone.replaceAll("[-)(]", "");
  }
}


