package edu.esiea.finals.tutoapi.dao;

import edu.esiea.finals.tutoapi.interfaces.IDAO;
import edu.esiea.finals.tutoapi.models.Step;
import edu.esiea.finals.tutoapi.models.Tutorial;
import edu.esiea.finals.tutoapi.models.Material;

public class DAOFactory {

	public static IDAO<Tutorial> getTutorialDAO() {
		return new GenericDAO<Tutorial>(Tutorial.class);
	}

	public static IDAO<Step> getStepDAO() {
		return new GenericDAO<Step>(Step.class);
	}

	public static IDAO<Material> getMaterialDAO() {
		return new GenericDAO<Material>(Material.class);
	}

}
