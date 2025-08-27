package com.wingflare.task.admin.dao;

import com.wingflare.task.admin.core.model.TaskGroup;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by xuxueli on 16/9/30.
 */
@Mapper
public interface TaskGroupDao {

    public List<TaskGroup> findAll();

    public List<TaskGroup> findByAddressType(@Param("addressType") int addressType);

    public int save(TaskGroup taskGroup);

    public int update(TaskGroup taskGroup);

    public int remove(@Param("id") int id);

    public TaskGroup load(@Param("id") int id);

    public List<TaskGroup> pageList(@Param("offset") int offset,
                                    @Param("pagesize") int pagesize,
                                    @Param("appname") String appname,
                                    @Param("title") String title);

    public int pageListCount(@Param("offset") int offset,
                             @Param("pagesize") int pagesize,
                             @Param("appname") String appname,
                             @Param("title") String title);

}
