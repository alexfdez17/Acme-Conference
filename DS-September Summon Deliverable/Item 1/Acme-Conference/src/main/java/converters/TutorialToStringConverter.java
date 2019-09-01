
package converters;

import javax.transaction.Transactional;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import domain.Tutorial;

@Component
@Transactional
public class TutorialToStringConverter implements Converter<Tutorial, String> {

	@Override
	public String convert(final Tutorial t) {

		String res;

		if (t == null)
			res = null;
		else
			res = String.valueOf(t.getId());
		return res;
	}

}
