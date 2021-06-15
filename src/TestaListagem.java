import java.sql.*;

public class TestaListagem {
    public static void main(String[] args) throws SQLException {        //avisando que ele pode dar sql exception.

       ConnectionFactory connectionFactory = new ConnectionFactory();
       Connection connection = connectionFactory.recuperarConexao();

        PreparedStatement stm = connection.prepareStatement("SELECT ID,NOME,DESCRICAO FROM PRODUTO");
        stm.execute();                                                  //quando retorna uma lista,ele retorna true.

        ResultSet rst = stm.getResultSet();                             //Pegar o resultado

        while(rst.next()){                                              //confere se tem um valor proximo na lista
            Integer id =rst.getInt("ID");                    //get Int para retornar um Inteiro     //getInt assim como getString podem passar 2 tipos de variavies,um inteiro sendo o numero da coluna , ou String que seria a label da coluna.
            String nome =rst.getString("NOME");              //get String pra retornar uma string
            String descricao =rst.getString("DESCRICAO");

            System.out.println(id);
            System.out.println(nome);
            System.out.println(descricao);

        }


        connection.close();

    }
}
