package de.pdbm.business.api.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * User entity from database. Holds user data.
 */
@Entity
public class User {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private int id;
        private String username;
        private String email;
        private String password;

        @Getter@Setter
        @OneToMany(mappedBy="user")
        private List<Pet> petList;

        @Getter@Setter
        @OneToMany(mappedBy="user")
        private List<Sessiontoken> sessiontokenList;

        public User() {

        }
        public User(String username, String email, String password) {
                this.username = username;
                this.email = email;
                this.password = password;
        }

        public int getId() {
                return id;
        }

        public String getUsername() {
                return username;
        }

        public void setUsername(String username) {
                this.username = username;
        }

        public String getPassword() {
                return password;
        }

        public void setPassword(String password) {
                this.password = password;
        }

        public String getEmail() {
                return email;
        }

        public void setEmail(String email) {
                this.email = email;
        }

        @Override
        public String toString() {
                return "User{" +
                        "user_id=" + id +
                        ", username='" + username + '\'' +
                        ", email='" + email + '\'' +
                        ", password='" + password + '\'' +
                        '}';
        }

}
