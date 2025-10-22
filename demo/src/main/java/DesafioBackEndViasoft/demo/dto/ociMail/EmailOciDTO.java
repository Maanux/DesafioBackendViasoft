package DesafioBackEndViasoft.demo.dto.ociMail;

import jakarta.validation.constraints.Size;

public record EmailOciDTO(
        @Size(max = 40) String recipientEmail,
        @Size(max = 50) String recipientName,
        @Size(max = 40) String senderEmail,
        @Size(max = 100) String subject,
        @Size(max = 250) String body
) {}
