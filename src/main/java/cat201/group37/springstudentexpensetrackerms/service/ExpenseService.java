package cat201.group37.springstudentexpensetrackerms.service;

import cat201.group37.springstudentexpensetrackerms.entity.Expense;
import cat201.group37.springstudentexpensetrackerms.entity.User;
import cat201.group37.springstudentexpensetrackerms.repository.ExpenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExpenseService {

    @Autowired
    private ExpenseRepository expenseRepository;

    public List<Expense> getAllExpenses() {
        return expenseRepository.findAll();
    }

    public Expense getExpenseById(Long id) {
        return expenseRepository.findById(id).orElse(null);
    }

    public Expense saveExpense(Expense expense) {
        return expenseRepository.save(expense);
    }

    public Expense updateExpense(Expense expense) {
        return expenseRepository.save(expense);
    }
    public void deleteExpense(Long id) {
        expenseRepository.deleteById(id);
    }

    public List<Expense> getExpenseByUser(User user) {
        return expenseRepository.findByUser(user);
    }

    public List<Expense> getExpenseByType(User user, String type) {
        return expenseRepository.findByUserAndType(user, type);
    }
}
