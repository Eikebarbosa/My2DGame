/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package login.db;

/**
 *
 * @author schix
 */
import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;
public class ConnectionFactory {
       private String host = "";
       private String port = "";
       private String db = "";
       private String user = "";
       private String password = "";
       
       // 4 partes: tipo de retorno, nome, lista de parâmetros, corpo
       public  Connection obterConexao( ){
           try{
                //https://www.google.com:80/search
                //jdbc:mysql://host:port/db
                String stringConexao = String.format(
                        "jdbc:mysql://%s:%s/%s", host, port, db
                );
                var conexao = DriverManager.getConnection(stringConexao, user, password);
                return conexao;               
           }
           catch(Exception e){
               JOptionPane.showMessageDialog(null, "Tente novamente mais tarde ;-;");
               return null;
           }
    }
       public static void main(String[] args) {
           var conexao = new ConnectionFactory().obterConexao();
           if (conexao != null)
               System.out.println("Conectou :)");
           else
               System.out.println("Não foi possivel conectar ;-;");
           
    }
}
