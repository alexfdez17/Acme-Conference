
package converters;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import repositories.SponsorRepository;
import domain.Sponsor;

@Component
@Transactional
public class StringToSponsorConverter implements Converter<String, Sponsor> {

	@Autowired
	SponsorRepository	sponsorRepository;


	@Override
	public Sponsor convert(final String source) {
		final Sponsor sponsor;
		int id;

		try {
			id = Integer.valueOf(source);
			sponsor = this.sponsorRepository.findOne(id);
		} catch (final Throwable t) {

			throw new IllegalArgumentException(t);

		}
		return sponsor;
	}

}
