package com.mycompany.smart_irrigation.security;

import com.mycompany.smart_irrigation.entities.Role;
import com.mycompany.smart_irrigation.entities.RoleDTO;
import com.mycompany.smart_irrigation.entities.User;
import com.mycompany.smart_irrigation.repositories.UserRepository;
import com.mycompany.smart_irrigation.security.Exception.UserAlreadyExistException;
import com.mycompany.smart_irrigation.security.Exception.UserForbiddenException;
import com.mycompany.smart_irrigation.security.Exception.UserNotAuthorizedException;
import com.mycompany.smart_irrigation.security.Exception.UserNotFoundException;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import javax.security.enterprise.SecurityContext;
import javax.security.enterprise.identitystore.Pbkdf2PasswordHash;
import java.security.Principal;
import java.util.*;
import java.util.stream.Collectors;


@ApplicationScoped
public class SecurityService {

    @Inject
    private UserRepository repository;

    @Inject
    private Pbkdf2PasswordHash passwordHash;

    @Inject
    private SecurityContext securityContext;

    private Random id = new Random();

    public void create(User user) {

        if (repository.existsById(user.getUserId().toString())) {

            throw new UserAlreadyExistException("There is an user with this id: " + user.getUserId());

        } else {


            user = User.builder()

                    .withPasswordHash(passwordHash)

                    .withPassword(user.getPassword())
                    .withUserId(id.nextLong())
                    .withUsername(user.getUsername())
                    .withEmail((user.getEmail()))
                    .withRoles(getRole())

                    .build();

            repository.save(user);

        }

    }

    public void updatePassword(String id, User dto) {

        final Principal principal = securityContext.getCallerPrincipal();

        if (isForbidden(id, securityContext, principal)) {

            throw new UserForbiddenException();

        }

        final User user = repository.findById(id)

                .orElseThrow(() -> new UserNotFoundException(id));

        user.updatePassword(dto.getPassword(), passwordHash);

        repository.save(user);

    }


    public void addRole(String id, RoleDTO dto) {

        final User user = repository.findById(id)

                .orElseThrow(() -> new UserNotFoundException(id));

        user.addRoles(dto.getRoles());

        repository.save(user);

    }

    public User getUser() {

        final Principal principal = securityContext.getCallerPrincipal();

        if (principal == null) {

            throw new UserNotAuthorizedException();

        }

        final User user = repository.findById(principal.getName())

                .orElseThrow(() -> new UserNotFoundException(principal.getName()));

        return user;
    }

    public List<User> getUsers() {
        return repository.findAll()

                .collect(Collectors.toList());

    }

    public void delete(String id){
        repository.deleteById(id);
    }

    public User findBy(String username) {
        return repository.findById(username)
                .orElseThrow(() -> new UserNotAuthorizedException());
    }

    public User login(String username, String password) {
        final User user = repository.findByUsername(username);

        System.out.println(user.getUsername());
        System.out.println(password);
        System.out.println(username);
        if (Objects.equals(username,user.getUsername())) {
            return user;
        }
        throw new UserNotAuthorizedException();

    }


    private Set<Role> getRole() {

        if (repository.count() == 0) {

            return Collections.singleton(Role.ADMIN);
        } else {

            return Collections.singleton(Role.USER);
        }
    }

    private boolean isForbidden(String id, SecurityContext context, Principal principal) {
        return !(context.isCallerInRole(Role.ADMIN.name()) || id.equals(principal.getName()));
    }
}
