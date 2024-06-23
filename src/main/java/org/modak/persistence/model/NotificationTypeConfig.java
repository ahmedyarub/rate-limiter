package org.modak.persistence.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class NotificationTypeConfig {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String type;

    // in seconds
    @Column(nullable = false)
    private Integer rateLimitingWindow;

    @Column(nullable = false)
    private Long maxRequestsPerWindow;
}
