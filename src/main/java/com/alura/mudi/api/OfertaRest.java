package com.alura.mudi.api;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alura.mudi.dto.RequisicaoNovaOferta;
import com.alura.mudi.model.Oferta;
import com.alura.mudi.model.Pedido;
import com.alura.mudi.repository.PedidoRepository;

@RestController
@RequestMapping("/api/ofertas")
public class OfertaRest {
    
    @Autowired
    private PedidoRepository pedidoRepository;

    @PostMapping
    public Oferta criarOferta(@Valid @RequestBody RequisicaoNovaOferta requisicao){

        Optional<Pedido> pedidoBuscado = pedidoRepository.findById(requisicao.getPedidoId());

        if (!pedidoBuscado.isPresent()) {
            return null;
        }

        Pedido pedido = pedidoBuscado.get();

        Oferta nova = requisicao.toOferta();
        nova.setPedido(pedido);
        pedido.getOfertas().add(nova);

        pedidoRepository.save(pedido);

        return nova;

    }
}
