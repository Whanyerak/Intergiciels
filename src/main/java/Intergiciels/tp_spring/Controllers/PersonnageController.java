package Intergiciels.tp_spring.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import Intergiciels.tp_spring.Entities.Personnage;
import Intergiciels.tp_spring.Repositories.PersonnageRepository;


@Controller
@RequestMapping(path="/personnages")
public class PersonnageController {
    @Autowired
    private PersonnageRepository personnageRepository;

    @GetMapping(path="/add")
    public @ResponseBody String addNewPersonnage (@RequestParam String name, @RequestParam Integer points) {

        Personnage n = new Personnage();
        n.setName(name);
        n.setPoints(points);
        personnageRepository.save(n);
        return "Saved";
    }

    @GetMapping(path="/all")
    public @ResponseBody Iterable<Personnage> getAllPersonnages() {
        // This returns a JSON or XML with the users
        return personnageRepository.findAll();
    }
}

