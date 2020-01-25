package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
public class ContactInfoTests extends TestBase {

  @Test
  public void testContactInfo() {
    ContactData contact = app.contact().all().iterator().next();
    ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);
    assertThat(contact.getFullInfo(), equalTo(mergeInfo(contactInfoFromEditForm)));

  }

  private String mergeInfo(ContactData contact) {
    return Arrays.asList(contact.getFirstname() , contact.getLastname() , contact.getAddress() ,
            contact.getHome(), contact.getMobile(), contact.getWork() ,
            "\n" + contact.getEmail(), contact.getEmail2(), contact.getEmail3())
            .stream().filter((s) -> !s.equals("")).map(ContactInfoTests::cleaned).collect(Collectors.joining("\n"));
  }

  public static String cleaned(String phone) {
    return phone.replaceAll("[-)(]", "");
  }
}
