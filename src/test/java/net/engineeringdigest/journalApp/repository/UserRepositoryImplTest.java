package net.engineeringdigest.journalApp.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserRepositoryImplTest {

    @Autowired
    private UserRepositoryImpl userRepoImpl;

    @Test
    public void testGetUserForSA(){
        Assertions.assertNotNull(userRepoImpl.getUserForSA());
    }

}
