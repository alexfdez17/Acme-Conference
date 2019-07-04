
package converters;

import javax.transaction.Transactional;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import domain.Comment;

@Component
@Transactional
public class CommentToStringConverter implements Converter<Comment, String> {

	@Override
	public String convert(final Comment c) {

		String res;

		if (c == null)
			res = null;
		else
			res = String.valueOf(c.getId());
		return res;
	}

}
