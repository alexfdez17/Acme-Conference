
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer> {

	@Query("select c from Comment c where c.commentable.id=?1")
	Collection<Comment> findByCommentable(int id);

	/*
	 * @Query(
	 * "select min(1.0*(select count(cm) from Comment cm where cm.activity.id = c.id)), max(1.0*(select count(cm) from Comment cm where cm.activity.id = c.id)), avg(1.0*(select count(cm) from Comment cm where cm.activity.id = c.id)), stddev(1.0*(select count(cm) from Comment cm where cm.activity.id = c.id)) from Conference c"
	 * )
	 * List<Double> minMaxAvgStddevPerConference();
	 * 
	 * @Query(
	 * "select min(1.0*(select count(cm) from Comment cm where cm.activity.id = a.id)), max(1.0*(select count(cm) from Comment cm where cm.activity.id = a.id)), avg(1.0*(select count(cm) from Comment cm where cm.activity.id = a.id)), stddev(1.0*(select count(cm) from Comment cm where cm.activity.id = a.id)) from Activity a"
	 * )
	 * List<Double> minMaxAvgStddevPerActivity();
	 */

}
