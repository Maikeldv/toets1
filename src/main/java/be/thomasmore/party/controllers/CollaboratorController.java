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

    @GetMapping({"/collaborators","/collaborators/{id}"})
    public String collaborator(Model model,
                                @PathVariable(required = false) Integer id) {
        final Iterable<Collaborator> allCollaborators = collaboratorRepository.findAll();
        model.addAttribute("collaborators", allCollaborators);
        if (id == null) return "error";

        Optional<Collaborator> optionalCollaborator = collaboratorRepository.findById(id);
        //noinspection OptionalIsPresent
        if (optionalCollaborator.isPresent()) {
            model.addAttribute("collaborator", optionalCollaborator.get());
        }
        return "collaborators";
    }
    @GetMapping("/collaborators/role/{filter}")
    public String collaboratorsRole(Model model, @PathVariable String filter) {
        Iterable<Collaborator> collaborators;

        switch (filter) {
            case "waiter":
                collaborators = collaboratorRepository.findByRoleEquals("waiter");

                break;
            case "ticket-counter":
                collaborators = collaboratorRepository.findByRoleEquals("ticket counter");
                break;
            case "director":
                collaborators = collaboratorRepository.findByRoleEquals("director");
                break;
            case "stage-coordinator":
                collaborators = collaboratorRepository.findByRoleEquals("stage coordinator");
                break;

            default:
                collaborators = collaboratorRepository.findAll();
                filter = null;
                break;

        }

        model.addAttribute(" collaborators", collaborators);
        model.addAttribute("filter", filter);
        return "collaborators";
    }

}
