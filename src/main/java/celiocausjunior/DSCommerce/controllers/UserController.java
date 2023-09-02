package celiocausjunior.DSCommerce.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import celiocausjunior.DSCommerce.models.dtos.UserDTO;
import celiocausjunior.DSCommerce.services.UserService;
import jakarta.annotation.security.RolesAllowed;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;


    @RolesAllowed({ "ROLE_OPERATOR", "ROLE_ADMIN" })
    @GetMapping("/me")
    public ResponseEntity<UserDTO> getMe() {
        UserDTO dto = userService.getMe();
        return ResponseEntity.ok(dto);
    }

}