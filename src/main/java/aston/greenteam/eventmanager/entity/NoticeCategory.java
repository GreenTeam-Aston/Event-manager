package aston.greenteam.eventmanager.entity;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "notice_category")
public class NoticeCategory {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private long noticeCategoryId;

    @Column(name = "title")
    private String title;

    @OneToMany(cascade = CascadeType.DETACH,
            mappedBy = "noticeCategory")
    private Set<Notice> allNotices;

    public long getNoticeCategoryId() {
        return noticeCategoryId;
    }

    public void setNoticeCategoryId(long noticeCategoryId) {
        this.noticeCategoryId = noticeCategoryId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
