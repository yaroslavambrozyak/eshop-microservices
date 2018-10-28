package com.study.yaroslavambrozyak.oauthservice.entity;

import com.study.yaroslavambrozyak.oauthservice.security.Role;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
public class UserAccount {

    @Id
    @GeneratedValue
    private UUID id;
    @Column(unique = true, nullable = false)
    private String email;
    private String userName;
    @Column(nullable = false)
    private String password;
    private Boolean activated = false;
    @Enumerated(EnumType.STRING)
    private Role role;
    @OneToOne(cascade = CascadeType.PERSIST, orphanRemoval = true)
    private UserActivationToken userActivationToken;

    public void addToken(UserActivationToken userActivationToken){
        this.userActivationToken = userActivationToken;
        userActivationToken.setUserAccount(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserAccount that = (UserAccount) o;

        return email != null ? email.equals(that.email) : that.email == null;
    }

    @Override
    public int hashCode() {
        return email != null ? email.hashCode() : 0;
    }
}
