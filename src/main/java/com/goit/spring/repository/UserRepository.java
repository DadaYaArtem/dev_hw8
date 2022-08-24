package com.goit.spring.repository;

import com.goit.spring.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends CrudRepository<User, Long> {
    @Query("SELECT u FROM User u WHERE u.email = ?1")
    User findByEmail(String email);

    @Query(nativeQuery = true, value = "SELECT name FROM USERS_ROLES JOIN USERS on USERS_ROLES.USER_ID = USERS.ID " +
            "JOIN ROLES on USERS_ROLES.ROLE_ID = ROLES.ID WHERE email = :email")
    String getRoleByEmail(@Param("email") String email);
}
