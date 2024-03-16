package br.com.appfastfood.configuracoes.exception;

import br.com.appfastfood.configuracoes.execption.BadRequestException;
import br.com.appfastfood.configuracoes.execption.HandlerException;
import br.com.appfastfood.configuracoes.execption.InternalServerErrorException;
import br.com.appfastfood.configuracoes.logs.Log;
import br.com.appfastfood.pedido.aplicacao.adaptadores.requisicao.RequisicaoExcecao;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class HandlerExceptionTest {

    @Mock
    private Log logger;

    @InjectMocks
    private HandlerException handlerException;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testBadRequestException_DeveRetornarErroCorreto() {
        // Arrange
        BadRequestException e = new BadRequestException("Mensagem de erro");

        // Act
        ResponseEntity<Object> response = handlerException.badRequestException(e);

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertTrue(response.getBody() instanceof RequisicaoExcecao);

    }

    @Test
    public void testInternalServerErrorException_DeveRetornarErroCorreto() {
        // Arrange
        InternalServerErrorException e = new InternalServerErrorException();

        // Act
        ResponseEntity<Object> response = handlerException.internalServerErrorException(e);

        // Assert
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertTrue(response.getBody() instanceof RequisicaoExcecao);

    }
}
