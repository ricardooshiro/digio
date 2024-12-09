package com.digio.winestore.record;

import java.util.List;

public record ClienteProdutoValor(
    String nome,
	String cpf,
	List<DescProd> descProds
	) {
}