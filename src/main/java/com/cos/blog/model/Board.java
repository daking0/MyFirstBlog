package com.cos.blog.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false,length = 100)
    private String title;

    @Lob //대용량 데이터에 사용
    private String content;


    // 조회수
    private int count;

    @OneToMany(mappedBy = "board",fetch = FetchType.EAGER)
    //mappedBy : 연관관계의 주인이 아니다.(난 FK가 아니에요, DB에 칼럼을 만들지 마세요)
    //fetch = FetchType.LAZY : 엄청 많이 가져오니까, 필요하면 가져오고 아니면 안가져올게
    private List<Reply> reply;

    @ManyToOne(fetch = FetchType.EAGER)
    //Many =Many, User=One
    //fetch = FetchType.EAGER : user정보는 하나니까 바로 가져올게
    @JoinColumn(name="userId")
    //필드이름은 userId로 만들어지고, 연관관게는 manytoone
    //User를 참조하고, 자동으로 FK가 만들어진다
    private User user;
    @CreationTimestamp
    private Timestamp createData;
}
