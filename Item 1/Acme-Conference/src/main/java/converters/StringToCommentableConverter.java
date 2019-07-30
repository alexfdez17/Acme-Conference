
package converters;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import repositories.ActivityRepository;
import repositories.CommentableRepository;
import domain.Activity;
import domain.Commentable;

@Component
@Transactional
public class StringToCommentableConverter implements Converter<String, Commentable> {

	@Autowired
	CommentableRepository	commentableRepository;

	@Autowired
	ActivityRepository		activityRepository;


	@Override
	public Commentable convert(final String source) {
		final Commentable commentable;
		int id;

		try {
			id = Integer.valueOf(source);
			if (this.commentableRepository.findOne(id) == null) {

				final Activity activity = this.activityRepository.findOne(id);
				final int newid = activity.getId();
				commentable = this.commentableRepository.findOne(newid);

			} else
				commentable = this.commentableRepository.findOne(id);
		} catch (final Throwable t) {

			throw new IllegalArgumentException(t);

		}
		return commentable;
	}

}
