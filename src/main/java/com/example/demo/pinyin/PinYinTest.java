package com.example.demo.pinyin;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

import java.util.Locale;

/**
 * @Author: hanDa
 * @Date: 2021/3/25 10:48
 * @Version:1.0
 * @Description:
 */
public class PinYinTest {
    /**
     * 获得汉语拼音首字母
     *
     * @param chines 汉字
     * @return
     */
    public static String getAlpha(String chines) {
        String pinyinName = "";
        char[] nameChar = chines.toCharArray();
        HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();
        defaultFormat.setCaseType(HanyuPinyinCaseType.UPPERCASE);
        defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        for (int i = 0; i < nameChar.length; i++) {
            if (nameChar[i] > 128) {
                try {
                    pinyinName += PinyinHelper.toHanyuPinyinStringArray(
                            nameChar[i], defaultFormat)[0].charAt(0);
                } catch (BadHanyuPinyinOutputFormatCombination e) {
                    e.printStackTrace();
                }
            } else {
                pinyinName += nameChar[i];
            }
        }
        return pinyinName;
    }

    /**
     * 将字符串中的中文转化为拼音,英文字符不变
     *
     * @param inputString 汉字
     * @return
     */
    public static String getPingYin(String inputString) {
        HanyuPinyinOutputFormat format = new HanyuPinyinOutputFormat();
        format.setCaseType(HanyuPinyinCaseType.LOWERCASE);
        format.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        format.setVCharType(HanyuPinyinVCharType.WITH_V);
        String output = "";
        if (inputString != null && inputString.length() > 0
                && !"null".equals(inputString)) {
            char[] input = inputString.trim().toCharArray();
            try {
                for (int i = 0; i < input.length; i++) {
                    if (java.lang.Character.toString(input[i]).matches(
                            "[\\u4E00-\\u9FA5]+")) {
                        String[] temp = PinyinHelper.toHanyuPinyinStringArray(
                                input[i], format);
                        output += temp[0];
                    } else {
                        output += Character.toString(input[i]);
                    }
                }
            } catch (BadHanyuPinyinOutputFormatCombination e) {
                e.printStackTrace();
            }
        } else {
            return "*";
        }
        return output;
    }

    /**
     * 汉字转换为汉语拼音首字母，英文字符不变
     *
     * @param chines 汉字
     * @return 拼音
     */
    public static String converterToFirstSpell(String chines) {
        String pinyinName = "";
        char[] nameChar = chines.toCharArray();
        HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();
        defaultFormat.setCaseType(HanyuPinyinCaseType.UPPERCASE);
        defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        for (int i = 0; i < nameChar.length; i++) {
            if (nameChar[i] > 128) {
                try {
                    pinyinName += PinyinHelper.toHanyuPinyinStringArray(
                            nameChar[i], defaultFormat)[0].charAt(0);
                } catch (BadHanyuPinyinOutputFormatCombination e) {
                    e.printStackTrace();
                }
            } else {
                pinyinName += nameChar[i];
            }
        }
        return pinyinName;
    }

    public static void main(String[] args) {
        String[] names = new String[]{
                "合肥南沪蓉",
                "集贤路线所",
                "长安集",
                "集贤路线所",
                "肥西京港场",
                "舒城东",
                "庐江西",
                "桐城东",
                "桐城南",
                "双岭线路所",
                "龙山线路所  ",
                "安庆     ",
                "双岭线路所  ",
                "池州宁安场  ",
                "安庆_宁安  ",
                "集贤路XLSF",
                "肥西JGF  ",
                "舒城东F   ",
                "庐江西F   ",
                "桐城东F   ",
                "桐城南F   ",
                "双岭线所F  ",
                "龙山线所F  ",
                "安庆F    ",
                "双岭线所F  ",
                "合肥南HRF ",
                "池州宁安F  ",
                "安庆_宁安F ",
        };
        StringBuffer stringBuffer = new StringBuffer();
        System.out.println(converterToFirstSpell("韩达"));
        System.out.println(getAlpha("韩达"));
        System.out.println(getPingYin("韩达"));
        for (int i=0;i<names.length;i++){
            String name = names[i].trim();
               stringBuffer.append("@")
                       .append(converterToFirstSpell(name).toLowerCase(Locale.ROOT))
                       .append("|").append(name).append("|").append(converterToFirstSpell(name)).append("|").append(getPingYin(name)).append("|")
                       .append(converterToFirstSpell(name).toLowerCase(Locale.ROOT)).append("|").append(3115+i);

        }
        System.out.println(stringBuffer.toString());

    }
}