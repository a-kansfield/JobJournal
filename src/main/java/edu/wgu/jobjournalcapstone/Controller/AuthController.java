package edu.wgu.jobjournalcapstone.Controller;

import edu.wgu.jobjournalcapstone.Beans.AuthenticationBean;
import edu.wgu.jobjournalcapstone.Data.DAO.UserDAO;
import edu.wgu.jobjournalcapstone.Data.Entity.User;
import edu.wgu.jobjournalcapstone.Service.ParserService;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@CrossOrigin(origins = {
        "http://localhost:4200",
        "https://a-kansfield.github.io/job-journal/"
})
public class AuthController {

    private static final Logger log = LoggerFactory.getLogger(AuthController.class);
    @Autowired
    private UserDAO userDAO;
    @Autowired
    private ParserService parserService;

    private HttpServletRequest request;
    @PostMapping(path = "/basic-auth")
    public AuthenticationBean authenticateUser(
            @RequestBody Map<String, Object> payload
    ){

        try {
            User user = userDAO.findByEmailAndPassword(
                    payload.get("email").toString(),
                    payload.get("password").toString()
            );
            return new AuthenticationBean(user.getId());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
