package cz.bisi1.toolsharing.service.impl;

import cz.bisi1.toolsharing.controller.dto.UserDto;
import cz.bisi1.toolsharing.entity.UserEntity;
import cz.bisi1.toolsharing.repository.UserRepository;
import cz.bisi1.toolsharing.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository repository;

    @Override
    public List<UserEntity> findAllUsers() {
        return repository.findAll();
    }

    @Override
    public Optional<UserEntity> findUserById(Long id) {
        return repository.findById(id);
    }

    @Override
    public UserEntity saveUser(UserEntity user) {
        return repository.save(user);
    }

    @Override
    public UserEntity updateUser(Long id, UserDto user) {
        UserEntity userDB = findUserById(id).get();
        userDB.setEmail(user.getEmail());
        userDB.setFirstName(user.getFirstName());
        userDB.setSecondName(user.getSecondName());
        userDB.setPhone(user.getPhone());
        userDB.set(userDB);
        return repository.save(userDB);
    }

    @Override
    public void deleteUser(Long id) {
        repository.deleteById(id);
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return repository.findUserByEmail(s).get();
    }
}
