package com.ruzhan.rxrepository.db.entity;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import com.ruzhan.rxrepository.model.UserModel;

/**
 * Created by ruzhan123 on 2018/6/21.
 */
@Entity(tableName = "user_entity")
public class UserEntity {

    public static final String NORMAL_ID = "normal_id";

    @NonNull
    @PrimaryKey
    public String id;
    public String name;
    public String desc;

    @Ignore
    public UserEntity() {
        id = NORMAL_ID;
    }

    public UserEntity(@NonNull String id, String name, String desc) {
        this.id = id;
        this.name = name;
        this.desc = desc;
    }

    public static UserEntity getUserEntity(UserModel bean) {
        UserEntity entity = new UserEntity();
        if (bean != null) {
            entity.name = bean.name;
            entity.desc = bean.desc;
        }
        return entity;
    }

    public static UserModel getUserModel(UserEntity entity) {
        UserModel bean = new UserModel();
        if (entity != null) {
            bean.name = entity.name;
            bean.desc = entity.desc;
        }
        return bean;
    }
}
