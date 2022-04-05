package com.weiyu.chaitoufeng.domain.system;

import lombok.Data;
import org.apache.ibatis.type.Alias;

import java.util.ArrayList;
import java.util.List;

/**
 * Description: 前端菜单数据封装信息
 * Since: 2022-03-12 22:36
 * Author: wish_dq
 */
@Data
@Alias("SysMenu")
public class SysMenu {

    //菜单编号
    private String id;
    //父节点
    private String parentId;
    //标题
    private String title;
    //菜单类型
    private String type;
    //打 开 类 型
    private String openType;
    //图标
    private String icon;
    //跳转路径
    private String href;
    //子菜单
    private List<SysMenu> children = new ArrayList<>();
    //用于参数传递
    private String username;
}
