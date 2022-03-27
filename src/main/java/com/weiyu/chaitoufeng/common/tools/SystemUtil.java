package com.weiyu.chaitoufeng.common.tools;

import cn.hutool.core.net.NetUtil;
import cn.hutool.core.util.NumberUtil;
import com.weiyu.chaitoufeng.common.server.CpuInfo;
import com.weiyu.chaitoufeng.common.server.MemInfo;
import com.weiyu.chaitoufeng.common.server.SysFileInfo;
import oshi.SystemInfo;
import oshi.hardware.CentralProcessor;
import oshi.hardware.GlobalMemory;
import oshi.hardware.HardwareAbstractionLayer;
import oshi.software.os.FileSystem;
import oshi.software.os.OSFileStore;
import oshi.software.os.OperatingSystem;
import oshi.util.Util;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

/**
 * Description: 系统工具类
 * Since: 2022-03-17 22:26
 * Author: wish_dq
 */
public class SystemUtil {

    private static final int WAIT_SECOND = 1000;

    public static CpuInfo getCpu() {
        CpuInfo cpu = new CpuInfo();
        SystemInfo systemInfo = new SystemInfo();
        HardwareAbstractionLayer hardware = systemInfo.getHardware();
        setCpuInfo(hardware.getProcessor(), cpu);
        MemInfo memInfo = setMemInfo(hardware.getMemory());
        cpu.setMemInfo(memInfo);
        setSysInfo(cpu);
        setJvmInfo(cpu);
        setSysFiles(systemInfo.getOperatingSystem(), cpu);
        return cpu;
    }
    private static void setSysInfo(CpuInfo cpu) {
        Properties props = System.getProperties();
        cpu.setSysInfoComputerName(getHostName());
        cpu.setSysInfoComputerIp(NetUtil.getLocalhostStr());
        cpu.setSysInfoOsName(props.getProperty("os.name"));
        cpu.setSysInfoOsArch(props.getProperty("os.arch"));
        cpu.setSysInfoUserDir(props.getProperty("user.dir"));
    }
    /**
     * 获取客户端主机名称
     */
    public static String getHostName() {
        try {
            return InetAddress.getLocalHost().getHostName();
        } catch (UnknownHostException ignored) {
        }
        return "未知";
    }
    /**
     * 设置Java虚拟机
     */
    private static void setJvmInfo(CpuInfo cpu) {
        Properties props = System.getProperties();
        cpu.setJvmInfoTotal(Runtime.getRuntime().totalMemory());
        cpu.setJvmInfoMax(Runtime.getRuntime().maxMemory());
        cpu.setJvmInfoFree(Runtime.getRuntime().freeMemory());
        cpu.setJvmInfoVersion(props.getProperty("java.version"));
        cpu.setJvmInfoHome(props.getProperty("java.home"));

    }

    /**
     * 设置磁盘信息
     */
    private static void setSysFiles(OperatingSystem os, CpuInfo cpu) {
        FileSystem fileSystem = os.getFileSystem();
        List<OSFileStore> fsArray = fileSystem.getFileStores();
        for (OSFileStore fs : fsArray) {
            long free = fs.getUsableSpace();
            long total = fs.getTotalSpace();
            long used = total - free;
            SysFileInfo sysFile = new SysFileInfo();
            sysFile.setDirName(fs.getMount());
            sysFile.setSysTypeName(fs.getType());
            sysFile.setTypeName(fs.getName());
            sysFile.setTotal(convertFileSize(total));
            sysFile.setFree(convertFileSize(free));
            sysFile.setUsed(convertFileSize(used));
            if (total == 0) {
                sysFile.setUsage(0);
            } else {
                sysFile.setUsage(NumberUtil.mul(NumberUtil.div(used, total, 4), 100));
            }
            cpu.getSysFiles().add(sysFile);
        }
    }
    /**
     * 字节转换
     *
     * @param size 字节大小
     * @return 转换后值
     */
    public static String convertFileSize(long size) {
        long kb = 1024;
        long mb = kb * 1024;
        long gb = mb * 1024;
        if (size >= gb) {
            return String.format("%.1f GB", (float) size / gb);
        } else if (size >= mb) {
            float f = (float) size / mb;
            return String.format(f > 100 ? "%.0f MB" : "%.1f MB", f);
        } else if (size >= kb) {
            float f = (float) size / kb;
            return String.format(f > 100 ? "%.0f KB" : "%.1f KB", f);
        } else {
            return String.format("%d B", size);
        }
    }
    /**
     * 设置CPU信息
     */
    private static void setCpuInfo(CentralProcessor processor, CpuInfo cpu) {
        // CPU信息
        long[] prevTicks = processor.getSystemCpuLoadTicks();
        // 睡眠1s
        Util.sleep(WAIT_SECOND);
        long[] ticks = processor.getSystemCpuLoadTicks();
        //返回当前系统利用率
        //processor.getSystemCpuLoadBetweenTicks(prevTicks);
        long nice = ticks[CentralProcessor.TickType.NICE.getIndex()] - prevTicks[CentralProcessor.TickType.NICE.getIndex()];
        long irq = ticks[CentralProcessor.TickType.IRQ.getIndex()] - prevTicks[CentralProcessor.TickType.IRQ.getIndex()];
        long softirq = ticks[CentralProcessor.TickType.SOFTIRQ.getIndex()] - prevTicks[CentralProcessor.TickType.SOFTIRQ.getIndex()];
        long steal = ticks[CentralProcessor.TickType.STEAL.getIndex()] - prevTicks[CentralProcessor.TickType.STEAL.getIndex()];
        long cSys = ticks[CentralProcessor.TickType.SYSTEM.getIndex()] - prevTicks[CentralProcessor.TickType.SYSTEM.getIndex()];
        long user = ticks[CentralProcessor.TickType.USER.getIndex()] - prevTicks[CentralProcessor.TickType.USER.getIndex()];
        long iowait = ticks[CentralProcessor.TickType.IOWAIT.getIndex()] - prevTicks[CentralProcessor.TickType.IOWAIT.getIndex()];
        long idle = ticks[CentralProcessor.TickType.IDLE.getIndex()] - prevTicks[CentralProcessor.TickType.IDLE.getIndex()];
        long totalCpu = user + nice + cSys + idle + iowait + irq + softirq + steal;

        cpu.setCpuNum(processor.getLogicalProcessorCount());

        cpu.setSys(NumberUtil.mul(NumberUtil.div(cSys,totalCpu,4),100));
        cpu.setUsed(NumberUtil.mul(NumberUtil.div(user,totalCpu,4),100));
        cpu.setWait(NumberUtil.mul(NumberUtil.div(iowait,totalCpu,4),100));
        cpu.setFree(NumberUtil.mul(NumberUtil.div(idle,totalCpu,4),100));
        cpu.setTotalUsed(NumberUtil.mul(NumberUtil.div(totalCpu-idle,totalCpu,4),100));

    }

    /**
     * 设置内存信息
     */
    private static MemInfo setMemInfo(GlobalMemory memory) {
        MemInfo mem = new MemInfo();
        mem.setTotal(memory.getTotal());
        mem.setUsed(memory.getTotal() - memory.getAvailable());
        mem.setFree(memory.getAvailable());
        return mem;
    }
}
