
package converters;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import repositories.TopicRepository;
import domain.Topic;

@Component
@Transactional
public class StringToTopicConverter implements Converter<String, Topic> {

	@Autowired
	TopicRepository	topicRepository;


	@Override
	public Topic convert(final String source) {
		final Topic topic;
		int id;

		try {
			id = Integer.valueOf(source);
			topic = this.topicRepository.findOne(id);
		} catch (final Throwable t) {

			throw new IllegalArgumentException(t);

		}
		return topic;
	}

}
