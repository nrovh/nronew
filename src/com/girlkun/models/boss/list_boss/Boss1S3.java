/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.girlkun.models.boss.list_boss;
import com.girlkun.models.boss.Boss;
import com.girlkun.models.boss.BossID;
import com.girlkun.models.boss.BossStatus;
import com.girlkun.models.boss.BossesData;
import com.girlkun.models.item.Item;
import com.girlkun.models.map.ItemMap;
import com.girlkun.models.player.Player;
import com.girlkun.server.Manager;
import com.girlkun.services.EffectSkillService;
import com.girlkun.services.Service;
import com.girlkun.utils.Util;
import java.util.Random;

/**
 * @@Stole By Hoàng Việt
 */
public class Boss1S3 extends Boss {

    public Boss1S3() throws Exception {
        super(BossID.BOSS_NRO1S3, BossesData.BOSS_NRO1S3);
    }

    @Override
    public void reward(Player plKill) {
        plKill.inventory.event+=5;
        Service.getInstance().sendThongBao(plKill, "Bạn đã nhận được 5 điểm săn Boss");
        byte randomDo = (byte) new Random().nextInt(Manager.itemIds_TL.length );
        byte randomDo1 = (byte) new Random().nextInt(Manager.itemIds_CUI.length);
        byte randomNR = (byte) new Random().nextInt(Manager.itemIds_NR_SB.length);
        
        // nro bí 
        ItemMap itnew = new ItemMap(this.zone, 705, 1, this.location.x-50, this.location.y, -1);
        Service.getInstance().dropItemMap(this.zone, itnew);
        
        if (Util.isTrue(4, 10)) {
            int b = 0;
            for (int i = 0; i < 3; i++) {
                ItemMap it1 = new ItemMap(this.zone, 722, 1, this.location.x + b, this.zone.map.yPhysicInTop(this.location.x, this.location.y - 24), plKill.id);
                Service.getInstance().dropItemMap(this.zone, it1);
                b += 10;
            }
        }
        // tẩy pháp sư
        if (Util.isTrue(3, 10)) {
            ItemMap it = new ItemMap(this.zone, 1236, 1, this.location.x-30, this.zone.map.yPhysicInTop(this.location.x,
                    this.location.y - 24), plKill.id);
            it.options.add(new Item.ItemOption(30, 1));
            Service.getInstance().dropItemMap(this.zone, it);
        } // dá pháp sư 
        else if (Util.isTrue(3, 10)) {
            int a = 20;
            for (int i = 0; i < 10; i++) {
                ItemMap it1 = new ItemMap(this.zone, 1235, 1, this.location.x + a, this.zone.map.yPhysicInTop(this.location.x,
                        this.location.y - 24), plKill.id);
                Service.getInstance().dropItemMap(this.zone, it1);
                a += 10;
            }
        }

        if ((Util.isTrue(5, 5))) { // ti le rot do tl la 2/10

           // ItemMap it2 = new ItemMap(this.zone, 20, 1, this.location.x, this.zone.map.yPhysicInTop(this.location.x, this.location.y - 24), plKill.id);
            ItemMap it2 = Util.ratiItem(this.zone, Manager.itemIds_TL[randomDo], 1, this.location.x, this.zone.map.yPhysicInTop(this.location.x, this.location.y - 27), plKill.id);

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

        } else if ((Util.isTrue(5, 8))) { // ti le rot do cui la 5/7
            //ItemMap it1 = new ItemMap(this.zone, 20, 1, this.location.x, this.zone.map.yPhysicInTop(this.location.x, this.location.y - 24), plKill.id);
            ItemMap it1 = Util.ratiItemC(this.zone, Manager.itemIds_CUI[randomDo1], 1, this.location.x, this.zone.map.yPhysicInTop(this.location.x, this.location.y - 18), plKill.id);
            Random splrd = new Random();
            int rdspl = splrd.nextInt(7) + 1;
            if (Util.isTrue(6, 10)) {
                it1.options.add(new Item.ItemOption(87, 1));
                Service.getInstance().sendThongBao(plKill, "|1|Bạn đã nhận " + it1.itemTemplate.name + " Ký gửi ngọc");
            }
            if (Util.isTrue(7, 10)) {
                it1.options.add(new Item.ItemOption(86, 1));
                Service.getInstance().sendThongBao(plKill, "|1|Bạn đã nhận " + it1.itemTemplate.name + " Ký gửi vàng");
            }
            if (Util.isTrue(9, 10)) {
                it1.options.add(new Item.ItemOption(107, rdspl));
                // Service.getInstance().sendThongBao(plKill, "|1|Bạn đã nhận " + it1.itemTemplate.name);
            }
            Service.getInstance().dropItemMap(this.zone, it1);
        } else {
            // ItemMap it1 = new ItemMap(this.zone, 20, 1, this.location.x, this.zone.map.yPhysicInTop(this.location.x, this.location.y - 24), plKill.id);
            ItemMap it1 = Util.ratiItemC(this.zone, Manager.itemIds_NR_SB[randomNR], 1, this.location.x, this.zone.map.yPhysicInTop(this.location.x, this.location.y - 24), plKill.id);
            if (Util.isTrue(1, 1)) {
                it1.options.add(new Item.ItemOption(86, 1));
                Service.getInstance().sendThongBao(plKill, "|1|Bạn đã nhận " + it1.itemTemplate.name + " Ký gửi vàng");
            }
            Service.getInstance().dropItemMap(this.zone, it1);
        }

    }

    @Override
    public void active() {
        super.active(); //To change body of generated methods, choose Tools | Templates.
        if (Util.canDoWithTime(st, 3500000)) {
            this.changeStatus(BossStatus.LEAVE_MAP);
        }
    }

    @Override
    public void joinMap() {
        super.joinMap(); //To change body of generated methods, choose Tools | Templates.
        st = System.currentTimeMillis();
    }
    private long st;

    @Override
    public double injured(Player plAtt, double damage, boolean piercing, boolean isMobAttack) {
        if (!this.isDie()) {
            if (!piercing && Util.isTrue(this.nPoint.tlNeDon - plAtt.nPoint.tlchinhxac, 1000)) {
                this.chat("Xí hụt");
                return 0;
            }
            damage = this.nPoint.subDameInjureWithDeff(damage);
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
}
