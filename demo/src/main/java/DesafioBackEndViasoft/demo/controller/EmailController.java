package DesafioBackEndViasoft.demo.controller;


import DesafioBackEndViasoft.demo.dto.mail.EmailDTO;
import DesafioBackEndViasoft.demo.service.EmailService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/emails")
public class EmailController {

    private final EmailService emailService;

    public EmailController(EmailService emailService) {
        this.emailService = emailService;
    }

    @PostMapping
    public ResponseEntity<?> enviarEmail(@RequestBody EmailDTO email) {
         emailService.processarEmail(email);
         return ResponseEntity.noContent().build();
    }
}
