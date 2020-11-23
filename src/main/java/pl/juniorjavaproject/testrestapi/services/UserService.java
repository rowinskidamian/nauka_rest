package pl.juniorjavaproject.testrestapi.services;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.juniorjavaproject.testrestapi.domain.model.User;
import pl.juniorjavaproject.testrestapi.domain.repositories.UserRepository;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Transactional
@Service
public class UserService {

    private final UserRepository userRepository;

    public User findUserById(long id) {
        return userRepository.findUserById(id);
    }
}
