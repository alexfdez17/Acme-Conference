
package services;

import java.util.Collection;

import javax.transaction.Transactional;
import javax.validation.ValidationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;

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
	@Autowired
	private Validator						validator;


	// Simple CRUD methods

	public SystemConfiguration save(final SystemConfiguration systemConfig) {
		Assert.notNull(systemConfig);

		final int systemConfigId = systemConfig.getId();

		Assert.isTrue(this.exists(systemConfigId));

		this.administratorService.findByPrincipal();

		return this.systemConfigurationRepository.save(systemConfig);
	}

	public SystemConfiguration find() {
		this.administratorService.findByPrincipal();

		return this.systemConfigurationRepository.find();
	}

	public boolean exists(final int systemConfigId) {
		return this.systemConfigurationRepository.exists(systemConfigId);
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

	public SystemConfiguration reconstruct(final SystemConfiguration systemConfiguration, final BindingResult binding) {
		final SystemConfiguration result = systemConfiguration;

		this.validator.validate(result, binding);

		if (binding.hasErrors())
			throw new ValidationException();

		return result;
	}
	// ----------------
}
