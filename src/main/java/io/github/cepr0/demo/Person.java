package io.github.cepr0.demo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import java.util.Set;

@Data
@EqualsAndHashCode(of = "email")
@ToString(of = {"id", "name", "email"})
@Entity
@Table(name = "people")
public class Person {
    @Id
    @GeneratedValue
    private Long id;

    @JsonIgnore
    @Version
    private Short version;

    @Column(nullable = false, length = 32)
    private String name;

    @NaturalId
    @Column(nullable = false, length = 32)
    private String email;

    @JsonIgnoreProperties({"friends", "friendsOf", "invitedFriends", "invitedFriendsOf"})
    @ManyToMany
    @JoinTable(name = "friends", joinColumns = @JoinColumn(name = "person_id"), inverseJoinColumns = @JoinColumn(name = "friend_id"))
    private Set<Person> friends;

    @JsonIgnoreProperties({"friends", "friendsOf", "invitedFriends", "invitedFriendsOf"})
    @ManyToMany
    @JoinTable(name = "friends", joinColumns = @JoinColumn(name = "friend_id"), inverseJoinColumns = @JoinColumn(name = "person_id"))
    private Set<Person> friendsOf;

    @JsonIgnoreProperties({"friends", "friendsOf", "invitedFriends", "invitedFriendsOf"})
    @ManyToMany
    @JoinTable(name = "invited_friends", joinColumns = @JoinColumn(name = "person_id"), inverseJoinColumns = @JoinColumn(name = "friend_id"))
    private Set<Person> invitedFriends;

    @JsonIgnoreProperties({"friends", "friendsOf", "invitedFriends", "invitedFriendsOf"})
    @ManyToMany
    @JoinTable(name = "invited_friends", joinColumns = @JoinColumn(name = "friend_id"), inverseJoinColumns = @JoinColumn(name = "person_id"))
    private Set<Person> invitedFriendsOf;
}
