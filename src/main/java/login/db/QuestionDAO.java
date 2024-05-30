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
    
    public static void insert(String key, String value) {
        Connection connection = new ConnectionFactory().obterConexao();
        
        try (PreparedStatement statement = connection.prepareStatement("INSERT INTO Pergunta (idPergunta, textoPergunta, respostaCorreta) VALUES (?, ?, ?)")) {
            statement.setInt(1, 0);
            statement.setString(2, key);
            statement.setString(3, value);
            
            statement.executeUpdate();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public static void delete(String key) {
        Connection connection = new ConnectionFactory().obterConexao();
        
        try (PreparedStatement statement = connection.prepareStatement("DELETE FROM Pergunta WHERE textoPergunta = ?")) {
            statement.setString(1, key);
            statement.execute();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public static boolean exists(String key) {
        Connection connection = new ConnectionFactory().obterConexao();
        
        try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM Pergunta WHERE textoPergunta = ?")) {
            statement.setString(1, key);
            
            ResultSet result = statement.executeQuery();
            
            return result != null && result.next();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        
        return false;
    }
    
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
