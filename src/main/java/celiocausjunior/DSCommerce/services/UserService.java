package celiocausjunior.DSCommerce.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

import celiocausjunior.DSCommerce.interfaces.UserDetailsProjection;
import celiocausjunior.DSCommerce.models.RoleModel;
import celiocausjunior.DSCommerce.models.UserModel;
import celiocausjunior.DSCommerce.models.dtos.UserDTO;
import celiocausjunior.DSCommerce.repositories.UserRepository;
import jakarta.transaction.Transactional;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        List<UserDetailsProjection> result = repository.searchUserAndRolesByEmail(username);

        if (result.isEmpty() || result.get(0).getUsername() == null) {
            throw new UsernameNotFoundException("Email not found");
        }

        UserModel user = new UserModel();
        user.setEmail(username);
        user.setPassword(result.get(0).getPassword());

        for (UserDetailsProjection role : result) {
            user.addRole(new RoleModel(role.getRoleId(), role.getAuthority()));
        }

        return user;
    }

    protected UserModel authenticated() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Jwt jwtPrincipal = (Jwt) authentication.getPrincipal();
        String username = jwtPrincipal.getClaim("username");

        UserModel user = repository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("Email not found"));

        return user;
    }


   @Transactional
    public UserDTO getMe() {
        UserModel user = authenticated();
        return new UserDTO(user);
    }
}
