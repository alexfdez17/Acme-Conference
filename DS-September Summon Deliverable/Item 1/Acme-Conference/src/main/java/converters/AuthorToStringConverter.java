
package converters;

import javax.transaction.Transactional;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import domain.Author;

@Component
@Transactional
public class AuthorToStringConverter implements Converter<Author, String> {

	@Override
	public String convert(final Author a) {

		String res;

		if (a == null)
			res = null;
		else
			res = String.valueOf(a.getId());
		return res;
	}

}
