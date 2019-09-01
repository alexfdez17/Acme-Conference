
package converters;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import repositories.FinderRepository;
import domain.Finder;

@Component
@Transactional
public class StringToFinderConverter implements Converter<String, Finder> {

	@Autowired
	FinderRepository	finderRepository;


	@Override
	public Finder convert(final String source) {
		final Finder finder;
		int id;

		try {
			id = Integer.valueOf(source);
			finder = this.finderRepository.findOne(id);
		} catch (final Throwable t) {

			throw new IllegalArgumentException(t);

		}
		return finder;
	}

}
