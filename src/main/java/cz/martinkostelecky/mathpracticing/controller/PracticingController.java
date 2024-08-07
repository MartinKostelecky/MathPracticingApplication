package cz.martinkostelecky.mathpracticing.controller;

import cz.martinkostelecky.mathpracticing.service.PracticingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequiredArgsConstructor
@RequestMapping(value = "practicing")
public class PracticingController {

    private final PracticingService practicingService;

    @RequestMapping(method = RequestMethod.GET)
    public String renderPracticingPage(Model model) {
        model.addAttribute(practicingService.getRandomQuestion());
        return "practicing";
    }
}
