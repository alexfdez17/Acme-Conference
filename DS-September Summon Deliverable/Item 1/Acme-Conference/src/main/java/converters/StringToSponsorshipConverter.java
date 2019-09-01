
package converters;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import repositories.SponsorshipRepository;
import domain.Sponsorship;

@Component
@Transactional
public class StringToSponsorshipConverter implements Converter<String, Sponsorship> {

	@Autowired
	SponsorshipRepository	sponsorshipRepository;


	@Override
	public Sponsorship convert(final String source) {
		final Sponsorship sponsorship;
		int id;

		try {
			id = Integer.valueOf(source);
			sponsorship = this.sponsorshipRepository.findOne(id);
		} catch (final Throwable t) {

			throw new IllegalArgumentException(t);

		}
		return sponsorship;
	}

}
