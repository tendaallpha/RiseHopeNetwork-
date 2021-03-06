package cm.genie6.risehope.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cm.genie6.risehope.model.Account;
import cm.genie6.risehope.model.Necessity;

@Repository
public interface NecessityRepository extends JpaRepository<Necessity, Integer> {
	List<Necessity> findByOwnernecessity(Account account);
}
