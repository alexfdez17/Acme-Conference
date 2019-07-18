
package repositories;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Conference;

@Repository
public interface ConferenceRepository extends JpaRepository<Conference, Integer> {

	@Query("select min(c.fee), max(c.fee), avg(c.fee), stddev(c.fee) from Conference c")
	List<Double> minMaxAvgStddevFee();

	@Query("select min(c.endDate - c.startDate), max(c.endDate - c.startDate), avg(c.endDate - c.startDate), stddev(c.endDate - c.startDate) from Conference c")
	List<Double> minMaxAvgStddevDays();

	@Query("select count(c.category) from Conference c group by c.category")
	Collection<Long> getConferencesPerCategory();

	@Query("select c from Conference c where c.isFinal = true")
	Collection<Conference> findFinals();

}
