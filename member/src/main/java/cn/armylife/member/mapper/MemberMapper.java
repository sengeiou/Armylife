package cn.armylife.member.mapper;

import cn.armylife.common.domain.Member;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface MemberMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table member
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Integer memberId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table member
     *
     * @mbggenerated
     */
    int insert(Member record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table member
     *
     * @mbggenerated
     */
    Member selectByPrimaryKey(Integer memberId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table member
     *
     * @mbggenerated
     */
    List<Member> selectAll();

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table member
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(Member record);

    /**
     * 登录学员,跑腿
     * @param member
     * @return
     */
    Member loginUser(Member member);

    /**
     * 登录商铺
     * @param member
     * @return
     */
    Member loginShop(Member member);

    /**
     * 重载用户信息
     * @param memberId
     * @return
     */
    Member reloadSession(Long memberId);

    /**
     * 更新用户信息
     * @param member
     * @return
     */
    int updateMember(Member member);

    /**
     * 检查用户openid是否注册
     * @param openid
     * @return
     */
    Member inspectMemberForOpenId(Member member);

    /**
     * 检查用户phone是否注册
     * @param phone
     * @return
     */
    Member inspectMemberForPhone(@Param("phone") String phone,@Param("type") String type);

    /**
     * 更新跑腿手机号码
     * @param member
     * @return
     */
    int updateDeliveryPhone(Member member);

    /**
     * 通过memberId查询member
     * @param memberId
     * @return
     */
    Member selectMemberforId(Long memberId);

    /**
     * 通过appId查询商户信息
     * @param appId
     * @return
     */
    Member ShopNewForAppId(String appId);

    /**
     * 设置商家的器送价格(total)
     * @param shopId
     * @param total
     * @return
     */
    int isdeliver(Long shopId,String total);
}