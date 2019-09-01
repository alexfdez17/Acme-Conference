
package converters;

import java.net.URLDecoder;

import org.springframework.core.convert.converter.Converter;

import domain.CreditCard;

public class StringToCreditCardConverter implements Converter<String, CreditCard> {

	@Override
	public CreditCard convert(final String source) {
		final CreditCard creditCard;
		String parts[];

		if (source == null)
			creditCard = null;
		else
			try {
				parts = source.split("\\|");

				creditCard = new CreditCard();

				creditCard.setBrand(URLDecoder.decode(parts[0], "UTF-8"));
				creditCard.setHolder(URLDecoder.decode(parts[1], "UTF-8"));
				creditCard.setNumber(URLDecoder.decode(parts[2], "UTF-8"));
				creditCard.setCvv(Integer.valueOf(URLDecoder.decode(parts[3], "UTF-8")));
				creditCard.setExpirationMonth(Integer.valueOf(URLDecoder.decode(parts[4], "UTF-8")));
				creditCard.setExpirationYear(Integer.valueOf(URLDecoder.decode(parts[5], "UTF-8")));
			} catch (final Throwable oops) {
				throw new RuntimeException(oops);
			}
		return creditCard;
	}

}
