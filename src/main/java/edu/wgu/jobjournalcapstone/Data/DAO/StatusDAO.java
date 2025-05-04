package edu.wgu.jobjournalcapstone.Data.DAO;

import edu.wgu.jobjournalcapstone.Data.Entity.Status;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StatusDAO extends JpaRepository<Status, Long> {
    Status findStatusById(long id);
}
