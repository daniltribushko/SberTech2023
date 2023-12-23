package com.example.sbertech2023.service.imp;

import com.example.sbertech2023.exceptions.users.UserByIdNotFoundException;
import com.example.sbertech2023.exceptions.users.UserByUserNameNotFoundException;
import com.example.sbertech2023.models.entities.User;
import com.example.sbertech2023.models.enums.Role;
import com.example.sbertech2023.repositories.UserRepository;
import com.example.sbertech2023.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Tribushko Danil
 * @since 19.12.2023
 *
 * Реализация сервиса, для работы с пользователями
 */
@Service
public class UserServiceImp implements UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserServiceImp(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserByIdNotFoundException(id));
    }

    @Override
    public User findByUserName(String userName) {
        return userRepository.findByLogin(userName)
                .orElseThrow(() -> new UserByUserNameNotFoundException(userName));
    }

    @Override
    public boolean isAdmin(User user) {
        boolean result = false;
        for (Role role : user.getRoles()){
            if (role.equals(Role.ADMIN)){
                result = true;
                break;
            }
        }
        return result;
    }
}
