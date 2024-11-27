package model;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Produto {
    private String nome;
    private int quantidade;
    private double preco;
    private double precoVenda;

    public Produto(String nome, int quantidade, double preco, double precoVenda) {
        this.nome = nome;
        this.quantidade = quantidade;
        this.preco = preco;
        this.precoVenda = precoVenda;
    }

    public void cadastrar(){

        Scanner leia = new Scanner(System.in);
        do {
            System.out.println("Digite o nome do produto:");
            nome = leia.nextLine();
        } while(nome.length() < 3 || !nome.matches("[a-zA-Z ]+"));

        boolean quantidadeValida =  false;
        do {
            System.out.println("Digite a quantidade:");
            try {
                quantidade = leia.nextInt();
                quantidadeValida = true; 
            } catch (InputMismatchException e) {
                System.out.println("Erro: Digite um número válido para a quantidade.");
                leia.next(); 
            }
        } while (!quantidadeValida);

        boolean precoValido =  false;
        do {
            System.out.println("Digite o preço de compra/produção:");
            try {
                preco = leia.nextDouble();
                precoValido = true; 
            } catch (InputMismatchException e) {
                System.out.println("Erro: Digite um número válido para a quantidade.");
                leia.next(); 
            }
        } while (!precoValido);

        boolean precoVendaValido = false;

        do {
            
            System.out.println("Digite o preço de venda");
            try {
                precoVenda = leia.nextDouble();
                precoVendaValido = true; 
            } catch (InputMismatchException e) {
                System.out.println("Erro: Digite um número válido para a quantidade.");
                leia.next(); 
            }

        } while (!precoVendaValido);
      
    }

    public String getNome() {
        return nome;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public double getPreco() {
        return preco;
    }

    public double getprecoVenda(){
        return precoVenda;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    @Override
    public String toString() {
        return "Nome: " + nome + ", Quantidade: " + quantidade + ", Preço de compra: R$ " + preco + " preço de venda: " + precoVenda;
    }
}