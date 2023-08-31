package celiocausjunior.DSCommerce.interfaces;

public interface UserDetailsProjection {

	String getUsername();
	String getPassword();
	Long getRoleId();
	String getAuthority();
}