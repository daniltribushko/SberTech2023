package com.example.sbertech2023.service.auth;

import com.example.sbertech2023.exceptions.users.UserAlreadyExistException;
import com.example.sbertech2023.exceptions.users.UserByIdNotFoundException;
import com.example.sbertech2023.exceptions.users.UserByUserNameNotFoundException;
import com.example.sbertech2023.exceptions.users.WrongRoleException;
import com.example.sbertech2023.models.dto.request.SaveUserRequestDto;
import com.example.sbertech2023.models.entities.User;
import com.example.sbertech2023.models.enums.RecordState;
import com.example.sbertech2023.models.enums.Role;
import com.example.sbertech2023.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * @author Tribushko Danil
 * @since 27.11.2023
 *
 * Сервис для работы с пользователями
 */
@Service
public class AuthUserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public AuthUserService(
            UserRepository userRepository,
            BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * Получение пользователя по логину
     *
     * @param username the username identifying the user whose data is required.
     * @return пользователь
     * @throws UsernameNotFoundException пользователь с текущем логином не найден
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByLogin(username)
                .orElseThrow(() -> new UsernameNotFoundException(username));
        return new org.springframework.security.core.userdetails.User(
                user.getLogin(),
                user.getPassword(),
                user.getRoles()
                        .stream()
                        .map(r -> new SimpleGrantedAuthority("ROLE_" + r.name()))
                        .toList())
                ;
    }

    /**
     * Сохранение нового пользователя
     *
     * @param request запрос на сохранение пользователя
     */
    public void saveUser(SaveUserRequestDto request){
        User user = userRepository.findByLogin(request.getLogin())
                .orElse(null);
        if (user != null){
            throw new UserAlreadyExistException(request.getLogin());
        } else {
            user = request.mapToEntity();
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            Role role = Role.mapFromString(request.getRole())
                    .orElseThrow(() -> new WrongRoleException(request.getRole()));
            user.setRecordState(RecordState.ACTIVE);
            user.setRoles(Set.of(role));
            userRepository.save(user);
        }
    }
}
