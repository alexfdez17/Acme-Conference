
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Report;

@Repository
public interface ReportRepository extends JpaRepository<Report, Integer> {

	@Query("select r from Report r where r.decision = 'ACCEPT' and r.submission.id=?1")
	Collection<Report> findAccept(int id);

	@Query("select r from Report r where r.decision = 'REJECT' and r.submission.id=?1")
	Collection<Report> findReject(int id);

	@Query("select r from Report r where r.decision = 'BORDER-LINE' and r.submission.id=?1")
	Collection<Report> findBorderLine(int id);

	@Query("select r from Report r where r.submission.id=?1")
	Collection<Report> findBySubmission(int id);

}
