package DesafioBackEndViasoft.demo.infra.exception;


import DesafioBackEndViasoft.demo.domain.ValidationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;


public class ErrorHandler {

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<String> tratarErro400(ValidationException ex) {
        return ResponseEntity.badRequest().body("Erro: " + ex.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> tratarErro500(Exception ex) {
        ex.printStackTrace();
        return ResponseEntity.internalServerError()
                .body("Erro interno ao processar a requisição");
    }
}
