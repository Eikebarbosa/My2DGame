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
public class OBJ_Key extends SuperObject {
    public OBJ_Key(GamePanel gp, int x, int y) {
        super(gp, x, y);
        name = "Key";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/images/key.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
