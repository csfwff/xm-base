package com.xiamo.xmbase.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringCheck{
    public static boolean userNameCheck(String s){
        //Pattern p = Pattern.compile("^[a-zA-Z][a-zA-Z0-9]{5,19}$");
        Pattern p = Pattern.compile("^[a-zA-Z0-9_\\u4e00-\\u9fa5]{1,20}$");
        Matcher m = p.matcher(s);
        boolean regMatch = m.matches();
        boolean hanzi = counthanzi(s)<=7;
        boolean shuzi = countShuzi(s)!=s.length();

        return regMatch&&hanzi&&shuzi;
    }

    //统计汉字个数
    public static int counthanzi(String text){
        int amount = 0;
        for(int i = 0;i<text.length();i++){
            boolean matches = Pattern.matches("^[\u4E00-\u9Fa5]{0,}$", text.charAt(i)+"");
            if(matches){
                amount ++;
            }
        }
        return amount;
    }

    //统计数字个数
    public static int countShuzi(String text){
        int amount = 0;
        for(int i = 0;i<text.length();i++){
            boolean matches = Pattern.matches("^[0-9]{0,}$", text.charAt(i)+"");
            if(matches){
                amount ++;
            }
        }
        return amount;
    }

    //密码
    public static boolean pwdCheck(String s){
        //Pattern p = Pattern.compile("((?=.*\\d)(?=.*\\D)|(?=.*[a-zA-Z])(?=.*[^a-zA-Z]))^.{6,12}$");
        //Pattern p = Pattern.compile("@\"^(?![0-9]+$)(?![a-z]+$)(?![A-Z]+$)(?![-`=\\\\\\[\\\\];',./~!@#$%^&*()_+|{}:\\\"<>?]+$)[0-9A-Za-z-`=\\\\\\[\\\\];',./~!@#$%^&*()_+|{}:\\\"<>?]{6,18}$\"");
        // Pattern p = Pattern.compile("^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,20}$");
        Pattern p = Pattern.compile("[A-Za-z\\d!@#.$%^&*?]{6,20}$");
        Matcher m = p.matcher(s);
        return m.matches();
    }

    //手机号
    public static boolean phoneCheck(String s){
        // Pattern p = Pattern.compile("^(0|86|17951)?(13[0-9]|15[0-9]|17[0135678]|18[0-9]|14[57])[0-9]{8}$");
        // Pattern p = Pattern.compile("^1(3|4|5|7|8|9)\\d{9}$");
        Pattern p = Pattern.compile("^1\\d{10}$");
        Matcher m = p.matcher(s);
        return m.matches();
    }

    //身份证
    public static boolean idNumCheck(String s){
        Pattern p = Pattern.compile("^[1-9]\\d{5}[1-9]\\d{3}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}([0-9]|X)$");
        Matcher m = p.matcher(s);
        return m.matches();
    }

    public static boolean isSystemAvatar(String s){
        return s.startsWith("system");
    }


    //身份证
    public static boolean exchangeCheck(String s){
        Pattern p = Pattern.compile("^[0-9]{0,8}(.[0-9]{1,2})?$");
        Matcher m = p.matcher(s);
        return m.matches();
    }

    //邮箱
    public static boolean mailCheck(String s){
        Pattern p = Pattern.compile("^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$");
        Matcher m = p.matcher(s);
        return m.matches();
    }

    //真实姓名
    public static boolean trueNameCheck(String s){
        Pattern p = Pattern.compile("^[a-zA-Z\u4e00-\u9fa5]+$");
        Matcher m = p.matcher(s);
        return m.matches();
    }

    //昵称
    public static boolean nickNameCheck(String s){
        Pattern p = Pattern.compile("^[\u4e00-\u9fa5_a-zA-Z0-9]+$");
        Matcher m = p.matcher(s);
        return m.matches();
    }


    //微信
    public static boolean isWechat(String s){
        Pattern p = Pattern.compile("^[a-zA-Z]{1}[-_a-zA-Z0-9]{5,19}$");
        Matcher m = p.matcher(s);
        return m.matches();
    }

    //是否表情
    public static boolean isEmoji(String string) {
        Pattern p = Pattern.compile("[\ud83c\udc00-\ud83c\udfff]|[\ud83d\udc00-\ud83d\udfff]|[\u2600-\u27ff]",
                Pattern.UNICODE_CASE | Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(string);
        return m.find();
    }


    /**
     * 校验银行卡卡号
     * @param cardId
     * @return
     */
    public static boolean checkBankCard(String cardId) {
        char bit = getBankCardCheckCode(cardId.substring(0, cardId.length() - 1));
        if(bit == 'N'){
            return false;
        }
        return cardId.charAt(cardId.length() - 1) == bit;
    }

    /**
     * 从不含校验位的银行卡卡号采用 Luhm 校验算法获得校验位
     * @param nonCheckCodeCardId
     * @return
     */
    public static char getBankCardCheckCode(String nonCheckCodeCardId){
        if(nonCheckCodeCardId == null || nonCheckCodeCardId.trim().length() == 0
                || !nonCheckCodeCardId.matches("\\d+")) {
            //如果传的不是数据返回N
            return 'N';
        }
        char[] chs = nonCheckCodeCardId.trim().toCharArray();
        int luhmSum = 0;
        for(int i = chs.length - 1, j = 0; i >= 0; i--, j++) {
            int k = chs[i] - '0';
            if(j % 2 == 0) {
                k *= 2;
                k = k / 10 + k % 10;
            }
            luhmSum += k;
        }
        return (luhmSum % 10 == 0) ? '0' : (char)((10 - luhmSum % 10) + '0');
    }
}