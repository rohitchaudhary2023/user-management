package com.cg.user.management.controller;

import com.cg.user.management.dto.Role;
import com.cg.user.management.exception.RoleNotFoundException;
import com.cg.user.management.service.RoleService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

@RestController
public class RoleController {

    private Logger logger = LoggerFactory.getLogger(RoleController.class);

    @Autowired
    private RoleService roleService;
    @Autowired
    private MessageSource messageSource;

    //Create Role
    @RequestMapping(method = RequestMethod.POST, value = "/roles")
    public ResponseEntity<Object> createRole(@RequestBody Role role){
        logger.info("Executing createRole method");
        Role dbRole = roleService.createRole(role);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(dbRole.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    //Update Role
    @RequestMapping(method = RequestMethod.PUT, value = "/roles")
    public Role updateRole(@RequestBody Role role){
        logger.info("Executing updateRole method");
        Optional<Role> dbRole = roleService.getRoleById(role.getId());
        if(dbRole.isPresent()){
            return roleService.updateRole(role);
        }else{
            throw new RoleNotFoundException(getMessage("role.not.found", role.getId().toString()));
        }
    }

    //Fetch All Roles
    @RequestMapping(method = RequestMethod.GET, value = "/roles")
    public List<Role> getAllRoles(){
        logger.info("Executing getAllRoles method");
        return roleService.getAllRoles();
    }

    //Fetch RoleById
    @RequestMapping(method = RequestMethod.GET, value = "/roles/{id}")
    public Role getRoleById(@PathVariable String id){
        logger.info("Executing getRoleById method with id -"+id);
        Optional<Role> role = roleService.getRoleById(Long.valueOf(id));
        if(role.isPresent()){
            return role.get();
        }else{
            throw new RoleNotFoundException(getMessage("role.not.found", id.toString()));
        }
    }

    //Fetch RolesByCategory
    @RequestMapping(method = RequestMethod.GET, value = "/roles/category/{category}")
    public List<Role> getRoleByCategory(@PathVariable String category){
       logger.info("Executing getRoleByCategory method- "+category);
        List<Role> roleByCategory = roleService.getRoleByCategory(category);
        if(roleByCategory.size()>0){
            return roleByCategory;
        }else{
            throw new RoleNotFoundException(getMessage("role.not.found.category", category));

        }
    }

    //  Fetch RolesByAction
    @RequestMapping(method = RequestMethod.GET, value = "/roles/action/{action}")
    public List<Role> getRoleByAction(@PathVariable String action){
        logger.info("Executing getRoleByAction method- "+action);
        List<Role> roleByAction = roleService.getRoleByAction(action);
        if(roleByAction.size()>0){
            return roleByAction;
        }else{
            throw new RoleNotFoundException(getMessage("role.not.found.action", action));
        }
    }

    //Delete RoleById
    @RequestMapping(method = RequestMethod.DELETE, value = "/roles/{id}")
    public void deleteRoleById(@PathVariable String id){
        logger.info("Executing deleteRoleById method- "+id);
        roleService.deleteRoleById(id);
    }

    //Delete RoleByCategory
    @RequestMapping(method = RequestMethod.DELETE, value = "/roles/category/{category}")
    public void deleteRoleByCategory(@PathVariable String category){
        logger.info("Executing deleteRoleByCategory method- "+category);
        roleService.deleteRoleByCategory(category);
    }

    //Delete RoleByAction
    @RequestMapping(method = RequestMethod.DELETE, value = "/roles/action/{action}")
    public void deleteRoleByAction(@PathVariable String action){
        logger.info("Executing deleteRoleByAction method- "+action);
        roleService.deleteRoleByAction(action);
    }

    public String getMessage(String messageKey, String... args){
        return messageSource.getMessage(messageKey, args, Locale.ENGLISH);
    }
}
