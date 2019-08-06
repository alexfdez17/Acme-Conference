
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Message;

@Repository
public interface MessageRepository extends JpaRepository<Message, Integer> {

	@Query("select m from Message m where m.owner.id = ?1 order by m.moment desc")
	Collection<Message> findAllByActorId(Integer actorId);

	@Query("select m from Message m join m.recipients r where m.owner.id = ?1 and r.id = ?2 order by m.moment desc")
	Collection<Message> findAllByActorIdAndRecipientId(Integer actorId, Integer recipientId);

	@Query("select m from Message m where m.owner.id = ?1 and m.sender.id = ?2 order by m.moment desc")
	Collection<Message> findAllByActorIdAndSenderId(Integer actorId, Integer senderId);

	@Query("select m from Message m where m.owner.id = ?1 and (m.topic.name = ?2 or m.topic.nameES = ?2) order by m.moment desc")
	Collection<Message> findAllByActorIdAndTopic(Integer actorId, String topic);
}
