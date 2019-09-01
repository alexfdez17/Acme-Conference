
package converters;

import java.net.URLDecoder;

import org.springframework.core.convert.converter.Converter;

import domain.Paper;

public class StringToPaperConverter implements Converter<String, Paper> {

	@Override
	public Paper convert(final String source) {
		final Paper paper;
		String parts[];

		if (source == null)
			paper = null;
		else
			try {
				parts = source.split("\\|");

				paper = new Paper();

				paper.setDocument(URLDecoder.decode(parts[0], "UTF-8"));
				paper.setSummary(URLDecoder.decode(parts[1], "UTF-8"));
				paper.setTitle(URLDecoder.decode(parts[2], "UTF-8"));
				paper.setAuthors(URLDecoder.decode(parts[3], "UTF-8"));

			} catch (final Throwable oops) {
				throw new RuntimeException(oops);
			}
		return paper;
	}

}
