package com.alura.mudi.controller;

import com.alura.mudi.dto.PedidoDto;
import com.alura.mudi.enuns.StatusPedido;
import com.alura.mudi.model.Pedido;
import com.alura.mudi.model.User;
import com.alura.mudi.repository.PedidoRepository;
import com.alura.mudi.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("pedido")
public class PedidoController {

    @Autowired
    private PedidoRepository pedidoRepository;


    Sort sort = Sort.by("dataDaEntrega").descending();
    PageRequest pageRequest = PageRequest.of(0, 10, sort);

    @GetMapping("/lista")
    public String listPedidos(Model model, Principal principal){

        

        List<Pedido> pedidos = pedidoRepository.findByUser(principal.getName(), pageRequest);
        model.addAttribute("pedidos", pedidos);

        return "pedido/pedidos";
    }

    @GetMapping("/formulario")
    public String formulario(PedidoDto requiredPedioNovo){

        return "pedido/pedidoFormulario";
    }

    @Autowired
    UserRepository userRepository;

    @PostMapping("/novo")
    public String NovoPedido(@Validated PedidoDto pedidoDto, BindingResult result){

            if(result.hasErrors()){
                System.out.println("Erros de validação encontrados:");
                List<FieldError> errors = result.getFieldErrors();
                for (FieldError error : errors) {
                    System.out.println("Campo: " + error.getField());
                    System.out.println("Mensagem de erro: " + error.getDefaultMessage());
                }
                return "pedido/pedidoFormulario";
            }else{
                Pedido pedido = pedidoDto.toPedido(pedidoDto);

                String username = SecurityContextHolder.getContext().getAuthentication().getName();
                User user = userRepository.findByUsername(username);
                pedido.setUser(user);

                pedidoRepository.save(pedido);

                return "redirect:/pedido/lista";
            }

    }

    @GetMapping("/lista/{status}")
    public String listPedidos(@PathVariable("status") String status, Model model, Principal principal){
    
        List<Pedido> pedidos = pedidoRepository.findByStatusUser(StatusPedido.valueOf(status.toUpperCase()), principal.getName(), pageRequest);
        model.addAttribute("pedidos", pedidos);

        return "pedido/pedidos";
    }

    @ExceptionHandler(IllegalAccessError.class)
    public String errors(){
        return "redirect:/pedido/lista/aguardando";
    }
    
}
