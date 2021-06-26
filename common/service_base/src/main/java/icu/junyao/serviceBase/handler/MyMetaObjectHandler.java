package icu.junyao.serviceBase.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * @author wu
 */
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {
    /**
     * MP功能 自动插入创建时间
     * @param metaObject
     */
    @Override
    public void insertFill(MetaObject metaObject) {
        this.strictInsertFill(metaObject, "gmtCreate", LocalDateTime::now, LocalDateTime.class);
        this.strictUpdateFill(metaObject, "gmtModified", LocalDateTime::now, LocalDateTime.class);
    }

    /**
     * MP 功能 自动插入修改时间
     * @param metaObject
     */
    @Override
    public void updateFill(MetaObject metaObject) {
        this.strictUpdateFill(metaObject, "gmtModified", LocalDateTime::now, LocalDateTime.class);
    }
}
