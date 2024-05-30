/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package login.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author schix
 */
public class QuestionDAO {
    
    public static Map<String, String> getQuestions() {
        Connection connection = new ConnectionFactory().obterConexao();
        Map<String, String> map = new HashMap<>();
        
        try (Statement statement = connection.createStatement()) {
            ResultSet result = statement.executeQuery("SELECT * FROM Pergunta");
            
            while (result.next()) {
                map.put(result.getString("textoPergunta"), result.getString("respostaCorreta"));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        
        return map;
    } 
}
