package cat201.group37.springstudentexpensetrackerms.repository;

import cat201.group37.springstudentexpensetrackerms.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository 
public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);

}
