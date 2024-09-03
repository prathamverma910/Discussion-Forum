package discussion.forum.units.service;

import java.util.ArrayList;

import com.forum.units.Question;
import com.forum.units.Reply;
import com.forum.units.Upvote;
import com.forum.units.User;

public class UpvoteServiceImpl implements UpvoteService {
	public static ArrayList<Upvote> upvotes = new ArrayList<>();
	
	public Upvote addUpvote(Question question, User user) {
		if (question != null && user != null) {
			Upvote upvote = getUpvote(user, question, null);
			if (upvote != null) {
				System.out.println("You have already upvoted");
				return upvote;
			}
			upvote = addUpvote(user, question, null);
			question.increaseUpvoteCount();
			return upvote;
		}
		System.out.println("Any specified field can't be empty");
		return null;
	}
	
	public long upvoteCount(Reply reply) {
		int count = 0;
		for (Upvote upvote : upvotes) {
			if (upvote != null && (upvote.getReply() == reply)) {
				count++;
			}
		}
		return count;
	}
	
	public Upvote addUpvote(Reply reply, User user) {
		if (reply != null && user != null) {
			Upvote upvote = getUpvote(user, null, reply);
			if (upvote != null) {
				System.out.println("You have already upvoted");
				return upvote;
			}
			upvote = addUpvote(user, null, reply);
			return upvote;
		}
		System.out.println("Any specified field can't be empty");
		return null;
	}
	
	private Upvote getUpvote(User user, Question question, Reply reply) {
		for (Upvote upvote : upvotes) {

            /* Here we are trying to compare whether the user is the user who wrote the question and
            * we are also trying to compare that he/she had already upvoted the particular question or not */
			if(upvote.getUser() == user && upvote.getQuestion() != null && upvote.getQuestion() == question){
				// user already upvoted the same question if condition true.
				return upvote;
			}
			/* Here we are trying to compare whether the user is the user who wrote the reply and
			 * we are also trying to compare that he/she had already upvoted the particular reply or not */
			else if (upvote.getUser() == user && upvote.getReply() != null && upvote.getReply() == reply) {
				//user already upvoted the same reply if condition is true.
				return upvote;
			}
		}
		//user not upvoted
		return null;
	}
	
	private Upvote addUpvote(User user, Question question, Reply reply) {
		Upvote upvote = new Upvote();
		upvote.setQuestion(question);
		upvote.setReply(reply);
		upvote.setUser(user);
		upvote.autoGenerateId();
		upvote.setCreated();
		upvotes.add(upvote);
		return upvote;
	}
}
