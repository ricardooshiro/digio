package com.digio.winestore.record;

import java.math.BigDecimal;

public record DescProd(
    int codigo,
    String tipo_vinho,
    BigDecimal preco,
    String safra,
    Integer ano_compra,
	int qtdCompra,
	BigDecimal valor
) {
}