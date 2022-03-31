//package com.weiyu.chaitoufeng.secure;
//
//import com.alibaba.fastjson.JSON;
//import com.alibaba.fastjson.JSONObject;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.weiyu.chaitoufeng.common.result.Result;
//import com.weiyu.chaitoufeng.common.tools.ServletUtil;
//import com.weiyu.chaitoufeng.domain.system.SysUser;
//import com.weiyu.chaitoufeng.mapper.system.SysUserMapper;
//import com.weiyu.chaitoufeng.service.system.ISysUserService;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//
//import javax.annotation.Resource;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.io.PrintWriter;
//import java.util.HashMap;
//import java.util.Map;
//
///**
// * Description:
// * Since: 2022-03-30 14:21
// * Author: wish_dq
// */
//public class MyUsernamePasswordFilter extends UsernamePasswordAuthenticationFilter {
//
//    @Resource
//    SysUserMapper userMapper;
//
//    private final AuthenticationManager authenticationManager;
//
//    public MyUsernamePasswordFilter(AuthenticationManager authenticationManager) {
//        this.authenticationManager = authenticationManager;
//    }
//
//    @Override
//    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
//        SysUser sysUser = new SysUser();
//        sysUser.setUsername(request.getParameter("username"));
//        //sysUser.setPassword(request.getParameter("password"));
//        if (userMapper.selectList(sysUser).size() == 0){
//            try {
//                response.getWriter().write(JSON.toJSONString(Result.failure("用户不存在")));
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//
//        return authenticationManager.authenticate(
//                                new UsernamePasswordAuthenticationToken(
//                                        sysUser.getUsername(),
//                                        sysUser.getPassword())
//                        );
//
//    }
////@Override
//    //public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
//    //    try {
//    //        //System.out.println(ServletUtil.getRequest().getParameter("username"));
//    //        //System.out.println(ServletUtil.getRequest().getParameter("password"));
//    //        //System.out.println(request);
//    //        SysUser sysUser = new SysUser();
//    //        sysUser.setUsername(request.getParameter("username"));
//    //        sysUser.setPassword(request.getParameter("password"));
//    //        //SysUser user = JSONObject.parseObject(request.getInputStream(),SysUser.class);
//    //        //System.out.println(user.toString());
//    //        return authenticationManager.authenticate(
//    //                new UsernamePasswordAuthenticationToken(
//    //                        sysUser.getUsername(),
//    //                        sysUser.getPassword())
//    //        );
//    //    } catch (Exception e) {
//    //        try {
//    //            response.setContentType("application/json;charset=utf-8");
//    //            //response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
//    //            //PrintWriter out = response.getWriter();
//    //            //Map<String, Object> map = new HashMap<>();
//    //            //map.put("code", HttpServletResponse.SC_UNAUTHORIZED);
//    //            //map.put("message", "账号或密码23错误！");
//    //            response.getWriter().write(JSON.toJSONString(Result.failure("账号或密码23错误")));
//    //            //out.write(new ObjectMapper().writeValueAsString(map));
//    //            //out.flush();
//    //            //out.close();
//    //        } catch (Exception e1) {
//    //            e1.printStackTrace();
//    //        }
//    //
//    //    }
//    //}
//}
