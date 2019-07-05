
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Conference;

@Repository
public interface ConferenceRepository extends JpaRepository<Conference, Integer> {

	@Query("select avg(c.fee),min(c.fee), max(c.fee), stddev(c.fee) from Conference c")
	Double[] getFees();

	@Query("select avg(c.endDate - c.startDate),min(c.endDate - c.startDate), max(c.endDate - c.startDate), stddev(c.endDate - c.startDate) from Conference c")
	Double[] getDays();

	@Query("select count(c.category) from Conference c group by c.category")
	Collection<Long> getConferencesPerCategory();

}
