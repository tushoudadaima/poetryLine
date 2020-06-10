package com.example.poetryline.entity;

import java.util.Calendar;
import java.util.Date;

public class Lauar {

    private static int monCyl,dayCyl,yearCyl;
    private static int year,month,day;
    private static boolean isLeap;
    private static int[] lunarInfo = {0x04bd8, 0x04ae0, 0x0a570, 0x054d5,
            0x0d260, 0x0d950, 0x16554, 0x056a0, 0x09ad0, 0x055d2, 0x04ae0,
            0x0a5b6, 0x0a4d0, 0x0d250, 0x1d255, 0x0b540, 0x0d6a0, 0x0ada2,
            0x095b0, 0x14977, 0x04970, 0x0a4b0, 0x0b4b5, 0x06a50, 0x06d40,
            0x1ab54, 0x02b60, 0x09570, 0x052f2, 0x04970, 0x06566, 0x0d4a0,
            0x0ea50, 0x06e95, 0x05ad0, 0x02b60, 0x186e3, 0x092e0, 0x1c8d7,
            0x0c950, 0x0d4a0, 0x1d8a6, 0x0b550, 0x056a0, 0x1a5b4, 0x025d0,
            0x092d0, 0x0d2b2, 0x0a950, 0x0b557, 0x06ca0, 0x0b550, 0x15355,
            0x04da0, 0x0a5d0, 0x14573, 0x052d0, 0x0a9a8, 0x0e950, 0x06aa0,
            0x0aea6, 0x0ab50, 0x04b60, 0x0aae4, 0x0a570, 0x05260, 0x0f263,
            0x0d950, 0x05b57, 0x056a0, 0x096d0, 0x04dd5, 0x04ad0, 0x0a4d0,
            0x0d4d4, 0x0d250, 0x0d558, 0x0b540, 0x0b5a0, 0x195a6, 0x095b0,
            0x049b0, 0x0a974, 0x0a4b0, 0x0b27a, 0x06a50, 0x06d40, 0x0af46,
            0x0ab60, 0x09570, 0x04af5, 0x04970, 0x064b0, 0x074a3, 0x0ea50,
            0x06b58, 0x055c0, 0x0ab60, 0x096d5, 0x092e0, 0x0c960, 0x0d954,
            0x0d4a0, 0x0da50, 0x07552, 0x056a0, 0x0abb7, 0x025d0, 0x092d0,
            0x0cab5, 0x0a950, 0x0b4a0, 0x0baa4, 0x0ad50, 0x055d9, 0x04ba0,
            0x0a5b0, 0x15176, 0x052b0, 0x0a930, 0x07954, 0x06aa0, 0x0ad50,
            0x05b52, 0x04b60, 0x0a6e6, 0x0a4e0, 0x0d260, 0x0ea65, 0x0d530,
            0x05aa0, 0x076a3, 0x096d0, 0x04bd7, 0x04ad0, 0x0a4d0, 0x1d0b6,
            0x0d250, 0x0d520, 0x0dd45, 0x0b5a0, 0x056d0, 0x055b2, 0x049b0,
            0x0a577, 0x0a4b0, 0x0aa50, 0x1b255, 0x06d20, 0x0ada0};
    private static String[] Animals = {"鼠","牛","虎","兔","龙","蛇","马","羊","猴","鸡","狗","猪"};
    private static String[] yearName = {"零","壹","贰","叁","肆","伍","陆","柒","捌","玖"};
    private static String[] monthNong = {"正","正","二","三","四","五","六","七","八","九","十","十一","十二"};
    private static String[] Gan = {"甲","乙","丙","丁","戊","己","庚","辛","壬","癸"};
    private static String[] Zhi = {"子","丑","寅","卯","辰","巳","午","未","申","酉","戌","亥"};
    private static String[] nStrOne = {"日","一","二","三","四","五","六","七","八","九","十"};
    private static String[] nStrTwo = {"初","十","廿","卅"," "};

