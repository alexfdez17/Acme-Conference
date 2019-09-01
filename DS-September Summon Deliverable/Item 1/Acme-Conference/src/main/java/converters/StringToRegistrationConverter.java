
package converters;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import repositories.RegistrationRepository;
import domain.Registration;

@Component
@Transactional
public class StringToRegistrationConverter implements Converter<String, Registration> {

	@Autowired
	RegistrationRepository	registrationRepository;


	@Override
	public Registration convert(final String source) {
		final Registration registration;
		int id;

		try {
			id = Integer.valueOf(source);
			registration = this.registrationRepository.findOne(id);
		} catch (final Throwable t) {

			throw new IllegalArgumentException(t);

		}
		return registration;
	}

}
