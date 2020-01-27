package io.github.cepr0.demo;

import lombok.Value;

@Value
public class PersonDto {
    private long id;
    private String name;
    private String email;

    public PersonDto(Person person) {
        this.id = person.getId();
        this.name = person.getName();
        this.email = person.getEmail();
    }
}
