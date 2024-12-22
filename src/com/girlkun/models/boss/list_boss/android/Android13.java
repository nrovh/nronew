package com.girlkun.models.boss.list_boss.android;

import com.girlkun.models.boss.Boss;
import com.girlkun.models.boss.BossID;
import com.girlkun.models.boss.BossManager;
import com.girlkun.models.boss.BossesData;
import com.girlkun.models.item.Item;
import com.girlkun.models.map.ItemMap;
import com.girlkun.models.player.Player;
import com.girlkun.server.Manager;
import com.girlkun.services.Service;
import com.girlkun.utils.Util;
import java.util.Random;


public class Android13 extends Boss {

    public Android13() throws Exception {
        super(BossID.ANDROID_13, BossesData.ANDROID_13);
    }

    @Override
    public void reward(Player plKill) {
//        int[] itemRan = new int[]{380, 381, 382, 383, 384, 385};
//        int itemId = itemRan[2];
//        if (Util.isTrue(15, 100)) {
//            ItemMap it = new ItemMap(this.zone, itemId, 1, this.location.x+30, this.zone.map.yPhysicInTop(this.location.x,
//                    this.location.y - 24), plKill.id);
//            Service.getInstance().dropItemMap(this.zone, it);
//        }
//        byte randomDo1 = (byte) new Random().nextInt(Manager.itemIds_CUI.length );
//        byte randomNR = (byte) new Random().nextInt(Manager.itemIds_NR_SB.length);
//        
//         int a = 0;
//        for (int i = 0; i < 10; i++) {
//            ItemMap it = new ItemMap(this.zone, 861, 2, this.location.x + a, this.zone.map.yPhysicInTop(this.location.x,
//                    this.location.y - 24), -1);
//            Service.getInstance().dropItemMap(this.zone, it);
//            a += 10;
//        }
//        ItemMap itnew = new ItemMap(this.zone, 708, 1, this.location.x+20, this.location.y, -1);
//        Service.getInstance().dropItemMap(this.zone, itnew);
//            if((Util.isTrue(5,10))) { // ti le rot do cui la 5/16
//               // ItemMap it1 = new ItemMap(this.zone,20, 1, this.location.x, this.zone.map.yPhysicInTop(this.location.x, this.location.y - 24), plKill.id);
//                ItemMap it1=Util.ratiItemC(this.zone, Manager.itemIds_CUI[randomDo1], 1, this.location.x, this.zone.map.yPhysicInTop(this.location.x, this.location.y - 24), plKill.id);
//                Random splrd = new Random();
//                int rdspl = splrd.nextInt(6) + 1;
//                if (Util.isTrue(8, 12)) {
//                    it1.options.add(new Item.ItemOption(87, 1));
//                    Service.getInstance().sendThongBao(plKill, "|1|Bạn đã nhận " + it1.itemTemplate.name + " Ký gửi ngọc");
//                }
//                if (Util.isTrue(9, 10)) {
//                    it1.options.add(new Item.ItemOption(86, 1));
//                    Service.getInstance().sendThongBao(plKill, "|1|Bạn đã nhận " + it1.itemTemplate.name + " Ký gửi vàng");
//                }
//                if (Util.isTrue(9, 10)) {
//                    it1.options.add(new Item.ItemOption(107, rdspl));
//                    Service.getInstance().sendThongBao(plKill, "|1|Bạn đã nhận " + it1.itemTemplate.name);
//                }
//                Service.getInstance().dropItemMap(this.zone, it1);
//            }else {
//                //ItemMap it1 = new ItemMap(this.zone, 20, 1, this.location.x, this.zone.map.yPhysicInTop(this.location.x, this.location.y - 24), plKill.id);
//                ItemMap it1=Util.ratiItemC(this.zone, Manager.itemIds_NR_SB[randomNR], 1, this.location.x, this.zone.map.yPhysicInTop(this.location.x, this.location.y - 24), plKill.id);
//                if (Util.isTrue(1, 1)) {
//                    it1.options.add(new Item.ItemOption(86, 1));
//                    Service.getInstance().sendThongBao(plKill, "|1|Bạn đã nhận " + it1.itemTemplate.name + " Ký gửi vàng");
//                }
//                Service.getInstance().dropItemMap(this.zone, it1);
//            }           

            // mới 
         plKill.achievement.plusCount(1);
        plKill.inventory.event++;
        Service.getInstance().sendThongBao(plKill, "Bạn đã nhận được 1 điểm săn Boss");
        byte randomDo = (byte) new Random().nextInt(Manager.itemIds_TL.length );
        byte randomNR = (byte) new Random().nextInt(Manager.itemIds_NR_SB.length);
        
        ItemMap itnew = new ItemMap(this.zone, 702, 1, this.location.x-50, this.location.y, -1);
        Service.getInstance().dropItemMap(this.zone, itnew);
 
         if ((Util.isTrue(1, 10))) { 
            //ItemMap it2 = new ItemMap(this.zone, 20, 1, this.location.x, this.zone.map.yPhysicInTop(this.location.x, this.location.y - 24), plKill.id);
            ItemMap it2 = Util.ratiItem(this.zone, Manager.itemIds_TL[randomDo], 1, this.location.x, this.zone.map.yPhysicInTop(this.location.x, this.location.y - 27), plKill.id);
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
        } else {
            //ItemMap it1 = new ItemMap(this.zone, 20, 1, this.location.x, this.zone.map.yPhysicInTop(this.location.x, this.location.y - 24), plKill.id);
            ItemMap it1 = Util.ratiItemC(this.zone, Manager.itemIds_NR_SB[randomNR], 1, this.location.x, this.zone.map.yPhysicInTop(this.location.x, this.location.y - 24), plKill.id);
            if (Util.isTrue(1, 1)) {
                it1.options.add(new Item.ItemOption(86, 1));
                Service.getInstance().sendThongBao(plKill, "|1|Bạn đã nhận " + it1.itemTemplate.name + " Ký gửi vàng");
            }
            Service.getInstance().dropItemMap(this.zone, it1);
        }
    }


    @Override
    public void doneChatS() {
        if (this.parentBoss == null) {
            return;
        }
        if (this.parentBoss.bossAppearTogether == null
                || this.parentBoss.bossAppearTogether[this.parentBoss.currentLevel] == null) {
            return;
        }
        for (Boss boss : this.parentBoss.bossAppearTogether[this.parentBoss.currentLevel]) {
            if (boss.id == BossID.ANDROID_15 && !boss.isDie()) {
                boss.changeToTypePK();
                break;
            }
        }
        this.parentBoss.changeToTypePK();
    }

    @Override
    public double injured(Player plAtt, double damage, boolean piercing, boolean isMobAttack) {
        if (damage >= this.nPoint.hp) {
            boolean flag = true;
            if (this.parentBoss != null) {
                if (this.parentBoss.bossAppearTogether != null && this.parentBoss.bossAppearTogether[this.parentBoss.currentLevel] != null) {
                    for (Boss boss : this.parentBoss.bossAppearTogether[this.parentBoss.currentLevel]) {
                        if (boss.id == BossID.ANDROID_15 && !boss.isDie()) {
                            flag = false;
                            break;
                        }
                    }
                }
                if (flag && !this.parentBoss.isDie()) {
                    flag = false;
                }
            }
            if (!flag) {
                return 0;
            }
        }
        return super.injured(plAtt, damage, piercing, isMobAttack);
    }
}

/**
 * Vui lòng không sao chép mã nguồn này dưới mọi hình thức.
 */
