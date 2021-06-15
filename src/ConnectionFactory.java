import com.mchange.v2.c3p0.ComboPooledDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionFactory { //encapsulamento de codigo para criar uma conxao, facilitando a mudança do codigo caso futuramente decidimos alterar o tipo de banco de dados, sem precisar alterar em todas as classes.

    public DataSource dataSource;

    public ConnectionFactory(){
        ComboPooledDataSource comboPooledDataSource = new ComboPooledDataSource();  //instaciação da pool.
        comboPooledDataSource.setJdbcUrl("jdbc:mysql://localhost:8080/loja_virtual?useTimezone=true&serverTimezone=UTC");
        comboPooledDataSource.setUser("root");
        comboPooledDataSource.setPassword("root");

        comboPooledDataSource.setMaxPoolSize(15);  //limitamos as conexoes.

        this.dataSource = comboPooledDataSource;  //faz com que o nosso pool funcione,e expor as  informações do pool de conexoes
    }

    public Connection recuperarConexao() throws SQLException {
       return this.dataSource.getConnection();
    }

}
