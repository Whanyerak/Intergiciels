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
import java.util.ArrayList;
import java.util.List;


@Controller
@RequestMapping(path="/personnages")
public class PersonnageController {
    @Autowired
    private PersonnageRepository personnageRepository;
    @Autowired
    private UserRepository userRepository;

    @GetMapping(path="/add")
    public String addPersonnageForm (Model model) {
        model.addAttribute("personnage", new Personnage());
        return "personnageCreation";
    }

    @PostMapping(path="/add") // Map ONLY GET Requests
    public String addNewPersonnage (@ModelAttribute Personnage personnage) {
        UserDetails details = (UserDetails)SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();

        User user = userRepository.findByUsername(details.getUsername());
        personnage.setUser(user);
        if(user.getPoints()-personnage.getPoints() < 0) {
            System.out.println("Pas assez de points disponibles pour créer le personnage.");
            return "personnageCreation";
        }
        user.setPoints(user.getPoints()-personnage.getPoints());
        personnageRepository.save(personnage);
        userRepository.save(user);
        return "home";
    }

    @PostMapping(path="/remove") // Map ONLY GET Requests
    public String removePersonnage (@RequestParam(required = true) String characterId, Model model) {
        Integer id = Integer.parseInt(characterId);
        Personnage personnage = personnageRepository.findById(id).get();
        personnageRepository.delete(personnage);
        return "home";
    }

    @GetMapping(path="/all")
    public @ResponseBody List<String> getAllPersonnages() {
        List<Personnage> list = (List)personnageRepository.findAll();
        List<String> nameList = new ArrayList<>();
        for(Personnage p : list) {
            nameList.add(p.getName());
        }
        return nameList;
    }
}