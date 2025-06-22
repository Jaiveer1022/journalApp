package net.engineeringdigest.journalApp.service;

import lombok.extern.slf4j.Slf4j;
import net.engineeringdigest.journalApp.entity.User;
import net.engineeringdigest.journalApp.repository.UserRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Component
@Slf4j
public class UserService {

    @Autowired
    private UserRepository userRepository;

    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public  boolean saveNewUser(User entry) {
        entry.setPassword(passwordEncoder.encode(entry.getPassword()));
        entry.setRoles(Arrays.asList("USER"));
        return  saveUser(entry);
    }

    public boolean saveUser(User user){
        boolean saved = false;
        try{
            userRepository.save(user);
            saved =  true;
        }catch(Exception e){
            log.warn("Duplicate user credentials for : {}", user.getUserName(),e);
            saved = false;
        }
        return saved;
    }

    public  boolean saveNewAdmin(User entry) {
        entry.setPassword(passwordEncoder.encode(entry.getPassword()));
        entry.setRoles(Arrays.asList("USER", "ADMIN"));
        return  saveUser(entry);
    }

    public List<User> getAll(){
        return userRepository.findAll();
    }

    public Optional<User> findEntryById(ObjectId id) {
        return userRepository.findById(id);
    }

    public void deleteEntryById(ObjectId id){
        userRepository.deleteById(id);
    }

    public User findByUserName(String userName){
        return userRepository.findByUserName(userName);
    }

    public void deleteByUserName(String userName){
        userRepository.deleteByUserName(userName);
    }

}
