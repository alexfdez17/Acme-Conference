
package converters;

import javax.transaction.Transactional;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import domain.Reviewer;

@Component
@Transactional
public class ReviewerToStringConverter implements Converter<Reviewer, String> {

	@Override
	public String convert(final Reviewer r) {

		String res;

		if (r == null)
			res = null;
		else
			res = String.valueOf(r.getId());
		return res;
	}

}
