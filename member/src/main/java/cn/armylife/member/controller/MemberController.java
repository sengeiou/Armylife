package cn.armylife.member.controller;

import cn.armylife.common.domain.Member;
import cn.armylife.common.domain.Transactions;
import cn.armylife.common.domain.WXtemplate;
import cn.armylife.member.feignservice.Integralmall;
import cn.armylife.member.feignservice.WechatPay;
import cn.armylife.member.service.MemberService;
import cn.armylife.member.service.WechatService;
import cn.armylife.member.util.AliDayunSms;
import cn.armylife.member.util.MessageWechat;
import cn.armylife.member.util.PasswordEncoderUtil;
import com.aliyuncs.exceptions.ClientException;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.Logger;

@Controller
public class MemberController {
    @Autowired
    private MemberService memberService;

    @Autowired
    WechatService wechatService;

    @Autowired
    Integralmall integralmall;

    @Autowired
    WechatPay wechatPay;

    @Autowired
    MessageWechat messageWechat;

    @Value("${server.port}")
    int port;

    Logger logger=Logger.getLogger("MemberController.class");

    @RequestMapping("register")
    @ResponseBody
    public int register(Member users,HttpServletRequest request){
        Map<Integer,String> userinfo=(Map<Integer, String>) wechatService.getUserInfo(request.getParameter("code"));//获取用户信息
        if(userinfo!=null){
            String openid=userinfo.get(2);
            if (memberService.inspectMemberForOpenId(openid,"2")){//检查openid是否注册
                String userinfos=userinfo.get(3);
                JSONObject json2 = JSONObject.fromObject(userinfos);
                users.setMemberType("2");
                users.setMemberWechat(openid);
                users.setMemberTotal(new BigDecimal(0));
                users.setMemberAvatar(json2.getString("headimgurl"));
                users.setMemberNickname(json2.getString("nickname"));
                logger.info("用户信息: "+userinfos);
                Long msg=memberService.insert(users);
                integralmall.registerIntegral(msg);
                HttpSession session=request.getSession();
                session.setAttribute("Student",users);
                session.setMaxInactiveInterval(86400*30);
                if (msg==0){
                    return 0;
                }
                return 1;
            }
            Member student=new Member();
            student.setMemberWechat(openid);
            Member loginMember=memberService.loginUser(student);
            if (loginMember!=null||loginMember.getMemberType().equals("2")){
                HttpSession session=request.getSession();
                session.setAttribute("Student",loginMember);
                session.setMaxInactiveInterval(86400*30);
                return 1;
            }
            return 0;
        }
        return 0;

    };

    @RequestMapping("loginUser")
    @ResponseBody
    public int loginUser(Member users,String code, HttpServletRequest request){
        Map<Integer,String> userinfo=(Map<Integer, String>) wechatService.getUserInfo(request.getParameter("code"));//获取用户信息
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Date date=new Date();
        String time=sdf.format(date);
        if(userinfo!=null) {
            String openid = userinfo.get(2);
            if (memberService.inspectMemberForOpenId(openid, "2")) {//检查openid是否注册
                String userinfos = userinfo.get(3);
                JSONObject json2 = JSONObject.fromObject(userinfos);
                users.setMemberType("2");
                users.setMemberWechat(openid);
                users.setMemberTotal(new BigDecimal(0));
                users.setMemberAvatar(json2.getString("headimgurl"));
                users.setMemberNickname(json2.getString("nickname"));
                users.setRegisterTime(time);
                logger.info("用户信息: " + userinfos);
                Long msg = memberService.insert(users);
                WXtemplate wXtemplate=new WXtemplate();
                wXtemplate.setUrl("ArmyErrands/index.html");
                wXtemplate.setTemplate("M33R3HtBtPfhAJxx7pC-UEI6JJQUZj08qVwvucoYkQo");
                wXtemplate.setOpenid(openid);
                wXtemplate.setFirst("您好,您已成为军旅生活会员!");
                wXtemplate.setRemark1("欢迎使用军旅生活,祝您生活愉快!");
                Map<String,String> key=new HashMap<>();
                key.put("1",json2.getString("nickname"));
                key.put("2",time);
                wXtemplate.setKey(key);
                messageWechat.newOrderService(wXtemplate);
                HttpSession session = request.getSession();
                session.setAttribute("Delivery", users);
                session.setMaxInactiveInterval(86400 * 30);
                if (msg == 0) {
                    return 0;
                }
                return 1;
            }
            Member student = new Member();
            student.setMemberWechat(openid);
            student.setMemberType("2");
            Member loginMember = memberService.loginUser(student);
            logger.info(loginMember.toString());
//            if (loginMember!=null||loginMember.getMemberType().equals("1")){
            HttpSession session = request.getSession();
            session.setAttribute("Delivery", loginMember);
            session.setMaxInactiveInterval(86400 * 30);
            return 1;
//            }
//            return 0;
        }
        return 0;
    }

