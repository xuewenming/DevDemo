package com.sam.dev;


import com.sam.pojo.Item;
import com.sam.repository.ItemRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Sort;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Sam
 * @date 2019/3/11
 * @time 22:00
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class EsDevTest {

    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;

    @Autowired
    private ItemRepository itemRepository;

    /**
     * 创建索引
     * PUT /索引库名称
     * {
     *     "settings":{
     *         "number_of_shards" : 5,
     *         "number_of_replace" : 1
     *     }
     *
     * }
     */
    @Test
    public void createIndex() {
        elasticsearchTemplate.createIndex(Item.class);
    }


    /**
     * 创建映射
     * PUT /索引库名称/_mapping/类型名称
     * {
     *     "properties":{
     *         "字段名称1":{
     *             "type":"类型",
     *             "index"："索引",
     *             "store":"存储",
     *             "analyzer":"分词"
     *         },
     *          "字段名称2":{
     *                  "type":"类型",
     *                  "index"："索引",
     *                  "store":"存储",
     *                  "analyzer":"分词"
     *               }
     *     }
     *
     * }
     */
    @Test
    public void creatMapping(){
        //elasticsearchTemplate.putMapping(Item.class);
        elasticsearchTemplate.putMapping(Item.class);
    }


    /**
     * 删除索引
     * 1、DELETE /索引库名称
     * 查看索引
     * 1、HEAD /索引库名称
     * 2、GET /索引库名称
     * 3、GET *
     */
    @Test
    public void deleteIndex() {
        elasticsearchTemplate.deleteIndex(Item.class);
    }


    /**
     * 新增文档
     * POST /索引库名称/类型名称/{id}
     * {
     *     "key":"value",
     *     "key":"value"
     * }
     */
    @Test
    public void postDocument() {
        Item item = new Item(1L,"小米手机9战斗天使","手机","小米",2999.00,"http://image.xiaomi.com/13123.jpg");
        itemRepository.save(item);
    }

    /**
     * 批量新增
     */
    @Test
    public void postBatchDocument() {
        List<Item> items = new ArrayList<>();
        items.add(new Item(2L, "坚果手机R1", " 手机", "锤子", 3699.00, "http://image.123.com/123.jpg"));
        items.add(new Item(3L, "华为META10", " 手机", "华为", 4499.00, "http://image.456.com/3.jpg"));
        itemRepository.saveAll(items);
    }

    /**
     * 修改文档
     * PUT /索引库名称/类型名称/{id}
     * {
     *     "key":"value"
     * }
     */
    @Test
    public void updateDocument() {
        Item item = new Item(1L,"小米手机9战斗天使","手机","小米",2999.00,"http://image.xiaomi.com/55555.jpg");
        itemRepository.save(item);
    }

    /**
     * 基本查询
     *
     */
    @Test
    public void getDocument() {
        //Iterable<Item> all = this.itemRepository.findAll();
        //all.forEach(item -> {
        //    System.out.println(item);
        //});
        Iterable<Item> prices = this.itemRepository.findAll(Sort.by(Sort.Direction.DESC, "price"));
        prices.forEach(price->{
            System.out.println(price);
        });
    }


    @Test
    public void postBatchByDocument() {
        List<Item> items = new ArrayList<>();
        items.add(new Item(1L, "小米手机7", "手机", "小米", 3299.00, "http://image.leyou.com/13123.jpg"));
        items.add(new Item(2L, "坚果手机R1", "手机", "锤子", 3699.00, "http://image.leyou.com/13123.jpg"));
        items.add(new Item(3L, "华为META10", "手机", "华为", 4499.00, "http://image.leyou.com/13123.jpg"));
        items.add(new Item(4L, "小米Mix2S", "手机", "小米", 4299.00, "http://image.leyou.com/13123.jpg"));
        items.add(new Item(5L, "荣耀V10", "手机", "华为", 2799.00, "http://image.leyou.com/13123.jpg"));
        itemRepository.saveAll(items);
    }


    /**
     * 自定义查询
     */
    @Test
    public void getPriceBetween() {
        List<Item> byPriceBetween = this.itemRepository.findByPriceBetween(3299.00, 4499.00);
        byPriceBetween.forEach(price->{
            System.out.println(price);
        });
    }

    @Test
    public void getTitle() {
        List<Item> items = this.itemRepository.findByTitleIsNotIn("锤子");
        items.forEach(item -> {
            System.out.println(item);
        });
    }


}