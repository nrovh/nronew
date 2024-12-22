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
 *
 * @author Administrator
 */
public class bossTet  extends Boss{
    
    public bossTet() throws Exception {
        super(BossID.BOSS_TET, BossesData.BOSS_TET);
    }
    
    @Override
    public void reward(Player plKill) {
        int a = 0;
        for (int i = 0; i < 10; i++) {
            ItemMap it = new ItemMap(this.zone, 861, 10, this.location.x + a, this.zone.map.yPhysicInTop(this.location.x,
                    this.location.y - 24), -1);
            Service.getInstance().dropItemMap(this.zone, it);
            a += 10;
        }
         int mhd = Util.nextInt(1327,1331);
        ItemMap itnew = new ItemMap(this.zone, mhd, 1, this.location.x-50, this.location.y, -1);
        Service.getInstance().dropItemMap(this.zone, itnew);
        
//        if(Util.isTrue(8, 10)){
//        ItemMap it = new ItemMap(this.zone, 2048, 1, this.location.x , this.zone.map.yPhysicInTop(this.location.x,this.location.y - 24), plKill.id);
//        Service.getInstance().dropItemMap(this.zone, it);
//        }
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