    @RequestMapping("loginMember")
    @ResponseBody
    public int loginMember(Member users,String code, HttpServletRequest request){
        Map<Integer,String> userinfo=(Map<Integer, String>) wechatService.getUserInfo(request.getParameter("code"));//获取用户信息
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Date date=new Date();
        String time=sdf.format(date);
        if(userinfo!=null){
            String openid=userinfo.get(2);
            if (memberService.inspectMemberForOpenId(openid,"1")){//检查openid是否注册
                String userinfos=userinfo.get(3);
                JSONObject json2 = JSONObject.fromObject(userinfos);
                users.setMemberType("1");
                users.setMemberWechat(openid);
                users.setMemberTotal(new BigDecimal(0));
                users.setMemberAvatar(json2.getString("headimgurl"));
                users.setMemberNickname(json2.getString("nickname"));
                logger.info("用户信息: "+userinfos);
                Long msg=memberService.insert(users);
                WXtemplate wXtemplate=new WXtemplate();
                wXtemplate.setUrl("ArmyStudents/index.html");
                wXtemplate.setTemplate("M33R3HtBtPfhAJxx7pC-UEI6JJQUZj08qVwvucoYkQo");
                wXtemplate.setOpenid(openid);
                wXtemplate.setFirst("您好,您已成为军旅生活会员!");
                wXtemplate.setRemark1("欢迎使用军旅生活,祝您生活愉快!");
                Map<String,String> key=new HashMap<>();
                key.put("key1",json2.getString("nickname"));
                key.put("key2",time);
                wXtemplate.setKey(key);
                messageWechat.newOrderService(wXtemplate);
                integralmall.registerIntegral(msg);
                HttpSession session=request.getSession();
                session.setAttribute("Student",users);
                session.setMaxInactiveInterval(86400*30);
                if (msg==0){
                    return 0;
                }
                return 1;
            }
            Member student=new Member();
            student.setMemberWechat(openid);
            student.setMemberType("1");
            Member loginMember=memberService.loginUser(student);
            logger.info(loginMember.toString());
            if (loginMember!=null||loginMember.getMemberType().equals("1")){
            HttpSession session=request.getSession();
            session.setAttribute("Student",loginMember);
            session.setMaxInactiveInterval(86400*30);
            return 1;
        }
            return 0;
        }
        return 0;
    }

    /**
     * 重载用户信息
     */
    @RequestMapping("reloadSession")
    @ResponseBody
    public Member reloadSession(Long memberId,HttpServletRequest request){
        logger.info("执行重载");
        Member member=memberService.reloadSession(memberId);
        logger.info(member.toString());
        HttpSession session=request.getSession();
        session.setAttribute("Student",member);
        return member;
    }

    /**
     * 更新用户信息
     * @param member
     * @return
     */
    @RequestMapping("updateMember")
    @ResponseBody
    public int updateMember(Member member){
        return memberService.updateMember(member);
    };

    /**
     * 获取当前登录用户信息
     * @param request
     * @return
     */
    @RequestMapping("/getMemberUserinfo")
    @ResponseBody
    public Member getMemberUserinfo(HttpServletRequest request){
        HttpSession session=request.getSession();
        Member member=(Member)session.getAttribute("Student");
        Member user=memberService.reloadSession(member.getMemberId());
        return user;
    }

    /**
     * 获取当前登录跑腿信息
     * @param request
     * @return
     */
    @RequestMapping("/getDeliveryUserinfo")
    @ResponseBody
    public Member getDeliveryUserinfo(HttpServletRequest request){
        HttpSession session=request.getSession();
        Member member=(Member)session.getAttribute("Delivery");
        logger.info(member.toString());
        Member user=memberService.reloadSession(member.getMemberId());
        return user;
    }

    /**
     * 获取当前登录商铺信息
     * @param request
     * @return
     */
    @RequestMapping("/getShopUserinfo")
    @ResponseBody
    public Member getShopUserinfo(HttpServletRequest request){
        HttpSession session=request.getSession();
        Member member=(Member)session.getAttribute("Shop");
        if (member==null){
            return null;
        }
        logger.info(member.toString());
        Member user=memberService.reloadSession(member.getMemberId());
        return user;
    }

    @RequestMapping("getPassword")
    @ResponseBody
    public String pass(String name){
       return PasswordEncoderUtil.encode(name);
    }

