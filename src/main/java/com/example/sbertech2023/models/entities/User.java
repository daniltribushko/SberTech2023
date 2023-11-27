package com.example.sbertech2023.models.entities;

import com.example.sbertech2023.models.enums.RecordState;
import com.example.sbertech2023.models.enums.Role;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Cascade;

import java.util.Set;

/**
 * @author Tribushko Danil
 * @since 27.11.2023
 *
 * Сущность пользователя
 */
@Entity
@Getter
@Setter
@Table(name = "users")
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "login", nullable = false)
    private String login;

    @Column(name = "sureName", nullable = false)
    private String sureName;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "record_state", nullable = false)
    @Enumerated(EnumType.STRING)
    private RecordState recordState;

    @CollectionTable(name = "user_role", joinColumns = @JoinColumn(name = "id_user"))
    @Enumerated(EnumType.STRING)
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    private Set<Role> roles;

    public User(String login, String sureName, String name, String password) {
        this.login = login;
        this.sureName = sureName;
        this.name = name;
        this.password = password;
        recordState = RecordState.ACTIVE;
    }
}
