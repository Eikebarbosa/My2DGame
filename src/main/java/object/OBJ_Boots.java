/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package object;

import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author Mateus
 */
public class OBJ_Boots extends SuperObject{
    public OBJ_Boots(){
        name = "Boots";
        try{
            image = ImageIO.read(getClass().getResourceAsStream("/images/key.png"));
            //por problemas q eu tive com o sprite da bota, ela está no jogo como uma chave
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    
}
