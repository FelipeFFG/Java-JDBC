import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TestaInsercao {

    public static void main(String[] args) throws SQLException {


        ConnectionFactory factory = new ConnectionFactory();
        Connection connection = factory.recuperarConexao();

        Statement stm = connection.createStatement();   //retornar o statement.
        // boolean resultado =  stm.execute("INSERT INTO PRODUTO(nome,descricao)VALUES('Teclado','Teclado Mecanico')");  //'' para clausulas do mysql . "" para clausulas do java.
        //System.out.println(resultado); //NAO RETORNA UMA LISTA.
        stm.execute("INSERT INTO PRODUTO(nome,descricao)VALUES('Teclado','Teclado Mecanico')",
                Statement.RETURN_GENERATED_KEYS);  //'' para clausulas do mysql . "" para clausulas do java.

        ResultSet rst = stm.getGeneratedKeys();


        while(rst.next()){
            Integer id = rst.getInt(1);
            System.out.println("O id criado foi: " + id );
        }



    }
}
