import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class ProdutosDAO {

    public boolean cadastrarProduto(ProdutosDTO produto) {
        String sql = "INSERT INTO produtos (nome, valor, status) VALUES (?, ?, ?)";

        try (Connection conn = new conectaDAO().connectDB();
             PreparedStatement prep = conn.prepareStatement(sql)) {

            prep.setString(1, produto.getNome());
            prep.setInt(2, produto.getValor());
            prep.setString(3, produto.getStatus());

            int rows = prep.executeUpdate();
            return rows > 0;

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar: " + e.getMessage());
            return false;
        }
    }

    public ArrayList<ProdutosDTO> listarProdutos() {
        ArrayList<ProdutosDTO> listagem = new ArrayList<>();
        String sql = "SELECT id, nome, valor, status FROM produtos";

        try (Connection conn = new conectaDAO().connectDB();
             PreparedStatement prep = conn.prepareStatement(sql);
             ResultSet rs = prep.executeQuery()) {

            while (rs.next()) {
                ProdutosDTO p = new ProdutosDTO();
                p.setId(rs.getInt("id"));
                p.setNome(rs.getString("nome"));
                p.setValor(rs.getInt("valor"));
                p.setStatus(rs.getString("status"));
                listagem.add(p);
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro listarProdutos: " + e.getMessage());
        }
        return listagem;
    }

    public boolean venderProduto(int id) {
        String sql = "UPDATE produtos SET status = 'Vendido' WHERE id = ?";

        try (Connection conn = new conectaDAO().connectDB();
             PreparedStatement prep = conn.prepareStatement(sql)) {

            prep.setInt(1, id);
            int rows = prep.executeUpdate();
            return rows > 0;

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro venderProduto: " + e.getMessage());
            return false;
        }
    }

    public ArrayList<ProdutosDTO> listarProdutosVendidos() {
        ArrayList<ProdutosDTO> listagem = new ArrayList<>();
        String sql = "SELECT id, nome, valor, status FROM produtos WHERE status = 'Vendido'";

        try (Connection conn = new conectaDAO().connectDB();
             PreparedStatement prep = conn.prepareStatement(sql);
             ResultSet rs = prep.executeQuery()) {

            while (rs.next()) {
                ProdutosDTO p = new ProdutosDTO();
                p.setId(rs.getInt("id"));
                p.setNome(rs.getString("nome"));
                p.setValor(rs.getInt("valor"));
                p.setStatus(rs.getString("status"));
                listagem.add(p);
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro listarProdutosVendidos: " + e.getMessage());
        }
        return listagem;
    }
}