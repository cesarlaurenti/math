package com.tenpo.math.operations;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class MathOperationResponse {

    public String operation;
    public Double leftOperator;
    public Double rightOperator;
    public Double result;
}
