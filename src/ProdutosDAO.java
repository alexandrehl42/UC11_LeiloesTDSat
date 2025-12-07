/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Adm
 */

import java.sql.PreparedStatement;
import java.sql.Connection;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.util.ArrayList;

public class ProdutosDAO {

    Connection conn;
    PreparedStatement prep;
    ResultSet resultset;

    public void cadastrarProduto(ProdutosDTO produto) {

    String sql = "UPDATE produtos SET status = 'Vendido' WHERE id = ?";

    try (Connection conn = new conectaDAO().connectDB();
         PreparedStatement prep = conn.prepareStatement(sql)) {

        prep.setInt(1, id);
        prep.executeUpdate();
        
        System.out.println("Produto vendido com sucesso!");

    } catch (Exception e) {
        System.out.println("Erro ao vender produto: " + e.getMessage());
    }
}

    public ArrayList<ProdutosDTO> listarProdutosVendidos() {
    String sql = "SELECT * FROM produtos WHERE status = 'Vendido'";
    ArrayList<ProdutosDTO> lista = new ArrayList<>();

    try (Connection conn = new conectaDAO().connectDB();
         PreparedStatement prep = conn.prepareStatement(sql);
         ResultSet rs = prep.executeQuery()) {

        while (rs.next()) {
            ProdutosDTO p = new ProdutosDTO();
            p.setId(rs.getInt("id"));
            p.setNome(rs.getString("nome"));
            p.setValor(rs.getInt("valor"));
            p.setStatus(rs.getString("status"));
            lista.add(p);
        }

    } catch (Exception e) {
        System.out.println("Erro ao listar vendidos: " + e.getMessage());
    }

    return lista;
}
    
}