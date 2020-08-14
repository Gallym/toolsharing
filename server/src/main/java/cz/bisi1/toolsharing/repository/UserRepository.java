package cz.bisi1.toolsharing.repository;

import cz.bisi1.toolsharing.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * <h1>UserRepository</h1>
 * The UserRepository interface implements an application that
 * manages users in database.
 */
@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    /**
     * Gets user specified by email
     * @param email The String represents user's email
     * @return if user with the email was found then return this user, otherwise return empty Optional
     */
    Optional<UserEntity> findUserByEmail(String email);
}
