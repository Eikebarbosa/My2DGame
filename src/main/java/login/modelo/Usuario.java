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
    // cod, login, senha e tipo
    private int codigo;
    private String login;
    private String senha;
    private int tipo;
    
    
    public Usuario() {
        
    }
    
    //getter
    public int getTipo(){
        return tipo;
    }
    //setter
    public void setTipo(int tipo){
       this.tipo = tipo;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
    public Usuario(String login, String senha) {
        this.login = login;
        this.senha = senha;
    }
}
