package com.girlkun.models.boss.list_boss.phoban;

import com.girlkun.consts.ConstPlayer;
import com.girlkun.models.boss.*;
import static com.girlkun.models.boss.BossStatus.ACTIVE;
import static com.girlkun.models.boss.BossStatus.JOIN_MAP;
import static com.girlkun.models.boss.BossStatus.RESPAWN;
import com.girlkun.models.boss.list_boss.cell.SieuBoHung;
import com.girlkun.models.item.Item;
import com.girlkun.models.map.ItemMap;
import com.girlkun.models.map.Zone;
import com.girlkun.models.map.bdkb.BanDoKhoBau;
import com.girlkun.models.map.bdkb.BanDoKhoBauService;
import com.girlkun.models.map.challenge.MartialCongressService;
import com.girlkun.models.mob.Mob;
import com.girlkun.models.player.Player;
import com.girlkun.models.skill.Skill;
import com.girlkun.services.EffectSkillService;
import com.girlkun.services.PlayerService;
import com.girlkun.services.Service;
import com.girlkun.server.Maintenance;
import com.girlkun.server.Manager;
import com.girlkun.services.MapService;
import com.girlkun.services.SkillService;
import com.girlkun.utils.SkillUtil;
import com.girlkun.utils.Util;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author BTH sieu cap vippr0
 */
public class TrungUyXanhLoBdkb extends Boss {

    private long lastUpdate = System.currentTimeMillis();
    private int levell;
    private int initSuper = 0;
    protected Player playerAtt;
    private int timeLive = 200000000;

    public TrungUyXanhLoBdkb(Zone zone, byte level, int dame, double hp, int id) throws Exception {
        super(id, new BossData(
                "Trung Úy Xanh Lơ", //name
                ConstPlayer.TRAI_DAT, //gender
                new short[]{135, 136, 137, -1, -1, -1}, //outfit {head, body, leg, bag, aura, eff}
                ((10000 * level)), //dame
                new double[]{((300000000L * level))}, //hp
                new int[]{148}, //map join
                new int[][]{
                    {Skill.GALICK, 5, 5},
                    {Skill.KAMEJOKO, 7, 12},
                    {Skill.THAI_DUONG_HA_SAN, 2, 20000},
                    {Skill.MASENKO, 6, 23},},
                new String[]{"|-1|Kho báu ở đây là của ta"}, //text chat 1
                new String[]{"|-1|Nhóc con"}, //text chat 2
                new String[]{"|-1|Ta sẽ tiêu diệt tất cả bang hội ngươi"}, //text chat 3
                60
        ));

        this.zone = zone;
        this.levell = level;
    }

