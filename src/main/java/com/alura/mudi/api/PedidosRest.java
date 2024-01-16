package com.alura.mudi.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alura.mudi.enuns.StatusPedido;
import com.alura.mudi.model.Pedido;
import com.alura.mudi.repository.PedidoRepository;
import java.util.List;

@RestController
@RequestMapping("api/pedidos")
public class PedidosRest {
    

    @Autowired
    private PedidoRepository pedidoRepository;


    Sort sort = Sort.by("id").descending();
    PageRequest pageRequest = PageRequest.of(0, 10, sort);

    @GetMapping("/aguardando")
    public List<Pedido> getPedidosAguardandoOferta(){
        return pedidoRepository.findByStatus(StatusPedido.AGUARDANDO, pageRequest);
    }
}
