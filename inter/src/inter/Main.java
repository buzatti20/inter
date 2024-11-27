import java.util.Scanner;
import model.Estoque;
import model.Produto;

public class Main {
    public static void main(String[] args) {
        Estoque estoque = new Estoque("Estoque Principal", 0, 0.0);
        Scanner scanner = new Scanner(System.in);
        int opcao;

        do {
            System.out.println("\n======= SISTEMA DE GERENCIAMENTO DE ESTOQUE =======");
            System.out.println("1. Adicionar Produto");
            System.out.println("2. Atualizar Quantidade");
            System.out.println("3. Buscar Produto");
            System.out.println("4. Listar Produtos");
            System.out.println("5. Sair");
            System.out.print("Escolha uma opção: ");

            while (!scanner.hasNextInt()) {
                System.out.println("Opção inválida. Digite um número entre 1 e 5.");
                scanner.next();
            }

            opcao = scanner.nextInt();
            scanner.nextLine(); // Consumir a quebra de linha

            switch (opcao) {
                case 1 -> {
                    System.out.println("\n--- Adicionar Produto ---");
                    Produto produto = new Produto("", 0, 0.0, 0.0);
                    produto.cadastrar();
                    estoque.adicionarProduto(produto);
                }
                case 2 -> {
                    System.out.println("\n--- Atualizar Quantidade ---");
                    System.out.print("Digite o nome do produto: ");
                    String nome = scanner.nextLine();
                    System.out.print("Digite a quantidade a ser adicionada: ");
                    int novaQuantidade = scanner.nextInt();
                    estoque.atualizarQuantidade(nome, novaQuantidade);
                }
                case 3 -> {
                    System.out.println("\n--- Buscar Produto ---");
                    System.out.print("Digite o nome do produto: ");
                    String nome = scanner.nextLine();
                    Produto produto = estoque.buscarProduto(nome);
                    if (produto != null) {
                        System.out.println("Produto encontrado: " + produto);
                    } else {
                        System.out.println("Produto não encontrado.");
                    }
                }
                case 4 -> {
                    System.out.println("\n--- Listar Produtos ---");
                    estoque.listarProdutos();
                }
                case 5 -> System.out.println("Saindo do sistema...");
                default -> System.out.println("Opção inválida. Escolha um número entre 1 e 5.");
            }
        } while (opcao != 5);

        scanner.close();
    }
}