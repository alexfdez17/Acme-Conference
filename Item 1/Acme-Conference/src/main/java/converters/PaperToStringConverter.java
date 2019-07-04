
package converters;

import java.net.URLEncoder;

import org.springframework.core.convert.converter.Converter;

import domain.Paper;

public class PaperToStringConverter implements Converter<Paper, String> {

	@Override
	public String convert(final Paper p) {

		String res;
		StringBuilder builder;

		if (p == null)
			res = null;
		else
			try {
				builder = new StringBuilder();

				builder.append(URLEncoder.encode(p.getDocument(), "UTF-8"));
				builder.append("|");
				builder.append(URLEncoder.encode(p.getSummary(), "UTF-8"));
				builder.append("|");
				builder.append(URLEncoder.encode(p.getTitle(), "UTF-8"));
				builder.append("|");
				builder.append(URLEncoder.encode(p.getAuthors(), "UTF-8"));

				res = builder.toString();
			} catch (final Throwable oops) {
				throw new RuntimeException(oops);
			}

		return res;
	}

}
