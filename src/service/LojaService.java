package service;

import model.*;
import exceptions.EstoqueInsuficienteException;
import java.util.ArrayList;

public class LojaService {

    public ArrayList<Cliente> clientes = new ArrayList<>();
    public ArrayList<Categoria> categorias = new ArrayList<>();
    public ArrayList<Produto> produtos = new ArrayList<>();
    public ArrayList<Pedido> pedidos = new ArrayList<>();

    private int idPedido = 1;

    public void criarPedido(Carrinho c) {
        Pedido p = new Pedido(idPedido++, c);
        pedidos.add(p);
    }

    public Produto buscarProdutoPorId(int id) {
        for (Produto p : produtos)
            if (p.getId() == id) return p;
        return null;
    }

    public Categoria buscarCategoriaPorId(int id) {
        for (Categoria c : categorias)
            if (c.getId() == id) return c;
        return null;
    }

    public void validarEstoque(Produto p, int qtd) throws EstoqueInsuficienteException {
        if (qtd > p.getEstoque()) {
            throw new EstoqueInsuficienteException("Estoque insuficiente para o produto: " + p.getNome());
        }
    }
}
