package Intergiciels.tp_spring.Controllers;

import Intergiciels.tp_spring.Entities.User;
import Intergiciels.tp_spring.Repositories.UserRepository;
import Intergiciels.tp_spring.Security.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import Intergiciels.tp_spring.Entities.Personnage;
import Intergiciels.tp_spring.Repositories.PersonnageRepository;

import java.security.Principal;


@Controller
@RequestMapping(path="/personnages")
public class PersonnageController {
    @Autowired
    private PersonnageRepository personnageRepository;
    private UserRepository userRepository;
    private UserService userService;


    //Récupére le joueur connecté courant
    public User getConnectedUser() {
        User user = null;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            user = userRepository.findByUsername(((UserDetails)principal).getUsername());
            System.out.println(user.getName());
        }
        return user;
    }

    @GetMapping(path="/add")
    public String addPersonnageForm (Model model) {
        model.addAttribute("personnage", new Personnage());
        return "personnageCreation";
    }

    @PostMapping(path="/add") // Map ONLY GET Requests
    public String addNewPersonnage (@ModelAttribute Personnage personnage) {
        User user = getConnectedUser();
        personnage.setUser(user);
        personnageRepository.save(personnage);
        return "home";
    }

    @GetMapping(path="/all")
    public @ResponseBody Iterable<Personnage> getAllPersonnages() {
        // This returns a JSON or XML with the users
        return personnageRepository.findAll();
    }
}