    //返回农历x年总天数
    private static int lYearDays(int x){
        int i;
        int sum = 348;//29*12
        for(i = 0x8000;i > 0x8;i >>= 1){
            sum += (lunarInfo[x - 1900] & i) == 0 ? 0 : 1;//大月加一天
        }
        return (sum + leapDays(x));
    }
    //返回农历x年闰月天数
    private static int leapDays(int x){
        if(leapMonth(x) != 0){
            return ((lunarInfo[x - 1900] & 0x10000) == 0 ? 29 :30);
        }else {
            return 0;
        }
    }
    //返回农历x年闰哪个月，无闰月返回0
    private static int leapMonth(int x){
        return (lunarInfo[x - 1900] & 0xf);
    }
    //返回农历x年y月总天数
    private static int monthDays(int x,int m){
        return ((lunarInfo[x - 1900] & (0x10000 >> m)) == 0 ? 29 : 30);
    }

    //算出农历，传入阳历日期，传回农历日期
    private static void LunarOne(Date date){
        int i,leap = 0,temp = 0;
        Calendar calendar = Calendar.getInstance();
        calendar.set(1900,0,31);//1900-1-31是农历1900年正月初一
        Date baseDate = calendar.getTime();
        int offset = (int)((date.getTime() - baseDate.getTime()) / 86400000);
        dayCyl = offset + 40;//1899-12-21是农历1899年腊月甲子日
        monCyl = 14;//1898-10-01是农历甲子月

        //得到年数
        for(i = 1900;i < 2050 && offset > 0; i++){
            temp = lYearDays(i);
            offset -= temp;
            monCyl += 12;
        }
        if(offset < 0){
            offset += temp;
            i--;
            monCyl -= 12;
        }
        year = i;//农历年份
        yearCyl = i - 1864;//1864是甲子年
        leap = leapMonth(i);//闰是哪月
        isLeap = false;
        for(i = 1;i < 13 && offset > 0;i++){
            //闰月
            if(leap > 0 && i == (leap + 1) && !isLeap){
                --i;
                isLeap = true;
                temp = leapDays(year);
            }else {
                temp = monthDays(year,i);
            }
            //解除闰月
            if(isLeap && i == (leap + 1)){
                isLeap = false;
            }
            offset -= temp;
            if(!isLeap){
                monCyl++;
            }
        }
        if(offset == 0 && leap > 0 && i == leap + 1){
            if(isLeap){
                isLeap = false;
            }else {
                isLeap = true;
                --i;
                --monCyl;
            }
        }
        if(offset < 0){
            offset += temp;
            --i;
            --monCyl;
        }
        month = i;//农历月份
        day = offset + 1;//农历天份
    }

    //输入offset输出干支,0为甲子
    private static String cyclical(int num){
        return (Gan[num % 10] + Zhi[num % 12]);
    }
    //中文日期
    private static String cDay(int x){
        String string;
        switch (x){
            case 10:
                string = "初十";
                break;
            case 20:
                string = "二十";
                break;
            case 30:
                string = "三十";
                break;
            default:
                string = nStrTwo[(int)(x / 10)];
                string += nStrOne[x % 10];
        }
        return string;
    }
    private static String cYear(int x){
        String s = " ";
        int d;
        while (x > 0){
            d = x % 10;
            x = (x - d) / 10;
            s = yearName[d] + s;
        }
        return s;
    }

    public static String getLauar(String year,String month,String day){
        Date sdObj;
        String string;
        int sy,sm,sd;
        int sy_next;
        sy = Integer.parseInt(year);
        sm = Integer.parseInt(month);
        sd = Integer.parseInt(day);
        sy_next = (sy - 4) % 12;
        Calendar calendar = Calendar.getInstance();
        calendar.set(sy,sm-1,sd);
        sdObj = calendar.getTime();
        //日期
        LunarOne(sdObj);
        string = ("农历闰" + monthNong[getMonth()] + "月");
        string += cDay(getDay());
//        string += cyclical(getYearCyl()) + Animals[sy_next] + "年 " + cyclical(getMonCyl()) + "月 " + cyclical(getDayCyl()) + "日";
        return string;
    }

    private static int getMonCyl(){
        return monCyl;
    }
    private static int getYearCyl(){
        return yearCyl;
    }
    private static int getDayCyl(){
        return dayCyl;
    }
    private static int getYear(){
        return year;
    }
    private static int getMonth(){
        return month;
    }
    private static int getDay(){
        return day;
    }
    private static boolean getIsLeap(){
        return isLeap;
    }
}
