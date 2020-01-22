package cm.genie6.risehope.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cm.genie6.risehope.model.Account;
import cm.genie6.risehope.model.Child;

@Repository
public interface ChildRepository extends JpaRepository<Child, Integer> {
	List<Child> findByOwnerchildren(Account account);
	List<Child> findByAdopted(boolean adopted);
	}
