
package converters;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import repositories.ActivityRepository;
import domain.Activity;

@Component
@Transactional
public class StringToActivityConverter implements Converter<String, Activity> {

	@Autowired
	ActivityRepository	activityRepository;


	@Override
	public Activity convert(final String source) {
		final Activity activity;
		int id;

		try {
			id = Integer.valueOf(source);
			activity = this.activityRepository.findOne(id);
		} catch (final Throwable t) {

			throw new IllegalArgumentException(t);

		}
		return activity;
	}

}
