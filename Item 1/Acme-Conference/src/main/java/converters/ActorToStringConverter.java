
package converters;

import javax.transaction.Transactional;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import domain.Actor;

@Component
@Transactional
public class ActorToStringConverter implements Converter<Actor, String> {

	@Override
	public String convert(final Actor a) {

		String res;

		if (a == null)
			res = null;
		else
			res = String.valueOf(a.getId());
		return res;
	}

}
