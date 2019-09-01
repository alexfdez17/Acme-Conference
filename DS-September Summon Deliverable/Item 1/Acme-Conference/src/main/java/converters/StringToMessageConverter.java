
package converters;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import repositories.MessageRepository;
import domain.Message;

@Component
@Transactional
public class StringToMessageConverter implements Converter<String, Message> {

	@Autowired
	MessageRepository	messageRepository;


	@Override
	public Message convert(final String source) {
		final Message message;
		int id;

		try {
			id = Integer.valueOf(source);
			message = this.messageRepository.findOne(id);
		} catch (final Throwable t) {

			throw new IllegalArgumentException(t);

		}
		return message;
	}

}
