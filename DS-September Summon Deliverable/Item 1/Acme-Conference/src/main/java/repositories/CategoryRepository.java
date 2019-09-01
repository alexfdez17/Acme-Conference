
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {

	@Query("select c from Category c where c.parent.id = ?1")
	Collection<Category> findAllByParentId(Integer parentId);

	@Query("select c from Category c where c.parent.title='CONFERENCE' or c.title='CONFERENCE'")
	Collection<Category> findAllRootChildrenAndRoot();

	@Query("select c.parent from Category c where c.id = ?1")
	Category findParentByChildId(Integer childId);

	@Query("select c from Category c where c.title='CONFERENCE'")
	Category findRoot();

}
