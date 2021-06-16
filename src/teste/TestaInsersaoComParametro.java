package teste;

import factory.ConnectionFactory;

import java.sql.*;

public class TestaInsersaoComParametro {
    public static void main(String[] args) throws SQLException {
        ConnectionFactory factory = new ConnectionFactory();
        try (Connection connection = factory.recuperarConexao();) {
            connection.setAutoCommit(false);                     //sinaliza que precisa de uma confirmacao para que seja adicionado no banco de dadaos

            try (PreparedStatement stm = connection.prepareStatement("INSERT INTO PRODUTO(nome,descricao)VALUES(?,?)", Statement.RETURN_GENERATED_KEYS);    //o jdbc prepara o statement, desta forma ele verifiica vulnerabilidades de sql injection e deixa o codigo mais legivel, ja que temos que setar os atributos que sao serao inseridos.
            ) {
                adicionarVariavel("SmartTV", "45 Polegadas", stm);
                adicionarVariavel("Radio", "Radio de bateria", stm);
                connection.commit();                             //faz com que apenas adicone no banco de dados caso as operações de adicionar variavel nao tenha dado nenhum erro.
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("ROLLBACK EXECUTADO");
                connection.rollback();                            //rollback desfaz as alterações e  sinaliza que ninguem foi adicionado.
            }
        }
    }

    public static void adicionarVariavel(String nome, String descricao, PreparedStatement stm) throws SQLException {
        stm.setString(1, nome);                                  //seta o atributo que vai ser escrita no primeiro ?
        stm.setString(2, descricao);                            //seta o atributo que vai ser escrito no segundo ?

        if (nome.equals("Radio")) {
            throw new RuntimeException("Nao foi possivel adicionar o produto.");
        }
        stm.execute();

        try (ResultSet rst = stm.getGeneratedKeys()) {
            while (rst.next()) {
                Integer id = rst.getInt(1);
                System.out.println("O id criado foi: " + id);
            }
        }

    }
}
