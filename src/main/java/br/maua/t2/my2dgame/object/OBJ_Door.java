/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.maua.t2.my2dgame.object;

import java.io.IOException;
import javax.imageio.ImageIO;

import br.maua.t2.my2dgame.main.GamePanel;

/**
 *
 * @author keyex
 */
public class OBJ_Door extends SuperObject {
    public int group;

    public OBJ_Door(GamePanel gp, int x, int y, int group) {
        super(gp, x, y);
        this.group = group;
        name = "Door";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/images/door_iron.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Deixar os itens com colisões
        collision = true;
    }
}