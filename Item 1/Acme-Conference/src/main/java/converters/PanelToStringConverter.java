
package converters;

import javax.transaction.Transactional;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import domain.Panel;

@Component
@Transactional
public class PanelToStringConverter implements Converter<Panel, String> {

	@Override
	public String convert(final Panel p) {

		String res;

		if (p == null)
			res = null;
		else
			res = String.valueOf(p.getId());
		return res;
	}

}
