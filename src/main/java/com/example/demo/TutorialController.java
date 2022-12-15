package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

//import java.util.Optional;
//
//@Controller
//public class TutorialController {
//
//    @Autowired
//    TutorialRepository tutorialRepository;
//
//    @GetMapping("/tutorials")
//    public String displayAlltutorials () {
//        Optional<Tutorial> tuto = tutorialRepository.findById(1L);
//        model.addAttribute("tutorial", tuto.get());
//        return "test";
//    }
//
//    @PostMapping("")
//    public String addGameSubmission() {
//        req.getParameter("title");
//        return "redirect:/tutorials";
//    }
//}
