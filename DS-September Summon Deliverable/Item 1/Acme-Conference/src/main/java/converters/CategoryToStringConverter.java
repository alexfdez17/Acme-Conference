
package converters;

import javax.transaction.Transactional;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import domain.Category;

@Component
@Transactional
public class CategoryToStringConverter implements Converter<Category, String> {

	@Override
	public String convert(final Category c) {

		String res;

		if (c == null)
			res = null;
		else
			res = String.valueOf(c.getId());
		return res;
	}

}
