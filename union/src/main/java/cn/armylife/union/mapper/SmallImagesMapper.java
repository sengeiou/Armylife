package cn.armylife.union.mapper;

import cn.armylife.union.domain.SmallImages;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SmallImagesMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table small_images
     *
     * @mbggenerated
     */
    int insert(SmallImages record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table small_images
     *
     * @mbggenerated
     */
    List<SmallImages> selectAll();
}