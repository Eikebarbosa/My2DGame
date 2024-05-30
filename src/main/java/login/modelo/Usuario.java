/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package login.modelo;

/**
 *
 * @author schix
 */
public class Usuario {

    protected final int codigo;
    protected final String login, senha;

    public Usuario(String login, String senha) {
        this.codigo = 0;
        this.login = login;
        this.senha = senha;
    }
    
    public int getCodigo() {
        return codigo;
    }
    
    public String getLogin() {
        return login;
    }
    
    public String getSenha() {
        return senha;
    }
}