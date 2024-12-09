package com.digio.winestore.controller;

import java.util.List;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.digio.winestore.entity.Produto;
import com.digio.winestore.record.ClienteProdutoValor;
import com.digio.winestore.record.ClienteValor;
import com.digio.winestore.service.WineStoreService;

@RestController
@RequestMapping("/busca")
public class WineStoreController {
    private WineStoreService wineStoreService;
    
    public WineStoreController(WineStoreService wineStoreService) {
        this.wineStoreService = wineStoreService;
    }

    // @GetMapping("/listProduto")
    // List<Produto> listProduto() {
    //     return wineStoreService.listProdutos();
    // }

    // @GetMapping("/listCliente")
    // List<Cliente> listCliente() {
    //     return wineStoreService.listClientes();
    // }

/* 1. GET: /compras
 - Retornar uma lista das compras ordenadas de forma crescente por valor, deve conter o nome dos clientes,
 cpf dos clientes, dado dos produtos, quantidade das compras e valores totais de cadacompra */
 @GetMapping("/compras")
    public List<ClienteProdutoValor> listComprasPorValor() {
        return wineStoreService.listComprasPorValor();
    }

/* 2. GET: /maior-compra/ano - (Exemplo: /maior_compra/2016)
 - Retornar a maior compra do ano informando os dados da compra disponibilizados, deve ter o nome do cliente, cp */
 @GetMapping("/maior-compra/{ano}")
    public ClienteProdutoValor maiorCompraAno(@PathVariable("ano") @NonNull final Integer anoCompra) {
        return wineStoreService.maiorCompraAno(anoCompra);
    }

/* 3.  GET: /clientes-fieis
 - Retornar o Top 3 clientes mais fieis, clientes que possuem mais compras recorrentes com maiores valores. */
 @GetMapping("/clientes-fieis")
    public List<ClienteValor> listClientesFieis() {
        return wineStoreService.listClientesFieis();
    }

/* 4.  GET: /recomendacao/cliente/tipo
 - Retornar uma recomendação de vinho baseado nos tipos de vinho que o cliente mais compra. */
 @GetMapping("/recomendacao/{cpf}/{tipo}")
    public Produto recomendacaoVinhoPorTipo(
        @PathVariable("cpf") @NonNull String cpf,
        @PathVariable("tipo") @NonNull String tipoVinho) {
        return wineStoreService.recomendacaoVinhoPorTipo(cpf, tipoVinho);
    }
}