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

import jakarta.security.enterprise.SecurityContext;
import jakarta.security.enterprise.identitystore.Pbkdf2PasswordHash;
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

        if (repository.existsById(user.getUserId().toString()) || repository.findByUsername(user.getUsername()).isPresent())  {

            throw new UserAlreadyExistException("There is an user with this username: " + user.getUsername());

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

    public void updatePassword(String username, User user1) {

        final Principal principal = securityContext.getCallerPrincipal();

        if (isForbidden(username, securityContext, principal)) {

            throw new UserForbiddenException();

        }

        final User user = repository.findByUsername(username)

                .orElseThrow(() -> new UserNotFoundException(username));

        System.out.println(user1.getPassword());
        user.updatePassword(user1.getPassword());

        repository.save(user);

    }


    public void addRole(String username, RoleDTO dto) {

        final User user = repository.findByUsername(username)

                .orElseThrow(() -> new UserNotFoundException(username));

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
        Optional<User> user = repository.findById(id);

        String userId = String.valueOf(user.get().getUserId());
        System.out.println("-------"+userId);
        System.out.println("-------"+id);
        if(id.substring(0, 14).equals(userId.substring(0, 14))){
            System.out.println("done");
            repository.deleteById(id);
        }
    }

    public User findBy(String username,String password) {
        final User user = repository.findByUsername(username)
                .orElseThrow(() -> new UserNotAuthorizedException());

        if (passwordHash.verify(password.toCharArray(), user.getPassword())) {
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
