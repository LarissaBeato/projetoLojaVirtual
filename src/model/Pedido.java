package model;

public class Pedido {

    private int id;
    private Carrinho carrinho;

    public Pedido(int id, Carrinho carrinho) {
        this.id = id;
        this.carrinho = carrinho;
    }

    @Override
    public String toString() {
        return "Pedido #" + id + " | Cliente: " + carrinho.getCliente().getNome() +
                " | Valor total: R$ " + carrinho.getTotal();
    }
}
