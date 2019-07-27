
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Reviewer;

@Repository
public interface ReviewerRepository extends JpaRepository<Reviewer, Integer> {

	@Query("select r from Reviewer r join r.keywords k where ?1 like '%k%' or ?2 like '%k%'")
	Collection<Reviewer> findAllByConferenceTitleAndSummary(String conferenceTitle, String conferenceSummary);

}
