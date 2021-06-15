import javax.swing.plaf.nimbus.State;
import java.sql.*;

public class TestaInsersaoComParametro {
    public static void main(String[] args) throws SQLException {

        String nome = "mouse'";
        String descricao = "Mouse sem fio);delete from Produtol;";          //sql injection, vulnerabilidades no sistema.


        ConnectionFactory factory = new ConnectionFactory();
        Connection connection = factory.recuperarConexao();

        PreparedStatement stm = connection.prepareStatement("INSERT INTO PRODUTO(nome,descricao)VALUES(?,?)", Statement.RETURN_GENERATED_KEYS);    //o jdbc prepara o statement, desta forma ele verifiica vulnerabilidades de sql injection e deixa o codigo mais legivel, ja que temos que setar os atributos que sao serao inseridos.
        stm.setString(1,nome);                                  //seta o atributo que vai ser escrita no primeiro ?
        stm.setString(2,descricao);                             //seta o atributo que vai ser escrito no segundo ?

        stm.execute();

        ResultSet rst = stm.getGeneratedKeys();


        while(rst.next()){
            Integer id = rst.getInt(1);
            System.out.println("O id criado foi: " + id );
        }
    }
}
