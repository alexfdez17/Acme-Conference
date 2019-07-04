
package converters;

import javax.transaction.Transactional;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import domain.Report;

@Component
@Transactional
public class ReportToStringConverter implements Converter<Report, String> {

	@Override
	public String convert(final Report r) {

		String res;

		if (r == null)
			res = null;
		else
			res = String.valueOf(r.getId());
		return res;
	}

}
