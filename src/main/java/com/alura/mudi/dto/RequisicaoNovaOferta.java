package com.alura.mudi.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.alura.mudi.model.Oferta;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequisicaoNovaOferta {
    
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    private Long pedidoId;

    @Pattern(regexp = "^\\d+(\\.\\d+{2})?$")
    @NotNull
    private String valor;

    @Pattern(regexp = "^\\d+{2}/\\d+{2}/\\d+{4}$")
    @NotNull
    private String dataEntrega;

    private String comentario;

    public Oferta toOferta(){
        Oferta oferta = new Oferta();

        oferta.setComentario(this.comentario);
        oferta.setDataEntrega(LocalDate.parse(this.dataEntrega, formatter));
        oferta.setValor(new BigDecimal(this.valor));
        
        return oferta;
    }

}
