package com.example.demo.code.unicode;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/** @ClassName: StringAndUnicode @Description: TODO @Author: handa @Date: 2020/5/8 16:04 */
public class StringAndUnicode {
  public static void main(String[] args) {
    String s = "加务劢";
    System.out.println("加务劢对应unicode码:" + strToUnicode(s)); // 把汉字转成UNICODE
    // unioncde 转汉字
    System.out.println("unioncde 转汉字:" + unicodeToStr("\u52a0\u52a1\u52a2"));
    // 把unicode 转十进制  \u52a0=21152
    int dec = hexToDic("\\u52a0");
    System.out.println("unicode转为十进制:" + dec);
  }

  /** 把汉字转成UNICODE */
  private static String strToUnicode(String s) {
    String str = "";
    for (int i = 0; i < s.length(); i++) {
      int ch = s.charAt(i);
      if (ch > 255) str += "\\u" + Integer.toHexString(ch);
      else str += "\\" + Integer.toHexString(ch);
    }

    return str;
  }

  /** unicode 转成汉字 */
  public static String unicodeToStr(String str) {
    // 将给定的正则表达式编译到模式中。
    // \\u 以便在从文件或键盘击键读取的表达式中使用 Unicode 转义
    // \p{XDigit} 十六进制数字：[0-9a-fA-F]
    Pattern p = Pattern.compile("(\\\\u(\\p{XDigit}{4}))");
    // 创建匹配给定输入与此模式的匹配器。
    Matcher m = p.matcher(str);

    char c;
    // 方法扫描输入序列以查找与该模式匹配的下一个子序列。
    while (m.find()) {
      // 返回在以前匹配操作期间由给定组捕获的输入子序列。
      String temp = m.group(2);
      c = (char) Integer.parseInt(temp, 16);
      str = str.replace(m.group(1), c + "");
    }

    return str;
  }

  /** 4个16进制快速转10进制 ，这个是方便自已算 自已写的，肯定会有不足 */
  public static int hexToDic(String hex) {

    hex = hex.toUpperCase();
    // 去除\\u 参数一定要加上转义附\ 要不然会自动转换为有效汉字的
    if ("\\U".equals(hex.substring(0, 2))) hex = hex.substring(2);

    int res = 0;
    Character[] charArray =
        new Character[] {
          '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'
        };

    char[] charAr = hex.toCharArray();
    if (charAr.length != 4) {
      System.out.println("该方法只转换4位16进制 用于计算unicode 对应的 十进");
      return 0;
    }

    int pow = 0;
    for (int i = 0; i < 4; i++) {
      switch (i) {
        case 0:
          pow = 3;
          break;
        case 1:
          pow = 2;
          break;
        case 2:
          pow = 1;
          break;
        case 3:
          pow = 0;
          break;
      }

      Character c = charAr[i];
      for (int j = 0; j < charArray.length; j++) {
        char charHex = charArray[j];
        // 取出字母对应的数字
        if (c.equals(charHex)) {
          // 得到次方合
          int powRes = (int) Math.pow(16, pow);
          // 具体数字十六进制数*次方和=
          res += j * powRes;
        }
      }
    }
    return res;
  }
}
