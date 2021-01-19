/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scoreservice;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ScoreController {
    @Autowired
    private ScoreRepository scoreRepository;
    @Autowired
    private GameRepository gameRepository;
    
    
    @PostMapping("/games/{name}/scores")
    @ResponseBody
    public Score postScore(@RequestBody Score score, @PathVariable String name) {
        score.setGame(gameRepository.findByName(name));
        return scoreRepository.save(score);
        
    }

    @GetMapping("/games/{name}/scores")
    public List<Score> getScores(@PathVariable String name) {
        return scoreRepository.findByGame(gameRepository.findByName(name));
    }
    @GetMapping("/games/{name}/scores/{id}")
    public Score getScores(@PathVariable String name, @PathVariable Long id) {
        
        return scoreRepository.findByGameAndId(gameRepository.findByName(name), id);
    }
    
  @DeleteMapping("/games/{name}/scores/{id}")
  public Score deleteScores(@PathVariable String name, @PathVariable Long id) {
      Score score = scoreRepository.findByGameAndId(gameRepository.findByName(name), id);
      scoreRepository.delete(score);
      return score;
  }
}
