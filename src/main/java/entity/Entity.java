/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import main.GamePanel;

/**
 *
 * @author keyex
 */
public class Entity {

    public GamePanel gp;
    public int worldX, worldY;
    public int speed;

    public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2;
    public BufferedImage attackUp1, attackUp2, attackDown1, attackDown2, attackLeft1, attackLeft2, attackRight1,
            attackRight2;
    public String direction;

    public String name;
    public int signumDirectionX;
    public int signumDirectionY;
    public int spriteCounter = 0;
    public int spriteNum = 1;
    public int spriteWidth;
    public int spriteHeight;
    public int spriteX;
    public int spriteY;
    public Rectangle solidArea = new Rectangle(0, 0, 40, 40);
    public Rectangle attackArea = new Rectangle(0, 0, 70, 70);
    public int solidAreaDefaultX, solidAreaDefaultY;
    public boolean collisionOn = false;
    public int actionLockCounter = 0;
    public boolean invincible = false;
    protected boolean entityHandleSpriteDrawing = true;
    boolean attacking = false;
    public int invincibleCounter = 0;
    String dialogues[] = new String[20];
    int dialogueIndex = 0;
    public int type;// 0 = player, 1 = npc, 2 = monster

    // character status
    public int maxLife;
    public int life;

    public Entity(GamePanel gp) {
        this.attackArea = new Rectangle(0, 0, 0, 0);
        this.gp = gp;
        this.spriteWidth = gp.tileSize;
        this.spriteHeight = gp.tileSize;
    }

    public BufferedImage resolveImage() {
        BufferedImage image = null;
        switch (direction) {
            case "up":
                if (spriteNum == 1) {
                    image = up1;
                }
                if (spriteNum == 2) {
                    image = up2;

                }
                break;
            case "down":
                if (spriteNum == 1) {
                    image = down1;
                }
                if (spriteNum == 2) {
                    image = down2;
                }
                break;
            case "left":
                if (spriteNum == 1) {
                    image = left1;
                }
                if (spriteNum == 2) {
                    image = left2;
                }
                break;
            case "right":
                if (spriteNum == 1) {
                    image = right1;
                }
                if (spriteNum == 2) {
                    image = right2;
                }
                break;
        }
        return image;
    }

    public void draw(Graphics2D g2) {
        int screenX = worldX - gp.player.worldX + gp.player.screenX;
        int screenY = worldY - gp.player.worldY + gp.player.screenY;

        if (worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
                worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
                worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
                worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {

            // name label
            if (name != null) {
                g2.setFont(new Font("Arial", Font.BOLD, 12));
                int lenght = (int) g2.getFontMetrics().getStringBounds(name, g2).getWidth();
                int x = spriteWidth / 2 - lenght / 2;

                g2.setColor(Color.black);
                g2.drawString(name, screenX + x, screenY - 20);
                g2.setColor(Color.white);
                g2.drawString(name, screenX + x + 1, screenY - 19);
            }

            // hp bar
            if (type != 0 && life != maxLife) {
                double oneScale = (double) gp.tileSize / maxLife;
                double hpBarValue = oneScale * life;

                g2.setColor(new Color(35, 35, 35));
                g2.fillRect(screenX - 1, screenY - 16, gp.tileSize + 2, 12);

                g2.setColor(new Color(255, 0, 30));
                g2.fillRect(screenX, screenY - 15, (int) hpBarValue, 10);
            }

            // DEBUG: HitBox drawing
            /*
             * g2.setColor(new Color(255, 0, 0, 100));
             * g2.fillRect(screenX + solidArea.x, screenY + solidArea.y, solidArea.width,
             * solidArea.height);
             */

            if (entityHandleSpriteDrawing) {
                BufferedImage image = resolveImage();
                g2.setColor(Color.white);
                g2.drawImage(image, screenX + spriteX, screenY + spriteY, spriteWidth, spriteHeight, null);
            }
        }

    }

    public void setAction() {

    }

    public void speak() {
        if (dialogues[dialogueIndex] == null) {
            dialogueIndex = 0;
        }
        gp.ui.currentDialogue = dialogues[dialogueIndex];
        dialogueIndex++;

        switch (gp.player.direction) {
            case "up":
                direction = "down";
                break;
            case "down":
                direction = "up";
                break;
            case "left":
                direction = "right";
                break;
            case "right":
                direction = "left";
                break;
        }
    }

    public void update() {
        setAction();

        collisionOn = false;
        gp.cChecker.checkTile(this);
        gp.cChecker.checkObject(this, false);
        gp.cChecker.checkEntity(this, gp.npc);
        gp.cChecker.checkEntity(this, gp.monster);
        boolean contactPlayer = gp.cChecker.checkPlayer(this);

        if (this.type == 2 && contactPlayer == true) {
            if (gp.player.invincible == false) {
                // we can give damage
                gp.player.life -= 1;
                gp.player.invincible = true;
            }
        }
        // IF COLLISION IS FALSE, PLAYER CAN MOVE
        if (collisionOn == false) {

            switch (direction) {
                case "up":
                    worldY -= speed;
                    break;
                case "down":
                    worldY += speed;
                    break;
                case "left":
                    worldX -= speed;
                    break;
                case "right":
                    worldX += speed;
                    break;
            }
            spriteCounter++;
            if (spriteCounter > 10) {
                if (spriteNum == 1) {
                    spriteNum = 2;
                } else if (spriteNum == 2) {
                    spriteNum = 1;
                }
                spriteCounter = 0;
            }

        }
        if (invincible == true) {
            invincibleCounter++;
            if (invincibleCounter > 40) {
                invincible = false;
                invincibleCounter = 0;
            }
        }
    }

}
