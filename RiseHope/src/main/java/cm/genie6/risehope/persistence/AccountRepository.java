package cm.genie6.risehope.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cm.genie6.risehope.model.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {

	Account findByCphone(int phone);

	List<Account> findByStatus(String account);

	List<Account> findPeopleDistinctByPlastnameOrPfirstnameContaining(String lastname, String firstname);

	List<Account> findPeopleDistinctByCcityContaining(String city);

	List<Account> findPeopleDistinctByCnameContaining(String centerName);
}
