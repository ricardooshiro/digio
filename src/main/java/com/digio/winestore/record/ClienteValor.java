package com.digio.winestore.record;

import java.math.BigDecimal;

public record ClienteValor(
	String nome,
	String cpf,
	BigDecimal valor) {
}
