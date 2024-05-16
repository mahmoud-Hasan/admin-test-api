package com.santechture.api.service;

import com.santechture.api.dto.GeneralResponse;
import com.santechture.api.dto.admin.AdminDto;
import com.santechture.api.dto.user.UserDto;
import com.santechture.api.entity.User;
import com.santechture.api.exception.BusinessExceptions;
import com.santechture.api.repository.UserRepository;
import com.santechture.api.validation.AddUserRequest;

import lombok.extern.slf4j.Slf4j;

import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserService {


    private final UserRepository userRepository;


    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public ResponseEntity<GeneralResponse> list(Pageable pageable){
        return new GeneralResponse().response(userRepository.findAll(pageable));
    }

    public ResponseEntity<GeneralResponse> addNewUser(AddUserRequest request) throws BusinessExceptions {
        if(userRepository.existsByUsernameIgnoreCase(request.getUsername())){
            throw new BusinessExceptions("username.exist");
        } else if (userRepository.existsByEmailIgnoreCase(request.getEmail())) {
            throw new BusinessExceptions("email.exist");
        }

        User user = new User(request.getUsername(),request.getEmail());
        userRepository.save(user);

        // get the current logged in user from security context 
        AdminDto admin = (AdminDto) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

    	log.info("User: {} , created by: {} ,with id: {}", request.getUsername(),admin.getUsername(),admin.getAdminId());
    	
        return new GeneralResponse().response(new UserDto(user));
    }

}
