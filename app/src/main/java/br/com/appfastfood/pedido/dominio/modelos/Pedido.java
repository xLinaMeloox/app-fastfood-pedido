package br.com.appfastfood.pedido.dominio.modelos;

import br.com.appfastfood.pedido.dominio.modelos.VO.ProdutoVO;
import br.com.appfastfood.pedido.dominio.modelos.enums.StatusPagamentoEnum;
import br.com.appfastfood.pedido.dominio.modelos.enums.StatusPedidoEnum;

import java.util.List;

public class Pedido {
    private Long id;
    private List<ProdutoVO> produtoVOS;
    private String cliente;
    private Double valorTotal;
    private StatusPedidoEnum status;
    private String tempoEspera;
    private StatusPagamentoEnum statusPagamento;

    public Pedido(List<ProdutoVO> produtoVOS, String cliente, Double valorTotal, StatusPedidoEnum status,
            String tempoEspera, StatusPagamentoEnum statusPagamento) {
        this.produtoVOS = produtoVOS;
        this.cliente = cliente;
        this.valorTotal = valorTotal;
        this.status = status;
        this.tempoEspera = tempoEspera;
        this.statusPagamento = statusPagamento;
    }

    public Pedido(Long id, List<ProdutoVO> produtoVOS, String cliente, Double valorTotal, StatusPedidoEnum status,
            String tempoEspera, StatusPagamentoEnum statusPagamento) {
        this.id = id;
        this.produtoVOS = produtoVOS;
        this.cliente = cliente;
        this.valorTotal = valorTotal;
        this.status = status;
        this.tempoEspera = tempoEspera;
        this.statusPagamento = statusPagamento;
    }
    
    public Pedido atualizaStatus() {

        return new Pedido(
                this.getId(),
                this.getProdutos(),
                this.getCliente(),
                this.getValorTotal(),
                StatusPedidoEnum.statusSeguinte(this.getStatus()),
                this.getTempoEspera(),
                StatusPagamentoEnum.validaPagamento(this.getStatusPagamento()));
    }

    public List<ProdutoVO> getProdutos() {
        return produtoVOS;
    }

    public String getCliente() {
        return cliente;
    }

    public Long getId() {
        return id;
    }

    public Double getValorTotal() {
        return valorTotal;
    }

    public StatusPedidoEnum getStatus() {
        return status;
    }

    public String getTempoEspera() {
        return tempoEspera;
    }

    public StatusPagamentoEnum getStatusPagamento() {
        return statusPagamento;
    }

}
