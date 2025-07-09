package connection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoBancoMysql {
    private static final String URL = "jdbc:mysql://localhost:3306/Agenda_Medicamentos";
    private static final String USUARIO = "root";
    private static final String SENHA = "root";

    public static Connection conectar() {
        try {
            return DriverManager.getConnection(URL, USUARIO, SENHA);
        } catch (SQLException e) {
            throw new RuntimeException("Erro na conexão com o banco: " + e.getMessage());
        }
        
    }
   
}
