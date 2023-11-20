package jmp.dto;

import java.time.LocalDate;

public class User {
    String name;
    String surname;

    LocalDate birthday;

    public User(String name, String surname, LocalDate birthday) {
        this.name = name;
        this.surname = surname;
        this.birthday = birthday;
    }

    public LocalDate getBirthday() {
        return birthday;
    }
}
