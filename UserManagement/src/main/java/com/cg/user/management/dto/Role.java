package com.cg.user.management.dto;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "ROLES")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String category;
    private String action;
    private String description;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "userRoles")
    private Set<User> users = new HashSet<>();

    public Role() {
    }

    public Role(String category, String action, String description) {
        this.category = category;
        this.action = action;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", category='" + category + '\'' +
                ", action='" + action + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
