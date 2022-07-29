package com.cg.user.management.repository;

import com.cg.user.management.dto.Role;
import com.cg.user.management.dto.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findByFirstName(String firstName);
    List<User> findByLastName(String lastName);
    User findByEmail(String email);
    Long deleteByFirstName(String firstName);
    Long deleteByLastName(String lastName);
    Long deleteByEmail(String email);

}
