package pl.juniorjavaproject.testrestapi.domain.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = Tweet.TABLE_NAME)
public class Tweet {
    public static final String TABLE_NAME = "tweets";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "created_on", nullable = false, updatable = false)
    private LocalDateTime createdOn;

    @Column(nullable = false)
    private String tweetTitle;

    @Column(nullable = false)
    private String tweetText;

    @ManyToOne
    private User user;

    @PrePersist
    public void setCreatedOn() {
        createdOn = LocalDateTime.now();
    }

}
