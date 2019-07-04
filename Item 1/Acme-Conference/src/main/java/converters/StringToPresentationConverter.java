
package converters;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import repositories.PresentationRepository;
import domain.Presentation;

@Component
@Transactional
public class StringToPresentationConverter implements Converter<String, Presentation> {

	@Autowired
	PresentationRepository	presentationRepository;


	@Override
	public Presentation convert(final String source) {
		final Presentation presentation;
		int id;

		try {
			id = Integer.valueOf(source);
			presentation = this.presentationRepository.findOne(id);
		} catch (final Throwable t) {

			throw new IllegalArgumentException(t);

		}
		return presentation;
	}

}
