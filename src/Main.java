import model.*;
import service.LojaService;
import exceptions.EstoqueInsuficienteException;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        LojaService loja = new LojaService();
        Scanner sc = new Scanner(System.in);

        int opc;

        do {
            System.out.println("\n===== MENU LOJA VIRTUAL =====");
            System.out.println("1 - Cadastrar Categoria");
            System.out.println("2 - Cadastrar Produto");
            System.out.println("3 - Cadastrar Cliente");
            System.out.println("4 - Criar Pedido");
            System.out.println("5 - Listar Pedidos");
            System.out.println("0 - Sair");
            System.out.print("Escolha: ");

            try {
                opc = Integer.parseInt(sc.nextLine());
            } catch (Exception e) {
                System.out.println("Entrada inválida!");
                opc = -1;
            }

            switch (opc) {

                case 1:
                    System.out.print("ID categoria: ");
                    int idc = Integer.parseInt(sc.nextLine());
                    System.out.print("Nome categoria: ");
                    String nomec = sc.nextLine();
                    loja.categorias.add(new Categoria(idc, nomec));
                    break;

                case 2:
                    System.out.print("ID produto: ");
                    int idp = Integer.parseInt(sc.nextLine());
                    System.out.print("Nome produto: ");
                    String nome = sc.nextLine();
                    System.out.print("Preço: ");
                    double preco = Double.parseDouble(sc.nextLine());
                    System.out.print("Estoque: ");
                    int est = Integer.parseInt(sc.nextLine());
                    System.out.print("ID da categoria: ");
                    int idcat = Integer.parseInt(sc.nextLine());

                    Categoria cat = loja.buscarCategoriaPorId(idcat);
                    if (cat == null) {
                        System.out.println("Categoria não encontrada!");
                        break;
                    }

                    loja.produtos.add(new Produto(idp, nome, preco, est, cat));
                    break;

                case 3:
                    System.out.print("ID cliente: ");
                    int idcli = Integer.parseInt(sc.nextLine());
                    System.out.print("Nome cliente: ");
                    String nomecli = sc.nextLine();
                    loja.clientes.add(new Cliente(idcli, nomecli));
                    break;

                case 4:
                    System.out.print("ID cliente: ");
                    int idcc = Integer.parseInt(sc.nextLine());

                    Cliente cli = null;
                    for (Cliente c : loja.clientes)
                        if (c.getId() == idcc) cli = c;

                    if (cli == null) {
                        System.out.println("Cliente não encontrado!");
                        break;
                    }

                    Carrinho carrinho = new Carrinho(cli);

                    while (true) {
                        System.out.print("ID produto (0 para finalizar): ");
                        int idprod = Integer.parseInt(sc.nextLine());
                        if (idprod == 0) break;

                        Produto prod = loja.buscarProdutoPorId(idprod);
                        if (prod == null) {
                            System.out.println("Produto não encontrado!");
                            continue;
                        }

                        System.out.print("Quantidade: ");
                        int qtd = Integer.parseInt(sc.nextLine());

                        try {
                            loja.validarEstoque(prod, qtd);
                            prod.reduzirEstoque(qtd);
                            carrinho.adicionarItem(new ItemCarrinho(prod, qtd));
                        }
                        catch (EstoqueInsuficienteException e) {
                            System.out.println("Erro: " + e.getMessage());
                        }
                    }

                    loja.criarPedido(carrinho);
                    System.out.println("Pedido criado com sucesso!");
                    break;

                case 5:
                    for (Pedido p : loja.pedidos)
                        System.out.println(p);
                    break;

                case 0:
                    System.out.println("Saindo...");
                    break;

                default:
                    System.out.println("Opção inválida!");
            }

        } while (opc != 0);
    }
}
