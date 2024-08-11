package cz.martinkostelecky.mathpracticing.controller;

import cz.martinkostelecky.mathpracticing.entity.Example;
import cz.martinkostelecky.mathpracticing.service.PracticingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
@RequiredArgsConstructor
@RequestMapping(value = "practicing")
public class PracticingController {

    private final PracticingService practicingService;

    @RequestMapping(method = RequestMethod.GET)
    public String renderPracticingPage(Model model) {

        Optional<Example> example = practicingService.getRandomExample();

        model.addAttribute("example", example);

        return "practicing";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String returnResult(@ModelAttribute Example example, RedirectAttributes redirectAttributes) {
        Boolean result = practicingService.getResult(example);
        if (result) {
            redirectAttributes.addFlashAttribute("successMessage", "JUPÍ, SPRÁVNĚ! :)");
        } else {
            redirectAttributes.addFlashAttribute("failureMessage", "ZKUS TO ZNOVU! :(");
        }
        return "redirect:/practicing";
    }

}
