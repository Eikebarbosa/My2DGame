/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.maua.t2.my2dgame.main;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

/**
 *
 * @author keyex
 */
public class KeyInfoDialog {

    public KeyInfoDialog() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        String title = "Informações das Teclas";
        String message = "<html><body style='width: 300px; font-size: 14px;'>" +
                         "<b style='color: #007bff;'>Teclas de Movimento:</b><br>" +
                         "<span style='color: #007bff;'>W / CIMA:</span> Mover para cima<br>" +
                         "<span style='color: #007bff;'>S / BAIXO:</span> Mover para baixo<br>" +
                         "<span style='color: #007bff;'>A / ESQUERDA:</span> Mover para esquerda<br>" +
                         "<span style='color: #007bff;'>D / DIREITA:</span> Mover para direita<br><br>" +
                         "<b style='color: #007bff;'>Outras Teclas:</b><br>" +
                         "<span style='color: #007bff;'>ENTER:</span> Atacar/Interagir<br>" +
                         "<span style='color: #007bff;'>ESC:</span> Pausar/Jogar<br>" +
                         "<span style='color: #007bff;'>T:</span> Ativar/Desativar tempo de desenho<br>" +
                         "<span style='color: #007bff;'>P:</span> Exibir posição do jogador</body></html>";

        Font font = new Font("Arial", Font.PLAIN, 14);
        UIManager.put("OptionPane.messageFont", font);
        UIManager.put("OptionPane.buttonFont", font);
        UIManager.put("OptionPane.messageForeground", Color.BLACK);
        UIManager.put("Panel.background", Color.WHITE);
        UIManager.put("OptionPane.background", Color.WHITE);

        JOptionPane.showMessageDialog(null, message, title, JOptionPane.INFORMATION_MESSAGE);
    }
}
