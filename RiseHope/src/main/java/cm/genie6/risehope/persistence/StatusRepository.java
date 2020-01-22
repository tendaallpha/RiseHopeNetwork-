package cm.genie6.risehope.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import cm.genie6.risehope.model.Account;
import cm.genie6.risehope.model.Status;

public interface StatusRepository extends JpaRepository<Status, Integer> {

	List<Status> findByOwnerstatus(Account idowner);
	Status findTop1ByOrderByIdstatusDesc();
}
