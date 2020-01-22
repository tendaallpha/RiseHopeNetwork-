package cm.genie6.risehope.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cm.genie6.risehope.model.Account;
import cm.genie6.risehope.model.Planning;
import cm.genie6.risehope.persistence.PlanningRepository;

@Service
public class PlanningService {

	PlanningRepository planningRepository;

	@Autowired
	public PlanningService(PlanningRepository planningInterface) {
		super();
		this.planningRepository = planningInterface;
	}

	public void savePlanning(Planning planning) {
		planningRepository.save(planning);
	}

	public Planning getIdplanning(Integer idActivity) {
		return planningRepository.findById(idActivity).get();
	}

	public List<Planning> getPlanning() {
		return planningRepository.findAll();
	}

	public List<Planning> getPlanningOf(Account id) {
		return planningRepository.findByOwnerplanning(id);
	}

	public void deletePlanningActivity(Planning activity) {
		planningRepository.delete(activity);
	}
}
