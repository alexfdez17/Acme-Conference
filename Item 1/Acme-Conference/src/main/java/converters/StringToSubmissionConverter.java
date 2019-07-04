
package converters;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import repositories.SubmissionRepository;
import domain.Submission;

@Component
@Transactional
public class StringToSubmissionConverter implements Converter<String, Submission> {

	@Autowired
	SubmissionRepository	submissionRepository;


	@Override
	public Submission convert(final String source) {
		final Submission submission;
		int id;

		try {
			id = Integer.valueOf(source);
			submission = this.submissionRepository.findOne(id);
		} catch (final Throwable t) {

			throw new IllegalArgumentException(t);

		}
		return submission;
	}

}
