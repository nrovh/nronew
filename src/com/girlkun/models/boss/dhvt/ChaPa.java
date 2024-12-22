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
public class ChaPa extends BossDHVT {

    public ChaPa(Player player) throws Exception {
        super(BossID.CHA_PA, BossesData.CHA_PA);
        this.playerAtt = player;
    }
}