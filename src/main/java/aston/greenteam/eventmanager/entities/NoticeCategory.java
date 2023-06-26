package aston.greenteam.eventmanager.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "notice_category")
@Data
@NoArgsConstructor
public class NoticeCategory {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long noticeCategoryId;

    private String title;

    @OneToMany(mappedBy = "noticeCategory")
    private List<Notice> allNotices; //
}
