package com.girlkun.models.item;

import com.girlkun.models.player.NPoint;
import com.girlkun.models.player.Player;
import com.girlkun.services.Service;
import com.girlkun.utils.Util;
import com.girlkun.services.ItemTimeService;

public class ItemTimeSieuCap {

    //id item text
    public static final int TIME_ITEM3 = 600000;
    public static final int TIME_KEO = 1800000;
    public static final int TIME_XI_MUOI = 600000;
    public static final int TIME_BTET = 1800000;
    public static final int TIME_BCHUNG = 1800000;
    public static final int TIME_TRUNGTHU = 3600000;
    public static final int TIME_EAT_MEAL = 600000;
    public static final int TIME_CBS = 600000;

    public static final int TIME_CUA = 600000;
    public static final int TIME_TOM = 600000;
    public static final int TIME_BT = 600000;
    public static final int TIME_SUP = 1800000;
    public static final int TIME_GATO = 1800000;
    public static final int TIME_HBG = 1800000;
    public static final int TIME_TMO = 600000;
    public static final int TIME_TMODB = 1800000;

    private Player player;

    public boolean isUseBoHuyet3;
    public boolean isUseBoKhi3;
    public boolean isUseGiapXen3;
    public boolean isUseCuongNo3;
    public boolean isUseAnDanh3;

    public long lastTimeBoHuyet3;
    public long lastTimeBoKhi3;
    public long lastTimeGiapXen3;
    public long lastTimeCuongNo3;
    public long lastTimeAnDanh3;

    public boolean isUseXiMuoi;
    public long lastTimeUseXiMuoi;

    public boolean isUseTrungThu;
    public long lastTimeUseBanh;
    public int iconBanh;

    public boolean isKeo;
    public long lastTimeKeo;
    
    public boolean iscbs;
    public long lastTimeiscbs;

    public boolean isTom;
    public long lastTimeTom;

    public boolean isCua;
    public long lastTimeCua;
    
    public boolean isBanhTet;
    public long lastTimeBanhTet;
    
    public boolean isBanhChung;
    public long lastTimeBanhChung;
    
    public boolean isSup;
    public long lastTimeSup;

    public boolean isGato;
    public long lastTimeGato;

    public boolean isHamburger;
    public long lastTimeHamburger;

    public boolean isThuocMo;
    public long lastTimeThuocMo;

    public boolean isThuocMoDB;
    public long lastTimeThuocMoDB;

    public boolean isBachTuot;
    public long lastTimeBachTuot;

    public boolean isEatMeal;
    public long lastTimeMeal;
    public int iconMeal;

    public ItemTimeSieuCap(Player player) {
        this.player = player;
    }

    public void update() {
        if (isEatMeal) {
            if (Util.canDoWithTime(lastTimeMeal, TIME_EAT_MEAL)) {
                isEatMeal = false;
                Service.getInstance().point(player);
            }
        }
        if (isUseBoHuyet3) {
            if (Util.canDoWithTime(lastTimeBoHuyet3, TIME_ITEM3)) {
                isUseBoHuyet3 = false;
                Service.getInstance().point(player);
//                Service.getInstance().Send_Info_NV(this.player);
            }
        }

        if (isUseBoKhi3) {
            if (Util.canDoWithTime(lastTimeBoKhi3, TIME_ITEM3)) {
                isUseBoKhi3 = false;
                Service.getInstance().point(player);
            }
        }

        if (isUseGiapXen3) {
            if (Util.canDoWithTime(lastTimeGiapXen3, TIME_ITEM3)) {
                isUseGiapXen3 = false;
            }
        }
        if (isUseCuongNo3) {
            if (Util.canDoWithTime(lastTimeCuongNo3, TIME_ITEM3)) {
                isUseCuongNo3 = false;
                Service.getInstance().point(player);
            }
        }
        if (isUseAnDanh3) {
            if (Util.canDoWithTime(lastTimeAnDanh3, TIME_ITEM3)) {
                isUseAnDanh3 = false;
            }
        }

        if (isKeo) {
            if (Util.canDoWithTime(lastTimeKeo, TIME_KEO)) {
                isKeo = false;
            }
        }
        
        if (iscbs) {
            if (Util.canDoWithTime(lastTimeiscbs, TIME_CBS)) {
                iscbs = false;
            }
        }
        
        if (isTom) {
            if (Util.canDoWithTime(lastTimeTom, TIME_TOM)) {
                isTom = false;
            }
        }
        if (isSup) {
            if (Util.canDoWithTime(lastTimeSup, TIME_SUP)) {
                isSup = false;
            }
        }
        if (isHamburger) {
            if (Util.canDoWithTime(lastTimeHamburger, TIME_HBG)) {
                isHamburger = false;
            }
        }       
        if (isGato) {
            if (Util.canDoWithTime(lastTimeGato, TIME_GATO)) {
                isGato = false;
            }
        } 
         if (isThuocMo) {
            if (Util.canDoWithTime(lastTimeThuocMo, TIME_TMO)) {
                isThuocMo = false;
            }
        } 
         if (isThuocMoDB) {
            if (Util.canDoWithTime(lastTimeThuocMoDB, TIME_TMODB)) {
                isThuocMoDB = false;
            }
        } 
        if (isBachTuot) {
            if (Util.canDoWithTime(lastTimeBachTuot, TIME_BT)) {
                isBachTuot = false;
            }
        }
        if (isCua) {
            if (Util.canDoWithTime(lastTimeCua, TIME_CUA)) {
                isCua = false;
            }
        }
        if(isBanhTet){
            if (Util.canDoWithTime(lastTimeBanhTet, TIME_BTET)) {
                isBanhTet = false;
            }
        }
        if(isBanhChung){
            if (Util.canDoWithTime(lastTimeBanhChung, TIME_BCHUNG)) {
                isBanhChung = false;
            }
        }
        if (isUseXiMuoi) {
            if (Util.canDoWithTime(lastTimeUseXiMuoi, TIME_XI_MUOI)) {
                isUseXiMuoi = false;
            }
        }
        if (isUseTrungThu) {
            if (Util.canDoWithTime(lastTimeUseBanh, TIME_TRUNGTHU)) {
                isUseTrungThu = false;
            }
        }
    }

    public void dispose() {
        this.player = null;
    }
}
