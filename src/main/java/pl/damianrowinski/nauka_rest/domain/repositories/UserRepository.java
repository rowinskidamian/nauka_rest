package pl.damianrowinski.nauka_rest.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.damianrowinski.nauka_rest.domain.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
