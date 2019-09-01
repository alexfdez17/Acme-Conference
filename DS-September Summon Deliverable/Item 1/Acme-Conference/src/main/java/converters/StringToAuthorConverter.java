
package converters;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import repositories.AuthorRepository;
import domain.Author;

@Component
@Transactional
public class StringToAuthorConverter implements Converter<String, Author> {

	@Autowired
	AuthorRepository	authorRepository;


	@Override
	public Author convert(final String source) {
		final Author author;
		int id;

		try {
			id = Integer.valueOf(source);
			author = this.authorRepository.findOne(id);
		} catch (final Throwable t) {

			throw new IllegalArgumentException(t);

		}
		return author;
	}

}
