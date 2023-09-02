package celiocausjunior.DSCommerce.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import celiocausjunior.DSCommerce.interfaces.UserDetailsProjection;
import celiocausjunior.DSCommerce.models.UserModel;

public interface UserRepository extends JpaRepository<UserModel, Long> {

    @Query(nativeQuery = true, value =
            "SELECT tb_users.email AS username, tb_users.password, tb_roles.id AS roleId, tb_roles.authority " +
            "FROM tb_users " +
            "INNER JOIN tb_users_roles ON tb_users.id = tb_users_roles.users_id " +
            "INNER JOIN tb_roles ON tb_roles.id = tb_users_roles.roles_id " +
            "WHERE tb_users.email = :email"
    )
    List<UserDetailsProjection> searchUserAndRolesByEmail(@Param("email") String email);


    Optional <UserModel> findByEmail(String email);
}
