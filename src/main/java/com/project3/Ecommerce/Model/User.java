package com.project3.Ecommerce.Model;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name ="users")
@Data

public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @NotEmpty
    @Column(nullable = false)
    private String firstName;

    private String lastName;

    @Column(nullable = false, unique = true)
    @NotEmpty
    @Email(message = "{errors.invalid_email}")
    private String email;

    @NotEmpty
    private String password;
    
 @ManyToMany(cascade = CascadeType.MERGE , fetch = FetchType.EAGER)
 @JoinTable(
    name = "user_role",
    joinColumns = {@JoinColumn(name="USER_ID",referencedColumnName ="ID")},
    inverseJoinColumns = {@JoinColumn(name ="ROLE_ID",referencedColumnName = "ID")}
 )
 private List<Role> roles;

 public User(User user){
    
     this.firstName=user.getFirstName();
     this.lastName=user.getLastName();
     this.email=user.getEmail();
     this.password=user.getPassword();
     this.roles=user.getRoles();
 }
 public User(){

 }

}
