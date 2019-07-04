
package converters;

import javax.transaction.Transactional;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import domain.Submission;

@Component
@Transactional
public class SubmissionToStringConverter implements Converter<Submission, String> {

	@Override
	public String convert(final Submission s) {

		String res;

		if (s == null)
			res = null;
		else
			res = String.valueOf(s.getId());
		return res;
	}

}
