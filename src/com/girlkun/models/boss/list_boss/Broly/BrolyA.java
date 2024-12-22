package com.girlkun.models.boss.list_boss.Broly;

import com.girlkun.consts.ConstTask;
import com.girlkun.models.boss.Boss;
import com.girlkun.models.boss.BossID;
import com.girlkun.models.boss.BossManager;
import com.girlkun.models.boss.BossStatus;
import com.girlkun.models.boss.BossesData;
import com.girlkun.models.boss.list_boss.cell.SieuBoHung;
import com.girlkun.models.item.Item;
import com.girlkun.models.map.ItemMap;
import com.girlkun.models.player.Player;
import com.girlkun.models.skill.Skill;
import com.girlkun.server.Manager;
import com.girlkun.services.EffectSkillService;
import com.girlkun.services.PlayerService;
import com.girlkun.services.Service;
import com.girlkun.services.TaskService;
import com.girlkun.utils.SkillUtil;
import com.girlkun.utils.Util;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;


public class BrolyA extends Boss {

    private long lastTimeHP;
    private int timeHP;
    public BrolyA() throws Exception {
        super(BossID.BROLY , BossesData.BROLYA, BossesData.BROLSP1);
    }
    @Override
    public void reward(Player plKill) {
        ItemMap it = new ItemMap(this.zone, 568, 1, this.location.x, this.zone.map.yPhysicInTop(this.location.x,
                this.location.y - 24), plKill.id);
        Service.getInstance().dropItemMap(this.zone, it);
        
         int bl =Util.nextInt(1395,1399);
        ItemMap it1 = new ItemMap(this.zone, bl, 1, this.location.x+30, this.zone.map.yPhysicInTop(this.location.x+30,this.location.y - 24), plKill.id);
        
        if(bl==1395){ // áo 
            it1.options.add(new Item.ItemOption(47, Util.nextInt(250,500)));
        }
        if(bl==1396){ // quần 
            it1.options.add(new Item.ItemOption(6, Util.nextInt(18000,32000)));
        }
        if(bl==1397){ // găn
            it1.options.add(new Item.ItemOption(0, Util.nextInt(1000,3000)));
        }
        if(bl==1398){ // giầy
            it1.options.add(new Item.ItemOption(7, Util.nextInt(18000,32000)));
        }
        if(bl==1399){
            it1.options.add(new Item.ItemOption(14, Util.nextInt(10,13)));
        }
        
        Service.getInstance().dropItemMap(this.zone, it1);
    }
    @Override
    public void active() {
        super.active(); //To change body of generated methods, choose Tools | Templates.
        if (Util.canDoWithTime(st, 900000)) {
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
            if (!piercing && Util.isTrue(this.nPoint.tlNeDon, 1000)) {
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


























