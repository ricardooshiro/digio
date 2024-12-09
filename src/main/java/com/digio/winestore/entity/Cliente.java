package com.digio.winestore.entity;

import java.util.ArrayList;
import java.util.List;
import lombok.*;

@Setter
@Getter
public class Cliente {
    private String nome; // 'Geraldo Pedro Julio Nascimento'
    private String cpf;  // '05870189179'
    private List<Compra> compras = new ArrayList<>();
}
