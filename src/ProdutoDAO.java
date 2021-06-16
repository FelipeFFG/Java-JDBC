import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


//Data Access Object -DAO
public class ProdutoDAO {

    private Connection connection;


    public ProdutoDAO(Connection connection) {
        this.connection = connection;
    }

    public void salvar(Produto produto) throws SQLException {
        String sql = "INSERT INTO PRODUTO(NOME,DESCRICAO)VALUES(?,?)";
        try (PreparedStatement pstm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstm.setString(1, produto.getNome());
            pstm.setString(2, produto.getDescricao());
            pstm.execute();
            this.connection = connection;
            try (ResultSet rst = pstm.getGeneratedKeys()) {
                while (rst.next()) {
                    produto.setId(rst.getInt(1));
                }
            }
        }
    }


    public void listar(Produto produto) throws SQLException {   //lista apenas um produto
        System.out.println(produto.toString());
    }


    public List<Produto> listar() throws SQLException {
        List<Produto> produtos = new ArrayList<Produto>();
        String sql = "SELECT  ID,NOME,DESCRICAO FROM PRODUTO";
        try (PreparedStatement pstm = connection.prepareStatement(sql)) {
            pstm.execute();
            try (ResultSet rst = pstm.getResultSet()) {
                while (rst.next()) {
                    Produto produto = new Produto(rst.getInt(1), rst.getString(2), rst.getString(3));
                    produtos.add(produto);
                }
            }
        }
        return produtos;
    }

//Metodo Listar que eu fiz.
/*    public void listar_Produtos() throws SQLException {
        try (PreparedStatement stm = connection.prepareStatement("SELECT  ID,NOME,DESCRICAO FROM PRODUTO")) {
            stm.execute();
            ResultSet rst = stm.getResultSet();
            while (rst.next()) {
                System.out.println(rst.getInt("ID"));
                System.out.println(rst.getString("NOME"));
                System.out.println(rst.getString("DESCRICAO"));
            }
        }
    }*/
}
