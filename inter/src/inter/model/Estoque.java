package model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Estoque {

    private String nome;
    private int totalProdutos;
    private double valorTotal;

    public Estoque(String nome, int totalProdutos, double valorTotal) {
        this.nome = nome;
        this.totalProdutos = totalProdutos;
        this.valorTotal = valorTotal;
    }

    public void adicionarProduto(Produto produto) {
        String sql = "INSERT INTO produtos (nome, quantidade, preco, preco_venda) VALUES (?, ?, ?, ?)";

        try (Connection conn = ConexaoPostgreSQL.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, produto.getNome());
            stmt.setInt(2, produto.getQuantidade());
            stmt.setDouble(3, produto.getPreco());
            stmt.setDouble(4, produto.getprecoVenda());
            stmt.executeUpdate();
            System.out.println("Produto adicionado com sucesso!");
        } catch (SQLException e) {
            System.out.println("Erro ao adicionar produto: " + e.getMessage());
        }
    }

    public Produto buscarProduto(String nome) {
        String sql = "SELECT * FROM produtos WHERE nome = ?";
        try (Connection conn = ConexaoPostgreSQL.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, nome);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Produto(
                    rs.getString("nome"),
                    rs.getInt("quantidade"),
                    rs.getDouble("preco"),
                    rs.getDouble("preco_venda")
                );
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar produto: " + e.getMessage());
        }
        return null;
    }

    public void atualizarQuantidade(String nome, int novaQuantidade) {
        String sql = "UPDATE produtos SET quantidade = quantidade + ? WHERE nome = ?";

        try (Connection conn = ConexaoPostgreSQL.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, novaQuantidade);
            stmt.setString(2, nome);
            int rowsUpdated = stmt.executeUpdate();

            if (rowsUpdated > 0) {
                System.out.println("Quantidade atualizada com sucesso!");
            } else {
                System.out.println("Produto não encontrado.");
            }
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar quantidade: " + e.getMessage());
        }
    }

    public void listarProdutos() {
        String sql = "SELECT * FROM produtos";

        try (Connection conn = ConexaoPostgreSQL.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            System.out.println("\n--- Produtos no Estoque ---");
            while (rs.next()) {
                System.out.println("Nome: " + rs.getString("nome") +
                                   ", Quantidade: " + rs.getInt("quantidade") +
                                   ", Preço: R$ " + rs.getDouble("preco") +
                                   ", Preço de Venda: R$ " + rs.getDouble("preco_venda"));
            }
        } catch (SQLException e) {
            System.out.println("Erro ao listar produtos: " + e.getMessage());
        }
    }

    public void removerQuantidadeMenu() {
        System.out.println("Função ainda não implementada. Considere criar um método similar a 'atualizarQuantidade'.");
    }
}