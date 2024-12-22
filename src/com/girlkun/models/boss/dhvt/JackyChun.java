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
public class JackyChun extends BossDHVT {

    public JackyChun(Player player) throws Exception {
        super(BossID.JACKY_CHUN, BossesData.JACKY_CHUN);
        this.playerAtt = player;
    }
}