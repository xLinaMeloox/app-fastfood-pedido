package br.com.appfastfood.pedido.dominio.modelos.enums;

import br.com.appfastfood.configuracoes.execption.BadRequestException;
import br.com.appfastfood.pedido.exceptions.ExceptionsMessages;

public enum StatusPedidoEnum {
    RECEBIDO(1, "RECEBIDO"),
    EM_PREPARACAO(2, "EM_PREPARACAO"),
    PRONTO(3, "PRONTO"),
    FINALIZADO(4, "FINALIZADO");

    private final int id;
    private final String nome;

    StatusPedidoEnum(int id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public static StatusPedidoEnum buscaEnumPorStatusString(String nome) {
        for (StatusPedidoEnum status : values()) {
            if (status.getNome().equalsIgnoreCase(nome)){
                return status;
            }
        }
        throw new IllegalArgumentException("Status Iválido: " + nome);
    }

    public static StatusPedidoEnum statusSeguinte (StatusPedidoEnum statusPedidoEnum) {
        if (statusPedidoEnum == StatusPedidoEnum.RECEBIDO) {
            return StatusPedidoEnum.EM_PREPARACAO;
        }

        if (statusPedidoEnum == StatusPedidoEnum.EM_PREPARACAO) {
            return StatusPedidoEnum.PRONTO;
        }

        if (statusPedidoEnum == StatusPedidoEnum.PRONTO) {
            return StatusPedidoEnum.FINALIZADO;
        }

        if(statusPedidoEnum == StatusPedidoEnum.FINALIZADO) {
            throw new BadRequestException(ExceptionsMessages.PEDIDO_JA_FINALIZADO.getValue());
        }

        throw new BadRequestException(ExceptionsMessages.STATUS_PEDIDO_NAO_PERMITIDO.getValue());

    }

    public static String retornaNomeEnum(StatusPedidoEnum status){
        for (StatusPedidoEnum statusEnum : values()) {
            if (statusEnum == status) {
                return statusEnum.getNome();
            }
        }
        throw new IllegalArgumentException("Enum de status de pedido Iválido");
    }
}