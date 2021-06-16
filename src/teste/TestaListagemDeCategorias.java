package teste;

import dao.CategoriaDAO;
import dao.ProdutoDAO;
import factory.ConnectionFactory;
import modelo.Categoria;
import modelo.Produto;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class TestaListagemDeCategorias {

    public static void main(String[] args) throws SQLException {
        try ( Connection connection = new ConnectionFactory().recuperarConexao()){
            CategoriaDAO categoriaDAO = new CategoriaDAO(connection);
            List<Categoria> listaDeCateogorias = categoriaDAO.listar();        //Query N+1
            listaDeCateogorias.stream().forEach(ct->{
                System.out.println(ct.getNome());
                try {
                    for (Produto produto : new ProdutoDAO(connection).buscar(ct)){
                        System.out.println(ct.getNome() + " - " +produto.getNome());
                    }
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            });



        }
    }
}
