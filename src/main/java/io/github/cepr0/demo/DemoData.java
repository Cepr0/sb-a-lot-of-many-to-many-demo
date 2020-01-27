package io.github.cepr0.demo;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Component
public class DemoData {

    private final PersonRepo personRepo;

    public DemoData(PersonRepo personRepo) {
        this.personRepo = personRepo;
    }

    @Transactional
    @EventListener(ApplicationReadyEvent.class)
    public void populateData() {
        Person john = new Person().setName("John").setEmail("john@mail.com");
        Person joan = new Person().setName("Joan").setEmail("joan@mail.com");
        Person joe = new Person().setName("Joe").setEmail("joe@mail.com");
        Person jin = new Person().setName("Jin").setEmail("jin@mail.com");
        personRepo.saveAll(List.of(john, joan, joe, jin));

        john.setFriends(Set.of(joan, joe));
        john.setInvitedFriends(Set.of(joan, joe));

        joan.setFriends(Set.of(jin, joe, john));
        joan.setInvitedFriends(Set.of(jin, joe, john));

        joe.setFriends(Set.of(john, joan));
        joe.setInvitedFriends(Set.of(john, joan));

        jin.setFriends(Set.of(joan));
        jin.setInvitedFriends(Set.of(joan));
    }
}
