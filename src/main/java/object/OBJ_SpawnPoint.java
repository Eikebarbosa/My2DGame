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
 * @author Mateus
 */
public class OBJ_SpawnPoint extends SuperObject {
    public OBJ_SpawnPoint(GamePanel gp, int x, int y) {
        super(gp, x, y);
        name = "SpawnPoint";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/images/spawnpoint.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
