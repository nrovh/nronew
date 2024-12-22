package com.girlkun.models.boss.list_boss.gas;

import com.girlkun.consts.ConstPlayer;
import com.girlkun.models.boss.*;
import static com.girlkun.models.boss.BossStatus.ACTIVE;
import static com.girlkun.models.boss.BossStatus.JOIN_MAP;
import static com.girlkun.models.boss.BossStatus.RESPAWN;
import com.girlkun.models.boss.list_boss.cell.SieuBoHung;
import com.girlkun.models.item.Item;
import com.girlkun.models.map.ItemMap;
import com.girlkun.models.map.Zone;
import com.girlkun.models.map.challenge.MartialCongressService;
import com.girlkun.models.player.Player;
import com.girlkun.models.skill.Skill;
import com.girlkun.services.EffectSkillService;
import com.girlkun.services.PlayerService;
import com.girlkun.services.Service;
import com.girlkun.server.Maintenance;
import com.girlkun.server.Manager;
import com.girlkun.services.SkillService;
import com.girlkun.utils.SkillUtil;
import com.girlkun.utils.Util;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author BTH sieu cap vippr0
 */
public class DrLyChee extends Boss {

    private static final int[][] FULL_DEMON = new int[][]{{Skill.DEMON, 1}, {Skill.DEMON, 2}, {Skill.DEMON, 3}, {Skill.DEMON, 4}, {Skill.DEMON, 5}, {Skill.DEMON, 6}, {Skill.DEMON, 7}};
    private long lastTimeHapThu;
    private int timeHapThu;
    private long lastUpdate = System.currentTimeMillis();
    private long timeJoinMap;
    private int levell;
    private int initSuper = 0;
    protected Player playerAtt;
    private int timeLive = 200000000;

    public DrLyChee(Zone zone, int level, int dame, double hp, int id) throws Exception {
        super(id, new BossData(
                "Drabura Frost", //name
                ConstPlayer.TRAI_DAT, //gender
                new short[]{1309, 1310, 1311, -1, -1, -1}, //outfit {head, body, leg, bag, aura, eff}
                ((1000 * level)), //dame
                new double[]{((275000000L * level))}, //hp
                new int[]{148}, //map join
                new int[][]{
                    {Skill.DEMON, 3, 1}, {Skill.DEMON, 6, 2}, {Skill.DRAGON, 7, 3}, {Skill.DRAGON, 1, 4}, {Skill.GALICK, 5, 5},
                    {Skill.KAMEJOKO, 7, 6}, {Skill.KAMEJOKO, 6, 7}, {Skill.KAMEJOKO, 5, 8}, {Skill.KAMEJOKO, 4, 9}, {Skill.KAMEJOKO, 3, 10}, {Skill.KAMEJOKO, 2, 11}, {Skill.KAMEJOKO, 1, 12},
                    {Skill.ANTOMIC, 1, 13}, {Skill.ANTOMIC, 2, 14}, {Skill.ANTOMIC, 3, 15}, {Skill.ANTOMIC, 4, 16}, {Skill.ANTOMIC, 5, 17}, {Skill.ANTOMIC, 6, 19}, {Skill.ANTOMIC, 7, 20},
                    {Skill.MASENKO, 1, 21}, {Skill.MASENKO, 5, 22}, {Skill.MASENKO, 6, 23},
                    {Skill.KAMEJOKO, 7, 1000},},
                new String[]{}, //text chat 1
                new String[]{"|-1|Nhóc con"}, //text chat 2
                new String[]{}, //text chat 3
                60
        ));

        this.zone = zone;
        this.levell = level;
    }

