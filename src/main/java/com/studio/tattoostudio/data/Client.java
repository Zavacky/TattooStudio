package com.studio.tattoostudio.Data;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
public class Client {
    private Long id;
    private String login;
    private String password;
    private String name;
    private String surname;
    private String mail;
    private String phoneNumber;
    private List<DateOfTattoo> datesOfTattoos;

    public Client(Long id, String login, String password, String name, String surname, String mail, String phoneNumber) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.mail = mail;
        this.phoneNumber = phoneNumber;
    }

    public Client(Long id, String name, String surname, String mail, String phoneNumber) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.mail = mail;
        this.phoneNumber = phoneNumber;
    }

    public Client(String name, String surname, String mail, String phoneNumber) {
        this.name = name;
        this.surname = surname;
        this.mail = mail;
        this.phoneNumber = phoneNumber;
    }

    public static Client clone(Client client) {
        return new Client(client.getId(), client.getName(), client.getSurname(), client.getMail(), client.getPhoneNumber());
    }
}
