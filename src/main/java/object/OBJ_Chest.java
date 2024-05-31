/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package object;

import java.io.IOException;
import javax.imageio.ImageIO;

import main.GamePanel;

/**
 *
 * @author keyex
 */
public class OBJ_Chest extends SuperObject {
    public OBJ_Chest(GamePanel gp) {
        super(gp);
        name = "Chest";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/images/chest.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
