package de.pdbm.business.api.entity;

import lombok.Getter;
import lombok.Setter;

import javax.ejb.Local;
import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Sessiontoken {

    @Id
    @Getter
    private int token;

    @Getter
    @Setter
    private LocalDateTime expiration;

    @Getter
    @Setter
    private LocalDateTime creation;

    @Getter
    @ManyToOne
    @JoinColumn(name = "id")
    private User user;

    public Sessiontoken(int token, LocalDateTime expiration, LocalDateTime creation, User user) {
        this.token = token;
        this.expiration = expiration;
        this.creation = creation;
        this.user = user;
    }

    @Override
    public String toString() {
        return "Sessiontoken{" +
                "token=" + token +
                ", expiration=" + expiration +
                ", creation=" + creation +
                ", user=" + user +
                '}';
    }
}
