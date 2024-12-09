package com.digio.winestore.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import com.digio.winestore.entity.Cliente;
import com.digio.winestore.entity.Compra;
import com.digio.winestore.entity.Produto;
import com.digio.winestore.record.ClienteProdutoValor;
import com.digio.winestore.record.ClienteValor;
import com.digio.winestore.record.DescProd;

@Service
public class WineStoreService {
    private MockService mockService;

    public WineStoreService(MockService mockService) {
        this.mockService = mockService;
    }

    private List<Produto> listProdutos() {
        return mockService.getProdutos();
    }

    private List<Cliente> listClientes() {
        return mockService.getClientes();
    }

    public List<ClienteProdutoValor> listComprasPorValor() {
        List<ClienteProdutoValor> listClienteProdutoValor = new ArrayList<>();

        for (Cliente cliente : listClientes()){
            // Traz lista de compras do cliente, com base nos produtos do ano compra
            List<Compra> comprasByCpf = cliente.getCompras();
            // Traz os produtos relacionados as compras do cliente
            List<Produto> produtosByCpf = listProdutos().stream()
            .filter(p -> comprasByCpf.stream()
                .anyMatch(i -> i.getCodigo() == p.getCodigo()) )
            .collect(Collectors.toList());

            List<DescProd> listDescProd = new ArrayList<>();
            for (Compra compra : comprasByCpf) {
                
                // Traz o produto com base nesta compra do cliente
                var produto = produtosByCpf.stream()
            .filter(p -> p.getCodigo() == compra.getCodigo())
            .findFirst().get();
            
                // Traz valor sub total dessa quantidade
                BigDecimal subTot = produto.getPreco().multiply(BigDecimal.valueOf(compra.getQuantidade()));
                // Separa esse produto e valor obtido
                listDescProd.add(new DescProd(
                    produto.getCodigo(),
                    produto.getTipo_vinho(),
                    produto.getPreco(),
                    produto.getSafra(),
                    produto.getAno_compra(),
                    compra.getQuantidade(),
                    subTot));
            }
            
            // Monta lista de clientes com produtos e valor
            listClienteProdutoValor.add(new ClienteProdutoValor(
                cliente.getNome(),
                cliente.getCpf(),
                listDescProd.stream()
                .sorted(Comparator.comparing(DescProd::valor))
                .collect(Collectors.toList())
                )
            );
        }

        return listClienteProdutoValor;
    }

    public ClienteProdutoValor maiorCompraAno(Integer anoCompra) {
        List<ClienteProdutoValorMax> listClienteProdutoValorMax = new ArrayList<>();

        // Filtra lista de produtos por ano_compra
        List<Produto> produtosByAno = listProdutos().stream()
        .filter(p -> p.getAno_compra().equals(anoCompra))
        .collect(Collectors.toList());

        for (Cliente cliente : listClientes()) {
        // Traz lista de compras do cliente, com base nos produtos do ano compra
        var comprasByAno = cliente.getCompras().stream()
        .filter(p -> produtosByAno.stream()
            .anyMatch(i -> i.getCodigo() == p.getCodigo()) )
        .collect(Collectors.toList());

        if (comprasByAno == null || comprasByAno.isEmpty()){
            continue;
        }

        List<DescProd> listDescProd = new ArrayList<>();
        for (Compra compra : comprasByAno) {

            // Traz o produto desse compra
            var produto = produtosByAno.stream()
        .filter(p -> p.getCodigo() == compra.getCodigo())
        .findFirst().get();
        
        // Traz valor sub total dessa quantidade
            BigDecimal subTot = produto.getPreco().multiply(BigDecimal.valueOf(compra.getQuantidade()));

            // Separa esse produto e valor obtido
            listDescProd.add(new DescProd(
                produto.getCodigo(),
                produto.getTipo_vinho(),
                produto.getPreco(),
                produto.getSafra(),
                produto.getAno_compra(),
                compra.getQuantidade(),
                subTot));
        }

        // Traz o max das compras desse cliente
        DescProd descProd = listDescProd.stream()
        .sorted(Comparator.comparing(DescProd::valor).reversed())
        .limit(1)
        .findFirst().get();

        // Armazena esse max acima junto com outros max para traz o max final
        listClienteProdutoValorMax.add(new ClienteProdutoValorMax(
            cliente.getNome(),
            cliente.getCpf(),
            descProd.codigo(),
            descProd.tipo_vinho(),
            descProd.preco(),
            descProd.safra(),
            descProd.ano_compra(),
            descProd.qtdCompra(),
            descProd.valor()
            )
        );
    }

    // Traz o max final
    ClienteProdutoValorMax clienteProdutoValorMax = listClienteProdutoValorMax.stream()
    .sorted(Comparator.comparing(ClienteProdutoValorMax::valor).reversed())
    .findFirst().get();

    // Monta o response somente com o max final
    DescProd descProdVolta = new DescProd(
        clienteProdutoValorMax.codigo(),
        clienteProdutoValorMax.tipo_vinho(),
        clienteProdutoValorMax.preco(),
        clienteProdutoValorMax.safra(),
        clienteProdutoValorMax.ano_compra(),
        clienteProdutoValorMax.qtdCompra(),
        clienteProdutoValorMax.valor()
    );

    List<DescProd> listDescProdVolta = new ArrayList<>();
    listDescProdVolta.add(descProdVolta);

    // Volta o max final estruturado
    return new ClienteProdutoValor(
        clienteProdutoValorMax.nome(),
        clienteProdutoValorMax.cpf(),
        listDescProdVolta);
    }

