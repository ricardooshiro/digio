package com.digio.winestore.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.digio.winestore.entity.Cliente;
import com.digio.winestore.entity.Produto;

@Service
public class MockService {
    @Value("${mock.produtos}")
    private String mockProdutos;
    @Value("${mock.clientes}")
    private String mockClientes;

    RestTemplate rest = new RestTemplate();
    private List<Produto> produtos = new ArrayList<>();
    private List<Cliente> clientes = new ArrayList<>();
    
    public void setProduto() {
      RestTemplate rest = new RestTemplate();
      ResponseEntity<Produto[]> response = rest.getForEntity(mockProdutos, Produto[].class);

      this.produtos = Arrays.asList(response.getBody());
    }

    public void setCliente() {
      ResponseEntity<Cliente[]> response = rest.getForEntity(mockClientes, Cliente[].class);

      this.clientes = Arrays.asList(response.getBody());
    }
  
    public List<Produto> getProdutos() {
      return produtos;
    }

    public List<Cliente> getClientes() {
      return clientes;
    }
}