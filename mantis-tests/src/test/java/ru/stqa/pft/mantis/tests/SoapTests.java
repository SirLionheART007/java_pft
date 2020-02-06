package ru.stqa.pft.mantis.tests;


import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.mantis.model.Issue;
import ru.stqa.pft.mantis.model.Project;

import javax.xml.rpc.ServiceException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.RemoteException;
import java.util.Objects;
import java.util.Set;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class SoapTests extends TestBase {
  private int issueId = 3;

  @BeforeMethod
  public void canTestRun() throws IOException, ServiceException {
    if (Objects.equals(isIssueOpen(issueId), true)) {
      skipIfNotFixed(issueId);
    }
  }

  @Test
  public void testGetPtojects() throws MalformedURLException, ServiceException, RemoteException {

    Set<Project> projects = app.soap().getProjects();
    System.out.println(projects.size());
    for (Project project : projects) {
      System.out.println(project.getName());
    }

  }

  @Test
  public void testCreateIssue() throws RemoteException, ServiceException, MalformedURLException {

    Set<Project> projects = app.soap().getProjects();
    Issue issue = new Issue().withSummary("Test issue")
            .withDescription("Test description").withProject(projects.iterator().next());
    Issue created = app.soap().addIssue(issue);
    assertEquals(issue.getSummary(), created.getSummary());
  }
}
