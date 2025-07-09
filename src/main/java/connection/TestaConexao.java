package connection;

import java.sql.Connection;

public class TestaConexao {

    public static void main(String[] args) {
        try {
            Connection conn = ConexaoBancoMysql.conectar();
            if (conn != null) {
                System.out.println("✅ Conexão com o banco de dados estabelecida com sucesso!");
                conn.close(); // boa prática: fechar conexão após o uso
            }
        } catch (Exception e) {
            System.out.println("❌ Erro ao conectar: " + e.getMessage());
        }
    }
}
