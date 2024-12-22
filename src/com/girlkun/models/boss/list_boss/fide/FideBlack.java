package com.girlkun.models.boss.list_boss.fide;

import com.girlkun.models.boss.Boss;
import com.girlkun.models.boss.BossID;
import com.girlkun.models.boss.BossManager;
import com.girlkun.models.boss.BossStatus;
import com.girlkun.models.boss.BossesData;
import com.girlkun.models.item.Item;
import com.girlkun.models.map.ItemMap;
import com.girlkun.models.player.Player;
import com.girlkun.server.Manager;
import com.girlkun.services.Service;
import com.girlkun.services.TaskService;
import com.girlkun.utils.Util;
import java.util.Random;

public class FideBlack extends Boss {

    public FideBlack() throws Exception {
        super(BossID.FIDEBLACK, BossesData.FIDE_BLACK);
    }

    @Override
    public void reward(Player plKill) {
          plKill.inventory.event+=10;
        Service.getInstance().sendThongBao(plKill, "Bạn đã nhận được 10 điểm săn Boss");
        byte randomDo = (byte) new Random().nextInt(Manager.itemIds_TL.length );
        byte randomNR = (byte) new Random().nextInt(Manager.itemIds_NR_SB.length);
        
        // ct v2
        ItemMap itnew = new ItemMap(this.zone, 1363, 2, this.location.x-50, this.location.y, -1);
        Service.getInstance().dropItemMap(this.zone, itnew);
        
        if (Util.isTrue(6, 7)) {
            int b = 0;
            for (int i = 0; i < 10; i++) {
                ItemMap it1 = new ItemMap(this.zone, 722, 1, this.location.x + b, this.zone.map.yPhysicInTop(this.location.x, this.location.y - 24), plKill.id);
                Service.getInstance().dropItemMap(this.zone, it1);
                b += 10;
            }
        }
        
        // đồ thần 
        
            ItemMap it4 = Util.ratiItem(this.zone, Manager.itemIds_TL[randomDo], 1, this.location.x, this.zone.map.yPhysicInTop(this.location.x, this.location.y - 27), plKill.id);

            Random splrd1 = new Random();
            int rdspl1 = splrd1.nextInt(7) + 1;
            if (Util.isTrue(5, 10)) {
                it4.options.add(new Item.ItemOption(107, rdspl1));
            }
            if (Util.isTrue(5, 10)) {
                it4.options.add(new Item.ItemOption(87, 1));
                Service.getInstance().sendThongBao(plKill, "|1|Bạn đã nhận " + it4.itemTemplate.name + " Ký gửi ngọc");
            }
            Service.getInstance().dropItemMap(this.zone, it4);
        
        // đồ thần 
        if (Util.isTrue(4, 5)) {
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
        }
         // ngọc rồng 
            int c = 0;
            for (int i = 0; i < 3; i++) {
            ItemMap it1 = Util.ratiItemC(this.zone, Manager.itemIds_NR_SB[randomNR], 1, this.location.x+c, this.zone.map.yPhysicInTop(this.location.x, this.location.y - 24), plKill.id);
            if (Util.isTrue(1, 1)) {
                it1.options.add(new Item.ItemOption(86, 1));
                Service.getInstance().sendThongBao(plKill, "|1|Bạn đã nhận " + it1.itemTemplate.name + " Ký gửi vàng");
            }
            Service.getInstance().dropItemMap(this.zone, it1);
            c+=20;
            }
       
    
    }

    @Override
    public void active() {
        super.active(); //To change body of generated methods, choose Tools | Templates.
        if (Util.canDoWithTime(st, 1500000)) {
            this.changeStatus(BossStatus.LEAVE_MAP);
        }
    }

    @Override
    public void joinMap() {
        super.joinMap(); //To change body of generated methods, choose Tools | Templates.
        st = System.currentTimeMillis();
    }
    private long st;

}
