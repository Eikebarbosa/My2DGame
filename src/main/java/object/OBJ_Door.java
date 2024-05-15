/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package object;

import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author keyex
 */
public class OBJ_Door extends SuperObject{
    public OBJ_Door(){
        name = "Door";
        try{
            image = ImageIO.read(getClass().getResourceAsStream("/images/door_iron.png"));
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
