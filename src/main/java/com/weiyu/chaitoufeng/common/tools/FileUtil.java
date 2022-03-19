package com.weiyu.chaitoufeng.common.tools;

import com.weiyu.chaitoufeng.common.constant.CommonConstant;

/**
 * Description: 文 件 工 具 类
 * Since: 2022-03-18 18:53
 * Author: wish_dq
 */
public class FileUtil {
    public static double size;
    public static String getPrintSize(long size) {
        // 如果字节数少于1024，则直接以B为单位，否则先除于1024，后3位因太少无意义
        if (size < CommonConstant.UNIT) {
            return size + "B";
        } else {
            size = size / CommonConstant.UNIT;
        }
        // 如果原字节数除于1024之后，少于1024，则可以直接以KB作为单位
        // 因为还没有到达要使用另一个单位的时候
        // 接下去以此类推
        if (size < CommonConstant.UNIT) {
            return size + "KB";
        } else {
            FileUtil.size = (double) size / CommonConstant.UNIT;
        }
        if (FileUtil.size < CommonConstant.UNIT) {
            // 如果以MB为单位的话，要保留最后2位小数，把此数乘以100取整之后再取余
            FileUtil.size = (double) (Math.round(FileUtil.size*CommonConstant.CENTI))/CommonConstant.CENTI;
            return FileUtil.size+ "MB";
        } else {
            // 否则如果要以GB为单位的，先除于1024再作同样的处理
            FileUtil.size = FileUtil.size / CommonConstant.UNIT;
            FileUtil.size = (double) (Math.round(FileUtil.size*CommonConstant.CENTI))/CommonConstant.CENTI;
            return  FileUtil.size+"GB";
        }
    }

}
