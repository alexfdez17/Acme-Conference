
package converters;

import java.net.URLEncoder;

import org.springframework.core.convert.converter.Converter;

import domain.CreditCard;

public class CreditCardToStringConverter implements Converter<CreditCard, String> {

	@Override
	public String convert(final CreditCard c) {

		String res;
		StringBuilder builder;

		if (c == null)
			res = null;
		else
			try {
				builder = new StringBuilder();

				builder.append(URLEncoder.encode(c.getBrand(), "UTF-8"));
				builder.append("|");
				builder.append(URLEncoder.encode(c.getHolder(), "UTF-8"));
				builder.append("|");
				builder.append(URLEncoder.encode(c.getNumber(), "UTF-8"));
				builder.append("|");
				builder.append(URLEncoder.encode(Integer.toString(c.getCvv()), "UTF-8"));
				builder.append("|");
				builder.append(URLEncoder.encode(Integer.toString(c.getExpirationMonth()), "UTF-8"));
				builder.append("|");
				builder.append(URLEncoder.encode(Integer.toString(c.getExpirationYear()), "UTF-8"));

				res = builder.toString();
			} catch (final Throwable oops) {
				throw new RuntimeException(oops);
			}

		return res;
	}

}
