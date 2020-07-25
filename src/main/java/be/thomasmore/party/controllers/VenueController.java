package be.thomasmore.party.controllers;

import be.thomasmore.party.model.Venue;
import be.thomasmore.party.repositories.VenueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@Controller
public class VenueController {
    @Autowired
    private VenueRepository venueRepository;

    @GetMapping("/venuelist")
    public String venuelist(Model model) {
        final Iterable<Venue> allVenues = venueRepository.findAll();
        model.addAttribute("venues", allVenues);
        model.addAttribute("filterOutdoor", "all");
        return "venuelist";
    }

    @GetMapping("/venuelist/outdoor/yes")
    public String venueListOutdoorYes(Model model) {
        Iterable<Venue> venues = venueRepository.findByOutdoor(true);
        model.addAttribute("venues", venues);
        model.addAttribute("filterOutdoor", "yes");
        return "venuelist";
    }

    @GetMapping("/venuelist/outdoor/no")
    public String venueListOutdoorNo(Model model) {
        Iterable<Venue> venues = venueRepository.findByOutdoor(false);
        model.addAttribute("venues", venues);
        model.addAttribute("filterOutdoor", "no");
        return "venuelist";
    }

    @GetMapping({"/venuedetails/{id}", "/venuedetails"})
    public String venuedetails(Model model, @PathVariable(required = false) Integer id) {
        if (id == null) return "venuedetails";

        Optional<Venue> venueFromDb = venueRepository.findById(id);
        //noinspection OptionalIsPresent
        if (venueFromDb.isPresent()) {
            model.addAttribute("venue", venueFromDb.get());
        }
        return "venuedetails";
    }

}
