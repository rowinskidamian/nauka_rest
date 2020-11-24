package pl.juniorjavaproject.testrestapi.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.juniorjavaproject.testrestapi.domain.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
