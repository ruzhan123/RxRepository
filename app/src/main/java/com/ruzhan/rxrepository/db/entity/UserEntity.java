package com.ruzhan.rxrepository.db.entity;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.ruzhan.rxrepository.model.UserModel;

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
            entity.name = bean.getName();
            entity.desc = bean.getDesc();
        }
        return entity;
    }

    public static UserModel getUserModel(UserEntity entity) {
        UserModel bean = new UserModel();
        if (entity != null) {
            bean.setName(entity.name);
            bean.setDesc(entity.desc);
        }
        return bean;
    }
}
