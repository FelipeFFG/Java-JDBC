
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class TestaRemocao {

    public static void main(String[] args) throws SQLException {

        ConnectionFactory factory = new ConnectionFactory();
        Connection connection = factory.recuperarConexao();


        PreparedStatement stm = connection.prepareStatement("DELETE FROM PRODUTO WHERE ID >?");  //Statement sao operações do mysql como SELECT, DELETE....
        stm.setInt(1,2);
        stm.execute();                                                                               //Deleta todos os produtos que tiverem os IDS maiores que 2.



        Integer linhas_modificadas = stm.getUpdateCount();
        System.out.println("Quantidade de linhas que forma modificadas: " + linhas_modificadas);     //Printa quantas linhas foram deletas.
    }
}
