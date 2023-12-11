package com.studio.tattoostudio.Data;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Client {
    private Long id;
    private String name;
    private String surname;
    private String mail;
    private String phoneNumber;

    public Client(String name, String surname, String mail, String phoneNumber) {
        this.name = name;
        this.surname = surname;
        this.mail = mail;
        this.phoneNumber = phoneNumber;
    }

    public static Client clone(Client client) {
        return new Client(client.getName(), client.getSurname(), client.getMail(), client.getPhoneNumber());
    }
}
