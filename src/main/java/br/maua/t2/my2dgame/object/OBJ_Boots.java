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
public class OBJ_Boots extends SuperObject {
    public OBJ_Boots(GamePanel gp, int x, int y) {
        super(gp, x, y);
        name = "Boots";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/images/boots.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
