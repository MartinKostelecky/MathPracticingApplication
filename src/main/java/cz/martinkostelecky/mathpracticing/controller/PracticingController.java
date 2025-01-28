package cz.martinkostelecky.mathpracticing.controller;

import cz.martinkostelecky.mathpracticing.entity.Example;
import cz.martinkostelecky.mathpracticing.exception.ExampleNotFoundException;
import cz.martinkostelecky.mathpracticing.service.PracticingService;
import cz.martinkostelecky.mathpracticing.service.UnicornBadgeService;
import cz.martinkostelecky.mathpracticing.service.impl.UnicornBadgeServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

/**
 * @author Martin Kostelecký
 */
@Controller
@RequiredArgsConstructor
public class PracticingController {

    private final PracticingService practicingService;
    private final UnicornBadgeService unicornBadgeService;

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

    @RequestMapping(value = "/logic", method = RequestMethod.POST)
    public String handleLogicOperatorsPost() {
        return "redirect:/logic_operators";
    }

    @RequestMapping(value = "/addition", method = RequestMethod.GET)
    public String getAdditionPracticing(Model model) throws ExampleNotFoundException {

        Example example = new Example();

        example.setCategory("Sčítání");

        Optional<Example> optionalExample = practicingService.getRandomExample(example);

        model.addAttribute("example", optionalExample);

        return "addition";
    }

    @RequestMapping(value = "/subtraction", method = RequestMethod.GET)
    public String getSubtractionPracticing(Model model) throws ExampleNotFoundException {

        Example example = new Example();

        example.setCategory("Odčítání");

        Optional<Example> optionalExample = practicingService.getRandomExample(example);

        model.addAttribute("example", optionalExample);

        return "subtraction";
    }

    @RequestMapping(value = "/logic_operators", method = RequestMethod.GET)
    public String getLogicOperatorsPracticing(Model model) {

        int firstNumber = practicingService.getRandomNumber();
        int secondNumber = practicingService.getRandomNumber();
        String chosenOperator = "";

        model.addAttribute("firstNumber", firstNumber);
        model.addAttribute("secondNumber", secondNumber);
        model.addAttribute("chosenOperator", chosenOperator);

        return "logic_operators";
    }

    @RequestMapping(value = "/result", method = RequestMethod.POST)
    public String returnResult(@ModelAttribute Example example, RedirectAttributes redirectAttributes) {

        Boolean result = practicingService.getResult(example);
        if (getUnicornBadgeList(redirectAttributes, result)) return "redirect:/success";

        if (example.getCategory().equals("Sčítání")) {
            return "redirect:/addition";
        } else if (example.getCategory().equals("Odčítání")) {
            return "redirect:/subtraction";
        }
        return "redirect:/";
    }

    @RequestMapping(value = "/result-logic", method = RequestMethod.POST)
    public String returnResultLogicOperators(@RequestParam("firstNumber") int firstNumber,
                                             @RequestParam("secondNumber") int secondNumber,
                                             @RequestParam("chosenOperator") String chosenOperator,
                                             RedirectAttributes redirectAttributes) {

        Boolean result = practicingService.getResultLogicOperators(firstNumber, secondNumber, chosenOperator);
        if (getUnicornBadgeList(redirectAttributes, result)) return "redirect:/success";

        return "redirect:/logic_operators";
    }

    private boolean getUnicornBadgeList(RedirectAttributes redirectAttributes, Boolean result) {
        List<UnicornBadgeServiceImpl.UnicornBadge> unicornBadgeList = unicornBadgeService.getListOfUnicornBadges(result);
        boolean isAccomplished = unicornBadgeService.getIsAccomplished(unicornBadgeList);

        if (isAccomplished) {
            unicornBadgeService.getNewListOfUnicornBadges(unicornBadgeList);
        }

        if (result) {
            if (isAccomplished) {
                redirectAttributes.addFlashAttribute("accomplishedMessage",
                        "!!!GRATULUJI, MÁŠ VŠECH DESET JEDNOROŽCŮ!!!");
                return true;
            }
            redirectAttributes.addFlashAttribute("successMessage", "JUPÍ, SPRÁVNĚ! :)");
            redirectAttributes.addFlashAttribute("unicorns", unicornBadgeList);
        } else {
            redirectAttributes.addFlashAttribute("failureMessage", "ZKUS TO ZNOVU! :(");
            redirectAttributes.addFlashAttribute("unicorns", unicornBadgeList);
        }
        return false;
    }

    @RequestMapping(value = "/about", method = RequestMethod.GET)
    public String aboutPage() {
        return "about";
    }

    @RequestMapping(value = "/success", method = RequestMethod.GET)
    public String successLandingPage() {
        return "success";
    }
}
