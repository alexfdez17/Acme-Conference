
package services;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import repositories.ConferenceRepository;

@Service
@Transactional
public class ConferenceService {

	// Managed Repository
	@Autowired
	private ConferenceRepository	conferenceRepository;


	// Supported services

	// CRUD

	// Other business methods

	//Stats for dashboard

	public List<Double> minMaxAvgStddevFee() {
		return this.conferenceRepository.minMaxAvgStddevFee();
	}

	public List<Double> minMaxAvgStddevDays() {
		return this.conferenceRepository.minMaxAvgStddevDays();
	}

	public Double[] applicationsPerRookieStats() {

		//this.administratorService.findByPrincipal();

		final Double[] res = new Double[4];
		final Collection<Long> stats = this.conferenceRepository.getConferencesPerCategory();

		if (!stats.isEmpty()) {
			Double sum = 0.0;
			for (final Long n : stats)
				sum += n;
			res[0] = sum / stats.size(); // Average

			res[1] = Collections.min(stats).doubleValue(); //Minimum

			res[2] = Collections.max(stats).doubleValue(); //Maximum

			Double num = 0.0;
			Double numi = 0.0;
			for (final Long n : stats) {
				numi = Math.pow(n - res[0], 2);
				num += numi;
			}
			if (num != 0)
				res[3] = Math.sqrt(num / (stats.size() - 1)); // Standard
																// deviation
			else
				res[3] = 0.;
		} else {
			res[0] = 0.;
			res[1] = 0.;
			res[2] = 0.;
			res[3] = 0.;
		}

		return res;
	}

}
