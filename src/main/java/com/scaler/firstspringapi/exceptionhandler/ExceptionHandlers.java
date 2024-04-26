package com.scaler.firstspringapi.exceptionhandler;

import com.scaler.firstspringapi.dtos.ExceptionDto;
import com.scaler.firstspringapi.dtos.ProductNotFoundExceptionDto;
import com.scaler.firstspringapi.exceptions.NoProductsException;
import com.scaler.firstspringapi.exceptions.ProductAlreadyExistsException;
import com.scaler.firstspringapi.exceptions.ProductNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlers {

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ProductNotFoundExceptionDto> handleProductNotFoundException(ProductNotFoundException exception) {
        ProductNotFoundExceptionDto dto = new ProductNotFoundExceptionDto();
        dto.setMessage("Product with " + exception.getId() + " not found");

        return new ResponseEntity<>(dto, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ArithmeticException.class)
    public ResponseEntity<ExceptionDto> handleArithmeticException(){
        ExceptionDto exceptionDto = new ExceptionDto();
        exceptionDto.setMessage("Something went wrong");
        exceptionDto.setResolution("Nothing can be done");
        ResponseEntity<ExceptionDto> responseEntity = new ResponseEntity<>(exceptionDto,
            HttpStatus.INTERNAL_SERVER_ERROR);
        return responseEntity;
    }

    @ExceptionHandler(ArrayIndexOutOfBoundsException.class)
    public ResponseEntity<Void> handleArrayIndexOutOfBoundException() {
        ResponseEntity<Void> responseEntity = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        return responseEntity;
    }

    @ExceptionHandler(ProductAlreadyExistsException.class)
    public ResponseEntity<ExceptionDto> handleProductAlreadyExistsException() {
        ExceptionDto exceptionDto = new ExceptionDto();
        exceptionDto.setMessage("Already Exists");
        exceptionDto.setResolution("Create product with new title");
        ResponseEntity<ExceptionDto> responseEntity = new ResponseEntity<>(exceptionDto,HttpStatus.BAD_REQUEST);
        return responseEntity;
    }
    @ExceptionHandler(NoProductsException.class)
    public ResponseEntity<ExceptionDto> handleGenericException() {
        ExceptionDto exceptionDto = new ExceptionDto();
        exceptionDto.setMessage("No products in DB");
        exceptionDto.setResolution("Create a new product");
        ResponseEntity<ExceptionDto> responseEntity = new ResponseEntity<>(exceptionDto,HttpStatus.BAD_REQUEST);
        return responseEntity;
    }
}
