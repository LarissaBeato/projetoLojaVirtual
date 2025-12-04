package model;

import java.util.ArrayList;

public class Carrinho {

    private Cliente cliente;
    private ArrayList<ItemCarrinho> itens = new ArrayList<>();

    public Carrinho(Cliente cliente) {
        this.cliente = cliente;
    }

    public void adicionarItem(ItemCarrinho item) {
        itens.add(item);
    }

    public ArrayList<ItemCarrinho> getItens() {
        return itens;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public double getTotal() {
        double total = 0;
        for (ItemCarrinho item : itens) {
            total += item.getProduto().getPreco() * item.getQuantidade();
        }
        return total;
    }

    @Override
    public String toString() {
        return "Carrinho do cliente: " + cliente.getNome() + " | Total: R$ " + getTotal();
    }
}
