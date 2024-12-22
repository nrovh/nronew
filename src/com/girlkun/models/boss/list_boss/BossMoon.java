package com.girlkun.models.boss.list_boss;
import com.girlkun.models.boss.Boss;
import com.girlkun.models.boss.BossID;
import com.girlkun.models.boss.BossStatus;
import com.girlkun.models.boss.BossesData;
import com.girlkun.models.item.Item;
import com.girlkun.models.map.ItemMap;
import com.girlkun.models.player.Player;
import com.girlkun.models.skill.Skill;
import com.girlkun.server.Manager;
import com.girlkun.services.EffectSkillService;
import com.girlkun.services.Service;
import com.girlkun.services.TaskService;
import com.girlkun.utils.Util;
import java.util.Random;


public class BossMoon extends Boss {

    public BossMoon() throws Exception {
        super(BossID.BOSS_BOSSMOON, BossesData.BOSSMOON, BossesData.BOSSMOON1);
    }
    @Override
    public void reward(Player plKill) {
        plKill.inventory.event++;
        Service.getInstance().sendThongBao(plKill, "Bạn đã nhận được 1 điểm săn Boss");
      //  byte randomDo = (byte) new Random().nextInt(Manager.itemIds_TL.length - 1);
        if (Util.isTrue(7, 10)) {
            int a = 20;
            for (int i = 0; i < 2; i++) {
                ItemMap it1 = new ItemMap(this.zone, 2018, 10, this.location.x + a, this.zone.map.yPhysicInTop(this.location.x,this.location.y - 24), plKill.id);
                Service.getInstance().dropItemMap(this.zone, it1);
                a += 10;
            }
        } else if (Util.isTrue(7, 10)){
            int a=10;
            int manh=Util.nextInt(1066,1069);
            for (int i = 0; i < 3; i++) {
                ItemMap it1 = new ItemMap(this.zone, manh , 1, this.location.x + a, this.zone.map.yPhysicInTop(this.location.x,this.location.y - 24), plKill.id);
                Service.getInstance().dropItemMap(this.zone, it1);
                a += 10;
            }
        }else if (Util.isTrue(1, 10)){
           //int rddo=Util.nextInt(1066,1069);
            ItemMap it1 = new ItemMap(this.zone, 12 , 1, this.location.x , this.zone.map.yPhysicInTop(this.location.x,this.location.y - 24), plKill.id);
            // cm
            it1.options.add(new Item.ItemOption(14, Util.nextInt(1,3)));
            // skh
            int skh =Util.nextInt(127,135);
            it1.options.add(new Item.ItemOption(skh,1 ));
            // kkrot
            if(skh==133){
            it1.options.add(new Item.ItemOption(136,1 ));
            }
            // calic
            if(skh==134){
            it1.options.add(new Item.ItemOption(137,1 ));
            }
            // nappa
            if(skh==135){
            it1.options.add(new Item.ItemOption(138,1 ));
            }
            // kaioken
            if(skh==127){
            it1.options.add(new Item.ItemOption(139,1 ));
            }
            //krilin
            if(skh==128){
            it1.options.add(new Item.ItemOption(140,1 ));
            }
            //songoku
            if(skh==129){
            it1.options.add(new Item.ItemOption(141,1 ));
            }
            //pocolo
            if(skh==130){
            it1.options.add(new Item.ItemOption(142,1 ));
            }
            //octiu
            if(skh==131){
            it1.options.add(new Item.ItemOption(143,1 ));
            }
            //daimaku
            if(skh==132){
            it1.options.add(new Item.ItemOption(144,1 ));
            }
            it1.options.add(new Item.ItemOption(30, 1));         
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
