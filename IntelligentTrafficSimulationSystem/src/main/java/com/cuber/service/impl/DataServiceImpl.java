package com.cuber.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cuber.entity.Data;
import com.cuber.entity.DetectSetting;
import com.cuber.entity.Road;
import com.cuber.entity.vo.DataQueryVO;
import com.cuber.entity.vo.DataVO;
import com.cuber.mapper.DataMapper;
import com.cuber.mapper.DetectSettingMapper;
import com.cuber.mapper.RoadMapper;
import com.cuber.service.IDataService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author cuber
 * @since 2022-02-27
 */
@Service
public class DataServiceImpl extends ServiceImpl<DataMapper, Data> implements IDataService {

    @Autowired
    private DataMapper dataMapper;

    @Autowired
    private RoadMapper roadMapper;

    @Autowired
    private DetectSettingMapper detectSettingMapper;

    @Override
    public List<Data> getByRoadId(Integer id) {
        QueryWrapper<Data> qw = new QueryWrapper<>();
        qw.eq("roadid", id);
        return list(qw);
    }

    @Override
    public IPage<DataVO> getByUserId(Integer userid, Integer current, DataQueryVO dataQueryVO) {
        //模糊查询获取对应的道路id
        List<Road> roads = roadMapper.selectList(Wrappers.lambdaQuery(Road.class)
                .select(Road::getId,Road::getName)
                .eq(Road::getUserid, userid)
                .like(StrUtil.isNotEmpty(dataQueryVO.getRoadName()),Road::getName, dataQueryVO.getRoadName()));

        Set<Integer> roadIds = roads.stream().map(Road::getId).collect(Collectors.toSet());
        Map<Integer,String> roadMap=roads.stream().collect(Collectors.toMap(Road::getId,Road::getName));

        //查询所有的检测参数
        List<DetectSetting> detectParams = detectSettingMapper.selectList(Wrappers
                        .lambdaQuery(DetectSetting.class)
                        .eq(DetectSetting::getUserid, userid));
        Map<Integer, String> detectParamMap = detectParams.stream().collect(Collectors.toMap(DetectSetting::getId, DetectSetting::getName));

        Timestamp starttime=null;
        Timestamp endtime=null;
        if (ObjectUtil.isNotEmpty(dataQueryVO.getStartDate())){
            starttime = new Timestamp(dataQueryVO.getStartDate().getTime());
        }
        if (ObjectUtil.isNotEmpty(dataQueryVO.getEndDate())){
            endtime = new Timestamp(dataQueryVO.getEndDate().getTime());
        }

        Page<Data> dataPage = new Page<>(current, 5);
        IPage<Data> dataIPage = dataMapper.selectPage(dataPage,
                Wrappers.lambdaQuery(Data.class)
                        //根据用户id查询
                        .eq(Data::getUserid,userid)
                        //根据车辆驶来方向查询
                        .in(CollUtil.isNotEmpty(dataQueryVO.getDirection()),Data::getDirection,dataQueryVO.getDirection())
                        //道路名称模糊查询
                        .in(CollUtil.isNotEmpty(roadIds),Data::getRoadid,roadIds)
                        //根据时间查询
                        .ge(ObjectUtil.isNotEmpty(starttime),Data::getStarttime,starttime)
                        .le(ObjectUtil.isNotEmpty(endtime),Data::getEndtime,endtime)
        );

        HashMap<Integer, String> sourceMap = new HashMap<>();
        sourceMap.put(1,"自动检测");
        sourceMap.put(2,"手动检测");

        IPage<DataVO> dataVOIPage = dataIPage.convert(DataVO::new);

        dataVOIPage.convert(dataVO->{
            if (dataVO.getParamId()==0){
                dataVO.setParamName("- - -");
            }else{
                dataVO.setParamName(detectParamMap.get(dataVO.getParamId()));
            }
            dataVO.setRoadName(roadMap.get(dataVO.getRoadid()));
            dataVO.setSourceName(sourceMap.get(dataVO.getSource()));
            return dataVO;
        });

        return dataVOIPage;
    }

    @Override
    public Data getById(Integer id) {
        Data data = dataMapper.selectById(id);
        return data;
    }
}
