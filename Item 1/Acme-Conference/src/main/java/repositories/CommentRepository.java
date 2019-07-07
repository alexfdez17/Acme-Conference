
package repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer> {

	@Query("select min(1.0*(select count(cm) from Comment cm where cm.activity.id = c.id)), max(1.0*(select count(cm) from Comment cm where cm.activity.id = c.id)), avg(1.0*(select count(cm) from Comment cm where cm.activity.id = c.id)), stddev(1.0*(select count(cm) from Comment cm where cm.activity.id = c.id)) from Conference c")
	List<Double> minMaxAvgStddevPerConference();

	@Query("select min(1.0*(select count(cm) from Comment cm where cm.activity.id = a.id)), max(1.0*(select count(cm) from Comment cm where cm.activity.id = a.id)), avg(1.0*(select count(cm) from Comment cm where cm.activity.id = a.id)), stddev(1.0*(select count(cm) from Comment cm where cm.activity.id = a.id)) from Activity a")
	List<Double> minMaxAvgStddevPerActivity();

}
