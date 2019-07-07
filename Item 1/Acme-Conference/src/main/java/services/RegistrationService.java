
package services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import repositories.RegistrationRepository;

@Service
@Transactional
public class RegistrationService {

	// Managed repository
	@Autowired
	private RegistrationRepository	registrationRepository;


	// ------------------

	// Supporting services

	// -------------------

	// Simple CRUD methods

	// -------------------

	// Other business methods
	// ----------------------

	// Dashboard

	public List<Double> minMaxAvgStddevPerConference() {
		return this.registrationRepository.minMaxAvgStddevPerConference();
	}

	// ---------

	// Auxiliary methods

	// -----------------

}
