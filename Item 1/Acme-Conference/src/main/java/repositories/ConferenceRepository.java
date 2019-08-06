
package repositories;

import java.util.Collection;
import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Repository;

import domain.Conference;

@Repository
public interface ConferenceRepository extends JpaRepository<Conference, Integer> {

	@Query("select min(c.fee), max(c.fee), avg(c.fee), stddev(c.fee) from Conference c")
	Double[] minMaxAvgStddevFee();

	@Query("select min(c.endDate - c.startDate), max(c.endDate - c.startDate), avg(c.endDate - c.startDate), stddev(c.endDate - c.startDate) from Conference c")
	Double[] minMaxAvgStddevDays();

	@Query("select count(c.category) from Conference c group by c.category")
	Collection<Long> getConferencesPerCategory();

	@Query("select c from Conference c where c.isFinal = true and (c.category.title like %?1%)")
	Collection<Conference> findByCategoryName(String name);

	@Query("select c from Conference c where c.isFinal = true and (c.fee <= ?1)")
	Collection<Conference> findByMaximumFee(Double maximumFee);

	@Query("select c from Conference c where c.isFinal = true and (c.startDate >= :min)")
	Collection<Conference> findByStartDate(@Param("min") @DateTimeFormat(pattern = "dd/MM/yyyy") Date min);

	@Query("select c from Conference c where c.isFinal = true and (c.endDate <= :max)")
	Collection<Conference> findByEndDate(@Param("max") @DateTimeFormat(pattern = "dd/MM/yyyy") Date max);

	@Query("select r.conference from Registration r where r.author.id = ?1")
	Collection<Conference> findAllAuthorIsRegistered(Integer authorId);

	@Query("select s.conference from Submission s where s.author.id = ?1")
	Collection<Conference> findAllAuthorHasSubmitted(Integer authorId);

	@Query("select c from Conference c where c.isFinal = true and (c.title like %?1% or c.acronym like %?1% or c.venue like %?1% or c.summary like %?1%)")
	Collection<Conference> findAllByKeyword(String keyword);

	@Query("select c from Conference c where DATEDIFF(c.cameraReadyDeadline, CURRENT_DATE) < 5")
	Collection<Conference> findAllCameraReadyDeadlineElapsesLess5Days();

	@Query("select c from Conference c where c.category.id = ?1")
	Collection<Conference> findAllByCategoryId(Integer categoryId);

	@Query("select c from Conference c where DATEDIFF(c.notificationDeadline, CURRENT_DATE) < 5")
	Collection<Conference> findAllNotificationDeadlineElapsesLess5Days();

	@Query("select c from Conference c where DATEDIFF(c.startDate, CURRENT_DATE) < 5")
	Collection<Conference> findAllOrganisedLess5Days();

	@Query("select c from Conference c where DATEDIFF(CURRENT_DATE, c.submissionDeadline) <= 5 and DATEDIFF(CURRENT_DATE, c.submissionDeadline) >= 0")
	Collection<Conference> findAllSubmissionDeadlineElapsedLast5Days();

	@Query("select c from Conference c where c.isFinal = true and c.startDate > CURRENT_DATE")
	Collection<Conference> findAllForthcoming();

	@Query("select c from Conference c where c.isFinal = true and c.endDate < CURRENT_DATE")
	Collection<Conference> findAllPast();

	@Query("select c from Conference c where c.isFinal = true and c.startDate <= CURRENT_DATE and c.endDate >= CURRENT_DATE")
	Collection<Conference> findAllRunning();

	@Query("select c from Conference c where c.isFinal = true")
	Collection<Conference> findFinals();

}
