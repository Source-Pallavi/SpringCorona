package com.coronareport.coronareportapp;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CoronaPojo {
    String combinedKey;
    Long active;
    Long recovered;
    Long confirmed;
    String lastUpdate;
}
