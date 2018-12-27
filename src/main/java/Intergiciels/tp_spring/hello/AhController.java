package Intergiciels.tp_spring.hello;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AhController {

    @GetMapping("/ah")
    public String greetingForm(Model model) {
        model.addAttribute("ah", new Ah());
        return "ah";
    }

    @PostMapping("/ah")
    public String greetingSubmit(@ModelAttribute Ah ah) {
        return "result";
    }

}