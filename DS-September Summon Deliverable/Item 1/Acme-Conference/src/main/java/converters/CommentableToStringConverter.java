
package converters;

import javax.transaction.Transactional;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import domain.Commentable;

@Component
@Transactional
public class CommentableToStringConverter implements Converter<Commentable, String> {

	@Override
	public String convert(final Commentable c) {

		String res;

		if (c == null)
			res = null;
		else
			res = String.valueOf(c.getId());
		return res;
	}

}
