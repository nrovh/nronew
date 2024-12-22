/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.girlkun.models.boss.list_boss.FideBack;

import com.girlkun.models.boss.Boss;
import com.girlkun.models.boss.BossID;
import com.girlkun.models.boss.BossesData;
import com.girlkun.models.item.Item;
import com.girlkun.models.map.ItemMap;
import com.girlkun.models.player.Player;
import com.girlkun.models.skill.Skill;
import com.girlkun.server.Manager;
import com.girlkun.services.PetService;
import com.girlkun.services.Service;
import com.girlkun.utils.Util;
import java.util.Random;

/**
 *
 * @Stole By Hoàng Việt
 */
public class Kingcold extends Boss {

    public Kingcold() throws Exception {
        super(BossID.VUA_COLD, BossesData.VUA_COLD);
    }

      @Override
    public void reward(Player plKill) {
        plKill.inventory.event++;
        Service.getInstance().sendThongBao(plKill, "Bạn đã nhận được 1 điểm săn Boss");

        byte randomDo = (byte) new Random().nextInt(Manager.itemIds_TL.length);
        byte randomDo1 = (byte) new Random().nextInt(Manager.itemIds_CUI.length);
        byte randomNR = (byte) new Random().nextInt(Manager.itemIds_NR_SB.length);
        
        if ((Util.isTrue(1, 12))) { // ti le rot do tl la 2/10

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

        } else if ((Util.isTrue(4, 7))) { // ti le rot do cui la 5/7
           // ItemMap it1 = new ItemMap(this.zone, 20, 1, this.location.x, this.zone.map.yPhysicInTop(this.location.x, this.location.y - 24), plKill.id);
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
            //ItemMap it1 = new ItemMap(this.zone, 20, 1, this.location.x, this.zone.map.yPhysicInTop(this.location.x, this.location.y - 24), plKill.id);
            ItemMap it1 = Util.ratiItemC(this.zone, Manager.itemIds_NR_SB[randomNR], 1, this.location.x, this.zone.map.yPhysicInTop(this.location.x, this.location.y - 24), plKill.id);
            if (Util.isTrue(1, 1)) {
                it1.options.add(new Item.ItemOption(86, 1));
                Service.getInstance().sendThongBao(plKill, "|1|Bạn đã nhận " + it1.itemTemplate.name + " Ký gửi vàng");
            }
            Service.getInstance().dropItemMap(this.zone, it1);
        }
    }
}
