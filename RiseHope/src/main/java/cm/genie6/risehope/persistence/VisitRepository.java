package cm.genie6.risehope.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cm.genie6.risehope.model.Visit;

@Repository
public interface VisitRepository extends JpaRepository<Visit, Integer> {
	Visit findTop1ByOrderByIdvisitDesc();
}
