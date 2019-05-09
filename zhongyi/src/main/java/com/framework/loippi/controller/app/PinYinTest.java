package com.framework.loippi.controller.app;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

public class PinYinTest {
	  public static String getFirstSpell(String chinese) {   
          StringBuffer pybf = new StringBuffer();   
          char[] arr = chinese.toCharArray();   
          HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();   
          defaultFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);   
          defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);   
          for (int i = 0; i < arr.length; i++) {   
                  if (arr[i] > 128) {   
                          try {   
                                  String[] temp = PinyinHelper.toHanyuPinyinStringArray(arr[i], defaultFormat);   
                                  if (temp != null) {   
                                          pybf.append(temp[0].charAt(0));   
                                  }   
                          } catch (BadHanyuPinyinOutputFormatCombination e) {   
                                  e.printStackTrace();   
                          }   
                  } else {   
                          pybf.append(arr[i]);   
                  }   
          }   
          return pybf.toString().substring(0,1).toUpperCase();
  }   
 
    public static void main(String[] args) {
        String chstrs = "1! Good homesick!";
        System.out.println("输入:" + chstrs);
        System.out.println("结果：" + getFirstSpell(chstrs));
    }
}
