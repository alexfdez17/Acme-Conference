
package converters;

import javax.transaction.Transactional;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import domain.Presentation;

@Component
@Transactional
public class PresentationToStringConverter implements Converter<Presentation, String> {

	@Override
	public String convert(final Presentation p) {

		String res;

		if (p == null)
			res = null;
		else
			res = String.valueOf(p.getId());
		return res;
	}

}
