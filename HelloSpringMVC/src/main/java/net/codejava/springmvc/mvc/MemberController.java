package net.codejava.springmvc.mvc;

 import javax.validation.Valid;

import net.codejava.springmvc.domain.Member;
import net.codejava.springmvc.repo.MemberDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value="/")
public class MemberController
{
    @Autowired
    private MemberDao memberDao;

    @RequestMapping(method=RequestMethod.GET)
    public String displaySortedMembers(Model model)
    {
        model.addAttribute("newMember", new Member());
        model.addAttribute("members", memberDao.findAllOrderedByName());
        return "index";
    }

    @RequestMapping(method=RequestMethod.POST)
    public String registerNewMember(@Valid @ModelAttribute("newMember") Member newMember, BindingResult result, Model model)
    {
        if (!result.hasErrors()) {
            memberDao.register(newMember);
            return "redirect:/";
        }
        else {
            model.addAttribute("members", memberDao.findAllOrderedByName());
            return "index";
        }
    }
    
    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String test(Model model) {
        String greetings = "Greetings, Spring MVC!";
        model.addAttribute("message", greetings);
     
        return "test";
    }
    
    @RequestMapping(value = "/postings", method = RequestMethod.GET)
    public String postings(Model model) {
        String greetings = "Greetings, Spring MVC Postings Page!";
        model.addAttribute("message", greetings);
     
        return "postings";
    }
}
