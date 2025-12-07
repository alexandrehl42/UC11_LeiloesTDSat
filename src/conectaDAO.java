import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class conectaDAO {

    public Connection connectDB(){
        Connection conn = null;

        try {
            String url = "jdbc:mysql://localhost/uc11?useSSL=false&serverTimezone=UTC";
            String user = "root";
            String pass = "";

            conn = DriverManager.getConnection(url, user, pass);
            System.out.println("Conectado com sucesso!");
        } catch (SQLException erro){
            JOptionPane.showMessageDialog(null, "Erro ConectaDAO: " + erro.getMessage());
            System.out.println("Erro ConectaDAO: " + erro.getMessage());
        }
        return conn;
    }

}