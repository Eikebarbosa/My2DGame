/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import entity.MON_SkeletonLord;
import object.OBJ_Chest;
import object.OBJ_Door;
import object.OBJ_Key;
import object.OBJ_Boots;
import entity.NPC_OldMan;
import entity.SlimeMonster;

/**
 *
 * @author keyex
 */
public class AssetSetter {
    GamePanel gp;
    public AssetSetter(GamePanel gp){
        this.gp = gp;
    }
    public void setObject(){
        //definindo aonde a chave vai ficar no mundo.
        gp.obj[0] = new OBJ_Key();
        gp.obj[0].worldX = 23 * gp.tileSize;
        gp.obj[0].worldY = 7 * gp.tileSize;
        
        gp.obj[1] = new OBJ_Key();
        gp.obj[1].worldX = 48 * gp.tileSize;
        gp.obj[1].worldY = 41 * gp.tileSize;
        
        gp.obj[2] = new OBJ_Door();
        gp.obj[2].worldX = 39 * gp.tileSize;
        gp.obj[2].worldY = 45 * gp.tileSize;
        
        gp.obj[3] = new OBJ_Door();
        gp.obj[3].worldX = 39 * gp.tileSize;
        gp.obj[3].worldY = 44 * gp.tileSize;
        
        gp.obj[4] = new OBJ_Door();
        gp.obj[4].worldX = 39 * gp.tileSize;
        gp.obj[4].worldY = 43* gp.tileSize;
        
        //gp.obj[5] = new OBJ_Chest();
        //gp.obj[5].worldX = 8 * gp.tileSize;
        //gp.obj[5].worldY = 28 * gp.tileSize;
        
        //gp.obj[6] = new OBJ_Chest();
        //gp.obj[6].worldX = 12 * gp.tileSize;
        //gp.obj[6].worldY = 22 * gp.tileSize;
        
        //gp.obj[7] = new OBJ_Chest();
        //gp.obj[7].worldX = 10 * gp.tileSize;
        //gp.obj[7].worldY = 7 * gp.tileSize;
        
        gp.obj[8] = new OBJ_Boots();
        gp.obj[8].worldX = 37 * gp.tileSize;
        gp.obj[8].worldY = 42 * gp.tileSize;
    }
    public void setNPC(){
        gp.npc[0] = new NPC_OldMan(gp);
        gp.npc[0].worldX = gp.tileSize*40;
        gp.npc[0].worldY = gp.tileSize*42;
    }
    
    public void setMonster(){
        gp.monster[0] = new SlimeMonster(gp);
        gp.monster[0].worldX = gp.tileSize*23;
        gp.monster[0].worldY = gp.tileSize*26;
        
        gp.monster[1] = new SlimeMonster(gp);
        gp.monster[1].worldX = gp.tileSize*23;
        gp.monster[1].worldY = gp.tileSize*24;
        
        gp.monster[2] = new SlimeMonster(gp);
        gp.monster[2].worldX = gp.tileSize*23;
        gp.monster[2].worldY = gp.tileSize*25;
        
        gp.monster[3] = new SlimeMonster(gp);
        gp.monster[3].worldX = gp.tileSize*23;
        gp.monster[3].worldY = gp.tileSize*27;
        
        gp.monster[4] = new SlimeMonster(gp);
        gp.monster[4].worldX = gp.tileSize*23;
        gp.monster[4].worldY = gp.tileSize*28;
        
        gp.monster[5] = new SlimeMonster(gp);
        gp.monster[5].worldX = gp.tileSize*23;
        gp.monster[5].worldY = gp.tileSize*29;
        
        gp.monster[6] = new SlimeMonster(gp);
        gp.monster[6].worldX = gp.tileSize*23;
        gp.monster[6].worldY = gp.tileSize*30;
        
        gp.monster[7] = new SlimeMonster(gp);
        gp.monster[7].worldX = gp.tileSize*24;
        gp.monster[7].worldY = gp.tileSize*30;
        
        gp.monster[8] = new SlimeMonster(gp);
        gp.monster[8].worldX = gp.tileSize*45;
        gp.monster[8].worldY = gp.tileSize*41;
        
        gp.monster[9] = new MON_SkeletonLord(gp);
        gp.monster[9].worldX = gp.tileSize*43;
        gp.monster[9].worldY = gp.tileSize*44;
        
        /*gp.monster[9] = new SlimeMonster(gp);
        gp.monster[9].worldX = gp.tileSize*26;
        gp.monster[9].worldY = gp.tileSize*26;
        
        gp.monster[10] = new SlimeMonster(gp);
        gp.monster[10].worldX = gp.tileSize*26;
        gp.monster[10].worldY = gp.tileSize*27;
        
        gp.monster[11] = new SlimeMonster(gp);
        gp.monster[11].worldX = gp.tileSize*27;
        gp.monster[11].worldY = gp.tileSize*28;*/
        
        
        
    }

}
