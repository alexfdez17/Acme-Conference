
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Paper;
import domain.Submission;

@Repository
public interface SubmissionRepository extends JpaRepository<Submission, Integer> {

	@Query("select min(1.0*(select count(s) from Submission s where s.conference.id = c.id)), max(1.0*(select count(s) from Submission s where s.conference.id = c.id)), avg(1.0*(select count(s) from Submission s where s.conference.id = c.id)), stddev(1.0*(select count(s) from Submission s where s.conference.id = c.id)) from Conference c")
	Double[] minMaxAvgStddevPerConference();

	@Query("select distinct s from Submission s join s.reviewers r where r.id = ?1")
	Collection<Submission> findAllByReviewerId(Integer reviewerId);

	@Query("select r.submission from Report r where r.reviewer.id = ?1")
	Collection<Submission> findAllWithReportWrittenByReviewer(Integer reviewerId);

	@Query("select s from Submission s where s.status = ?1")
	Collection<Submission> findAllByStatus(String status);

	@Query("select s from Submission s where s.status != 'UNDER-REVIEW'")
	Collection<Submission> findAllNotUnderReview();

	@Query("select s from Submission s where s.status = 'UNDER-REVIEW'")
	Collection<Submission> findAllUnderReview();

	@Query("select s.cameraReadyPaper from Submission s where s.author.id = ?1 and s.cameraReadyPaper != null")
	Collection<Paper> findAllCameraReadyPapersByAuthorId(Integer authorId);

	@Query("select s from Submission s where s.author.id=?1")
	Collection<Submission> findByAuthorId(int id);

	@Query("select s from Submission s where s.conference.id=?1")
	Collection<Submission> findByConference(int id);

}
