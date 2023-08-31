package celiocausjunior.DSCommerce.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import celiocausjunior.DSCommerce.interfaces.UserDetailsProjection;
import celiocausjunior.DSCommerce.models.RoleModel;
import celiocausjunior.DSCommerce.models.UserModel;
import celiocausjunior.DSCommerce.repositories.UserRepository;

@Service
public class UserService implements UserDetailsService{

    @Autowired
    private UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

       List<UserDetailsProjection> result = repository.searchUserAndRolesByEmail(username);
       
       if (result.isEmpty()) {
           throw new UsernameNotFoundException("Email not found");
       }
       
       UserModel user = new UserModel();
       user.setEmail(username);
       user.setPassword(result.get(0).getPassword());

       for(UserDetailsProjection role : result) {
           user.addRole(new RoleModel(role.getRoleId(), role.getAuthority()));
       }

       return user;
    }

    
}
