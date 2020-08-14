package cz.bisi1.toolsharing.controller;

import cz.bisi1.toolsharing.controller.dto.ReservationDto;
import cz.bisi1.toolsharing.controller.dto.UserDto;
import cz.bisi1.toolsharing.entity.ReservationEntity;
import cz.bisi1.toolsharing.entity.UserEntity;
import cz.bisi1.toolsharing.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * <h1>UserController</h1>
 * The UserController class implements an application that
 * manages users in frontend.
 */
@RestController
@RequestMapping("/api/user")
@CrossOrigin
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * Returns all users from database.
     * @return List of UserEntity in json format with status 200.
     */
    @GetMapping(value = "", produces = "application/json")
    public ResponseEntity<?> getAllUsers() {
        return ResponseEntity.ok(userService.findAllUsers());
    }

    /**
     * If user with that id exists method will return it.
     * @param id of the user that method will return.
     * @return UserEntity in json format with status 200 or nothing with status 404.
     */
    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<?> getUserById(@PathVariable(value = "id") Long id) {
        Optional<UserEntity> user = userService.findUserById(id);
        if (!user.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(user.get());
    }

    @GetMapping(value = "/{id}/reservation", produces = "application/json")
    public ResponseEntity<?> getUsersReservations(@PathVariable(value = "id") Long id) {
        Optional<UserEntity> user = userService.findUserById(id);
        if (!user.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        List<ReservationDto> reservations = new ArrayList<>();
        for (ReservationEntity res : user.get().getReservations()) {
            ReservationDto dto = new ReservationDto(res);
            dto.setToolId(res.getId());
            reservations.add(dto);
        }

        return ResponseEntity.ok(reservations);
    }

    /**
     * Creates new user and saves it in the database.
     * @param user that method will save in database.
     * @return UserEntity in json format with status 200.
     */
    @PostMapping(produces = "application/json")
    public ResponseEntity<?> createUser(@Valid @RequestBody UserEntity user) {
        return ResponseEntity.ok(userService.saveUser(user));
    }

    /**
     * If user with that id do exists changes data in reservation with id in first parameter
     * for data in second parameter.
     * @param id of the user that method will update.
     * @param user all the new data.
     * @return UserEntity in json format with status 200 or nothing with status 404.
     */
    @PutMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<?> updateUser(@PathVariable(value = "id") Long id, @Valid @RequestBody UserDto user) {
        if (!userService.findUserById(id).isPresent()) {
            return  ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(userService.updateUser(id, user));
    }

    /**
     * If user with that id do exists deletes it from database.
     * @param id of the user that method will delete.
     * @return nothing with status 200 or nothing with status 404.
     */
    @DeleteMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<?> deleteUser(@PathVariable(value = "id") Long id) {
        if (!userService.findUserById(id).isPresent()) {
            return  ResponseEntity.notFound().build();
        }
        userService.deleteUser(id);
        return ResponseEntity.ok().build();
    }
}
