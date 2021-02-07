/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jokes;

import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Service
public class VoteService {
    
    @Autowired
    private VoteRepository voteRepository;
    
    public Vote findByJokeId(Long jokeID){
        return voteRepository.findByJokeId(jokeID);
    }
    
    public void save(Vote v){
        voteRepository.save(v);
    }
    
    @Transactional
    public void vote(Long id, String value) {

        Vote vote = voteRepository.findByJokeId(id);
        if (vote == null) {
            vote = new Vote(id, 0, 0);
        }
        if ("up".equals(value)) {
            vote.setUpVotes(vote.getUpVotes() + 1);
        } else if ("down".equals(value)) {
            vote.setDownVotes(vote.getDownVotes() + 1);
        }
        voteRepository.save(vote);
    }
}
