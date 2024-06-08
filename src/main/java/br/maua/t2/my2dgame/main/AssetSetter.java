/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.maua.t2.my2dgame.main;

import br.maua.t2.my2dgame.entity.BossFinal;
import br.maua.t2.my2dgame.entity.MON_SkeletonLord;
import br.maua.t2.my2dgame.entity.NPC_OldMan;
import br.maua.t2.my2dgame.entity.SlimeMonster;
import br.maua.t2.my2dgame.object.OBJ_Boots;
import br.maua.t2.my2dgame.object.OBJ_Door;
import br.maua.t2.my2dgame.object.OBJ_Key;
import br.maua.t2.my2dgame.object.OBJ_SpawnPoint;

/**
 *
 * @author keyex
 */
public class AssetSetter {
    GamePanel gp;

    public AssetSetter(GamePanel gp) {
        this.gp = gp;
    }

    public void addSpawnPoint(int x, int y) {
        if (gp.spawnPoint.worldX / gp.tileSize != x && gp.spawnPoint.worldY / gp.tileSize != y) {
            gp.obj.add(new OBJ_SpawnPoint(gp, x, y));
        }
    }

    public void setObject() {

        gp.obj.add(new OBJ_Key(gp, 48, 42));

        gp.obj.add(new OBJ_Door(gp, 39, 45, 0));
        gp.obj.add(new OBJ_Door(gp, 39, 44, 0));
        gp.obj.add(new OBJ_Door(gp, 39, 43, 0));
        // addSpawnPoint(38, 44);

        gp.obj.add(new OBJ_Door(gp, 33, 34, 1));
        gp.obj.add(new OBJ_Door(gp, 33, 35, 1));
        gp.obj.add(new OBJ_Door(gp, 33, 36, 1));
        addSpawnPoint(31, 35);

        gp.obj.add(new OBJ_Door(gp, 19, 42, 2));
        gp.obj.add(new OBJ_Door(gp, 19, 43, 2));
        gp.obj.add(new OBJ_Door(gp, 19, 44, 2));
        addSpawnPoint(17, 43);

        gp.obj.add(new OBJ_Door(gp, 14, 32, 3));
        gp.obj.add(new OBJ_Door(gp, 15, 32, 3));
        gp.obj.add(new OBJ_Door(gp, 16, 32, 3));
        gp.obj.add(new OBJ_Door(gp, 17, 32, 3));
        gp.obj.add(new OBJ_Door(gp, 18, 32, 3));
        addSpawnPoint(16, 30);

        gp.obj.add(new OBJ_Door(gp, 18, 21, 4));
        gp.obj.add(new OBJ_Door(gp, 17, 21, 4));
        gp.obj.add(new OBJ_Door(gp, 16, 21, 4));
        gp.obj.add(new OBJ_Door(gp, 15, 21, 4));
        gp.obj.add(new OBJ_Door(gp, 14, 21, 4));
        addSpawnPoint(16, 19);

        gp.obj.add(new OBJ_Door(gp, 18, 8, 5));
        gp.obj.add(new OBJ_Door(gp, 17, 8, 5));
        gp.obj.add(new OBJ_Door(gp, 16, 8, 5));
        gp.obj.add(new OBJ_Door(gp, 15, 8, 5));
        gp.obj.add(new OBJ_Door(gp, 14, 8, 5));
        addSpawnPoint(16, 6);

        gp.obj.add(new OBJ_Door(gp, 30, 3, 6));
        gp.obj.add(new OBJ_Door(gp, 30, 4, 6));
        gp.obj.add(new OBJ_Door(gp, 30, 5, 6));
        gp.obj.add(new OBJ_Door(gp, 30, 6, 6));
        gp.obj.add(new OBJ_Door(gp, 30, 7, 6));

        gp.obj.add(new OBJ_Boots(gp, 37, 42));
    }

    public void setNPC() {
        gp.npc.add(new NPC_OldMan(gp, 40, 42));

        gp.npc.add(new BossFinal(gp, 40, 14));
    }

    public void setMonster() {

        gp.monster.add(new SlimeMonster(gp, 35, 35, 0));
        gp.monster.add(new SlimeMonster(gp, 35, 37, 0));
        gp.monster.add(new SlimeMonster(gp, 35, 39, 0));
        gp.monster.add(new SlimeMonster(gp, 37, 39, 0));

        gp.monster.add(new SlimeMonster(gp, 28, 39, 1));
        gp.monster.add(new SlimeMonster(gp, 23, 43, 1));
        gp.monster.add(new SlimeMonster(gp, 30, 43, 1));
        gp.monster.add(new MON_SkeletonLord(gp, 22, 37, 1));
        gp.monster.add(new SlimeMonster(gp, 28, 41, 1));

        gp.monster.add(new SlimeMonster(gp, 15, 44, 2));
        gp.monster.add(new SlimeMonster(gp, 15, 36, 2));
        gp.monster.add(new SlimeMonster(gp, 14, 38, 2));

        gp.monster.add(new SlimeMonster(gp, 23, 24, 3));
        gp.monster.add(new SlimeMonster(gp, 23, 26, 3));
        gp.monster.add(new MON_SkeletonLord(gp, 23, 28, 3));
        gp.monster.add(new SlimeMonster(gp, 23, 30, 3));
        gp.monster.add(new SlimeMonster(gp, 15, 25, 3));
        gp.monster.add(new SlimeMonster(gp, 17, 23, 3));

        gp.monster.add(new SlimeMonster(gp, 14, 18, 4));
        gp.monster.add(new SlimeMonster(gp, 17, 16, 4));
        gp.monster.add(new MON_SkeletonLord(gp, 15, 13, 4));
        gp.monster.add(new MON_SkeletonLord(gp, 17, 10, 4));

        gp.monster.add(new MON_SkeletonLord(gp, 25, 5, 5, true));
        gp.monster.add(new MON_SkeletonLord(gp, 27, 4, 5, true));

    }

}
