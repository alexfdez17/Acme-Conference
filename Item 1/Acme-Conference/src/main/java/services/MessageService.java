
package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.transaction.Transactional;
import javax.validation.ValidationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;

import repositories.MessageRepository;
import domain.Actor;
import domain.Message;
import domain.Submission;
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
	private AuthorService			authorService;

	@Autowired
	private AdministratorService	administratorService;

	@Autowired
	private SubmissionService		submissionService;

	@Autowired
	private TopicService			topicService;

	// -------------------

	@Autowired
	private Validator				validator;


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

		return this.messageRepository.save(message);
	}

	public Message broadcast(final Message message) {
		this.administratorService.findByPrincipal();

		return this.save(message);
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
	public Message broadcastToAll() {
		final Collection<Actor> recipients = this.actorService.findAll();
		final Message result = this.setRecipients(recipients);

		return result;
	}

	public Message broadcastToAllAuthors() {
		final Collection<Actor> recipients = this.authorService.findAllAsActor();
		final Message result = this.setRecipients(recipients);

		return result;
	}

	public Message broadcastToAuthorsWithRegistration() {
		final Collection<Actor> recipients = this.authorService.findAllWithRegistration();
		final Message result = this.setRecipients(recipients);

		return result;
	}

	public Message broadcastToAuthorsWithSubmission() {
		final Collection<Actor> recipients = this.authorService.findAllWithSubmission();
		final Message result = this.setRecipients(recipients);

		return result;
	}

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

	public Collection<Message> findAllByPrincipalAndTopic(final String topic) {
		final Actor principal = this.actorService.findPrincipal();
		final int principalId = principal.getId();

		return this.findAllByActorIdAndTopic(principalId, topic);
	}

	public void notifyDecisionOnSubmissions() {
		final Message result = this.create();

		result.setMoment(new Date());

		final Collection<Actor> recipients = new ArrayList<>();
		final Collection<Submission> submissions = this.submissionService.findAllNotUnderReview();

		for (final Submission submission : submissions) {
			final String conferenceTitle = submission.getConference().getTitle();
			final Actor recipient = submission.getAuthor();
			final String decision = submission.getStatus().toLowerCase();
			final String ticker = submission.getTicker();

			final String subject = "Submission " + decision;
			final String body = "Your submission with ticker " + ticker + " to the conference " + conferenceTitle + " has been " + decision + ". You can now access to the reports made on your submission.";
			final Topic topic = this.topicService.findByName("OTHER");

			result.setBody(body);
			result.setSubject(subject);
			result.setTopic(topic);

			recipients.add(recipient);

			submission.setReportsAvailable(true);

			this.submissionService.update(submission);
		}

		result.setRecipients(recipients);

		this.addToRecipients(result);
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
		result.setIsBroadcast(false);
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

	private Collection<Message> findAllByActorIdAndTopic(final int actorId, final String topic) {
		topic.toUpperCase();

		return this.messageRepository.findAllByActorIdAndTopic(actorId, topic);
	}

	private void isOwnedByPrincipal(final Message message) {
		final Actor principal = this.actorService.findPrincipal();
		final Actor owner = message.getOwner();

		Assert.isTrue(principal.getId() == owner.getId());
	}

	private Message setRecipients(final Collection<Actor> recipients) {
		final Actor principal = this.administratorService.findByPrincipal();
		final Message result = this.create();

		if (recipients.contains(principal))
			recipients.remove(principal);

		result.setRecipients(recipients);

		return result;
	}
	// -----------------

	public Message reconstruct(final Message message, final BindingResult binding) {
		final Message result = message;
		final Actor principal = this.actorService.findPrincipal();

		result.setOwner(principal);
		result.setSender(principal);
		message.setMoment(new Date());

		this.validator.validate(result, binding);

		if (binding.hasErrors())
			throw new ValidationException();

		return result;
	}

}
