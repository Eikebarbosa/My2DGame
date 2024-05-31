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

    protected final int codigo, recorde;
    protected final String login, senha;

    public Usuario(String login, String senha, int recorde) {
        this.codigo = 0;
        this.login = login;
        this.senha = senha;
        this.recorde = recorde;
    }

    public int getRecorde() {
        return recorde;
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