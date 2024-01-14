package cat201.group37.springstudentexpensetrackerms.controller;

import cat201.group37.springstudentexpensetrackerms.entity.Expense;
import cat201.group37.springstudentexpensetrackerms.entity.User;
import cat201.group37.springstudentexpensetrackerms.repository.UserRepository;
import cat201.group37.springstudentexpensetrackerms.service.ExpenseService;
import cat201.group37.springstudentexpensetrackerms.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/expenses")
public class ExpenseController {
    @Autowired
    private ExpenseService expenseService;

    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public String getAllExpenses(Model model) {
        // We need to get the currently authenticated user
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        // Remember, we use email to login
        String email = authentication.getName();
        User currentUser = userRepository.findByEmail(email);
        List<Expense> expenses = expenseService.getExpenseByUser(currentUser);
        model.addAttribute("expenses", expenses);
        return "expenses/list";
    }

    @GetMapping("/{id}")
    public String getExpenseById(@PathVariable Long id, Model model) {
        Expense expense = expenseService.getExpenseById(id);
        model.addAttribute("expense", expense);
        return "expenses/details";
    }

    @PostMapping
    public String saveExpense(@ModelAttribute("expense") Expense expense) {
        // We need to assign the currently authenticated user to the expense record
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        // Remember, we use email to login
        String email = authentication.getName();
        User currentUser = userRepository.findByEmail(email);

        expense.setUser(currentUser);
        expenseService.saveExpense(expense);
        return "redirect:/expenses";
    }

    @PostMapping("/edit/{id}")
    public String updateExpense(@PathVariable Long id, @ModelAttribute("expense") Expense updatedExpense) {
        // Get old expense from db by id
        Expense expense = expenseService.getExpenseById(id);

        // Update the fields in the old expense
        expense.setAmount(updatedExpense.getAmount());
        expense.setType(updatedExpense.getType());
        expense.setDescription(updatedExpense.getDescription());

        // Write to db
        expenseService.updateExpense(expense);

        return "redirect:/expenses";
    }

    @GetMapping("/create")
    public String showCreateExpenseForm(Model model) {
        //create expense object to hold expense form data
        model.addAttribute("expense", new Expense());
        return "expenses/create";
    }

    @GetMapping("/edit/{id}")
    public String showEditExpenseForm(@PathVariable Long id, Model model) {
        Expense expense = expenseService.getExpenseById(id);
        model.addAttribute("expense", expense);
        return "expenses/edit";
    }

    @PostMapping("/delete/{id}")
    public String deleteExpense(@PathVariable Long id) {
        expenseService.deleteExpense(id);
        return "redirect:/expenses";
    }
}
