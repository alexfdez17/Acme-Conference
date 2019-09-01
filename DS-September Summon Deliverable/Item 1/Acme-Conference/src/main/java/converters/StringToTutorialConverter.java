
package converters;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import repositories.TutorialRepository;
import domain.Tutorial;

@Component
@Transactional
public class StringToTutorialConverter implements Converter<String, Tutorial> {

	@Autowired
	TutorialRepository	tutorialRepository;


	@Override
	public Tutorial convert(final String source) {
		final Tutorial tutorial;
		int id;

		try {
			id = Integer.valueOf(source);
			tutorial = this.tutorialRepository.findOne(id);
		} catch (final Throwable t) {

			throw new IllegalArgumentException(t);

		}
		return tutorial;
	}

}
