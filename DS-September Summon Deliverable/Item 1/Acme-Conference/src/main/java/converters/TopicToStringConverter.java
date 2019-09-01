
package converters;

import javax.transaction.Transactional;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import domain.Topic;

@Component
@Transactional
public class TopicToStringConverter implements Converter<Topic, String> {

	@Override
	public String convert(final Topic t) {

		String res;

		if (t == null)
			res = null;
		else
			res = String.valueOf(t.getId());
		return res;
	}

}
