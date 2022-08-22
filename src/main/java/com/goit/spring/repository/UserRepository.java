package com.goit.spring.repository;

import com.goit.spring.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
public interface UserRepository extends CrudRepository<User, Long> {

    @Query("SELECT u from User u Where u.email = :email")
    public User getUserByEmail(@Param("email") String email);
}
