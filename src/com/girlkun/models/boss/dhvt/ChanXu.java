package com.girlkun.models.boss.dhvt;

import com.girlkun.models.boss.BossData;
import com.girlkun.models.boss.BossID;
import com.girlkun.models.boss.BossesData;
import com.girlkun.models.map.ItemMap;
import com.girlkun.models.player.Player;
import com.girlkun.services.Service;
import com.girlkun.utils.Util;
/**
 * @author BTH sieu cap vippr0 
 */
public class ChanXu extends BossDHVT {

    public ChanXu(Player player) throws Exception {
        super(BossID.CHAN_XU, BossesData.CHAN_XU);
        this.playerAtt = player;
    }
    @Override
    public void reward(Player plKill) {
        if(Util.isTrue(4, 5)){
            ItemMap itnew = new ItemMap(this.zone, 702, 1, this.location.x, this.location.y, -1);
            Service.getInstance().dropItemMap(this.zone, itnew);
        }
    }
}