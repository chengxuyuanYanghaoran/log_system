package com.hlwxy.xu_boot2.system.dao;
import com.hlwxy.xu_boot2.system.domain.DataDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author shkstart
 * @create 2019-09-21-22:59
 */
@Mapper
public interface DataDao {
     List<DataDO> findDel();
     void del(List<DataDO> list);
}
