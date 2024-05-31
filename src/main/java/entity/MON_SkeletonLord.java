/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.io.IOException;
import java.util.Random;
import javax.imageio.ImageIO;
import main.GamePanel;

/**
 *
 * @author Mateus
 */
public class MON_SkeletonLord extends Entity {

    public MON_SkeletonLord(GamePanel gp) {
        super(gp);
        this.gp = gp;

        name = "Esqueleto";
        type = 2;
        direction = "down";
        speed = 3;
        maxLife = 4;
        life = maxLife;

        solidArea.x = 8;
        solidArea.y = 8;
        solidArea.width = 50;
        solidArea.height = 60;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        attackArea.width = 48;
        attackArea.height = 48;

        getImage();
        getAttackImage();
    }

    public void getImage() {
        try {
            up1 = ImageIO.read(getClass().getResourceAsStream("/images/skeletonlord_up_1.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/images/skeletonlord_up_2.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("/images/skeletonlord_down_1.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/images/skeletonlord_down_2.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/images/skeletonlord_left_1.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/images/skeletonlord_left_2.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/images/skeletonlord_right_1.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/images/skeletonlord_right_2.png"));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void getAttackImage() {
        try {
            // attackUp1 =
            // ImageIO.read(getClass().getResourceAsStream("/images/skeletonlord_attack_up_1.png"));
            // attackUp2 =
            // ImageIO.read(getClass().getResourceAsStream("/images/skeletonlord_attack_up_2.png"));
            // attackDown1 =
            // ImageIO.read(getClass().getResourceAsStream("/images/skeletonlord_attack_down_1.png"));
            // attackDown2 =
            // ImageIO.read(getClass().getResourceAsStream("/images/skeletonlord_attack_down_2.png"));
            attackLeft1 = ImageIO.read(getClass().getResourceAsStream("/images/skeletonlord_attack_left_1.png"));
            attackLeft2 = ImageIO.read(getClass().getResourceAsStream("/images/skeletonlord_attack_left_2.png"));
            // attackRight1 =
            // ImageIO.read(getClass().getResourceAsStream("/images/skeletonlord_attack_right_1.png"));
            // attackRight2 =
            // ImageIO.read(getClass().getResourceAsStream("/images/skeletonlord_attack_right_2.png"));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setAction() {

        actionLockCounter++;

        if (actionLockCounter == 75) {
            Random random = new Random();
            int i = random.nextInt(100) + 1; // escolhe um numero de 1 a 100

            if (i <= 25) {
                direction = "up";
            }
            if (i > 25 && i <= 50) {
                direction = "down";
            }
            if (i > 50 && i <= 75) {
                direction = "left";
            }
            if (i > 75 && i <= 100) {
                direction = "right";
            }

            actionLockCounter = 0;
        }
    }

}
