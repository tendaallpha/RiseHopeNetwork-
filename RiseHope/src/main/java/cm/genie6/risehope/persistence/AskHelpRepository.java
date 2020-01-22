package cm.genie6.risehope.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cm.genie6.risehope.model.AskHelp;

@Repository
public interface AskHelpRepository extends JpaRepository<AskHelp, Integer> {
}
