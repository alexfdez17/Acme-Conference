
package services;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.SystemConfigurationRepository;
import domain.SystemConfiguration;

@Service
@Transactional
public class SystemConfigurationService {

	// Managed repository
	@Autowired
	private SystemConfigurationRepository	systemConfigurationRepository;
	// ------------------

	// Supporting services
	@Autowired
	private AdministratorService			administratorService;


	// -------------------

	// Simple CRUD methods

	public SystemConfiguration save(final SystemConfiguration systemConfig) {
		Assert.notNull(systemConfig);

		this.administratorService.findByPrincipal();

		return this.systemConfigurationRepository.save(systemConfig);
	}

	public SystemConfiguration find() {
		this.administratorService.findByPrincipal();

		return this.systemConfigurationRepository.find();
	}

	// Other business methods
	public Collection<String> findAllCreditCardMakes() {
		return this.systemConfigurationRepository.findAllCreditCardMakes();
	}

	public Collection<String> findAllVoidWords() {
		return this.systemConfigurationRepository.findAllVoidWords();
	}

	public String findBanner() {
		return this.systemConfigurationRepository.findBanner();
	}

	public String findCountryCode() {
		return this.systemConfigurationRepository.findCountryCode();
	}

	public String findSystemName() {
		return this.systemConfigurationRepository.findSystemName();
	}

	public String findWelcomeMessage() {
		return this.systemConfigurationRepository.findWelcomeMessage();
	}

	public String findWelcomeMessageES() {
		return this.systemConfigurationRepository.findWelcomeMessageES();
	}
}
