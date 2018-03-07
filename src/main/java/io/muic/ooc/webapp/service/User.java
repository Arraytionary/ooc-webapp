package io.muic.ooc.webapp.service;

public class User {
    String userName;
    String password;
    String name;
    String email;
    String lastname;

    public User(String userName, String password, String name, String email, String lastname) {
        this.userName = userName;
        this.password = password;
        this.name = name;
        this.email = email;
        this.lastname = lastname;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getLastname() {
        return lastname;
    }
}
