package com.sam.repository;

import com.sam.pojo.Item;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

/**
 * Repository 仓库
 * @author Sam
 * @date 2019/3/11
 * @time 23:02
 */
public interface ItemRepository extends ElasticsearchRepository<Item,Long> {

    /**
     * 根据价格区间查询数据
     * @param price1
     * @param price2
     * @return
     */
    List<Item> findByPriceBetween(double price1, double price2);


    /**
     * 查询不是这个title的商品
     * @param title
     * @return
     */
    List<Item> findByTitleIsNotIn(String title);

}
