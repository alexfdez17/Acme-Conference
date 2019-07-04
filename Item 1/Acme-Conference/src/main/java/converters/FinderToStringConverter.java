
package converters;

import javax.transaction.Transactional;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import domain.Finder;

@Component
@Transactional
public class FinderToStringConverter implements Converter<Finder, String> {

	@Override
	public String convert(final Finder f) {

		String res;

		if (f == null)
			res = null;
		else
			res = String.valueOf(f.getId());
		return res;
	}

}
