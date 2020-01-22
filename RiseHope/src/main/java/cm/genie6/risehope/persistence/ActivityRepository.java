package cm.genie6.risehope.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cm.genie6.risehope.model.Account;
import cm.genie6.risehope.model.Activity;

@Repository
public interface ActivityRepository extends JpaRepository<Activity, Integer> {
	List<Activity> findByOwneractivity(Account idowner);

	List<Activity> findAllByOrderByIdactivityDesc();

	Activity findTop1ByOrderByIdactivityDesc();
}
