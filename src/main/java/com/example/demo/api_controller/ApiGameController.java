package com.example.demo.api_controller;

import com.example.demo.model.Game;
import com.example.demo.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("http://localhost:4200")
public class ApiGameController {

    @Autowired
    private GameRepository gameRepository;

    @GetMapping("/api/games")
    public ResponseEntity<List<Game>> getAllGames() {
        List<Game> games = new ArrayList<>();

        gameRepository.findAll().forEach(t -> games.add(t));

        return ResponseEntity.ok().body((games));
    }

    @GetMapping("/api/games/{id}")
    public ResponseEntity<Game> getGameById(@PathVariable("id") long id) {
        Optional<Game> gameData = gameRepository.findById(id);

        if (gameData.isPresent()) {
            return new ResponseEntity<>(gameData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/api/games")
    public ResponseEntity<Game> createTutorial(@RequestBody Game gameToCreate) {
        try {
            Game game = gameRepository.save(new Game(gameToCreate.getName(), gameToCreate.getDescription()));
            return new ResponseEntity<>(game, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/api/games/{id}")
    public ResponseEntity<Game> updateTutorial(@PathVariable("id") long id, @RequestBody Game gameToUpdate) {
        Optional<Game> tutorialData = gameRepository.findById(id);

        if (tutorialData.isPresent()) {
            Game game = tutorialData.get();
            game.setName(gameToUpdate.getName());
            game.setDescription(gameToUpdate.getDescription());
            return new ResponseEntity<>(gameRepository.save(game), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/api/games/{id}")
    public ResponseEntity<HttpStatus> deleteTutorial(@PathVariable("id") long id) {
        try {
            gameRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
