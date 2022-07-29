package com.cg.user.management.repository;

import com.cg.user.management.dto.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface RoleRepository extends JpaRepository<Role, Long> {
    List<Role> findByCategory(String category);

    List<Role> findByAction(String action);

    Role findByCategoryAndAction(String category, String action);

    Long deleteByCategory(String category);

    Long deleteByAction(String action);
}
