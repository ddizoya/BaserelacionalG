/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package baserelacionalg;

import com.sun.org.apache.bcel.internal.generic.Type;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import oracle.jdbc.OracleDriver;
import sun.applet.Main;

/**
 *
 * @author oracle
 */
public class BaserelacionalG {
    
String URL = "jdbc:oracle:thin:@localhost:1521:orcl";
String USER = "hr";
String PASS = "hr";
Connection conn;
CallableStatement chamada_a_funcion;

    public BaserelacionalG() throws SQLException{
        DriverManager.registerDriver(new OracleDriver());
        connection();
    }
    
    public void connection() throws SQLException{
        conn = DriverManager.getConnection(URL, USER, PASS);
    }
    
    public void retornoFuncion(String cod) throws SQLException {
        chamada_a_funcion = conn.prepareCall("{? = call prezo_produto(?) }"); //Creamos la llamada a la función. 
        chamada_a_funcion.setString(2, cod); //Le decimos que el primer ? es del código que le pasamos. (El parámetro)
        chamada_a_funcion.registerOutParameter(1, java.sql.Types.VARCHAR); //Le decimos que el primer ? es el resultado: ? = call 
        chamada_a_funcion.execute();
        int prezo = chamada_a_funcion.getInt(1);
        System.out.println("Precio de ese producto: " + prezo);
        
    }
    
    public static void main(String [] args) throws SQLException{
        new BaserelacionalG().retornoFuncion("p3");
    }
        
}
