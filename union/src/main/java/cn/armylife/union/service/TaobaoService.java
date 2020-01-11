package cn.armylife.union.service;

import cn.armylife.union.domain.Favorites;
import cn.armylife.union.domain.SmallImages;
import com.github.pagehelper.Page;
import com.taobao.api.domain.UatmTbkItem;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TaobaoService {

    /**
     * 添加商品选品库列表
     * @param favorites
     * @return
     */
   int FavoritesInsert(Favorites favorites);

    /**
     * 查询全部选品库列表
     * @return
     */
   List<Favorites> FavoritesAll();

    /**
     * 选品库商品
     * @param favoritesItem
     * @return
     */
   int FavoritesItemInsert(UatmTbkItem favoritesItem);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table favorites_item
     *
     * @mbggenerated
     */
    Page<UatmTbkItem> FavoritesItemAll();

    /**
     * 小图添加
     * @param smallImages
     * @return
     */
   int smallImagesInsert(SmallImages smallImages);



    int deleteAll();
}