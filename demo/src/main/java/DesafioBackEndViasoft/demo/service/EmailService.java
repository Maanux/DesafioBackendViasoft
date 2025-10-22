package DesafioBackEndViasoft.demo.service;

import DesafioBackEndViasoft.demo.dto.awsMail.EmailAwsDTO;
import DesafioBackEndViasoft.demo.dto.mail.EmailDTO;
import DesafioBackEndViasoft.demo.dto.ociMail.EmailOciDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


@Service
public class EmailService {

    @Value("${mail.integracao}")
    private String integracao;

    private final ObjectMapper mapper = new ObjectMapper();

    public void processarEmail(EmailDTO email) {
        try {
            if (email.emailDoDestinatario() == null || email.emailDoDestinatario().isBlank()) {
                throw new IllegalArgumentException("O e-mail do destinatário é obrigatório");
            }
            if (email.nomeDestinatario() == null || email.nomeDestinatario().isBlank()) {
                throw new IllegalArgumentException("O nome do destinatário é obrigatório");
            }
            if (email.emailDoRemetente() == null || email.emailDoRemetente().isBlank()) {
                throw new IllegalArgumentException("O e-mail do remetente é obrigatório");
            }
            if (email.assunto() == null || email.assunto().isBlank()) {
                throw new IllegalArgumentException("O assunto é obrigatório");
            }
            if (email.conteudo() == null || email.conteudo().isBlank()) {
                throw new IllegalArgumentException("O conteúdo é obrigatório");
            }

            Object emailAdaptado;

            if ("AWS".equalsIgnoreCase(integracao)) {
                emailAdaptado = new EmailAwsDTO(
                        email.emailDoDestinatario(),
                        email.nomeDestinatario(),
                        email.emailDoRemetente(),
                        email.assunto(),
                        email.conteudo()
                );
                System.out.println("Enviando via AWS...");

            } else if ("OCI".equalsIgnoreCase(integracao)) {
                emailAdaptado = new EmailOciDTO(
                        email.emailDoDestinatario(),
                        email.nomeDestinatario(),
                        email.emailDoRemetente(),
                        email.assunto(),
                        email.conteudo()
                );
                System.out.println("Enviando via OCI...");

            } else {
                throw new IllegalArgumentException("Configuração inválida: " + integracao);
            }
            try {
                String json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(emailAdaptado);
                System.out.println("===== EMAIL SERIALIZADO =====");
                System.out.println(json);
                System.out.println("==============================");
            } catch (Exception e) {
                throw new RuntimeException("Falha ao serializar o e-mail", e);
            }

        } catch (IllegalArgumentException e) {
            System.err.println("Erro de validação: " + e.getMessage());
            throw e;

        } catch (Exception e) {
            System.err.println("Erro interno ao processar e-mail: " + e.getMessage());
            throw new RuntimeException("Erro interno ao processar o e-mail", e);
        }
    }
}

