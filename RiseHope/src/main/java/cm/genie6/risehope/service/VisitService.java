package cm.genie6.risehope.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cm.genie6.risehope.model.Visit;
import cm.genie6.risehope.persistence.VisitRepository;

@Service
public class VisitService {
	
	private VisitRepository visitRepository;
	
	@Autowired
	public VisitService(VisitRepository visitRepository) {
		super();
		this.visitRepository = visitRepository;
	}
	
	public void saveVisit(Visit visit) {
		visitRepository.save(visit);
	}
	
	public Visit getVisitMoreRecent() {
		return visitRepository.findTop1ByOrderByIdvisitDesc();
	}
}
