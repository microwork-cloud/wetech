package com.wetech.crawler.project.taobao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.wetech.crawler.project.aifashion.Tags;
import com.wetech.crawler.project.taobao.domain.WetechTaobaoCloth;


/**
 * 淘宝服装Mapper接口
 * 
 * @author Levin
 * @date 2019-09-16
 */
@Mapper
public interface WetechTaobaoClothMapper 
{
    /**
     * 查询淘宝服装
     * 
     * @param id 淘宝服装ID
     * @return 淘宝服装
     */
    public WetechTaobaoCloth selectWetechTaobaoClothById(Long id);

    /**
     * 查询淘宝服装列表
     * 
     * @param wetechTaobaoCloth 淘宝服装
     * @return 淘宝服装集合
     */
    public List<WetechTaobaoCloth> selectWetechTaobaoClothList(WetechTaobaoCloth wetechTaobaoCloth);

    /**
     * 新增淘宝服装
     * 
     * @param wetechTaobaoCloth 淘宝服装
     * @return 结果
     */
    public int insertWetechTaobaoCloth(WetechTaobaoCloth wetechTaobaoCloth);

    /**
     * 修改淘宝服装
     * 
     * @param wetechTaobaoCloth 淘宝服装
     * @return 结果
     */
    public int updateWetechTaobaoCloth(WetechTaobaoCloth wetechTaobaoCloth);

    /**
     * 删除淘宝服装
     * 
     * @param id 淘宝服装ID
     * @return 结果
     */
    public int deleteWetechTaobaoClothById(Long id);

    /**
     * 批量删除淘宝服装
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteWetechTaobaoClothByIds(String[] ids);
    
    @Select("SELECT \"品牌\" as tagName,shop_title as tagValue, sum(cloth_amount) as tagWeight FROM ry.wetech_taobao_cloth group by shop_title order by tagWeight desc ")
    public List<Tags> getBrandTags();
    
    @Select("SELECT * FROM ry.wetech_taobao_cloth ORDER BY cloth_amount desc limit 10")
    public List<WetechTaobaoCloth> getTopClothList();
}
