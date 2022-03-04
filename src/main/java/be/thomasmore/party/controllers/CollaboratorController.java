package be.thomasmore.party.controllers;

import be.thomasmore.party.model.Artist;
import be.thomasmore.party.model.Collaborator;
import be.thomasmore.party.model.Venue;
import be.thomasmore.party.repositories.ArtistRepository;
import be.thomasmore.party.repositories.CollaboratorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@Controller
public class CollaboratorController {
    @Autowired
    private CollaboratorRepository collaboratorRepository;

    @GetMapping("/collaborators")
    public String collaborators(Model model) {
        Iterable<Collaborator> collaborator = collaboratorRepository.findAll();
        model.addAttribute("collaborators", collaborator);
        return "collaborators";
    }

}
