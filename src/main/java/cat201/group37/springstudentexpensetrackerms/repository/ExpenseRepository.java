package cat201.group37.springstudentexpensetrackerms.repository;

import cat201.group37.springstudentexpensetrackerms.entity.Expense;
import cat201.group37.springstudentexpensetrackerms.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Long> {
    // implement custom queries
    @Query("SELECT e FROM Expense e WHERE e.user = :user AND e.type = :type") // JPA uses JPQL syntax, not SQL
    List<Expense> findByUserAndType(@Param("user") User user, @Param("type") String type);

    List<Expense> findByUser(User user);
}
