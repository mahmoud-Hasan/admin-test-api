package com.santechture.api.controller;


import com.santechture.api.dto.LoginResponse;
import com.santechture.api.dto.admin.AdminDto;
import com.santechture.api.exception.BusinessExceptions;
import com.santechture.api.service.AdminService;
import com.santechture.api.util.JwtUtil;
import com.santechture.api.validation.LoginRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "admin")
public class AdminController {

    private JwtUtil jwtUtil;
    private final AdminService adminService;

    public AdminController(AdminService adminService, JwtUtil jwtUtil) {
        this.adminService = adminService;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping
    public ResponseEntity<?> login(@RequestBody LoginRequest request) throws BusinessExceptions {
        
    	try{
    		AdminDto admin =adminService.loginJWT(request);
            String token = jwtUtil.createToken(admin);
            
            LoginResponse res = new LoginResponse(token,admin.getUsername(),admin.getAdminId());
            return ResponseEntity.ok(res);

    	}catch ( BusinessExceptions be) {
            return ResponseEntity.badRequest().body("Invalid Credentials");

    	}
    
    }
}
