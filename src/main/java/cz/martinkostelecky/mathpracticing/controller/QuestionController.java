package cz.martinkostelecky.mathpracticing.controller;

import cz.martinkostelecky.mathpracticing.entity.Question;
import cz.martinkostelecky.mathpracticing.service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Controller
@RequiredArgsConstructor
@RequestMapping(value = "math_practicing/admin")
public class QuestionController {

    private final QuestionService questionService;

    @RequestMapping(value = "/questions", method = RequestMethod.GET)
    public String getAllQuestions() {
        return "questions";
    }


    @RequestMapping(value = "/add", method = GET)
    public String addQuestionForm(Model model) {

        Question question = new Question();
        model.addAttribute("qusetion", question);

        return "add_question";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String addQuestion(@ModelAttribute("question") Question question) {

        questionService.saveQuestion(question);

        return "redirect:/questions";
    }
}
