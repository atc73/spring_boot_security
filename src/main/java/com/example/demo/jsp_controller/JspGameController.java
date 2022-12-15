package com.example.demo.jsp_controller;

import com.example.demo.model.Game;
import com.example.demo.repository.GameRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class JspGameController {

    @Autowired
    private GameRepository gameRepository;

    @GetMapping("/games")
    public String displayAllGames(Model model) {
        List<Game> games = new ArrayList<>();

            gameRepository.findAll().forEach(t -> games.add(t));
            model.addAttribute("games", games);
            return "game-list";
    }

    @GetMapping("/games/game-details")
    public String displayOneGame(HttpServletRequest req, Model model) {

        Optional game = gameRepository.findById(Long.valueOf(req.getParameter("id")));

        model.addAttribute("game", game.get());
        return "game-details";
    }



    @GetMapping("/games/add")
    public String displayGameForm() {
        return "add-game";
    }

    @PostMapping("/games/delete")
    public String deleteGame(HttpServletRequest req) {
        try {
            gameRepository.deleteById(Long.valueOf(req.getParameter("idGame")));
        } catch (Exception e) {
            System.out.println(e);
        }
        return "redirect:/games";
    }


    @PostMapping("/games/add")
    public String createGame(HttpServletRequest req) {
        String gameName = req.getParameter("gameName");
        String gameDescription = req.getParameter("gameDescription");
        try {
            gameRepository.save(new Game(gameName, gameDescription));
        } catch (Exception e) {
            System.out.println("can't add the game");
        }
        return "redirect:/games";
    }

    @GetMapping("/games/update")
    public String displayUpdateForm(HttpServletRequest req, Model model) {
        Optional game = gameRepository.findById(Long.valueOf(req.getParameter("id")));

        model.addAttribute("game", game.get());
        return "update-game";
    }

    @PostMapping("games/update")
    public String updateGame(HttpServletRequest req) {
        String idGame = req.getParameter("gameId");
        String nameToUpdate = req.getParameter("gameName");
        String descriptionToUpdate = req.getParameter("gameDescription");

        Optional<Game> gameToUpdate = gameRepository.findById(Long.valueOf(idGame));

        if (gameToUpdate.isPresent()) {
            Game game = gameToUpdate.get();
            game.setName(nameToUpdate);
            game.setDescription(descriptionToUpdate);
            gameRepository.save(game);
        } else {
            System.out.println("game not found");
        }

        return "redirect:/games";
    }


    @PutMapping("/games/{id}")
    public void updateTutorial(@RequestBody Game game) {

    }
}
