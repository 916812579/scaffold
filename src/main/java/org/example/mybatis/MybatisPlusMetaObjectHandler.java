package org.example.mybatis;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@Slf4j
public class MybatisPlusMetaObjectHandler implements MetaObjectHandler {


    @Override
    public void insertFill(MetaObject metaObject) {
        log.info("start insert fill ....");
        try {
            this.setFieldValByName("createTime", LocalDateTime.now(), metaObject);
            this.setFieldValByName("updateTime", LocalDateTime.now(), metaObject);
            // 根据具体情况获取当前用户ID
            Long userId = null;
            this.setFieldValByName("createBy", userId, metaObject);
            this.setFieldValByName("updateBy", userId, metaObject);
        } catch (Exception e) {
            log.warn(e.getMessage());
        }

    }

    @Override
    public void updateFill(MetaObject metaObject) {
        log.info("start update fill ....");
        // 根据具体情况获取当前用户ID
        Long userId = null;
        try {
            this.setFieldValByName("updateTime", LocalDateTime.now(), metaObject);
            this.setFieldValByName("updateBy", userId, metaObject);
        } catch (Exception e) {
            log.warn(e.getMessage(), e);
        }
    }
}