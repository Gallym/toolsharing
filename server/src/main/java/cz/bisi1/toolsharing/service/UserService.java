package cz.bisi1.toolsharing.service;

import cz.bisi1.toolsharing.controller.dto.UserDto;
import cz.bisi1.toolsharing.entity.UserEntity;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;
import java.util.Optional;

public interface UserService extends UserDetailsService {

    /**
     * Finds all users in the system.
     * @return list of all users are in the system
     */
    List<UserEntity> findAllUsers();

    /**
     * Finds user by id
     * @param id the identifier, a number which uniquely identifies an user
     * @return If user with this id was found, return Optional with non-null value. Then value will be UserEntity object.
     * Otherwise return empty Optional object.
     */
    Optional<UserEntity> findUserById(Long id);

    /**
     * Tries to save new UserEntity object. If it is already exists, update it,
     * otherwise add new UserEntity object to the system
     * @param user is object to save
     * @return saved UserEntity object
     */
    UserEntity saveUser(UserEntity user);

    /**
     * Finds the UserEntity object identifies by id and update it's data
     * @param id the identifier, a number that uniquely identifies a UserEntity object should be updated
     * @param user is object to change to
     * @return updated UserEntity
     */
    UserEntity updateUser(Long id, UserDto user);

    /**
     * Tries to find UserEntity object by id and delete it from the system
     * @param id the identifier, a number that uniquely identifies an user.
     */
    void deleteUser(Long id);
}
