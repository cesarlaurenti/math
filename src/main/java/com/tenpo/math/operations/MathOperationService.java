package com.tenpo.math.operations;

import org.springframework.stereotype.Service;

@Service
public class MathOperationService {

    Double getMultiplication(Double leftOperator, Double rightOperator) {
        return  leftOperator * rightOperator;
    }
}
