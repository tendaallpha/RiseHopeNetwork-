package cm.genie6.risehope.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cm.genie6.risehope.model.Opportunity;
import cm.genie6.risehope.persistence.OpportunityRepository;

@Service
public class OpportunityService {
	
	private OpportunityRepository opportunityRepository;

	@Autowired
	public OpportunityService(OpportunityRepository opportunityRepository) {
		super();
		this.opportunityRepository = opportunityRepository;
	}
	
	public List<Opportunity> getOpportunities(){
		return opportunityRepository.findAllByOrderByIdopportunityDesc();
	}
	
	public void saveOpportunity(Opportunity opportunity) {
		opportunityRepository.save(opportunity);
	}
	
}
