package com.wetech.crawler.project.taobao.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wetech.crawler.project.aifashion.Tags;
import com.wetech.crawler.project.common.Convert;
import com.wetech.crawler.project.common.DateUtils;
import com.wetech.crawler.project.taobao.domain.WetechTaobaoCloth;
import com.wetech.crawler.project.taobao.mapper.WetechTaobaoClothMapper;
import com.wetech.crawler.project.taobao.service.IWetechTaobaoClothService;

/**
 * 淘宝服装Service业务层处理
 * 
 * @author Levin
 * @date 2019-09-16
 */
@Service
public class WetechTaobaoClothServiceImpl implements IWetechTaobaoClothService 
{
    @Autowired
    private WetechTaobaoClothMapper wetechTaobaoClothMapper;

    /**
     * 查询淘宝服装
     * 
     * @param id 淘宝服装ID
     * @return 淘宝服装
     */
    @Override
    public WetechTaobaoCloth selectWetechTaobaoClothById(Long id)
    {
        return wetechTaobaoClothMapper.selectWetechTaobaoClothById(id);
    }

    /**
     * 查询淘宝服装列表
     * 
     * @param wetechTaobaoCloth 淘宝服装
     * @return 淘宝服装
     */
    @Override
    public List<WetechTaobaoCloth> selectWetechTaobaoClothList(WetechTaobaoCloth wetechTaobaoCloth)
    {
        return wetechTaobaoClothMapper.selectWetechTaobaoClothList(wetechTaobaoCloth);
    }

    /**
     * 新增淘宝服装
     * 
     * @param wetechTaobaoCloth 淘宝服装
     * @return 结果
     */
    @Override
    public int insertWetechTaobaoCloth(WetechTaobaoCloth wetechTaobaoCloth)
    {
        wetechTaobaoCloth.setCreateTime(DateUtils.getNowDate());
        return wetechTaobaoClothMapper.insertWetechTaobaoCloth(wetechTaobaoCloth);
    }

    /**
     * 修改淘宝服装
     * 
     * @param wetechTaobaoCloth 淘宝服装
     * @return 结果
     */
    @Override
    public int updateWetechTaobaoCloth(WetechTaobaoCloth wetechTaobaoCloth)
    {
        wetechTaobaoCloth.setUpdateTime(DateUtils.getNowDate());
        return wetechTaobaoClothMapper.updateWetechTaobaoCloth(wetechTaobaoCloth);
    }

    /**
     * 删除淘宝服装对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteWetechTaobaoClothByIds(String ids)
    {
        return wetechTaobaoClothMapper.deleteWetechTaobaoClothByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除淘宝服装信息
     * 
     * @param id 淘宝服装ID
     * @return 结果
     */
    public int deleteWetechTaobaoClothById(Long id)
    {
        return wetechTaobaoClothMapper.deleteWetechTaobaoClothById(id);
    }

}
