/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.maua.t2.my2dgame.entity;

import java.io.IOException;
import java.util.Random;
import javax.imageio.ImageIO;

import br.maua.t2.my2dgame.main.GamePanel;

/**
 *
 * @author Mateus
 */
public class MON_SkeletonLord extends Entity {

    boolean evolved;
    int directionTimer;

    public MON_SkeletonLord(GamePanel gp, int x, int y, int group) {
        this(gp, x, y, group, false);
    }

    public MON_SkeletonLord(GamePanel gp, int x, int y, int group, boolean evolved) {
        super(gp, x, y);
        this.gp = gp;
        this.group = group;
        this.evolved = evolved;
        directionTimer = evolved ? 55 : 75;

        name = evolved ? "Esqueleto (lvl 3)" : "Bacteriófago (lvl 2)";
        type = 2;
        direction = "down";
        speed = evolved ? 5 : 3;
        maxLife = evolved ? 4 : 2;
        life = maxLife;

        spriteScale = 3;
        solidArea.x = 16;
        solidArea.width = 70;
        solidArea.height = 95;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        attackArea.width = 48;
        attackArea.height = 48;

        if (evolved) {
            getSkeletonImages();
        } else {
            getBacteriaImages();
        }
        sprite = resolveImage();
    }

    public void getBacteriaImages() {
        try {
            for (int i = 0; i < 8; i++) {
                if (i % 2 == 0) {
                    walkingSprites[i] = ImageIO
                            .read(getClass().getResourceAsStream("/images/bacteria1.png"));
                    attackSprites[i] = ImageIO
                            .read(getClass().getResourceAsStream("/images/bacteria1.png"));
                } else {
                    walkingSprites[i] = ImageIO
                            .read(getClass().getResourceAsStream("/images/bacteria2.png"));
                    attackSprites[i] = ImageIO
                            .read(getClass().getResourceAsStream("/images/bacteria2.png"));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        onFreezeSprite = Entity.redImage(walkingSprites[2]);
    }

    public void getSkeletonImages() {
        try {
            walkingSprites[0] = ImageIO
                    .read(getClass().getResourceAsStream("/images/skeletonlord_phase2_up_1.png"));
            walkingSprites[1] = ImageIO
                    .read(getClass().getResourceAsStream("/images/skeletonlord_phase2_up_2.png"));
            walkingSprites[2] = ImageIO
                    .read(getClass().getResourceAsStream("/images/skeletonlord_phase2_down_1.png"));
            walkingSprites[3] = ImageIO
                    .read(getClass().getResourceAsStream("/images/skeletonlord_phase2_down_2.png"));
            walkingSprites[4] = ImageIO
                    .read(getClass().getResourceAsStream("/images/skeletonlord_phase2_left_1.png"));
            walkingSprites[5] = ImageIO
                    .read(getClass().getResourceAsStream("/images/skeletonlord_phase2_left_2.png"));
            walkingSprites[6] = ImageIO
                    .read(getClass().getResourceAsStream("/images/skeletonlord_phase2_right_1.png"));
            walkingSprites[7] = ImageIO
                    .read(getClass().getResourceAsStream("/images/skeletonlord_phase2_right_2.png"));
            onFreezeSprite = Entity.redImage(walkingSprites[2]);
            attackSprites[0] = ImageIO
                    .read(getClass().getResourceAsStream("/images/skeletonlord_phase2_attack_up_1.png"));
            attackSprites[1] = ImageIO
                    .read(getClass().getResourceAsStream("/images/skeletonlord_phase2_attack_up_2.png"));
            attackSprites[2] = ImageIO
                    .read(getClass().getResourceAsStream("/images/skeletonlord_phase2_attack_down_1.png"));
            attackSprites[3] = ImageIO
                    .read(getClass().getResourceAsStream("/images/skeletonlord_phase2_attack_down_2.png"));
            attackSprites[4] = ImageIO
                    .read(getClass().getResourceAsStream("/images/skeletonlord_phase2_attack_left_1.png"));
            attackSprites[5] = ImageIO
                    .read(getClass().getResourceAsStream("/images/skeletonlord_phase2_attack_left_2.png"));
            attackSprites[6] = ImageIO
                    .read(getClass().getResourceAsStream("/images/skeletonlord_phase2_attack_right_1.png"));
            attackSprites[7] = ImageIO
                    .read(getClass().getResourceAsStream("/images/skeletonlord_phase2_attack_right_2.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setAction() {
        if (attackDelayCounter > 0 && !freezed) {
            if (evolved) {
                switch (direction) {
                    case "up" -> {
                        spriteOffsetY = -32;
                    }
                    case "left" -> {
                        spriteOffsetX = -32;
                    }
                }
            }
        } else {
            spriteOffsetX = 0;
            spriteOffsetY = 0;
            actionLockCounter++;

            if (collisionOn) {
                switch (direction) {
                    case "up":
                        setDirection("down");
                        break;
                    case "down":
                        setDirection("up");
                        break;
                    case "left":
                        setDirection("right");
                        break;
                    case "right":
                        setDirection("left");
                        break;
                }
            }

            if (actionLockCounter == directionTimer) {
                Random random = new Random();
                int i = random.nextInt(100) + 1; // escolhe um numero de 1 a 100

                if (i <= 25) {
                    setDirection("up");
                }
                if (i > 25 && i <= 50) {
                    setDirection("down");
                }
                if (i > 50 && i <= 75) {
                    setDirection("left");
                }
                if (i > 75 && i <= 100) {
                    setDirection("right");
                }

                actionLockCounter = 0;
            }
        }
    }

    @Override
    public void damage(int damage) {
        super.damage(damage);
        freezed = true;
        freezedCounter = 60;
    }

}
