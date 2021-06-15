import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory { //encapsulamento de codigo para criar uma conxao, facilitando a mudança do codigo caso futuramente decidimos alterar o tipo de banco de dados, sem precisar alterar em todas as classes.

    public Connection recuperarConexao() throws SQLException {
       return DriverManager.getConnection("jdbc:mysql://localhost:8080/loja_virtual?useTimezone=true&serverTimezone=UTC", "root", "root");
    }

}
