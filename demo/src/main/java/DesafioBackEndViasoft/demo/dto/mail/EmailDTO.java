package DesafioBackEndViasoft.demo.dto.mail;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record EmailDTO(
        @Email @NotBlank String emailDoDestinatario,
        @NotBlank String nomeDestinatario,
        @Email @NotBlank String emailDoRemetente,
        @NotBlank String assunto,
        @NotBlank String conteudo
) {}
