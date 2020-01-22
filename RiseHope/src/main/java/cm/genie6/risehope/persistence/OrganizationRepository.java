package cm.genie6.risehope.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cm.genie6.risehope.model.Account;
import cm.genie6.risehope.model.Organization;

@Repository
public interface OrganizationRepository extends JpaRepository<Organization, Integer> {
	List<Organization> findByUsermember(Account account);
}
	