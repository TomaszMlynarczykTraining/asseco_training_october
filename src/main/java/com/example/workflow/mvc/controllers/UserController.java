package com.example.workflow.mvc.controllers;

import com.example.workflow.mvc.entity.Client;
import com.example.workflow.mvc.entity.User;
import com.example.workflow.mvc.repository.UserRepository;
import com.example.workflow.mvc.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1/")
@RequiredArgsConstructor
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ClientService clientService;

    // get all employees
    @GetMapping("/user")
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @GetMapping("/client")
    public List<Client> getAllClients() {
        return clientService.getClients();
    }


    @PostMapping("/login")
    public User login(@RequestBody User user) {

        Optional<User> byLogin = userRepository.getByLogin(user.getLogin());

        if (byLogin.isPresent() && byLogin.get().getPassword().equals(user.getLogin())) {
            byLogin.get().setIsLogged("1");
            return userRepository.save(user);
        } else {
            throw new RuntimeException("User not found");
        }
    }


    // create employee rest api
    @PostMapping("/user")
    public User createUser(@RequestBody User user) {
        return userRepository.save(user);
    }

    // get employee by id rest api
    @GetMapping("/user/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not exist with id :" + id));
        return ResponseEntity.ok(user);
    }

    // update employee rest api

    @PutMapping("/user/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User userDetails) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not exist with id :" + id));

        user.setFirstName(userDetails.getFirstName());
        user.setLastName(userDetails.getLastName());
        user.setEmailId(userDetails.getEmailId());

        User updatedUser = userRepository.save(user);
        return ResponseEntity.ok(updatedUser);
    }

    // delete employee rest api
    @DeleteMapping("/user/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteUser(@PathVariable Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not exist with id :" + id));

        userRepository.delete(user);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }


}
