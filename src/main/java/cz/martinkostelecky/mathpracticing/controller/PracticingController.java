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

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class PracticingController {

    private final PracticingService practicingService;

    @RequestMapping(method = RequestMethod.GET)
    public String renderPracticingPage() {

        return "practicing";
    }

    @RequestMapping(value = "/addition", method = RequestMethod.POST)
    public String handleAdditionPost() {
        return "redirect:/addition";
    }

    @RequestMapping(value = "/subtraction", method = RequestMethod.POST)
    public String handleSubtractionPost() {
        return "redirect:/subtraction";
    }

    @RequestMapping(value = "/addition", method = RequestMethod.GET)
    public String getAdditionPracticing(Model model) {

        Example example = new Example();

        example.setCategory("Sčítání");

        Optional<Example> optionalExample = practicingService.getRandomExample(example);

        model.addAttribute("example", optionalExample);

        return "addition";
    }

    @RequestMapping(value = "/subtraction", method = RequestMethod.GET)
    public String getSubtractionPracticing(Model model) {

        Example example = new Example();
        example.setCategory("Odčítání");

        Optional<Example> optionalExample = practicingService.getRandomExample(example);

        model.addAttribute("example", optionalExample);

        return "subtraction";
    }

    @RequestMapping(value = "/result", method = RequestMethod.POST)
    public String returnResult(@ModelAttribute Example example, RedirectAttributes redirectAttributes) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        Boolean result = practicingService.getResult(example);
        //TODO switch when multiplication and division added?
        if (example.getCategory().equals("Sčítání")) {
            if (result) {
                redirectAttributes.addFlashAttribute("successMessage", "JUPÍ, SPRÁVNĚ! :)");
                practicingService.playSound(true);

            } else {
                redirectAttributes.addFlashAttribute("failureMessage", "ZKUS TO ZNOVU! :(");
                practicingService.playSound(false);
            }
            return "redirect:/addition";

        } else if (example.getCategory().equals("Odčítání")) {
            if (result) {
                redirectAttributes.addFlashAttribute("successMessage", "JUPÍ, SPRÁVNĚ! :)");
                practicingService.playSound(true);

            } else {
                redirectAttributes.addFlashAttribute("failureMessage", "ZKUS TO ZNOVU! :(");
                practicingService.playSound(false);

            }
            return "redirect:/subtraction";
        }
        return "redirect:/";
    }

}
