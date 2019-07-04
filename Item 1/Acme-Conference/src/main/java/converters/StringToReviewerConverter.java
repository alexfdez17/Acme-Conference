
package converters;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import repositories.ReviewerRepository;
import domain.Reviewer;

@Component
@Transactional
public class StringToReviewerConverter implements Converter<String, Reviewer> {

	@Autowired
	ReviewerRepository	reviewerRepository;


	@Override
	public Reviewer convert(final String source) {
		final Reviewer reviewer;
		int id;

		try {
			id = Integer.valueOf(source);
			reviewer = this.reviewerRepository.findOne(id);
		} catch (final Throwable t) {

			throw new IllegalArgumentException(t);

		}
		return reviewer;
	}

}
