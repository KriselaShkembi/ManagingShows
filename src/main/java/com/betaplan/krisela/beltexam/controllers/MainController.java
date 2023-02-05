package com.betaplan.krisela.beltexam.controllers;

import com.betaplan.krisela.beltexam.models.LoginUser;
import com.betaplan.krisela.beltexam.models.Rating;
import com.betaplan.krisela.beltexam.models.Show;
import com.betaplan.krisela.beltexam.models.User;
import com.betaplan.krisela.beltexam.services.RatingService;
import com.betaplan.krisela.beltexam.services.ShowService;
import com.betaplan.krisela.beltexam.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Controller
public class MainController {

    @Autowired
    private UserService userService;
    @Autowired
    private ShowService showService;

    @Autowired
    private RatingService ratingService;

    //Login, Register, Log out
    @GetMapping("/")
    public String index(@ModelAttribute("newUser") User newUser,
                        @ModelAttribute("newLogin")User newLogin,
                        Model model){
        model.addAttribute("newUser", newUser);
        model.addAttribute("newLogin", newLogin);
        return "index";
    }

    @PostMapping("/register")
    public String register(@Valid @ModelAttribute("newUser") User newUser,
                           @NotNull BindingResult result,
                           Model model,
                           HttpSession session){
        userService.register(newUser, result);
        if (result.hasErrors()){
            model.addAttribute("newLogin", new LoginUser());
            return "index";
        }
        session.setAttribute("loogedInUserId", newUser.getId());
        return "redirect:/shows";
    }

    @PostMapping("/login")
    public String login(@Valid @ModelAttribute("newLogin") LoginUser newLogin,
                        @NotNull BindingResult result,
                        Model model,
                        HttpSession session){
        User user = userService.login(newLogin,result);
        if (result.hasErrors()){
            model.addAttribute("newUser", new User());
            return "index";
        }
        session.setAttribute("loggedInUserId", user.getId());
        return "redirect:/shows";
    }

    @RequestMapping("/logout")
    public String logout(HttpSession session){
        session.invalidate();
        return "redirect:/";
    }

    // Dashboard
    @RequestMapping("/shows")
    public String dashboard(HttpSession session, Model model){
        Long loggedInUserId = (Long) session.getAttribute("loggedInUserId");
        if(loggedInUserId==null){
            return "redirect:/";
        }
        User loggedInUser = userService.findOneUser(loggedInUserId);
        model.addAttribute("user",loggedInUser);
        List<Show> shows =  showService.getAll();
        addAverage(shows);

        model.addAttribute("shows", shows);

        return "dashboard";
    }


    private void addAverage( List<Show> shows){

        for(Show show : shows){
            Double sum = 0.0;
            List<Rating> ratingList = show.getRatingsShow();
            for(Rating rating : ratingList){
                sum += rating.getRatingValue();
            }
            show.setAverage(sum/ratingList.size());
        }
    }

    @RequestMapping("/shows/new")
    public String newProject(@ModelAttribute("show") Show show,
                             Model model) {
        model.addAttribute("shows", showService.getAll());
        return "newShow";
    }

    @PostMapping("/shows/new")
    public String addNew(@Valid @ModelAttribute("show") Show show,
                         @NotNull BindingResult result, Model model,
                         HttpSession session) {
        Long userId = (Long) session.getAttribute("loggedInUserId");
        User userLogged = userService.findOneUser(userId);
        List<Show> showList = showService.findByTitle(show.getTitle());
        if(!showList.isEmpty()){
            return "newShow";
        }


        if (result.hasErrors()) {
            return "newShow";
        } else {
            show.setUser(userLogged);
            showService.create(show);
            return "redirect:/shows";
        }
    }

    @RequestMapping("/shows/{id}")
    public String bookDetail(@ModelAttribute("rating") Rating rating, BindingResult result, Model model,
                             @PathVariable("id") Long id,
                             HttpSession session) {
        if(session.getAttribute("loggedInUserId") == null){
            return "redirect:/shows";
        }
        if (result.hasErrors()){
            return "redirect:/shows/{id}";
        }
        Long loggedInUserId = (Long) session.getAttribute("loggedInUserId");
        Show show = showService.findById(id);
        User loggedInUser = userService.findOneUser(loggedInUserId);
        List<Rating> ratingList = ratingService.findByShow(show);
        model.addAttribute("user", loggedInUser);
        model.addAttribute("show", show);
        model.addAttribute("ratings", ratingList);

        return "view";
    }

    @GetMapping("/shows/{id}/edit")
    public String getEdit(@PathVariable("id") Long id, Model model){
        Show show = showService.findById(id);
        model.addAttribute("show", show);
        return "edit";
    }

    @PutMapping("/shows/{id}/edit")
    public String edit(@Valid @ModelAttribute("show") Show show,
                       @NotNull BindingResult result, Model model,
                       @PathVariable("id") Long id, HttpSession session) {
        Long userId = (Long) session.getAttribute("loggedInUserId");
        User userLogged = userService.findOneUser(userId);
        List<Show> showList = showService.findByTitle(show.getTitle());
        if(!showList.isEmpty()){
            return "edit";
        }
        if (result.hasErrors()) {
            return "edit";
        } else {
            show.setUser(userLogged);
            showService.save(show);
            return "redirect:/shows";
        }
    }

    @DeleteMapping("/shows/{id}/delete")
    public String delete(@PathVariable("id") Long id){
        showService.delete(id);
        return "redirect:/shows";
    }


    //Ratings
    @PostMapping("/shows/{id}")
    public String rate(@Valid @ModelAttribute("rating") Rating rating,
                       @NotNull BindingResult result, Model model, @PathVariable("id") Long id,
                       HttpSession session){
        if(session.getAttribute("loggedInUserId") == null) {
            return "redirect:/logout";
        }
        if(result.hasErrors()){
            return "redirect:/shows/{id}";
        }

        Long loggedInUserId = (Long) session.getAttribute("loggedInUserId");
        Show show = showService.findById(id);
        User loggedInUser = userService.findOneUser(loggedInUserId);
        List<Rating> ratingList = ratingService.findByShow(show);

        Rating ratingToAdd = new Rating();
        ratingToAdd.setUser(loggedInUser);
        ratingToAdd.setShow(show);
        ratingToAdd.setRatingValue(rating.getRatingValue());
        ratingService.create(ratingToAdd);
        ratingList.add(ratingToAdd);

        model.addAttribute("user", loggedInUser);
        model.addAttribute("show", show);
        model.addAttribute("ratings", ratingList);

        return "redirect:/shows/{id}";

    }

}