    @Override
    public void reward(Player plKill) {

        if (levell == 1) {
            ItemMap it = new ItemMap(this.zone, 1201, 1, this.location.x, this.zone.map.yPhysicInTop(this.location.x, this.location.y - 24), plKill.id);
            Service.getInstance().dropItemMap(this.zone, it);
        }else if(levell<=5){
            ItemMap it = new ItemMap(this.zone, 1201, 2, this.location.x, this.zone.map.yPhysicInTop(this.location.x, this.location.y - 24), plKill.id);
            Service.getInstance().dropItemMap(this.zone, it);
        } else if (levell <= 10) {
            ItemMap it = new ItemMap(this.zone, 1201, 3, this.location.x, this.zone.map.yPhysicInTop(this.location.x, this.location.y - 24), plKill.id);
            Service.getInstance().dropItemMap(this.zone, it);
             // cải trang 
            //ItemMap ct = new ItemMap(this.zone, 1206, 1, this.location.x - 10, this.zone.map.yPhysicInTop(this.location.x, this.location.y - 24), plKill.id);
            ItemMap ct = Util.ratiItem(this.zone, 1206, 1, this.location.x, this.zone.map.yPhysicInTop(this.location.x, this.location.y - 27), plKill.id);
            // option cho cải trang 
             // sd
            ct.options.add(new Item.ItemOption(50, Util.nextInt(10, 20)));
            //hp
            ct.options.add(new Item.ItemOption(77, Util.nextInt(10, 25)));
            //ki
            ct.options.add(new Item.ItemOption(103, Util.nextInt(10, 25)));
            // giáp 
            ct.options.add(new Item.ItemOption(94, Util.nextInt(5, 15)));
            // chống lạnh 
            ct.options.add(new Item.ItemOption(106, 1));
            // hsd
            ct.options.add(new Item.ItemOption(93, Util.nextInt(3, 30)));
            Service.getInstance().dropItemMap(this.zone, ct);
            
        } else if (levell <= 20) {
            ItemMap it = new ItemMap(this.zone, 1201, 5, this.location.x, this.zone.map.yPhysicInTop(this.location.x, this.location.y - 24), plKill.id);
            Service.getInstance().dropItemMap(this.zone, it);
            
             // cải trang 
            //ItemMap ct = new ItemMap(this.zone, 1206, 1, this.location.x - 10, this.zone.map.yPhysicInTop(this.location.x, this.location.y - 24), plKill.id);
            ItemMap ct = Util.ratiItem(this.zone, 1206, 1, this.location.x, this.zone.map.yPhysicInTop(this.location.x, this.location.y - 27), plKill.id);
            // option cho cải trang 
            // sd
            ct.options.add(new Item.ItemOption(50, Util.nextInt(15, 23)));
            //hp
            ct.options.add(new Item.ItemOption(77, Util.nextInt(15, 30)));
            //ki
            ct.options.add(new Item.ItemOption(103, Util.nextInt(15, 30)));
            // giáp 
            ct.options.add(new Item.ItemOption(94, Util.nextInt(9, 18)));
            // chống lạnh 
            ct.options.add(new Item.ItemOption(106, 1));
            // hsd
            ct.options.add(new Item.ItemOption(93, Util.nextInt(3, 30)));
            Service.getInstance().dropItemMap(this.zone, ct);
            
            if ((Util.isTrue(3, 8))) {
                byte randomNR = (byte) new Random().nextInt(Manager.itemIds_NR_SB.length);
                //ItemMap it1 = new ItemMap(this.zone, Manager.itemIds_NR[randomNR], 1, this.location.x, this.zone.map.yPhysicInTop(this.location.x, this.location.y - 24), plKill.id);
                ItemMap it1 = Util.ratiItemC(this.zone, Manager.itemIds_NR_SB[randomNR], 1, this.location.x, this.zone.map.yPhysicInTop(this.location.x, this.location.y - 24), plKill.id);
                if (Util.isTrue(1, 1)) {
                    it1.options.add(new Item.ItemOption(86, 1));
                    Service.getInstance().sendThongBao(plKill, "|1|Bạn đã nhận " + it1.itemTemplate.name + " Ký gửi vàng");
                }
                Service.getInstance().dropItemMap(this.zone, it1);
            }
        } else if (levell <= 40) {
            ItemMap it = new ItemMap(this.zone, 1201, 6, this.location.x, this.zone.map.yPhysicInTop(this.location.x, this.location.y - 24), plKill.id);
            Service.getInstance().dropItemMap(this.zone, it);
            
            // cải trang 
           // ItemMap ct = new ItemMap(this.zone, 1206, 1, this.location.x - 10, this.zone.map.yPhysicInTop(this.location.x, this.location.y - 24), plKill.id);
            ItemMap ct = Util.ratiItem(this.zone, 1206, 1, this.location.x, this.zone.map.yPhysicInTop(this.location.x, this.location.y - 27), plKill.id);
            // option cho cải trang 
           // sd
            ct.options.add(new Item.ItemOption(50, Util.nextInt(15, 26)));
            //hp
            ct.options.add(new Item.ItemOption(77, Util.nextInt(20, 35)));
            //ki
            ct.options.add(new Item.ItemOption(103, Util.nextInt(20, 35)));
            // giáp 
            ct.options.add(new Item.ItemOption(94, Util.nextInt(10, 20)));
            // chống lạnh 
            ct.options.add(new Item.ItemOption(106, 1));
            // hsd
            ct.options.add(new Item.ItemOption(93, Util.nextInt(3, 30)));
            Service.getInstance().dropItemMap(this.zone, ct);
            
            if ((Util.isTrue(4, 8))) {
                byte randomNR = (byte) new Random().nextInt(Manager.itemIds_NR_SB.length);
                //ItemMap it1 = new ItemMap(this.zone, Manager.itemIds_NR[randomNR], 1, this.location.x, this.zone.map.yPhysicInTop(this.location.x, this.location.y - 24), plKill.id);
               ItemMap it1 = Util.ratiItemC(this.zone, Manager.itemIds_NR_SB[randomNR], 1, this.location.x, this.zone.map.yPhysicInTop(this.location.x, this.location.y - 24), plKill.id);
                if (Util.isTrue(1, 1)) {
                    it1.options.add(new Item.ItemOption(86, 1));
                    Service.getInstance().sendThongBao(plKill, "|1|Bạn đã nhận " + it1.itemTemplate.name + " Ký gửi vàng");
                }
                Service.getInstance().dropItemMap(this.zone, it1);
            }
        } else if (levell <= 80) {
            if(Util.isTrue(1, 5)){
            ItemMap itnew = new ItemMap(this.zone, 702, 1, this.location.x, this.location.y, -1);
            Service.getInstance().dropItemMap(this.zone, itnew);
            }
            // đá 
            ItemMap it = new ItemMap(this.zone, 1201, 7, this.location.x, this.zone.map.yPhysicInTop(this.location.x, this.location.y - 24), plKill.id);
            Service.getInstance().dropItemMap(this.zone, it);
             // cải trang 
           // ItemMap ct = new ItemMap(this.zone, 1206, 1, this.location.x - 10, this.zone.map.yPhysicInTop(this.location.x, this.location.y - 24), plKill.id);
            ItemMap ct = Util.ratiItem(this.zone, 1206, 1, this.location.x-20, this.zone.map.yPhysicInTop(this.location.x, this.location.y - 27), plKill.id);
            // option cho cải trang 
            // sd
            ct.options.add(new Item.ItemOption(50, Util.nextInt(22, 29)));
            //hp
            ct.options.add(new Item.ItemOption(77, Util.nextInt(23, 38)));
            //ki
            ct.options.add(new Item.ItemOption(103, Util.nextInt(23, 38)));
            // giáp 
            ct.options.add(new Item.ItemOption(94, Util.nextInt(13, 25)));
            // chống lạnh 
            ct.options.add(new Item.ItemOption(106, 1));
            // hsd
            ct.options.add(new Item.ItemOption(93, Util.nextInt(3, 30)));
            Service.getInstance().dropItemMap(this.zone, ct);

            if ((Util.isTrue(5, 8))) {
                byte randomNR = (byte) new Random().nextInt(Manager.itemIds_NR_SB.length);
                //ItemMap it1 = new ItemMap(this.zone, Manager.itemIds_NR[randomNR], 1, this.location.x, this.zone.map.yPhysicInTop(this.location.x, this.location.y - 24), plKill.id);
                ItemMap it1 = Util.ratiItemC(this.zone, Manager.itemIds_NR_SB[randomNR], 1, this.location.x, this.zone.map.yPhysicInTop(this.location.x, this.location.y - 24), plKill.id);
                if (Util.isTrue(1, 1)) {
                    it1.options.add(new Item.ItemOption(86, 1));
                    Service.getInstance().sendThongBao(plKill, "|1|Bạn đã nhận " + it1.itemTemplate.name + " Ký gửi vàng");
                }
                Service.getInstance().dropItemMap(this.zone, it1);
            }

        } else if (levell <= 100) {
            ItemMap it = new ItemMap(this.zone, 1201, 8, this.location.x, this.zone.map.yPhysicInTop(this.location.x, this.location.y - 24), plKill.id);
            Service.getInstance().dropItemMap(this.zone, it);
            if(Util.isTrue(2, 5)){
            ItemMap itnew = new ItemMap(this.zone, 702, 1, this.location.x, this.location.y, -1);
            Service.getInstance().dropItemMap(this.zone, itnew);
            }
            // cải trang 
           // ItemMap ct = new ItemMap(this.zone, 1206, 1, this.location.x - 10, this.zone.map.yPhysicInTop(this.location.x, this.location.y - 24), plKill.id);
           ItemMap ct = Util.ratiItem(this.zone, 1206, 1, this.location.x-40, this.zone.map.yPhysicInTop(this.location.x, this.location.y - 27), plKill.id);
            // option cho cải trang 
            // sd
            ct.options.add(new Item.ItemOption(50, Util.nextInt(25, 32)));
            //hp
            ct.options.add(new Item.ItemOption(77, Util.nextInt(26, 42)));
            //ki
            ct.options.add(new Item.ItemOption(103, Util.nextInt(26, 42)));
            // giáp 
            ct.options.add(new Item.ItemOption(94, Util.nextInt(15, 30)));
            // chống lạnh 
            ct.options.add(new Item.ItemOption(106, 1));
            // hsd
            ct.options.add(new Item.ItemOption(93, Util.nextInt(3, 30)));
            Service.getInstance().dropItemMap(this.zone, ct);

            if ((Util.isTrue(6, 8))) {
                byte randomNR = (byte) new Random().nextInt(Manager.itemIds_NR_SB.length);
                //ItemMap it1 = new ItemMap(this.zone, Manager.itemIds_NR[randomNR], 1, this.location.x, this.zone.map.yPhysicInTop(this.location.x, this.location.y - 24), plKill.id);
                ItemMap it1 = Util.ratiItemC(this.zone, Manager.itemIds_NR_SB[randomNR], 1, this.location.x, this.zone.map.yPhysicInTop(this.location.x, this.location.y - 24), plKill.id);
                if (Util.isTrue(1, 1)) {
                    it1.options.add(new Item.ItemOption(86, 1));
                    Service.getInstance().sendThongBao(plKill, "|1|Bạn đã nhận " + it1.itemTemplate.name + " Ký gửi vàng");
                }
                Service.getInstance().dropItemMap(this.zone, it1);
            }
        } else if (levell < 110) {
            ItemMap it = new ItemMap(this.zone, 1201, 9, this.location.x, this.zone.map.yPhysicInTop(this.location.x, this.location.y - 24), plKill.id);
            Service.getInstance().dropItemMap(this.zone, it);
             if(Util.isTrue(3, 5)){
            ItemMap itnew = new ItemMap(this.zone, 702, 1, this.location.x, this.location.y, -1);
            Service.getInstance().dropItemMap(this.zone, itnew);
            }
            // cải trang 
           // ItemMap ct = new ItemMap(this.zone, 1206, 1, this.location.x - 10, this.zone.map.yPhysicInTop(this.location.x, this.location.y - 24), plKill.id);
           ItemMap ct = Util.ratiItem(this.zone, 1206, 1, this.location.x, this.zone.map.yPhysicInTop(this.location.x-40, this.location.y - 27), plKill.id);
            // option cho cải trang 
            // sd
            ct.options.add(new Item.ItemOption(50, Util.nextInt(28, 35)));
            //hp
            ct.options.add(new Item.ItemOption(77, Util.nextInt(29, 45)));
            //ki
            ct.options.add(new Item.ItemOption(103, Util.nextInt(29, 45)));
            // giáp 
            ct.options.add(new Item.ItemOption(94, Util.nextInt(20, 32)));
            // chống lạnh 
            ct.options.add(new Item.ItemOption(106, 1));
            // hsd
            ct.options.add(new Item.ItemOption(93, Util.nextInt(3, 30)));
            Service.getInstance().dropItemMap(this.zone, ct);

            if ((Util.isTrue(7, 8))) {
                byte randomNR = (byte) new Random().nextInt(Manager.itemIds_NR_SB.length);
               // ItemMap it1 = new ItemMap(this.zone, Manager.itemIds_NR[randomNR], 1, this.location.x, this.zone.map.yPhysicInTop(this.location.x, this.location.y - 24), plKill.id);
                ItemMap it1 = Util.ratiItemC(this.zone, Manager.itemIds_NR_SB[randomNR], 1, this.location.x, this.zone.map.yPhysicInTop(this.location.x, this.location.y - 24), plKill.id);
                if (Util.isTrue(1, 1)) {
                    it1.options.add(new Item.ItemOption(86, 1));
                    Service.getInstance().sendThongBao(plKill, "|1|Bạn đã nhận " + it1.itemTemplate.name + " Ký gửi vàng");
                }
                Service.getInstance().dropItemMap(this.zone, it1);
            }
        } else if (levell == 110) {
            ItemMap it = new ItemMap(this.zone, 1201, 10, this.location.x, this.zone.map.yPhysicInTop(this.location.x, this.location.y - 24), plKill.id);
            Service.getInstance().dropItemMap(this.zone, it);
            
            ItemMap itnew = new ItemMap(this.zone, 702, 1, this.location.x+40, this.location.y, -1);
            Service.getInstance().dropItemMap(this.zone, itnew);
            
            // cải trang 
            //ItemMap ct = new ItemMap(this.zone, 1206, 1, this.location.x - 10, this.zone.map.yPhysicInTop(this.location.x, this.location.y - 24), plKill.id);
            ItemMap ct = Util.ratiItem(this.zone, 1206, 1, this.location.x-40, this.zone.map.yPhysicInTop(this.location.x, this.location.y - 27), plKill.id);
            // option cho cải trang 
            // sd
            ct.options.add(new Item.ItemOption(50, Util.nextInt(32, 45)));
            //hp
            ct.options.add(new Item.ItemOption(77, Util.nextInt(35, 50)));
            //ki
            ct.options.add(new Item.ItemOption(103, Util.nextInt(35, 50)));
            // giáp 
            ct.options.add(new Item.ItemOption(94, Util.nextInt(25, 35)));
            // chống lạnh 
            ct.options.add(new Item.ItemOption(106, 1));
            // hsd
            ct.options.add(new Item.ItemOption(93, Util.nextInt(3, 30)));
            Service.getInstance().dropItemMap(this.zone, ct);
        } else if (levell < 120) {
            ItemMap it = new ItemMap(this.zone, 1201, 10, this.location.x, this.zone.map.yPhysicInTop(this.location.x, this.location.y - 24), plKill.id);
            Service.getInstance().dropItemMap(this.zone, it);
            
            ItemMap itnew = new ItemMap(this.zone, 702, 1, this.location.x+40, this.location.y, -1);
            Service.getInstance().dropItemMap(this.zone, itnew);
            
            // cải trang 
            //ItemMap ct = new ItemMap(this.zone, 1206, 1, this.location.x - 10, this.zone.map.yPhysicInTop(this.location.x, this.location.y - 24), plKill.id);
            ItemMap ct = Util.ratiItem(this.zone, 1206, 1, this.location.x-40, this.zone.map.yPhysicInTop(this.location.x, this.location.y - 27), plKill.id);
            // option cho cải trang 
            // sd
            ct.options.add(new Item.ItemOption(50, Util.nextInt(32, 50)));
            //hp
            ct.options.add(new Item.ItemOption(77, Util.nextInt(35, 60)));
            //ki
            ct.options.add(new Item.ItemOption(103, Util.nextInt(35, 60)));
            // giáp 
            ct.options.add(new Item.ItemOption(94, Util.nextInt(25, 35)));
            // chống lạnh 
            ct.options.add(new Item.ItemOption(106, 1));
            // hsd
            ct.options.add(new Item.ItemOption(93, Util.nextInt(3, 30)));
            Service.getInstance().dropItemMap(this.zone, ct);
        } else if (levell < 130) {
            ItemMap it = new ItemMap(this.zone, 1201, 10, this.location.x, this.zone.map.yPhysicInTop(this.location.x, this.location.y - 24), plKill.id);
            Service.getInstance().dropItemMap(this.zone, it);
            
            ItemMap itnew = new ItemMap(this.zone, 702, 1, this.location.x+40, this.location.y, -1);
            Service.getInstance().dropItemMap(this.zone, itnew);
            
            // cải trang 
            //ItemMap ct = new ItemMap(this.zone, 1206, 1, this.location.x - 10, this.zone.map.yPhysicInTop(this.location.x, this.location.y - 24), plKill.id);
            ItemMap ct = Util.ratiItem(this.zone, 1206, 1, this.location.x-40, this.zone.map.yPhysicInTop(this.location.x, this.location.y - 27), plKill.id);
            // option cho cải trang 
            // sd
            ct.options.add(new Item.ItemOption(50, Util.nextInt(32, 55)));
            //hp
            ct.options.add(new Item.ItemOption(77, Util.nextInt(35, 65)));
            //ki
            ct.options.add(new Item.ItemOption(103, Util.nextInt(35, 65)));
            // giáp 
            ct.options.add(new Item.ItemOption(94, Util.nextInt(25, 35)));
            // chống lạnh 
            ct.options.add(new Item.ItemOption(106, 1));
            // hsd
            ct.options.add(new Item.ItemOption(93, Util.nextInt(3, 30)));
            Service.getInstance().dropItemMap(this.zone, ct);
        } else if (levell < 140) {
            ItemMap it = new ItemMap(this.zone, 1201, 10, this.location.x, this.zone.map.yPhysicInTop(this.location.x, this.location.y - 24), plKill.id);
            Service.getInstance().dropItemMap(this.zone, it);
            
            ItemMap itnew = new ItemMap(this.zone, 702, 1, this.location.x+40, this.location.y, -1);
            Service.getInstance().dropItemMap(this.zone, itnew);
            
            // cải trang 
            //ItemMap ct = new ItemMap(this.zone, 1206, 1, this.location.x - 10, this.zone.map.yPhysicInTop(this.location.x, this.location.y - 24), plKill.id);
            ItemMap ct = Util.ratiItem(this.zone, 1206, 1, this.location.x-40, this.zone.map.yPhysicInTop(this.location.x, this.location.y - 27), plKill.id);
            // option cho cải trang 
            // sd
            ct.options.add(new Item.ItemOption(50, Util.nextInt(40, 55)));
            //hp
            ct.options.add(new Item.ItemOption(77, Util.nextInt(45, 65)));
            //ki
            ct.options.add(new Item.ItemOption(103, Util.nextInt(45, 65)));
            // giáp 
            ct.options.add(new Item.ItemOption(94, Util.nextInt(25, 45)));
            // chống lạnh 
            ct.options.add(new Item.ItemOption(106, 1));
            // hsd
            ct.options.add(new Item.ItemOption(93, Util.nextInt(3, 30)));
            Service.getInstance().dropItemMap(this.zone, ct);
            
             // cải trang 
            //ItemMap ct = new ItemMap(this.zone, 1206, 1, this.location.x - 10, this.zone.map.yPhysicInTop(this.location.x, this.location.y - 24), plKill.id);
            ItemMap ct1 = Util.ratiItem(this.zone, 1206, 1, this.location.x-80, this.zone.map.yPhysicInTop(this.location.x, this.location.y - 27), plKill.id);
            // option cho cải trang 
            // sd
            ct1.options.add(new Item.ItemOption(50, Util.nextInt(40, 55)));
            //hp
            ct1.options.add(new Item.ItemOption(77, Util.nextInt(45, 65)));
            //ki
            ct1.options.add(new Item.ItemOption(103, Util.nextInt(45, 65)));
            // giáp 
            ct1.options.add(new Item.ItemOption(94, Util.nextInt(25, 45)));
            // chống lạnh 
            ct1.options.add(new Item.ItemOption(106, 1));
            // hsd
            ct1.options.add(new Item.ItemOption(93, Util.nextInt(3, 30)));
            Service.getInstance().dropItemMap(this.zone, ct1);
        } else if (levell <= 150) {
            ItemMap it = new ItemMap(this.zone, 1201, 10, this.location.x, this.zone.map.yPhysicInTop(this.location.x, this.location.y - 24), plKill.id);
            Service.getInstance().dropItemMap(this.zone, it);
            
            ItemMap itnew = new ItemMap(this.zone, 702, 1, this.location.x+40, this.location.y, -1);
            Service.getInstance().dropItemMap(this.zone, itnew);
            
            // cải trang 
            //ItemMap ct = new ItemMap(this.zone, 1206, 1, this.location.x - 10, this.zone.map.yPhysicInTop(this.location.x, this.location.y - 24), plKill.id);
            ItemMap ct = Util.ratiItem(this.zone, 1206, 1, this.location.x-40, this.zone.map.yPhysicInTop(this.location.x, this.location.y - 27), plKill.id);
            // option cho cải trang 
            // sd
            ct.options.add(new Item.ItemOption(50, Util.nextInt(40, 55)));
            //hp
            ct.options.add(new Item.ItemOption(77, Util.nextInt(45, 65)));
            //ki
            ct.options.add(new Item.ItemOption(103, Util.nextInt(45, 65)));
            // giáp 
            ct.options.add(new Item.ItemOption(94, Util.nextInt(25, 45)));
            // chống lạnh 
            ct.options.add(new Item.ItemOption(106, 1));
            // hsd
            ct.options.add(new Item.ItemOption(93, Util.nextInt(3, 30)));
            Service.getInstance().dropItemMap(this.zone, ct);
            
             // cải trang 
            //ItemMap ct = new ItemMap(this.zone, 1206, 1, this.location.x - 10, this.zone.map.yPhysicInTop(this.location.x, this.location.y - 24), plKill.id);
            ItemMap ct1 = Util.ratiItem(this.zone, 1206, 1, this.location.x-80, this.zone.map.yPhysicInTop(this.location.x, this.location.y - 27), plKill.id);
            // option cho cải trang 
            // sd
            ct1.options.add(new Item.ItemOption(50, Util.nextInt(40, 55)));
            //hp
            ct1.options.add(new Item.ItemOption(77, Util.nextInt(45, 65)));
            //ki
            ct1.options.add(new Item.ItemOption(103, Util.nextInt(45, 65)));
            // giáp 
            ct1.options.add(new Item.ItemOption(94, Util.nextInt(25, 45)));
            // chống lạnh 
            ct1.options.add(new Item.ItemOption(106, 1));
            // hsd
            ct1.options.add(new Item.ItemOption(93, Util.nextInt(3, 30)));
            Service.getInstance().dropItemMap(this.zone, ct1);
//            byte randomDo = (byte) new Random().nextInt(Manager.itemIds_TL.length - 1);
//            if ((Util.isTrue(4, 10))) { // ti le rot do tl la 4/10
//
//                //ItemMap it2 = new ItemMap(this.zone, Manager.itemIds_TL[randomDo], 1, this.location.x, this.zone.map.yPhysicInTop(this.location.x, this.location.y - 24), plKill.id);
//                ItemMap it2 = Util.ratiItem(this.zone, Manager.itemIds_TL[randomDo], 1, this.location.x, this.zone.map.yPhysicInTop(this.location.x, this.location.y - 27), plKill.id);
//
//                Random splrd = new Random();
//                int rdspl = splrd.nextInt(7) + 1;
//                if (Util.isTrue(5, 10)) {
//                    it2.options.add(new Item.ItemOption(107, rdspl));
//                }
//                if (Util.isTrue(5, 10)) {
//                    it2.options.add(new Item.ItemOption(87, 1));
//                    Service.getInstance().sendThongBao(plKill, "|1|Bạn đã nhận " + it2.itemTemplate.name + " Ký gửi ngọc");
//                }
//                Service.getInstance().dropItemMap(this.zone, it2);
//
//            }
        }
    }

    @Override
    public void active() {
        super.active();
    }

    public int injured(Player plAtt, int damage, boolean piercing, boolean isMobAttack) {
        if (!this.isDie()) {
            if (!piercing && Util.isTrue(this.nPoint.tlNeDon - plAtt.nPoint.tlchinhxac, 1000)) {
                this.chat("Xí hụt");
                return 0;
            }
            damage = (int) this.nPoint.subDameInjureWithDeff(damage / 2);
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
