package cm.genie6.risehope.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cm.genie6.risehope.model.Opportunity;
@Repository
public interface OpportunityRepository extends JpaRepository<Opportunity, Integer> {
	List<Opportunity> findAllByOrderByIdopportunityDesc();
}