    @Override
    public void reward(Player plKill) {
        ItemMap itnew = new ItemMap(this.zone, 706, 1, this.location.x, this.location.y, -1);
        Service.getInstance().dropItemMap(this.zone, itnew);
        if (levell==1){
            int a = 0;
            for (int i = 0; i < 20; i++) {
                ItemMap it = new ItemMap(this.zone, 861, 2, this.location.x, this.zone.map.yPhysicInTop(this.location.x,
                        this.location.y - 24), -1);
                Service.getInstance().dropItemMap(this.zone, it);
                a += 10;
            }
        }else if(levell<=5){
             int a = 0;
            for (int i = 0; i < 20; i++) {
                ItemMap it = new ItemMap(this.zone, 861, 4, this.location.x, this.zone.map.yPhysicInTop(this.location.x,
                        this.location.y - 24), -1);
                Service.getInstance().dropItemMap(this.zone, it);
                a += 10;
            }
        }else if (levell <= 10) {
            int a = 0;
            for (int i = 0; i < 20; i++) {
                ItemMap it = new ItemMap(this.zone, 861, 7, this.location.x, this.zone.map.yPhysicInTop(this.location.x,
                        this.location.y - 24), -1);
                Service.getInstance().dropItemMap(this.zone, it);
                a += 10;
            }
        }else if (levell <= 20) {
            int a = 0;
            for (int i = 0; i < 20; i++) {
                ItemMap it = new ItemMap(this.zone, 861, 10, this.location.x, this.zone.map.yPhysicInTop(this.location.x,
                        this.location.y - 24), -1);
                Service.getInstance().dropItemMap(this.zone, it);
                a += 10;
            }
            if((Util.isTrue(3,8))) {
                byte randomNR = (byte) new Random().nextInt(Manager.itemIds_NR_SB.length);
                ItemMap it1 = new ItemMap(this.zone, Manager.itemIds_NR[randomNR], 1, this.location.x, this.zone.map.yPhysicInTop(this.location.x, this.location.y - 24), plKill.id);
                it1=Util.ratiItemC(this.zone, Manager.itemIds_NR_SB[randomNR], 1, this.location.x, this.zone.map.yPhysicInTop(this.location.x, this.location.y - 24), plKill.id);
                if (Util.isTrue(1, 1)) {
                    it1.options.add(new Item.ItemOption(86, 1));
                    Service.getInstance().sendThongBao(plKill, "|1|Bạn đã nhận " + it1.itemTemplate.name + " Ký gửi vàng");
                }
                Service.getInstance().dropItemMap(this.zone, it1);
            }
        }else if (levell <= 40) {
            int a = 0;
            for (int i = 0; i < 20; i++) {
                ItemMap it = new ItemMap(this.zone, 861, 20, this.location.x, this.zone.map.yPhysicInTop(this.location.x,
                        this.location.y - 24), -1);
                Service.getInstance().dropItemMap(this.zone, it);
                a += 10;
            }
            if((Util.isTrue(4,8))) {
                byte randomNR = (byte) new Random().nextInt(Manager.itemIds_NR_SB.length);
                ItemMap it1 = new ItemMap(this.zone, Manager.itemIds_NR[randomNR], 1, this.location.x, this.zone.map.yPhysicInTop(this.location.x, this.location.y - 24), plKill.id);
                it1=Util.ratiItemC(this.zone, Manager.itemIds_NR_SB[randomNR], 1, this.location.x, this.zone.map.yPhysicInTop(this.location.x, this.location.y - 24), plKill.id);
                if (Util.isTrue(1, 1)) {
                    it1.options.add(new Item.ItemOption(86, 1));
                    Service.getInstance().sendThongBao(plKill, "|1|Bạn đã nhận " + it1.itemTemplate.name + " Ký gửi vàng");
                }
                Service.getInstance().dropItemMap(this.zone, it1);
            }
        }else if (levell <= 80) {
            int a = 0;
            for (int i = 0; i < 20; i++) {
                ItemMap it = new ItemMap(this.zone, 861, 40, this.location.x, this.zone.map.yPhysicInTop(this.location.x,
                        this.location.y - 24), -1);
                Service.getInstance().dropItemMap(this.zone, it);
                a += 10;
            }
            if((Util.isTrue(5,8))) {
                byte randomNR = (byte) new Random().nextInt(Manager.itemIds_NR_SB.length);
                ItemMap it1 = new ItemMap(this.zone, Manager.itemIds_NR[randomNR], 1, this.location.x, this.zone.map.yPhysicInTop(this.location.x, this.location.y - 24), plKill.id);
                it1=Util.ratiItemC(this.zone, Manager.itemIds_NR_SB[randomNR], 1, this.location.x, this.zone.map.yPhysicInTop(this.location.x, this.location.y - 24), plKill.id);
                if (Util.isTrue(1, 1)) {
                    it1.options.add(new Item.ItemOption(86, 1));
                    Service.getInstance().sendThongBao(plKill, "|1|Bạn đã nhận " + it1.itemTemplate.name + " Ký gửi vàng");
                }
                Service.getInstance().dropItemMap(this.zone, it1);
            }
            
        }else if (levell <= 100) {
            int a = 0;
            for (int i = 0; i < 20; i++) {
                ItemMap it = new ItemMap(this.zone, 861, 50, this.location.x, this.zone.map.yPhysicInTop(this.location.x,
                        this.location.y - 24), -1);
                Service.getInstance().dropItemMap(this.zone, it);
                a += 10;
            }
            if((Util.isTrue(6,8))) {
                byte randomNR = (byte) new Random().nextInt(Manager.itemIds_NR_SB.length);
                ItemMap it1 = new ItemMap(this.zone, Manager.itemIds_NR[randomNR], 1, this.location.x, this.zone.map.yPhysicInTop(this.location.x, this.location.y - 24), plKill.id);
                it1=Util.ratiItemC(this.zone, Manager.itemIds_NR_SB[randomNR], 1, this.location.x, this.zone.map.yPhysicInTop(this.location.x, this.location.y - 24), plKill.id);
                if (Util.isTrue(1, 1)) {
                    it1.options.add(new Item.ItemOption(86, 1));
                    Service.getInstance().sendThongBao(plKill, "|1|Bạn đã nhận " + it1.itemTemplate.name + " Ký gửi vàng");
                }
                Service.getInstance().dropItemMap(this.zone, it1);
            }
        } else if (levell < 110) {
            int a = 0;
            for (int i = 0; i < 20; i++) {
                ItemMap it = new ItemMap(this.zone, 861, 70, this.location.x+a, this.zone.map.yPhysicInTop(this.location.x,
                        this.location.y - 24), -1);
                Service.getInstance().dropItemMap(this.zone, it);
                a += 10;
            }
            if((Util.isTrue(7,8))) {
                byte randomNR = (byte) new Random().nextInt(Manager.itemIds_NR_SB.length);
                ItemMap it1 = new ItemMap(this.zone, Manager.itemIds_NR[randomNR], 1, this.location.x, this.zone.map.yPhysicInTop(this.location.x, this.location.y - 24), plKill.id);
                it1=Util.ratiItemC(this.zone, Manager.itemIds_NR_SB[randomNR], 1, this.location.x, this.zone.map.yPhysicInTop(this.location.x+30, this.location.y - 24), plKill.id);
                if (Util.isTrue(1, 1)) {
                    it1.options.add(new Item.ItemOption(86, 1));
                    Service.getInstance().sendThongBao(plKill, "|1|Bạn đã nhận " + it1.itemTemplate.name + " Ký gửi vàng");
                }
                Service.getInstance().dropItemMap(this.zone, it1);
            }
        }else if (levell==110){
           int a = 0;
            for (int i = 0; i < 15; i++) {
                ItemMap it = new ItemMap(this.zone, 861, 130, this.location.x+a, this.zone.map.yPhysicInTop(this.location.x,
                        this.location.y - 24), -1);
                Service.getInstance().dropItemMap(this.zone, it);
                a += 10;
            }
            if((Util.isTrue(7,7))) {
                byte randomNR = (byte) new Random().nextInt(Manager.itemIds_NR_SB.length);
                //ItemMap it1 = new ItemMap(this.zone, Manager.itemIds_NR[randomNR], 1, this.location.x, this.zone.map.yPhysicInTop(this.location.x, this.location.y - 24), plKill.id);
                ItemMap it1=Util.ratiItemC(this.zone, Manager.itemIds_NR_SB[randomNR], 1, this.location.x, this.zone.map.yPhysicInTop(this.location.x+30, this.location.y - 24), plKill.id);
                if (Util.isTrue(1, 1)) {
                    it1.options.add(new Item.ItemOption(86, 1));
                    Service.getInstance().sendThongBao(plKill, "|1|Bạn đã nhận " + it1.itemTemplate.name + " Ký gửi vàng");
                }
                Service.getInstance().dropItemMap(this.zone, it1);
            }
            if((Util.isTrue(5,7))) {
              
                ItemMap it3=Util.ratiItemC(this.zone, 459, 1, this.location.x, this.zone.map.yPhysicInTop(this.location.x+30, this.location.y - 24), plKill.id);
                if (Util.isTrue(1, 1)) {
                    it3.options.add(new Item.ItemOption(30, 0));
                    Service.getInstance().sendThongBao(plKill, "|1|Bạn đã nhận " + it3.itemTemplate.name + "");
                }
                Service.getInstance().dropItemMap(this.zone, it3);
            }
            byte randomDo = (byte) new Random().nextInt(Manager.itemIds_TL.length - 1);
            if ((Util.isTrue(4,10))) { // ti le rot do tl la 4/10
                
                 //it2 = new ItemMap(this.zone, Manager.itemIds_TL[randomDo], 1, this.location.x, this.zone.map.yPhysicInTop(this.location.x, this.location.y - 24), plKill.id);
                ItemMap it2=Util.ratiItem(this.zone, Manager.itemIds_TL[randomDo], 1, this.location.x, this.zone.map.yPhysicInTop(this.location.x +50, this.location.y - 27), plKill.id);
                
                Random splrd = new Random();
                int rdspl = splrd.nextInt(7) + 1;
                if (Util.isTrue(5, 10)) {
                    it2.options.add(new Item.ItemOption(107, rdspl));
                }
                if (Util.isTrue(5, 10)) {
                    it2.options.add(new Item.ItemOption(87, 1));
                    Service.getInstance().sendThongBao(plKill, "|1|Bạn đã nhận " + it2.itemTemplate.name + " Ký gửi ngọc");
                }            
                Service.getInstance().dropItemMap(this.zone, it2);
                
            }
        }
        BanDoKhoBauService.gI().timeoutmap = 20;
    }

    @Override
    public void active() {
        super.active();
    }

    @Override
    public double injured(Player plAtt, double damage, boolean piercing, boolean isMobAttack) {
        if (!this.isDie()) {
            if (!piercing && Util.isTrue(this.nPoint.tlNeDon - plAtt.nPoint.tlchinhxac, 1000)) {
                this.chat("Xí hụt");
                return 0;
            }
            damage = (double) this.nPoint.subDameInjureWithDeff(damage / 2);

            for (Mob mob : plAtt.zone.mobs) {
                if (!mob.isDie()) {
                    damage = 0;
                }
            }
            if (!piercing && effectSkill.isShielding) {
                if (damage > nPoint.hpMax) {
                    EffectSkillService.gI().breakShield(this);
                }
                damage = 1;
            }
            this.nPoint.subHP(damage);
            if (isDie()) {
                this.setDie(plAtt);
                die(plAtt);
            }
            return damage;
        } else {
            return 0;
        }
    }

    @Override
    public void leaveMap() {
        super.leaveMap();
        BossManager.gI().removeBoss(this);
    }

}
