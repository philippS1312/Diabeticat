package de.pdbm.business.api.entity;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * User entity from database. Holds customer data.
 */
@Entity
public class User {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private int user_id;

        private String user_name;
        private String email;
        private LocalDate create_date;
        private LocalDateTime last_update;

        public User() {

        }
        public User(int user_id, String user_name, String email, LocalDate create_date, LocalDateTime last_update) {
                this.user_id = user_id;
                this.user_name = user_name;
                this.email = email;
                this.create_date = create_date;
                this.last_update = last_update;
        }

        public int getUser_id() {
                return user_id;
        }

        public void setUser_id(int user_id) {
                this.user_id = user_id;
        }

        public String getUser_name() {
                return user_name;
        }

        public void setUser_name(String user_name) {
                this.user_name = user_name;
        }

        public String getEmail() {
                return email;
        }

        public void setEmail(String email) {
                this.email = email;
        }

        public LocalDate getCreate_date() {
                return create_date;
        }

        public void setCreate_date(LocalDate create_date) {
                this.create_date = create_date;
        }

        public LocalDateTime getLast_update() {
                return last_update;
        }

        public void setLast_update(LocalDateTime last_update) {
                this.last_update = last_update;
        }

        @Override
        public String toString() {
                return "User{" +
                        "user_id=" + user_id +
                        ", user_name='" + user_name + '\'' +
                        ", email='" + email + '\'' +
                        ", create_date=" + create_date +
                        ", last_update=" + last_update +
                        '}';
        }

}
