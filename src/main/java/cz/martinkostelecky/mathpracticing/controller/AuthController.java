package cz.martinkostelecky.mathpracticing.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 * @author Martin Kostelecký
 */
@Controller
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final BCryptPasswordEncoder passwordEncoder;
    private final UserDetailsService userDetailsService;

    @RequestMapping(value = "/login", method = GET)
    public String loginPage() {
        return "login";
    }

    @RequestMapping(value = "/authenticate", method = POST)
    public String authenticate(@RequestParam("username") String username,
                               @RequestParam("password") String password,
                               HttpServletRequest httpServletRequest,
                               HttpServletResponse httpServletResponse,
                               RedirectAttributes redirectAttributes) {

        if (!passwordEncoder.matches(password, userDetailsService.loadUserByUsername(username).getPassword())) {
            redirectAttributes.addAttribute("error", "Chybné uživatelské jméno nebo heslo.");
            return "redirect:/login";
        }

        var authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));

        SecurityContext context = SecurityContextHolder.getContext();

        context.setAuthentication(authentication);

        new HttpSessionSecurityContextRepository().saveContext(context, httpServletRequest, httpServletResponse);

        redirectAttributes.addAttribute("success");
        return "redirect:/examples";
    }

}
