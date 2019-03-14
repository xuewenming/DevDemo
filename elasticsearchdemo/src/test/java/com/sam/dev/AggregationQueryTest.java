package com.sam.dev;

import com.sam.pojo.Item;
import com.sam.repository.ItemRepository;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.bucket.terms.StringTerms;
import org.elasticsearch.search.aggregations.metrics.avg.InternalAvg;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.aggregation.AggregatedPage;
import org.springframework.data.elasticsearch.core.query.FetchSourceFilter;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * 聚合查询
 * @author Sam
 * @date 2019/3/14
 * @time 20:24
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class AggregationQueryTest {

    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;
    @Autowired
    private ItemRepository itemRepository;

    /**
     * 桶就是分组，这里按照品牌brand进行分组
     *
     * GET /items/_search
     * {
     *   "size": 0,
     *   "aggs": {
     *     "brands": {
     *       "terms": {
     *         "field": "brand"
     *       },
     *       "aggs": {
     *         "num": {
     *           "sum": {
     *             "field": "id"
     *           }
     *         }
     *       }
     *     }
     *   }
     * }
     */
    @Test
    public void testAgg() {
        // 1、构建查询JSON格式请求体
        NativeSearchQueryBuilder queryBuilder = new NativeSearchQueryBuilder();
        // 2、不进行查询数据
        queryBuilder.withSourceFilter(new FetchSourceFilter(null, null));
        // 3、创建聚合对象
        queryBuilder.addAggregation(AggregationBuilders
                .terms("brands").field("brand"));
        // 4、查询
        AggregatedPage<Item> aggregatedPage = (AggregatedPage<Item>) this.itemRepository.search(queryBuilder.build());
        // 5、解析
        StringTerms terms = (StringTerms) aggregatedPage.getAggregation("brands");
        List<StringTerms.Bucket> buckets = terms.getBuckets();
        for (StringTerms.Bucket bucket : buckets) {
            System.out.println(bucket.getKeyAsString());
            System.out.println(bucket.getDocCount());
        }
    }


    /**
     * 嵌套聚合求平均值
     */
    @Test
    public void testSubAgg() {
        // 1、构建JSON格式的查询体
        NativeSearchQueryBuilder queryBuilder = new NativeSearchQueryBuilder();
        // 2、不查询任何结果
        queryBuilder.withSourceFilter(new FetchSourceFilter(null,null));
        // 3、添加一个新的聚合brands，在brands聚合桶内进行聚合嵌套，求平均值。
        queryBuilder.addAggregation(AggregationBuilders.terms("brands").field("brand")
                .subAggregation(AggregationBuilders.avg("brandAvg").field("price")));


        // 4.查询
        AggregatedPage<Item> itemAggregatedPage =
                (AggregatedPage<Item>) this.itemRepository.search(queryBuilder.build());
        // 5、解析
        // 5.1 从结果中把brands聚合取出
        StringTerms terms = (StringTerms) itemAggregatedPage.getAggregation("brands");
        // 5.2 获取桶
        List<StringTerms.Bucket> buckets = terms.getBuckets();
        for (StringTerms.Bucket bucket : buckets) {

            // 获取子聚合结果
            InternalAvg avg = (InternalAvg) bucket.getAggregations().asMap().get("brandAvg");
            System.out.println(avg.getValue());
        }



    }
}
