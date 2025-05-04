package edu.wgu.jobjournalcapstone.Data.DAO;


import edu.wgu.jobjournalcapstone.Data.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDAO extends JpaRepository<User, Long> {

}
