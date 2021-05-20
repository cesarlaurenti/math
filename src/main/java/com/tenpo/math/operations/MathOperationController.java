package com.tenpo.math.operations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
//@RequestMapping(path = "/", produces = MediaType.APPLICATION_JSON_VALUE)
class MathOperationController {

    @Autowired
    MathOperationService mathOperationService;

    @GetMapping(path = "/multiply/{a}/{b}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity multiply (@PathVariable("a") double leftOperator, @PathVariable("b") double rightOperator){
        Double result = mathOperationService.getMultiplication(leftOperator, rightOperator);
        return ResponseEntity.status(HttpStatus.OK).
                body(new MathOperationResponse(OperationEnum.MULTIPLY.name()
                        ,leftOperator,rightOperator,result));
    }
}