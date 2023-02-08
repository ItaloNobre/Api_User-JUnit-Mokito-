package br.com.api.testes.controller.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class StandardError {
    LocalDateTime timestamp;
    Integer status;
    String error;
    String path;
}