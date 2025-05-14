package edu.wgu.jobjournalcapstone;

import edu.wgu.jobjournalcapstone.Data.DAO.UserDAO;
import edu.wgu.jobjournalcapstone.Data.Entity.User;
import edu.wgu.jobjournalcapstone.Service.ParserService;
import org.aspectj.lang.annotation.After;
import org.hibernate.annotations.Source;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junitpioneer.jupiter.json.JsonFileSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class UserTests {

    @Autowired
    private ParserService parserService;
    @Autowired
    private UserDAO userDAO;


    @AfterEach
    void deleteData(){
        List<User> users = userDAO.findByFirstName("Test7132");
        userDAO.deleteAll(users);
    }

    // Utilizes simulated front end input.
    // Tests that a user can effectively be saved, as well as if the password is properly encrypted.
    @ParameterizedTest
    @JsonFileSource("src/test/java/edu/wgu/jobjournalcapstone/TestFiles/duplicateEmail.json")
    void createUser(Map<String, Object> stringObjectMap) {
        User user = parserService.parseUser(stringObjectMap);
        User savedUser = userDAO.save(user);

        // Assert that user has an ID and that it has been changed from the default.
        assert savedUser.getId() != null && savedUser.getId() != -1;

        // Asserts that the password has been encrypted.
        assert savedUser.getPassword() != stringObjectMap.get("password");
    }

    // Asserts that a user is findable by a known email
    @ParameterizedTest
    @CsvSource({"email@email.com"})
    void findByEmail(String email){
        User user = userDAO.findByEmail(email);
        assert user != null;
    };

    // Asserts that a user cannot enter an email that has already been used.
    @ParameterizedTest
    @JsonFileSource("src/test/java/edu/wgu/jobjournalcapstone/TestFiles/duplicateEmail.json")
    void duplicateEmailThrowsException(Map<String, Object> jsonPayload) {
        User user1 = parserService.parseUser(jsonPayload);
        Exception e = assertThrows(Exception.class, () -> {
            User user2 = parserService.parseUser(jsonPayload);
        });

    }

}
