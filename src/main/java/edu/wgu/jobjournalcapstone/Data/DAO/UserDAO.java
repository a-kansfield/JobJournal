package edu.wgu.jobjournalcapstone.Data.DAO;


import edu.wgu.jobjournalcapstone.Data.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserDAO extends JpaRepository<User, Long> {
    User findUserById(long id);

}
