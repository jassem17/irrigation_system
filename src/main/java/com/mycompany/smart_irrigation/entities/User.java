/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.smart_irrigation.entities;

import com.mycompany.smart_irrigation.FieldPropertyVisibilityStrategy;
import jakarta.nosql.mapping.Column;
import jakarta.nosql.mapping.Entity;
import jakarta.nosql.mapping.Id;

import javax.json.bind.annotation.JsonbVisibility;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.security.enterprise.identitystore.Pbkdf2PasswordHash;
import java.io.Serializable;
import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import static java.util.Objects.requireNonNull;
import static java.util.stream.Collectors.toUnmodifiableSet;


@Entity
@JsonbVisibility(FieldPropertyVisibilityStrategy.class)
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long userId;
    @Column
    private String username;
    @Column
    private String email;
    @Column
    private String password;

    @Column
    private Set<Role> roles;

    private Pbkdf2PasswordHash passwordHash;




    public User() {
    }

    public User(Long userId, String username,String email,String password , Set<Role> roles) {
        this.userId=userId;
        this.username=username;
        this.roles=roles;
        this.email=email;
        this.password=password;
    }

    public Long getUserId() {
        return userId;
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }
    public Set<String> getRoles() {
        if (roles == null) {
            return Collections.emptySet();
        }
        return roles.stream().map(Role::get)
                .collect(Collectors.toUnmodifiableSet());
    }

    public String getEmail() {
        return email;
    }

    public void updatePassword(String password, Pbkdf2PasswordHash passwordHash) {
        this.password = passwordHash.generate(password.toCharArray());
    }

    public void addRoles(Set<Role> roles) {
        if (this.roles == null) {
            this.roles = new HashSet<>();
        }
        this.roles.addAll(roles);
    }
    public boolean isAdmin() {
        return getRoles().stream().anyMatch(Role.ADMIN::equals);
    }
    public static UserBuilder builder() {
        return new UserBuilder();
    }

    public static class UserBuilder {

        private Long userId;

        private String username;
        private String email;

        private String password;
        private Set<Role> roles;

        private Pbkdf2PasswordHash passwordHash;

        private UserBuilder() {
        }
        public UserBuilder( Long userId,String username,String email,String password , Set<Role> roles) {
            this.userId=userId;
            this.username=username;
            this.roles=roles;
            this.email=email;
            this.password=password;
        }

        public UserBuilder withUserId(Long userId) {
            this.userId = userId;
            return this;
        }
        public UserBuilder withUsername(String username) {
            this.username = username;
            return this;
        }


        public UserBuilder withEmail(String email) {
            this.email = email;
            return this;
        }


        public UserBuilder withPassword(String password) {
            this.password = password;
            return this;
        }

        public UserBuilder withRoles(Set<Role> roles) {
            this.roles = roles;
            return this;
        }

        public UserBuilder withPasswordHash(Pbkdf2PasswordHash passwordHash) {
            this.passwordHash = passwordHash;
            return this;
        }
        public User build() {
            User user = new User();
            user.roles = roles;
            user.userId = userId;
            user.username = username;
            user.email = email;
            user.password = passwordHash.generate(password.toCharArray());
            return user;
        }


    }



    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof User)) {
            return false;
        }
        User person = (User) o;
        return Objects.equals(username, person.username);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(username);
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", email=" + email +
                ", roles=" + roles +
                ", password=" + password +
                '}';
    }
}
