/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.maua.t2.my2dgame;

import javax.swing.JFrame;

import br.maua.t2.my2dgame.main.GamePanel;
import br.maua.t2.my2dgame.main.KeyInfoDialog;
import br.maua.t2.my2dgame.persistencia.modelos.Usuario;
import br.maua.t2.my2dgame.telas.LoginTela;

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
        window.setTitle("DoctorAdventure 1.3.1");

        GamePanel gamePanel = new GamePanel(user);
        window.add(gamePanel);

        window.pack();

        window.setLocationRelativeTo(null);
        window.setVisible(true);
        gamePanel.setupGame();
        new KeyInfoDialog();
        gamePanel.startGameThread();
    }

}
