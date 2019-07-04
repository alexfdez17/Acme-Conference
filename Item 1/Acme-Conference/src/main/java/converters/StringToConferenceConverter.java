
package converters;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import repositories.ConferenceRepository;
import domain.Conference;

@Component
@Transactional
public class StringToConferenceConverter implements Converter<String, Conference> {

	@Autowired
	ConferenceRepository	conferenceRepository;


	@Override
	public Conference convert(final String source) {
		final Conference conference;
		int id;

		try {
			id = Integer.valueOf(source);
			conference = this.conferenceRepository.findOne(id);
		} catch (final Throwable t) {

			throw new IllegalArgumentException(t);

		}
		return conference;
	}

}
