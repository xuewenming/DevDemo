package com.sam.dev;

import com.sam.pojo.Item;
import com.sam.repository.ItemRepository;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author Sam
 * @date 2019/3/12
 * @time 21:10
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class HightQueryTest {

    @Autowired
    private ItemRepository itemRepository;

    /**
     * 基本查询
     */
    @Test
    public void testQuery() {
        //词条查询
        MatchQueryBuilder queryBuilder = new MatchQueryBuilder("title","小米");
        // 执行查询
        Iterable<Item> items = this.itemRepository.search(queryBuilder);
        items.forEach(item -> {
            System.out.println(item);
        });
    }


    /**
     * 基本查询match query
     */
    @Test
    public void testNativeQuery() {
        // 构建查询条件
        NativeSearchQueryBuilder queryBuilder = new NativeSearchQueryBuilder();

        // 添加基本的分词查询
        queryBuilder.withQuery(QueryBuilders.matchQuery("title", "坚果"));

        // 执行搜索，获取结果
        NativeSearchQuery build = queryBuilder.build();
        Page<Item> items = this.itemRepository.search(build);
        System.out.println(items.getTotalElements());
        System.out.println(items.getTotalPages());
        items.forEach(System.out::println);
    }

    @Test
    public void testQueryPage() {
        // 构建查询条件
        NativeSearchQueryBuilder queryBuilder = new NativeSearchQueryBuilder();
        // 添加基本分词查询
        queryBuilder.withQuery(QueryBuilders.termQuery("category","手机"));

        // 初始化分页参数
        int page = 0;
        int size = 3;
        // 设置分页参数
        queryBuilder.withPageable(PageRequest.of(page, size));

        // 执行搜索结果
        Page<Item> items = this.itemRepository.search(queryBuilder.build());
        // 1.打印总条数
        System.out.println(items.getTotalElements());

        // 2.打印总页数
        System.out.println(items.getTotalPages());

        // 3.每页大小
        System.out.println(items.getSize());
        // 4.当前页
        System.out.println(items.getNumber());

        items.forEach(System.out::println);
    }

    /**
     * 排序
     */
    @Test
    public void testQuerySort() {
        // 构建一个JSON格式的请求体
        NativeSearchQueryBuilder queryBuilder = new NativeSearchQueryBuilder();
        queryBuilder.withQuery(QueryBuilders.termQuery("category", "手机"));

        // 排序
        queryBuilder.withSort(SortBuilders.fieldSort("price").order(SortOrder.DESC));

        // 执行搜索结果
        Page<Item> items = this.itemRepository.search(queryBuilder.build());

        items.forEach(System.out::println);


    }

}