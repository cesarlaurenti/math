package com.tenpo.math.audit;

import com.tenpo.math.base.BaseEntity;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Audit extends BaseEntity{
    private LocalDateTime auditTime;
    private String loggedUser;
    private String httpMethod;
    @Column(length = 1000)
    private String requestURI;
    @Column(length = 2000)
    private String body;
}
