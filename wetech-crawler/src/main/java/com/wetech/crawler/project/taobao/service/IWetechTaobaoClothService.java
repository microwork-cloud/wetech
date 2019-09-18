package com.wetech.crawler.project.taobao.service;

import java.util.List;

import com.wetech.crawler.project.aifashion.Tags;
import com.wetech.crawler.project.taobao.domain.WetechTaobaoCloth;


/**
 * 淘宝服装Service接口
 * 
 * @author Levin
 * @date 2019-09-16
 */
public interface IWetechTaobaoClothService 
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
     * 批量删除淘宝服装
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteWetechTaobaoClothByIds(String ids);

    /**
     * 删除淘宝服装信息
     * 
     * @param id 淘宝服装ID
     * @return 结果
     */
    public int deleteWetechTaobaoClothById(Long id);
    
}
