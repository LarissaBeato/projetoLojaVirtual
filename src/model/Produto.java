package model;

public class Produto {

    private int id;
    private String nome;
    private double preco;
    private int estoque;
    private Categoria categoria;

    public Produto(int id, String nome, double preco, int estoque, Categoria categoria) {
        this.id = id;
        this.nome = nome;
        this.preco = preco;
        this.estoque = estoque;
        this.categoria = categoria;
    }

    public int getId() { return id; }
    public String getNome() { return nome; }
    public double getPreco() { return preco; }
    public int getEstoque() { return estoque; }
    public Categoria getCategoria() { return categoria; }

    public void reduzirEstoque(int qtd) {
        this.estoque -= qtd;
    }

    @Override
    public String toString() {
        return id + " - " + nome + " | Categoria: " + categoria.getNome() +
                " | Preço: " + preco + " | Estoque: " + estoque;
    }
}
