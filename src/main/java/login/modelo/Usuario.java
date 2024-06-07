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

    private final int codigo, recorde;
    private final String login, senha;

    public Usuario(int codigo, String login, String senha, int recorde) {
        this.codigo = codigo;
        this.login = login;
        this.senha = senha;
        this.recorde = recorde;
    }

    public Usuario(String login, String senha, int recorde) {
        this(0, login, senha, recorde);
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