    public List<ClienteValor> listClientesFieis() {
        List<ClienteValor> clienteValores = new ArrayList<>();

        for (Cliente cliente : listClientes()) {
            // Traz lista de compras do cliente
            List<Compra> comprasByCpf = cliente.getCompras();
            // Traz os produtos relacionados as compras do cliente
            List<Produto> produtosByCpf = listProdutos().stream()
            .filter(p -> comprasByCpf.stream()
                .anyMatch(i -> i.getCodigo() == p.getCodigo()) )
            .collect(Collectors.toList());

            List<ClienteValor> valoresByCliente = new ArrayList<>();
            for (Compra compra : comprasByCpf) {
                
                // Traz o prdouto dessa compra
                    var produto = produtosByCpf.stream()
                .filter(p -> p.getCodigo() == compra.getCodigo())
                .findFirst().get();

                // Traz valor sub total dessa quantidade
                BigDecimal subTot = produto.getPreco().multiply(BigDecimal.valueOf(compra.getQuantidade()));

                // Separa cliente com subtotal
                valoresByCliente.add(new ClienteValor(cliente.getNome(), cliente.getCpf(), subTot));
            }
            
            // Traz maior valor da lista de compras do cliente
            Optional<ClienteValor> maxValorByCliente = valoresByCliente.stream()
            .max(Comparator.comparing(ClienteValor::valor));

            clienteValores.add(maxValorByCliente.get());
        }
        
        // Retorna o top 3 clientes e valores
        return clienteValores.stream()
         .sorted(Comparator.comparing(ClienteValor::valor).reversed())
         .limit(3)
         .collect(Collectors.toList());
    }

    public Produto recomendacaoVinhoPorTipo(String cpf, String tipoVinho) {
        Produto produto = new Produto();

        // Separa a lista de compras do cliente
        List<Compra> comprasByCpf = listClientes().stream()
         .filter(i -> i.getCpf().equalsIgnoreCase(cpf))
         .findFirst().get()
         .getCompras();

        // Separa os produtos com base no tipo vinho
        List<Produto> produtosByTipo = listProdutos().stream()
         .filter(i -> i.getTipo_vinho().equalsIgnoreCase(tipoVinho))
         .collect(Collectors.toList());

         if ((comprasByCpf == null || comprasByCpf.isEmpty()) &&
         produtosByTipo == null || produtosByTipo.isEmpty()) {
            return produto;
        }

        // Traz a maior compra por tipo vinho
        Optional<Compra> maxCompraByTipoVinho = comprasByCpf.stream()
         .filter(c -> produtosByTipo.stream()
             .anyMatch(i -> i.getCodigo() == c.getCodigo())
         )
         .max(Comparator.comparing(Compra::getQuantidade));

        if (maxCompraByTipoVinho == null || maxCompraByTipoVinho.isEmpty()) {
            return produto;
        }

        // Separa o retorno do produto com base na maior compra
        produto = produtosByTipo.stream()
         .filter(i -> i.getCodigo() == maxCompraByTipoVinho.get().getCodigo())
         .findFirst().get();

         if (produtosByTipo == null || produtosByTipo.isEmpty()) {
            return new Produto();
        }

        return produto;
    }

    private record ClienteProdutoValorMax(
        String nome,
        String cpf,
        int codigo,
        String tipo_vinho,
        BigDecimal preco,
        String safra,
        Integer ano_compra,
        int qtdCompra,
        BigDecimal valor) {
    };
}