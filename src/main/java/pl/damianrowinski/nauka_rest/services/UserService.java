package pl.damianrowinski.nauka_rest.services;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.damianrowinski.nauka_rest.domain.model.User;
import pl.damianrowinski.nauka_rest.domain.repositories.UserRepository;
import pl.damianrowinski.nauka_rest.exceptions.ElementNotFoundException;

import javax.transaction.Transactional;
import java.util.Optional;

@RequiredArgsConstructor
@Transactional
@Service
public class UserService {

    private final UserRepository userRepository;

    public User findUserById(long id) throws ElementNotFoundException {
        Optional<User> optionalUser = userRepository.findById(id);

        return optionalUser.orElseThrow(() -> new ElementNotFoundException("Nie znaleziono użytkownika o podanym ID."));
    }
}
