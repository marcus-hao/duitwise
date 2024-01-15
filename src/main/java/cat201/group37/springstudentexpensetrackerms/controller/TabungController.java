package cat201.group37.springstudentexpensetrackerms.controller;

import cat201.group37.springstudentexpensetrackerms.entity.Tabung;
import cat201.group37.springstudentexpensetrackerms.entity.User;
import cat201.group37.springstudentexpensetrackerms.repository.UserRepository;
import cat201.group37.springstudentexpensetrackerms.service.TabungService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/tabung")
public class TabungController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TabungService tabungService;

    @GetMapping
    public String getAllTabung(Model model) {
        // We need to get the currently authenticated user
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        // Remember, we use email to login
        String email = authentication.getName();
        User currentUser = userRepository.findByEmail(email);
        List<Tabung> tabungs = tabungService.getTabungByUser(currentUser);
        model.addAttribute("tabungs", tabungs);
        return "tabung/list";
    }

    @GetMapping("/add")
    public String showCreateTabungForm(Model model) {
        model.addAttribute("tabung", new Tabung());
        return "tabung/create";
    }

    @GetMapping("/edit/{id}")
    public String showEditTabungForm(@PathVariable Long id, Model model) {
        Tabung tabung = tabungService.getTabungById(id);
        model.addAttribute("tabung", tabung);
        return "tabung/edit";
    }

    @PostMapping
    public String saveTabung(@ModelAttribute("tabung") Tabung tabung) {
        // We need to get the currently authenticated user
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        // Remember, we use email to login
        String email = authentication.getName();
        User currentUser = userRepository.findByEmail(email);

        tabung.setUser(currentUser);
        tabungService.saveTabung(tabung);
        return "redirect:/tabung";
    }

    @PostMapping("/edit/{id}")
    public String updateTabung(@PathVariable Long id, @ModelAttribute("tabung") Tabung updatedTabung) {
        // Get old tabung from db
        Tabung tabung = tabungService.getTabungById(id);

        // Update the old tabung with new values
        tabung.setName(updatedTabung.getName());
        tabung.setTarget_amount(updatedTabung.getTarget_amount());
        tabung.setStart_date(updatedTabung.getStart_date());
        tabung.setEnd_date(updatedTabung.getEnd_date());

        // Write back to DB
        tabungService.updateTabung(tabung);

        return "redirect:/tabung";
    }

    @PostMapping("/delete/{id}")
    public String deleteTabung(@PathVariable Long id) {
        tabungService.deleteTabung(id);
        return "redirect:/tabung";
    }
}
