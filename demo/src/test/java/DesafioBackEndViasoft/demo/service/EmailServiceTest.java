package DesafioBackEndViasoft.demo.service;

import DesafioBackEndViasoft.demo.dto.mail.EmailDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

import static org.junit.jupiter.api.Assertions.*;

class EmailServiceTest {

    private EmailService service;

    @BeforeEach
    void setup() {
        service = new EmailService();
    }

    @Test
    void processarEmailComSucesso_AWS() {
        ReflectionTestUtils.setField(service, "integracao", "AWS");

        var email = new EmailDTO("dest@teste.com", "Destinatário", "remetente@teste.com", "Assunto", "Conteúdo");

        assertDoesNotThrow(() -> service.processarEmail(email));
    }

    @Test
    void lancarErroquandoEmailDoDestinatarioForNuloAWS() {
        ReflectionTestUtils.setField(service, "integracao", "AWS");

        var email = new EmailDTO(null, "Destinatário", "remetente@teste.com", "Assunto", "Conteúdo");

        var ex = assertThrows(IllegalArgumentException.class, () -> service.processarEmail(email));
        assertEquals("O e-mail do destinatário é obrigatório", ex.getMessage());
    }

    @Test
    void lancarErroQuandoONomeDoDestinatarioForNuloAWS() {
        ReflectionTestUtils.setField(service, "integracao", "AWS");

        var email = new EmailDTO("dest@teste.com", null, "remetente@teste.com", "Assunto", "Conteúdo");

        var ex = assertThrows(IllegalArgumentException.class, () -> service.processarEmail(email));
        assertEquals("O nome do destinatário é obrigatório", ex.getMessage());
    }

    @Test
    void lancarErroQuandoEmailDoRemetenteForNuloAWS() {
        ReflectionTestUtils.setField(service, "integracao", "AWS");

        var email = new EmailDTO("dest@teste.com", "Destinatário", null, "Assunto", "Conteúdo");

        var ex = assertThrows(IllegalArgumentException.class, () -> service.processarEmail(email));
        assertEquals("O e-mail do remetente é obrigatório", ex.getMessage());
    }

    @Test
    void lancarErroQuandoAssuntoForNuloAWS() {
        ReflectionTestUtils.setField(service, "integracao", "AWS");

        var email = new EmailDTO("dest@teste.com", "Destinatário", "remetente@teste.com", null, "Conteúdo");

        var ex = assertThrows(IllegalArgumentException.class, () -> service.processarEmail(email));
        assertEquals("O assunto é obrigatório", ex.getMessage());
    }

    @Test
    void lancarErroQuandoConteudoForNuloAWS() {
        ReflectionTestUtils.setField(service, "integracao", "AWS");

        var email = new EmailDTO("dest@teste.com", "Destinatário", "remetente@teste.com", "Assunto", null);

        var ex = assertThrows(IllegalArgumentException.class, () -> service.processarEmail(email));
        assertEquals("O conteúdo é obrigatório", ex.getMessage());
    }

    @Test
    void processarEmailComSucesso_OCI() {
        ReflectionTestUtils.setField(service, "integracao", "OCI");

        var email = new EmailDTO("dest@teste.com", "Destinatário", "remetente@teste.com", "Assunto", "Conteúdo");

        assertDoesNotThrow(() -> service.processarEmail(email));
    }


    @Test
    void erroQuandoEmailDoDestinatarioForNuloOCI() {
        ReflectionTestUtils.setField(service, "integracao", "OCI");

        var email = new EmailDTO(null, "Destinatário", "remetente@teste.com", "Assunto", "Conteúdo");

        var ex = assertThrows(IllegalArgumentException.class, () -> service.processarEmail(email));
        assertEquals("O e-mail do destinatário é obrigatório", ex.getMessage());
    }

    @Test
    void lancarErroQuandoONomeDoDestinatarioForNuloOCI() {
        ReflectionTestUtils.setField(service, "integracao", "OCI");

        var email = new EmailDTO("dest@teste.com", null, "remetente@teste.com", "Assunto", "Conteúdo");

        var ex = assertThrows(IllegalArgumentException.class, () -> service.processarEmail(email));
        assertEquals("O nome do destinatário é obrigatório", ex.getMessage());
    }

    @Test
    void lancarErroQuandoEmailDoRemetenteForNuloOCI() {
        ReflectionTestUtils.setField(service, "integracao", "OCI");

        var email = new EmailDTO("dest@teste.com", "Destinatário", null, "Assunto", "Conteúdo");

        var ex = assertThrows(IllegalArgumentException.class, () -> service.processarEmail(email));
        assertEquals("O e-mail do remetente é obrigatório", ex.getMessage());
    }

    @Test
    void lancarErroQuandoAssuntoForNuloOCI() {
        ReflectionTestUtils.setField(service, "integracao", "OCI");

        var email = new EmailDTO("dest@teste.com", "Destinatário", "remetente@teste.com", null, "Conteúdo");

        var ex = assertThrows(IllegalArgumentException.class, () -> service.processarEmail(email));
        assertEquals("O assunto é obrigatório", ex.getMessage());
    }

    @Test
    void lancarErroQuandoConteudoForNuloOCI() {
        ReflectionTestUtils.setField(service, "integracao", "OCI");

        var email = new EmailDTO("dest@teste.com", "Destinatário", "remetente@teste.com", "Assunto", null);

        var ex = assertThrows(IllegalArgumentException.class, () -> service.processarEmail(email));
        assertEquals("O conteúdo é obrigatório", ex.getMessage());
    }



    @Test
    void lancarErroQuandoIntegracaoInvalida() {
        ReflectionTestUtils.setField(service, "integracao", "XYZ");

        var email = new EmailDTO("dest@teste.com", "Destinatário", "remetente@teste.com", "Assunto", "Conteúdo");

        var ex = assertThrows(IllegalArgumentException.class, () -> service.processarEmail(email));
        assertTrue(ex.getMessage().contains("Configuração inválida"));
    }
}
