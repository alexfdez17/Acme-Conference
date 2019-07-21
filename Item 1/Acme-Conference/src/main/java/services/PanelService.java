
package services;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.PanelRepository;
import domain.Panel;

@Service
@Transactional
public class PanelService {

	// Managed Repository
	@Autowired
	private PanelRepository	panelRepository;


	// Supported Services

	// CRUD

	public Panel create() {
		Panel result;

		result = new Panel();

		return result;
	}

	public Panel save(final Panel panel) {
		Assert.notNull(panel);
		Panel result;
		result = this.panelRepository.save(panel);
		this.panelRepository.flush();
		return result;
	}

	public void delete(final Panel panel) {
		Assert.notNull(panel);
		Assert.isTrue(panel.getId() != 0);

		this.panelRepository.delete(panel);
	}

	public Collection<Panel> findAll() {
		Collection<Panel> result;

		result = this.panelRepository.findAll();

		return result;
	}

	public Panel findOne(final int panelId) {
		Panel result;

		result = this.panelRepository.findOne(panelId);
		Assert.notNull(result);

		return result;
	}

	public void flush() {
		this.panelRepository.flush();
	}

	//Other business methods

}
