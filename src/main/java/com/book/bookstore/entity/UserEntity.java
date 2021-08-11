package com.book.bookstore.entity;

import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Document(collection = "user")
@Data
public class UserEntity implements UserDetails, Serializable {

    @Id
    private ObjectId id;

    @Indexed(unique = true)
    private String username;

    private String password;

    private String mail;

    private Set<RoleEntity> authorities = new HashSet<>();

    private boolean enabled = true;

    public UserEntity(String username, String password) {
        this.username = username;
        this.password = password;
    }

    @Override
    public boolean isAccountNonExpired() {
        return isEnabled();
    }

    @Override
    public boolean isAccountNonLocked() {
        return isEnabled();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return isEnabled();
    }
}
