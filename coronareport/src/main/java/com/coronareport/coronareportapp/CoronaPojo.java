package com.coronareport.coronareportapp;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class CoronaPojo {
    @Id
    @Column(name="id",updatable = false,unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String combinedKey;
    String active;
    String recovered;
    String confirmed;
    LocalDateTime lastUpdate;
}
