package org.example.game_tournement.Controller;


import jakarta.servlet.http.HttpSession;
import org.example.game_tournement.Entity.Article;
import org.example.game_tournement.Entity.Tournament;
import org.example.game_tournement.Entity.User;
import org.example.game_tournement.Service.ArticleService;
import org.example.game_tournement.Service.AuthService;
import org.example.game_tournement.Service.tournoisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller

public class tournoisController {

    @Autowired
    HttpSession session;

    @Autowired
    tournoisService tournoisService;

    @Autowired
    AuthService authService;

    //Read ALL
    @RequestMapping("/Actualite")
    private String pageActualite(Model model) {
        List<Article> articles = ArticleService.getAllArticles();
        model.addAttribute("articles", articles);
        return "Actualite";
    }

    //Create
    @RequestMapping("/addtournoi")
    private String creationTournoi(Model model) {
        model.addAttribute("tournoi", new Tournament());
        return "CreationTournoi";
    }

    @PostMapping("/savetournoi")
    private String creationTournoi(@ModelAttribute("tournoi") Tournament tournoi) {
        tournoisService.addTournament(tournoi);
        return "redirect:/tournois";
    }

    //Read One
    @RequestMapping("detailtournois/{studentID}")
    public String detail(@PathVariable("studentID") int id, Model model) {
        Tournament tournament = tournoisService.getTournamentById(id);
        model.addAttribute("tournoi", tournament);
        return "detailtournois";
    }

    //Update

    //Delete
    @RequestMapping("/deletetournoi/{id}")
    public String showDeleteConfirmation(@PathVariable("id") int id, Model model) {
        Tournament tournament = tournoisService.getTournamentById(id);
        model.addAttribute("tournoi", tournament);
        return "deleteConfirmation";
    }

    // Supprimer le tournoi
    @PostMapping("/deletetournoi/{id}")
    public String deleteTournoi(@PathVariable("id") int id) {
        Tournament tournament = tournoisService.getTournamentById(id);
        tournoisService.deleteTournament(tournament);
        return "redirect:/tournois";
    }

    @RequestMapping("/tournois")
    public String listTournaments(Model model) {
        System.out.println(authService.isLogged());
        List<Tournament> tournaments = tournoisService.getAllTournaments();
        model.addAttribute("tournois", tournaments);

        //A AJOUTER POUR LA GESTION DES ADMIN/USER
        // Récupérer l'utilisateur connecté
        String username = (String) session.getAttribute("username");
        User user = authService.getUserByName(username);

        // Ajoutez l'utilisateur au modèle
        model.addAttribute("user", user);
        return "ListTournois";
    }
}