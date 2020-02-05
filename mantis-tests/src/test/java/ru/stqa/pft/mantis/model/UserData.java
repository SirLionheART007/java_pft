package ru.stqa.pft.mantis.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "mantis_user_mantis")
public class UserData {


  @Id
  @Column(name = "id")
  private int id = Integer.MAX_VALUE;
  @Column(name = "username")
  private String username;
  @Column(name = "realname")
  private String realname;
  @Column(name = "email")
  private String email;

  public String getUsername() {
    return username;
  }

  public UserData withName(String username) {
    this.username = username;
    return this;
  }

  public String getEmail() {
    return email;
  }

  public UserData withEmail(String email) {
    this.email = email;
    return this;
  }
  public int getId() {
    return id;
  }

  public void withId(int id) {
    this.id = id;
  }

  public String getRealname() {
    return realname;
  }

  public void setRealname(String realname) {
    this.realname = realname;
  }
  @Override
  public String toString() {
    return "UserData{" +
            "id=" + id +
            ", username='" + username + '\'' +
            ", realname='" + realname + '\'' +
            ", email='" + email + '\'' +
            '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof UserData)) return false;
    UserData userData = (UserData) o;
    return getId() == userData.getId() &&
            Objects.equals(getUsername(), userData.getUsername()) &&
            Objects.equals(getEmail(), userData.getEmail());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getId(), getUsername(), getEmail());
  }
}