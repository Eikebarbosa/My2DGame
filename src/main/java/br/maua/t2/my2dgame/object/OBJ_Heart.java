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
 * @author Mateus
 */
public class OBJ_Heart extends SuperObject {

    public OBJ_Heart(GamePanel gp, int x, int y) {
        super(gp, x, y);
        name = "Heart";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/images/heart_full.png"));
            image2 = ImageIO.read(getClass().getResourceAsStream("/images/heart_half.png"));
            image3 = ImageIO.read(getClass().getResourceAsStream("/images/heart_blank.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
