package com.alura.mudi.dto;

import com.alura.mudi.enuns.StatusPedido;
import com.alura.mudi.model.Pedido;
import javax.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PedidoDto {

    @NotBlank
    private String nomeProduto;

    @NotBlank
    private String urlProduto;

    @NotBlank
    private String urlImagem;

    @NotBlank
    private String descricao;

    private double valorNegociado;

    public Pedido toPedido(PedidoDto pedidoDto){
        Pedido pedido = new Pedido();
        pedido.setNomeProduto(nomeProduto);
        pedido.setUrlProduto(urlProduto);
        pedido.setUrlImagem(urlImagem);
        pedido.setDescricao(descricao);
        pedido.setStatus(StatusPedido.AGUARDANDO);
        pedido.setValorNegociado(valorNegociado);
        return pedido;
    }

}
