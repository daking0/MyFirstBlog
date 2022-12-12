package com.cos.blog.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.sql.Timestamp;

//ORM이란 object를 테이블로 매핑해주는 기술
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
// @DynamicInsert  insert 시에 null값은 안들어감
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, length = 30, unique = true)
    private String username;

    @Column(nullable = false,length = 100)
    private String password;

    @Column(nullable = false,length = 50)
    private String email;

//    @ColumnDefault("'users'")
//    private String roles;

    // 틀리지 않도록 enum으로 지정하고, 그 안에서만 값을 가져올 수 있도록 한다.
    // db에는 roletype이 없어서 , 어노테이션으로 알려줌
    @Enumerated(EnumType.STRING)
    private RoleType roles;

    @CreationTimestamp
    private Timestamp createDate;

}
