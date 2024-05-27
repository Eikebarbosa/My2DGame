/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.io.IOException;
import javax.imageio.ImageIO;
import main.GamePanel;

/**
 *
 * @author Mateus
 */
public class MON_SkeletonLord extends Entity {
    
    GamePanel gp;
    public static final String monName = "Skeleton Lord";
    
    public MON_SkeletonLord(GamePanel gp){
        super(gp);
        this.gp = gp;
        
        type = 2;
        speed = 1;
        maxLife = 50;
        life = maxLife;
        
        int size = gp.tileSize;
        solidArea.x = 48;
        solidArea.y = 48;
        solidArea.width = 40;
        solidArea.height = 44;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        attackArea.width = 48;
        attackArea.height = 48;
        
        getImage();
        getAttackImage();
    }
    public void getImage(){
         try {
            up1 = ImageIO.read(getClass().getResourceAsStream("/images/skeletonlord_up_1.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/images/skeletonlord_up_2.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("/images/skeletonlord_down_1.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/images/skeletonlord_down_2.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/images/skeletonlord_Left_1.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/images/skeletonlord_Left_2.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/images/skeletonlord_right_1.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/images/skeletonlord_right_2.png"));
            
        
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    
    public void getAttackImage(){ 
        try {
            attackUp1 = ImageIO.read(getClass().getResourceAsStream("/images/skeletonlord_attack_up_1.png"));
            attackUp2 = ImageIO.read(getClass().getResourceAsStream("/images/skeletonlord_attack_up_2.png"));
            attackDown1 = ImageIO.read(getClass().getResourceAsStream("/images/skeletonlord_attack_down_1.png"));
            attackDown2 = ImageIO.read(getClass().getResourceAsStream("/images/skeletonlord_attack_down_2.png"));
            attackLeft1 = ImageIO.read(getClass().getResourceAsStream("/images/skeletonlord_attack_Left_1.png"));
            attackLeft2 = ImageIO.read(getClass().getResourceAsStream("/images/skeletonlord_attack_Left_2.png"));
            attackRight1 = ImageIO.read(getClass().getResourceAsStream("/images/skeletonlord_attack_right_1.png"));
            attackRight2 = ImageIO.read(getClass().getResourceAsStream("/images/skeletonlord_attack_right_2.png"));
            
        
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    
    
    
}
