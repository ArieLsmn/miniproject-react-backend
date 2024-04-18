package com.pointofsales.miniproject.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
@Setter
public class ResponseMessage {
    HttpStatus status;
    String message;


}
