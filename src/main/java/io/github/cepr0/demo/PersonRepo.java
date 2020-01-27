package io.github.cepr0.demo;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional(readOnly = true)
public interface PersonRepo extends JpaRepository<Person, Long> {
    @EntityGraph(attributePaths = {"friends", "friendsOf", "invitedFriends", "invitedFriendsOf"})
    Optional<Person> getById(Long aLong);
}
