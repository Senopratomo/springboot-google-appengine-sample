package org.senolab.springbootappenginedatastoredemo.controller;

import org.senolab.springbootappenginedatastoredemo.model.BatchUser;
import org.senolab.springbootappenginedatastoredemo.model.Message;
import org.senolab.springbootappenginedatastoredemo.model.User;
import org.senolab.springbootappenginedatastoredemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController()
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/user-api/v1/hello")
    public ResponseEntity<Message> hello() {
        return ResponseEntity.ok(new Message("Hello World!"));
    }

    @GetMapping("/user-api/v1/hello/{id}")
    public ResponseEntity<Message> hello(@PathVariable("id") String id) {
        User user = userService.getUser(id);
        return ResponseEntity.ok(new Message("Hello "+user.getFullName()));
    }

    @RequestMapping(value = "/user-api/v1/users", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @RequestMapping(value = "/user-api/v1/users/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> getUser(@PathVariable("id") String id) {
        return ResponseEntity.ok(userService.getUser(id));
    }
    /**
     * Upsert new user
     *
     * @param
     * @return
     */
    @RequestMapping(value = "/user-api/v1/users", method = RequestMethod.POST)
    public ResponseEntity<Message> addUser(@Valid @RequestBody User user) {
        userService.createUser(user);
        return ResponseEntity.ok().body(new Message("Created"));
    }

    /**
     * Upsert new users
     *
     * @param users
     * @return
     */
    @RequestMapping(value = "/user-api/v1/batch", method = RequestMethod.POST)
    public ResponseEntity<Message> addUsers(@Valid @RequestBody BatchUser users) {
        userService.createUser(users);
        return ResponseEntity.ok().body(new Message("Batch created: " + users.getUsers().size()));
    }

    /**
     * Update existing user
     *
     * @param id
     * @param user
     * @return
     */
    @RequestMapping(value = "/user-api/v1/users/{id}", method = RequestMethod.PUT)
    private ResponseEntity<User> updateUser(@PathVariable("id") String id, @Valid @RequestBody User user) {
        return ResponseEntity.ok().body(userService.updateUser(user));
    }

    /**
     * Delete a user
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/user-api/v1/users/{id}", method = RequestMethod.DELETE)
    private ResponseEntity<Message> deleteUser(@PathVariable("id") String id) {
        return ResponseEntity.ok().body(new Message(userService.deleteUser(id)));
    }

}
