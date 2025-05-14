package edu.wgu.jobjournalcapstone;

import edu.wgu.jobjournalcapstone.Data.DAO.ApplicationDAO;
import edu.wgu.jobjournalcapstone.Data.DAO.UserDAO;
import edu.wgu.jobjournalcapstone.Data.Entity.Application;
import edu.wgu.jobjournalcapstone.Data.Entity.User;
import edu.wgu.jobjournalcapstone.Service.ParserService;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junitpioneer.jupiter.json.JsonSource;
import org.junitpioneer.jupiter.json.JsonFileSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Map;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class ApplicationTests {

    @Autowired
    private ApplicationDAO applicationDAO;
    @Autowired
    private ParserService parserService;
    @Autowired
    private UserDAO userDAO;

//    @AfterEach
//    void cleanup(){
//        User user = userDAO.findById(27L).get();
//        applicationDAO.deleteAllByUser(user);
//    }
    // An application with no due date and an applied date should be able to be saved
    @ParameterizedTest
    @JsonFileSource("src/test/java/edu/wgu/jobjournalcapstone/TestFiles/appliedApplication.json")
    void createCompleteApplication(Map<String, Object> jsonPayload) {
        Application application = parserService.parseApplication(jsonPayload);

        // Asserts that date due is null and that date applied has a value.
        assert application.getDateDue() == null;
        assert application.getDateApplied() != null;

        Application savedApplication = applicationDAO.save(application);
        // Asserts that the application has been saved and that the ID has been changed from default.
        assert savedApplication != null;
        assert savedApplication.getId() != -1;
    }

    // An application with a due date and no applied date should be able to be saved
    @ParameterizedTest
    @JsonFileSource("src/test/java/edu/wgu/jobjournalcapstone/TestFiles/unfinishedApplication.json")
    void createUnfinishedApplication(Map<String, Object> jsonPayload) {
        Application application = parserService.parseApplication(jsonPayload);

        assert application.getDateDue() != null;
        assert application.getDateApplied() == null;

        Application savedApplication = applicationDAO.save(application);

        // Asserts that the application has been saved and that the ID has been changed from default.
        assert savedApplication != null;
        assert savedApplication.getId() != -1;
    }

    //Applications with no due date or applied date will fail to save
    @ParameterizedTest
    @JsonFileSource("src/test/java/edu/wgu/jobjournalcapstone/TestFiles/noDateApplication.json")
    void rejectApplicationWithNoDates(Map<String, Object> jsonPayload) {
        Exception e = assertThrows(Exception.class, () -> {
            Application application = parserService.parseApplication(jsonPayload);
        });
    }

    // Update Functionality
    @ParameterizedTest
    @JsonFileSource("src/test/java/edu/wgu/jobjournalcapstone/TestFiles/appliedApplication.json")
    void updateApplication(Map<String, Object> jsonPayload) {
        Application application = parserService.parseApplication(jsonPayload);
        Application savedApplication = applicationDAO.save(application);

        savedApplication.setJobTitle("New");
        savedApplication.setEmployer("New");
        Application editedApplication = applicationDAO.save(savedApplication);

        assertNotEquals(application.getEmployer(), editedApplication.getEmployer());
        assertNotEquals(application.getJobTitle(), editedApplication.getJobTitle());

    }

    // Delete Functionality
    @ParameterizedTest
    @JsonFileSource("src/test/java/edu/wgu/jobjournalcapstone/TestFiles/appliedApplication.json")
    void deleteApplication(Map<String, Object> jsonPayload){
        Application application = parserService.parseApplication(jsonPayload);

        Integer id = application.getId();
        applicationDAO.deleteById(Long.valueOf(id));
        assertNotNull(id);
    }
}
