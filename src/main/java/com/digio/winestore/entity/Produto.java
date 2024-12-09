package com.digio.winestore.entity;

import java.math.BigDecimal;
import lombok.*;

@Setter
@Getter
public class Produto {
    private int codigo;         // 1
    private String tipo_vinho;  // 'Tinto'
    private BigDecimal preco;   // 229.99
    private String safra;       // '2017'
    private Integer ano_compra; // 2018
}