    /**
     * 跑腿添加手机号发送验证码
     * @param req
     * @param Phone
     * @return
     * @throws ClientException
     */
    @RequestMapping("/SMS.do")
    @ResponseBody
    public int SMS(HttpServletRequest req,String Phone) throws ClientException {
        AliDayunSms aliDayunSms=new AliDayunSms();
        String MSgcode= (String) aliDayunSms.getMsgCode();
        HttpSession session = req.getSession();
        session.setAttribute(Phone,MSgcode);
        String Message= aliDayunSms.SMS(Phone,MSgcode,"");
        this.removeAttrbute(session,Phone);
//        System.out.println(Message);
        logger.info(Message);
        if (Message.equals("=====success====")){
            return 1;
        }
        return 0;
    }
    /**
     * 设置5分钟后删除session中的验证码
     * @param session
     * @param attrName
     */
    private void removeAttrbute(final HttpSession session, final String attrName) {
        final Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                // 删除session中存的验证码
                session.removeAttribute(attrName);
                timer.cancel();
            }
        }, 5 * 60 * 1000);
    }

    /**
     * 填写跑腿手机号
     * @param SmsCode
     * @param request
     * @param phone
     * @return
     * @throws ClientException
     */
    @RequestMapping("updateDeliveryPhone")
    @ResponseBody
    public int updateDeliveryPhone(String SmsCode,HttpServletRequest request,String phone) throws ClientException {
        HttpSession session=request.getSession();
        String code=(String)session.getAttribute(phone);
        Member user=(Member)session.getAttribute("Delivery");
        if (code.equals(SmsCode)){
            Member member=new Member();
            member.setMemberId(user.getMemberId());
            member.setMemberPhone(phone);
            return memberService.updateDeliveryPhone(member);
        }else {
            return 0;
        }
    }

    /**
     * 跑腿提现
     * @param request
     * @param orderfee
     * @return
     */
    @RequestMapping("deliveyExtract")
    @ResponseBody
    public String deliveyExtract(HttpServletRequest request,String orderfee){
        HttpSession session=request.getSession();
        Member delivery=(Member)session.getAttribute("Delivery");
        Member member=memberService.reloadSession(delivery.getMemberId());
        logger.info(member.toString());
        BigDecimal total=member.getMemberTotal();
        BigDecimal ordertotal=new BigDecimal(orderfee);
        if (total.compareTo(ordertotal)==-1){
            return "0";
        }
        BigDecimal newtotal=total.subtract(ordertotal);
        Member user=new Member();
        user.setMemberTotal(newtotal);
        user.setMemberId(member.getMemberId());
        int msg=wechatPay.WechatExtract(member.getMemberWechat(),member.getMemberTotal(),member.getMemberId(),orderfee);
        if (msg==0){
            return "0";
        }
        int num=memberService.updateMember(user);
        if (num==0){
            return "0";
        };
        return "1";
    }

    /**
     * 查询跑腿零钱明细
     * @param request
     * @return
     */
    @RequestMapping("selectForDelivery")
    @ResponseBody
    public PageInfo<Transactions> selectForDelivery(HttpServletRequest request,int pageNum){
        HttpSession session=request.getSession();
        Member member=(Member)session.getAttribute("Delivery");
        PageHelper.startPage(pageNum, 10);
        PageInfo<Transactions> pageInfo = new PageInfo<>(memberService.selectForDelivery(member.getMemberId()));
        if (pageInfo.getPageNum()<=1&pageNum>1){
            return null;
        }
        return pageInfo;
    }

    /**
     * 查询跑腿零钱明细
     * @param request
     * @return
     */
    @RequestMapping("selectExtractForShop")
    @ResponseBody
    public PageInfo<Transactions> selectExtractForShop(HttpServletRequest request,int pageNum){
        HttpSession session=request.getSession();
        Member member=(Member)session.getAttribute("Shop");
        PageHelper.startPage(pageNum, 10);
        PageInfo<Transactions> pageInfo = new PageInfo<>(memberService.selectForDelivery(member.getMemberId()));
        if (pageInfo.getPageNum()<=1&pageNum>1){
            return null;
        }
        return pageInfo;
    }

    /**
     * 跑腿添加手机号发送验证码
     * @param req
     * @param phone
     * @return
     * @throws ClientException
     */
    @RequestMapping("/ShopSMS.do")
    @ResponseBody
    public int ShopSMS(HttpServletRequest req,String phone) throws ClientException {
        AliDayunSms aliDayunSms=new AliDayunSms();
        String MSgcode= (String) aliDayunSms.getMsgCode();
        HttpSession session = req.getSession();
        session.setAttribute(phone,MSgcode);
        String Message= aliDayunSms.SMS(phone,MSgcode,"SMS_180460059");
        this.removeAttrbute(session,phone);
//        System.out.println(Message);
        logger.info(Message);
        if (Message.equals("=====success====")){
            return 1;
        }
        return 0;
    }

    @RequestMapping("/inspectShopForPhone")
    @ResponseBody
    public int inspectShopForPhone(String phone){
        if (memberService.inspectMemberForPhone(phone,"3")){
            return 1;
        };
        return 0;
    }

    @RequestMapping("/inspectShopForALID")
    @ResponseBody
    public int inspectShopForALID(String alid){
        if (!memberService.inspectShopForALID(alid)){
            return 0;
        };
        return 1;
    }

    @RequestMapping("loginShop")
    @ResponseBody
    public int loginShop(String SmsCode,String phone,String alid,HttpServletRequest request,String Wechatcode)throws ClientException {
        HttpSession session=request.getSession();
        String code=(String)session.getAttribute(phone);
        String openid=wechatService.getOpenId(Wechatcode);
        if (memberService.ShopNewForAppId(alid)!=null){
            return 0;
        }
        logger.info("code:"+code);
        Member member=new Member();
        member.setMemberPhone(phone);
        Member loginTime=new Member();
        Long memberid=null;
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM dd-mm-ss");
        Date date=new Date();
        String time=sdf.format(date);
        if (!code.equals(SmsCode)){
            return 0;
        }
        if (memberService.inspectMemberForPhone(phone,"3")){
            member.setMemberType("3");
            member.setMemberWechat(openid);
            member.setAlid(alid);
            member.setMemberTotal(new BigDecimal(0));
            member.setShopStatus(0);
            memberid=memberService.insert(member);
            loginTime.setRegisterTime(time);
            integralmall.registerIntegral(memberid);
            return 2;
        };
        Member shop=memberService.loginShop(member);
        logger.info(shop.toString());
        if (memberid==null){
            memberid=shop.getMemberId();
        }
        session.setAttribute("Shop",shop);
        session.setMaxInactiveInterval(86400*30);
        if (shop.getShopName()==null){
            return 3;
        }
        loginTime.setLastloginTime(time);
        loginTime.setMemberId(memberid);
        memberService.updateMember(loginTime);
        return 1;
    }

    @RequestMapping("updataShop")
    @ResponseBody
    public int updataShop(Member member,HttpServletRequest request){
        HttpSession session=request.getSession();
        Member user=(Member)session.getAttribute("Shop");
        member.setMemberId(user.getMemberId());
        int msg=memberService.updateMember(member);
        memberService.reloadSession(user.getMemberId());
        if (msg==0){
            return 0;
        }
        return 1;
    }

    /**
     * 修改商家头像
     * @return
     */
    @RequestMapping("/updateShopAvatar.do")
    @ResponseBody
    public int updateCompanyAvatar(MultipartFile file, HttpServletRequest request) throws IOException {
        logger.info("图片:"+file);
        HttpSession session=request.getSession();
        Member member=new Member();
        try{
            member=(Member)session.getAttribute("Shop");
        }catch (NullPointerException e){
            logger.info("123:"+e);
        }

        logger.info(member.toString());
        String shopAvatar=member.getMemberAvatar();
        String newName = null;
        String exeName = null;
        // 判断文件是否为空
        if (!file.isEmpty()) {
            logger.info("图片不为空");
            // 图片原始名字
            String oldName = file.getOriginalFilename();
            // 图片新名字
            newName = UUID.randomUUID().toString();
            // 后缀名
            // exeName = oldName.substring(oldName.indexOf("."));
            exeName=".png";
            File pic = new File("/home/armylife/shopAvatar/public_html/" + newName + exeName);
//            File pic = new File("E:\\Temp\\" + newName + exeName);
            // 保存图片到本地磁盘
            file.transferTo(pic);
            Member user=new Member();
            user.setMemberId(member.getMemberId());
            user.setMemberAvatar("http://pic.armylife.cn/" +newName + exeName);
            memberService.updateMember(user);
        }
        if(shopAvatar!=null){
            File oldname=new File("http://pic.armylife.cn/" +shopAvatar) ;
            oldname.delete();
        }
        return 1;
    }

    /**
     * 通过memberId查询member
     * @param memberId
     * @return
     */
    @RequestMapping("selectMemberforId")
    @ResponseBody
    public Member selectMemberforId(Long memberId){
        return memberService.selectMemberforId(memberId);
    };

    /**
     * 设置商家的起送价格(total)
     * @param isdeliver
     * @return
     */
    @RequestMapping("isdeliver")
    @ResponseBody
    public int isdeliver(String isdeliver,HttpServletRequest request){
        HttpSession session=request.getSession();
        Member member=(Member)session.getAttribute("Shop");
        Long shopId=member.getMemberId();
        return memberService.isdeliver(shopId,isdeliver);
    };

    @RequestMapping("newOrderService")
    @ResponseBody
    public String newOrderService(WXtemplate wXtemplate){
        return messageWechat.newOrderService(wXtemplate);
    }


}
