package com.girlkun.models.boss.list_boss.cell;

import com.girlkun.consts.ConstPlayer;
import com.girlkun.models.boss.Boss;
import com.girlkun.models.boss.BossID;
import com.girlkun.models.boss.BossManager;
import com.girlkun.models.boss.BossesData;
import com.girlkun.models.map.ItemMap;
import com.girlkun.models.player.Player;
import com.girlkun.server.Manager;
import com.girlkun.models.boss.BossStatus;
import com.girlkun.models.item.Item;
import com.girlkun.services.EffectSkillService;
import com.girlkun.services.Service;
import com.girlkun.services.TaskService;
import com.girlkun.utils.Util;
import com.girlkun.services.PlayerService;
import java.util.Random;

public class SieuBoHung extends Boss {

    private long lastTimeHapThu;
    private int timeHapThu;

    public SieuBoHung() throws Exception {
        super(BossID.SIEU_BO_HUNG, BossesData.SIEU_BO_HUNG_1, BossesData.SIEU_BO_HUNG_2, BossesData.SIEU_BO_HUNG_3);
    }

    @Override
    public void reward(Player plKill) {
        plKill.achievement.plusCount(3);
        plKill.inventory.event++;
        Service.getInstance().sendThongBao(plKill, "Bạn đã nhận được 1 điểm săn Boss");

        byte randomDo = (byte) new Random().nextInt(Manager.itemIds_TL.length);
        byte randomDo1 = (byte) new Random().nextInt(Manager.itemIds_CUI.length);
        byte randomNR = (byte) new Random().nextInt(Manager.itemIds_NR_SB.length);

        ItemMap itnew = new ItemMap(this.zone, 707, 1, this.location.x+30, this.location.y, -1);
        Service.getInstance().dropItemMap(this.zone, itnew);
        
        if ((Util.isTrue(1, 13))) { // ti le rot do tl la 3/10
            //ItemMap it2 = new ItemMap(this.zone, 20, 1, this.location.x, this.zone.map.yPhysicInTop(this.location.x, this.location.y - 24), plKill.id);
            ItemMap it2 = Util.ratiItem(this.zone, Manager.itemIds_TL[randomDo], 1, this.location.x+50, this.zone.map.yPhysicInTop(this.location.x, this.location.y - 27), plKill.id);

            Random splrd = new Random();
            int rdspl = splrd.nextInt(5) + 1;
            if (Util.isTrue(5, 10)) {
                it2.options.add(new Item.ItemOption(107, rdspl));
            }
            if (Util.isTrue(5, 10)) {
                it2.options.add(new Item.ItemOption(87, 1));
                Service.getInstance().sendThongBao(plKill, "|1|Bạn đã nhận " + it2.itemTemplate.name + " Ký gửi ngọc");
            }
            Service.getInstance().dropItemMap(this.zone, it2);

        } else if ((Util.isTrue(5, 7))) { // ti le rot do cui la 5/7
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
        } else if ((Util.isTrue(6, 8))) {
            //ItemMap it1 = new ItemMap(this.zone, 20, 1, this.location.x, this.zone.map.yPhysicInTop(this.location.x, this.location.y - 24), plKill.id);
            ItemMap it1 = Util.ratiItemC(this.zone, Manager.itemIds_NR_SB[randomNR], 1, this.location.x, this.zone.map.yPhysicInTop(this.location.x, this.location.y - 24), plKill.id);
            if (Util.isTrue(1, 1)) {
                it1.options.add(new Item.ItemOption(86, 1));
                Service.getInstance().sendThongBao(plKill, "|1|Bạn đã nhận " + it1.itemTemplate.name + " Ký gửi vàng");
            }
            Service.getInstance().dropItemMap(this.zone, it1);

        }
        TaskService.gI().checkDoneTaskKillBoss(plKill, this);
    }

    @Override
    public void active() {
        super.active(); //To change body of generated methods, choose Tools | Templates.
        if (Util.canDoWithTime(st, 1000000)) {
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
//    @Override
//    public void moveTo(int x, int y) {
//        if(this.currentLevel == 1){
//            return;
//        }
//        super.moveTo(x, y);
//    }
//
//    @Override
//    public void reward(Player plKill) {
//        if(this.currentLevel == 1){
//            return;
//        }
//        super.reward(plKill);
//    }
//    
//    @Override
//    protected void notifyJoinMap() {
//        if(this.currentLevel == 1){
//            return;
//        }
//        super.notifyJoinMap();
//    }
}
