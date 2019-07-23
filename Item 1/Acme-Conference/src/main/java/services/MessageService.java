
package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.MessageRepository;
import domain.Actor;
import domain.Message;
import domain.Topic;

@Service
@Transactional
public class MessageService {

	// Managed repository
	@Autowired
	private MessageRepository		messageRepository;

	// ------------------

	// Supporting services
	@Autowired
	private ActorService			actorService;

	@Autowired
	private AdministratorService	administratorService;


	// -------------------

	// Simple CRUD methods
	public Message create() {
		final Actor principal = this.actorService.findPrincipal();
		final Message result = new Message();
		final Collection<Actor> recipients = new ArrayList<>();

		result.setOwner(principal);
		result.setRecipients(recipients);
		result.setSender(principal);

		return result;
	}

	public Message save(final Message message) {
		Assert.notNull(message);

		this.actorService.findPrincipal();
		this.addToRecipients(message);

		message.setMoment(new Date());

		return this.messageRepository.save(message);
	}

	public void delete(final Message message) {
		Assert.notNull(message);

		this.isOwnedByPrincipal(message);
		this.messageRepository.delete(message);
	}

	public Message findOne(final int messageId) {
		Assert.isTrue(this.exists(messageId));

		final Message message = this.messageRepository.findOne(messageId);

		this.isOwnedByPrincipal(message);

		return message;
	}

	public boolean exists(final int messageId) {
		return this.messageRepository.exists(messageId);
	}
	// -------------------

	// Other business methods
	public Collection<Message> findAllByPrincipal() {
		final Actor principal = this.actorService.findPrincipal();
		final int principalId = principal.getId();

		return this.findAllByActorId(principalId);
	}

	public Collection<Message> findAllByPrincipalAndRecipientId(final int recipientId) {
		final Actor principal = this.actorService.findPrincipal();
		final int principalId = principal.getId();

		return this.findAllByActorIdAndRecipientId(principalId, recipientId);
	}

	public Collection<Message> findAllByPrincipalAndSenderId(final int senderId) {
		final Actor principal = this.actorService.findPrincipal();
		final int principalId = principal.getId();

		return this.findAllByActorIdAndSenderId(principalId, senderId);
	}

	public Collection<Message> findAllByPrincipalAndTopic(final Topic topic) {
		final Actor principal = this.actorService.findPrincipal();
		final int principalId = principal.getId();

		return this.findAllByActorIdAndTopic(principalId, topic);
	}
	// ----------------------

	// Auxiliary methods
	private void addToRecipients(final Message message) {
		final Collection<Actor> recipients = message.getRecipients();

		for (final Actor recipient : recipients) {
			final Message copy = this.copy(message);
			final Collection<Actor> copyRecipients = new ArrayList<>();

			copyRecipients.add(recipient);
			copy.setRecipients(copyRecipients);
			copy.setOwner(recipient);

			this.messageRepository.save(copy);
		}
	}

	private Message copy(final Message message) {
		Assert.notNull(message);

		final Message result = new Message();

		final String body = message.getBody();
		final Date moment = message.getMoment();
		final Actor sender = message.getSender();
		final String subject = message.getSubject();
		final Topic topic = message.getTopic();

		result.setBody(body);
		result.setMoment(moment);
		result.setSender(sender);
		result.setSubject(subject);
		result.setTopic(topic);

		return result;
	}

	private Collection<Message> findAllByActorId(final int actorId) {
		return this.messageRepository.findAllByActorId(actorId);
	}

	private Collection<Message> findAllByActorIdAndRecipientId(final int actorId, final int recipientId) {
		return this.messageRepository.findAllByActorIdAndRecipientId(actorId, recipientId);
	}

	private Collection<Message> findAllByActorIdAndSenderId(final int actorId, final int senderId) {
		return this.messageRepository.findAllByActorIdAndSenderId(actorId, senderId);
	}

	private Collection<Message> findAllByActorIdAndTopic(final int actorId, final Topic topic) {
		final String topicName = topic.getName();

		return this.messageRepository.findAllByActorIdAndTopic(actorId, topicName);
	}

	private void isOwnedByPrincipal(final Message message) {
		final Actor principal = this.actorService.findPrincipal();
		final Actor owner = message.getOwner();

		Assert.isTrue(principal.getId() == owner.getId());
	}
	// -----------------

}
