
package converters;

import javax.transaction.Transactional;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import domain.Registration;

@Component
@Transactional
public class RegistrationToStringConverter implements Converter<Registration, String> {

	@Override
	public String convert(final Registration r) {

		String res;

		if (r == null)
			res = null;
		else
			res = String.valueOf(r.getId());
		return res;
	}

}
