/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package object;

import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author keyex
 */
public class OBJ_Key extends SuperObject {
    public OBJ_Key(){
        name = "Key";
        try{
            image = ImageIO.read(getClass().getResourceAsStream("/images/key.png"));
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
