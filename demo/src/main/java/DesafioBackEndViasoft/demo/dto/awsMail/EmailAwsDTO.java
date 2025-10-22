package DesafioBackEndViasoft.demo.dto.awsMail;

import jakarta.validation.constraints.Size;

public record EmailAwsDTO(
        @Size(max = 45) String recipient,
        @Size(max = 60) String recipientName,
        @Size(max = 45) String sender,
        @Size(max = 120) String subject,
        @Size(max = 256) String content
) {}
