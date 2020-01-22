package cm.genie6.risehope.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cm.genie6.risehope.model.Account;
import cm.genie6.risehope.model.Organization;
import cm.genie6.risehope.persistence.OrganizationRepository;

@Service
public class OrganizationService {
	@Autowired
	private OrganizationRepository organizationRepository;

	public void saveOrganization(Organization organization) {
		organizationRepository.save(organization);
	}

	public List<Organization> getAllOrganisations() {
		return organizationRepository.findAll();
	}

	public Organization getIdOrganization(Integer id) {
		return organizationRepository.findById(id).get();
	}
	
	public List<Organization> getOrnizationOf(Account account){
		return organizationRepository.findByUsermember(account);
	}
}
