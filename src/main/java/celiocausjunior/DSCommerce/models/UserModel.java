package celiocausjunior.DSCommerce.models;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_users")
public class UserModel implements UserDetails {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
   
    @Column(unique = true)
    private String email;
   
    @Column(unique = true)
    private String phone;
   
    private LocalDate birthDate;
    private String password;


    @ManyToMany
    @JoinTable(name = "tb_users_roles",
    joinColumns = @JoinColumn(name = "user_id"),
    inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<RoleModel> roles = new HashSet<>();

    @OneToMany(mappedBy = "client")
    private List<OrderModel> orders = new ArrayList<>();


    public UserModel() {
    }


    public UserModel(Long id, String name, String email, String phone, LocalDate birthDate, String password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.birthDate = birthDate;
        this.password = password;
    }


    public Long getId() {
        return id;
    }


    public void setId(Long id) {
        this.id = id;
    }


    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }


    public String getEmail() {
        return email;
    }


    public void setEmail(String email) {
        this.email = email;
    }


    public String getPhone() {
        return phone;
    }


    public void setPhone(String phone) {
        this.phone = phone;
    }


    public LocalDate getBirthDate() {
        return birthDate;
    }


    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }


    public String getPassword() {
        return password;
    }


    public void setPassword(String password) {
        this.password = password;
    }

        public List<OrderModel> getOrders() {
        return orders;
    }

    public void addRole(RoleModel role) {
        roles.add(role);
    }

    public boolean hasRole(String roleName) {
        for (RoleModel role : roles) {
            if (role.getAuthority().equals(roleName)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }


    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        UserModel other = (UserModel) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }


    @Override
    public String getUsername() {
        return email;
    }


    @Override
    public boolean isAccountNonExpired() {
      return true;
    }


    @Override
    public boolean isAccountNonLocked() {
      return true;
    }


    @Override
    public boolean isCredentialsNonExpired() {
         return true;
    }


    @Override
    public boolean isEnabled() {
         return true;
    }



   
}
