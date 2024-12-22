package com.girlkun.models.boss.list_boss.Mabu12h;

import com.girlkun.models.boss.Boss;
import com.girlkun.models.boss.BossManager;
import com.girlkun.models.boss.BossStatus;
import com.girlkun.models.boss.BossesData;
import com.girlkun.models.item.Item;
import com.girlkun.models.map.ItemMap;
import com.girlkun.models.player.Player;
import com.girlkun.server.Manager;
import com.girlkun.services.Service;
import com.girlkun.utils.Util;

import java.util.Random;

public class BuiBui2 extends Boss {

    public BuiBui2() throws Exception {
        super(Util.randomBossId(), BossesData.BUI_BUI_2);
    }

    @Override
    public void reward(Player plKill) {
       byte randomDo = (byte) new Random().nextInt(Manager.itemIds_TL.length);
        byte randomDo1 = (byte) new Random().nextInt(Manager.itemIds_CUI.length);
        byte randomNR = (byte) new Random().nextInt(Manager.itemIds_NR_SB.length);
        byte randomc12 = (byte) new Random().nextInt(Manager.itemDC12.length);

        plKill.inventory.event++;
        Service.getInstance().sendThongBao(plKill, "Bạn đã nhận được 1 điểm săn Boss");

        if (Util.isTrue(8, 10)) { // item c2
            Service.getInstance().dropItemMap(this.zone, new ItemMap(Util.RaitiDoc12(zone, Manager.itemDC12[randomc12], 1, this.location.x, this.location.y, plKill.id)));
        }
        
        ItemMap itnew = new ItemMap(this.zone, 704, 1, this.location.x+50, this.location.y, -1);
        Service.getInstance().dropItemMap(this.zone, itnew);
        
        if ((Util.isTrue(1, 15))) { // ti le rot do tl la 2/10
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

        } else if ((Util.isTrue(5, 10))) { // ti le rot do cui la 5/7
            //ItemMap it1 = new ItemMap(this.zone, 20, 1, this.location.x, this.zone.map.yPhysicInTop(this.location.x, this.location.y - 24), plKill.id);
            ItemMap it1 = Util.ratiItemC(this.zone, Manager.itemIds_CUI[randomDo1], 1, this.location.x, this.zone.map.yPhysicInTop(this.location.x, this.location.y - 18), plKill.id);
            Random splrd = new Random();
            int rdspl = splrd.nextInt(6) + 1;
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
            ItemMap it1 = Util.ratiItemC(this.zone, Manager.itemIds_NR_SB[randomNR], 1, this.location.x+100, this.zone.map.yPhysicInTop(this.location.x, this.location.y - 24), plKill.id);
            if (Util.isTrue(1, 1)) {
                it1.options.add(new Item.ItemOption(86, 1));
                Service.getInstance().sendThongBao(plKill, "|1|Bạn đã nhận " + it1.itemTemplate.name + " Ký gửi vàng");
            }
            Service.getInstance().dropItemMap(this.zone, it1);
        }
        plKill.fightMabu.changePoint((byte) 20);
    }
//    @Override
//    public void active() {
//        super.active(); //To change body of generated methods, choose Tools | Templates.
//        if (Util.canDoWithTime(st, 300000)) {
//            this.changeStatus(BossStatus.LEAVE_MAP);
//        }
//    }
//
//    @Override
//    public void joinMap() {
//        super.joinMap(); //To change body of generated methods, choose Tools | Templates.
//        st = System.currentTimeMillis();
//    }
//    private long st;

}
