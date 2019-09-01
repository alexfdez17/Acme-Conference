
package converters;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import repositories.PanelRepository;
import domain.Panel;

@Component
@Transactional
public class StringToPanelConverter implements Converter<String, Panel> {

	@Autowired
	PanelRepository	panelRepository;


	@Override
	public Panel convert(final String source) {
		final Panel panel;
		int id;

		try {
			id = Integer.valueOf(source);
			panel = this.panelRepository.findOne(id);
		} catch (final Throwable t) {

			throw new IllegalArgumentException(t);

		}
		return panel;
	}

}
