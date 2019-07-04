
package converters;

import javax.transaction.Transactional;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import domain.Activity;

@Component
@Transactional
public class ActivityToStringConverter implements Converter<Activity, String> {

	@Override
	public String convert(final Activity a) {

		String res;

		if (a == null)
			res = null;
		else
			res = String.valueOf(a.getId());
		return res;
	}

}
