package com.cg.user.management.service;

import com.cg.user.management.dto.Role;
import com.cg.user.management.exception.DBException;
import com.cg.user.management.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;

    public Role createRole(Role role) {
        try{
            Role savedRole = roleRepository.save(role);
            return savedRole;
        }catch(Exception e){
            throw new DBException(e.getMessage());
        }
    }

    public List<Role> getAllRoles(){
        return roleRepository.findAll();
    }

    public Optional<Role> getRoleById(Long id) {
        return roleRepository.findById(id);
    }

    public List<Role> getRoleByCategory(String category) {
        return roleRepository.findByCategory(category);
    }

    public List<Role> getRoleByAction(String action) {
        return roleRepository.findByAction(action);
    }

    public Role updateRole(Role role) {
        try{
            Role savedRole = roleRepository.save(role);
            return savedRole;
        }catch(Exception e){
            throw new DBException(e.getMessage());
        }
    }

    public void deleteRoleById(String id) {
        roleRepository.deleteById(Long.valueOf(id));
    }

    public void deleteRoleByCategory(String category) {
        roleRepository.deleteByCategory(category);
    }

    public void deleteRoleByAction(String action) {
        roleRepository.deleteByAction(action);
    }
}
