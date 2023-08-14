package com.rushchanskii.storeapp.users;
import org.springframework.data.jpa.repository.JpaRepository;



public interface UsersRepository extends JpaRepository<Users, Long>{
	 
	Users findByLoginName(String username);

}
