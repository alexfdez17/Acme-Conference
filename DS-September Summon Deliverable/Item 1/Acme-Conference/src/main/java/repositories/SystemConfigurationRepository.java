
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.SystemConfiguration;

@Repository
public interface SystemConfigurationRepository extends JpaRepository<SystemConfiguration, Integer> {

	@Query("select s from SystemConfiguration s")
	SystemConfiguration find();

	@Query("select c from SystemConfiguration s join s.creditCardMakes c")
	Collection<String> findAllCreditCardMakes();

	@Query("select v from SystemConfiguration s join s.voidWords v")
	Collection<String> findAllVoidWords();

	@Query("select s.banner from SystemConfiguration s")
	String findBanner();

	@Query("select s.countryCode from SystemConfiguration s")
	String findCountryCode();

	@Query("select s.systemName from SystemConfiguration s")
	String findSystemName();

	@Query("select s.welcomeMessage from SystemConfiguration s")
	String findWelcomeMessage();

	@Query("select s.welcomeMessageES from SystemConfiguration s")
	String findWelcomeMessageES();

}
