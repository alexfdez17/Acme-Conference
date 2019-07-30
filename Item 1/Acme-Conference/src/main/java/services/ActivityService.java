
package services;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.ActivityRepository;
import domain.Activity;

@Service
@Transactional
public class ActivityService {

	// Managed Repository ----------------------------------------------------

	@Autowired
	private ActivityRepository	activityRepository;


	// Supported Services --------------------------------------------------

	//CRUD

	public Activity create() {
		Activity result;
		result = new Activity();

		return result;
	}

	public Activity save(final Activity activity) {
		Assert.notNull(activity);
		Activity result;
		result = this.activityRepository.save(activity);
		this.activityRepository.flush();
		return result;
	}

	public void delete(final Activity activity) {
		Assert.notNull(activity);
		Assert.isTrue(activity.getId() != 0);

		this.activityRepository.delete(activity);
	}

	public Collection<Activity> findAll() {
		Collection<Activity> result;

		result = this.activityRepository.findAll();

		return result;
	}

	public Activity findOne(final int activityId) {
		Activity result;

		result = this.activityRepository.findOne(activityId);
		Assert.notNull(result);

		return result;
	}

	public void flush() {
		this.activityRepository.flush();
	}

	// Other Business Methods

}
