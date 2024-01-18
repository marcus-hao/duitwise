package cat201.group37.springstudentexpensetrackerms.repository;

import cat201.group37.springstudentexpensetrackerms.entity.Tabung;
import cat201.group37.springstudentexpensetrackerms.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TabungRepository extends JpaRepository<Tabung, Long> {
    // implement custom queries
    @Query("SELECT t FROM Tabung t WHERE t.user = :user AND t.type = :type") // JPA uses JPQL syntax, not SQL
    List<Tabung> findByUserAndType(@Param("user") User user, @Param("type") String type);

    List<Tabung> findByUser(User user);
}
