
package converters;

import javax.transaction.Transactional;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import domain.Message;

@Component
@Transactional
public class MessageToStringConverter implements Converter<Message, String> {

	@Override
	public String convert(final Message m) {

		String res;

		if (m == null)
			res = null;
		else
			res = String.valueOf(m.getId());
		return res;
	}

}
