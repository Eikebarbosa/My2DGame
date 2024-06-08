/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.maua.t2.my2dgame.main;

import javax.swing.JFrame;

import br.maua.t2.my2dgame.login.modelo.Usuario;
import br.maua.t2.my2dgame.login.telas.LoginTela;

/**
 *
 * @author keyex
 */
public class Main {

    public static void main(String args[]) {
        LoginTela.main(args);
    }

    public static void iniciarJogo(Usuario user) {

        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("DoctorAdventure 1.3.0");

        GamePanel gamePanel = new GamePanel(user);
        window.add(gamePanel);

        window.pack();

        window.setLocationRelativeTo(null);
        window.setVisible(true);

        gamePanel.setupGame();
        gamePanel.startGameThread();
    }

}
