package teste;

import dao.CategoriaDAO;
import factory.ConnectionFactory;
import modelo.Categoria;
import modelo.Produto;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class TestaListagemDeCategoriaInnerJoin {

    public static void main(String[] args) throws SQLException {
        try ( Connection connection = new ConnectionFactory().recuperarConexao()){
            CategoriaDAO categoriaDAO = new CategoriaDAO(connection);
            List<Categoria> listaDeCateogorias = categoriaDAO.listarComProdutos();        //Query N+1
            listaDeCateogorias.stream().forEach(ct->{
                System.out.println(ct.getNome());
                for (Produto produto : ct.getProdutos()) {
                    System.out.println(ct.getNome() + " - " + produto.getNome());
                }
            });



        }
    }
}
