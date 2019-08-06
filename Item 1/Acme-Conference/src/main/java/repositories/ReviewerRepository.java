
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Reviewer;

@Repository
public interface ReviewerRepository extends JpaRepository<Reviewer, Integer> {

	@Query("select r from Reviewer r join r.keywords k where r not in (select r from Submission s join s.reviewers r where s.id = ?3) and (k like %?1% or k like %?2%)")
	Collection<Reviewer> findAllByConferenceTitleAndSummaryNotAssignedToSubmission(String conferenceTitle, String conferenceSummary, Integer submissionId);

	@Query("select r from Reviewer r where r not in (select r from Submission s join s.reviewers r where s.id = ?1)")
	Collection<Reviewer> findAllNotAssginedToSubmission(Integer submissionId);

}
