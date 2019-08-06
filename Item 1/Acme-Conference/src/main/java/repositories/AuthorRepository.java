
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Actor;
import domain.Author;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Integer> {

	@Query("select a from Author a")
	Collection<Actor> findAllAsActor();

	@Query("select distinct r.author from Registration r")
	Collection<Actor> findAllWithRegistration();

	@Query("select distinct s.author from Submission s")
	Collection<Actor> findAllWithSubmission();

}
