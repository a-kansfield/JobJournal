package edu.wgu.jobjournalcapstone.Data.DAO;


import edu.wgu.jobjournalcapstone.Data.Entity.Application;
import edu.wgu.jobjournalcapstone.Data.Entity.Status;
import edu.wgu.jobjournalcapstone.Data.Entity.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface ApplicationDAO extends JpaRepository<Application, Long> {
    List<Application> getApplicationsByUser(User user);

    List<Application> getApplicationsByUserAndStatus(User user, Status status);

    @Query(value =  "SELECT * "
            +       "FROM applications "
            +       "WHERE user_id = :userID "
            +           "AND (job_title LIKE '%'||:query||'%' "
            +           "OR employer LIKE '%'||:query||'%');",
            nativeQuery = true)
    List<Application> searchByString(@Param("userID") long userID, @Param("query") String query);


    @Modifying(clearAutomatically = true)
    @Transactional
    @Query(value = "DELETE FROM applications WHERE id = :appID", nativeQuery = true)
    void delete(Long appID);
}
