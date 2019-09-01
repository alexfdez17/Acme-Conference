
package converters;

import javax.transaction.Transactional;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import domain.Sponsor;

@Component
@Transactional
public class SponsorToStringConverter implements Converter<Sponsor, String> {

	@Override
	public String convert(final Sponsor s) {

		String res;

		if (s == null)
			res = null;
		else
			res = String.valueOf(s.getId());
		return res;
	}

}
