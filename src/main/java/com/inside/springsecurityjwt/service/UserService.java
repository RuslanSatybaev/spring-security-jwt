package com.inside.springsecurityjwt.service;

import com.inside.springsecurityjwt.dao.UserDAO;
import com.inside.springsecurityjwt.entity.RoleEntity;
import com.inside.springsecurityjwt.entity.UserEntity;
import com.inside.springsecurityjwt.repository.RoleEntityRepository;
import com.inside.springsecurityjwt.repository.UserEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserEntityRepository userEntityRepository;
    @Autowired
    private RoleEntityRepository roleEntityRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    UserDAO userDAO;

    public UserEntity saveUser(UserEntity userEntity) {
        RoleEntity userRole = roleEntityRepository.findByName("ROLE_USER");
        userEntity.setRoleEntity(userRole);
        userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));
        return userEntityRepository.save(userEntity);
    }

    public UserEntity findByLogin(String login) {
        return userEntityRepository.findByLogin(login);
    }

    public UserEntity findByLoginAndPassword(String login, String password) {
        UserEntity userEntity = findByLogin(login);
        if (userEntity != null) {
            if (passwordEncoder.matches(password, userEntity.getPassword())) {
                return userEntity;
            }
        }
        return null;
    }

    public UserEntity saveMessage(UserEntity userEntity) {
        return userEntityRepository.save(userEntity);
    }



    public List<String> showHistoryMessage(int countMessage) {
        List<String> lastMessages = userDAO.getMessageDB();
        return lastMessages.subList(lastMessages.size() - countMessage, lastMessages.size());

    }
}
