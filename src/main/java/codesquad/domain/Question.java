package codesquad.domain;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
public class Question {
    private static final String DATE_FORMAT = "yyyy-MM-dd HH:mm";
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_user_id"), nullable = false)
    private User writer;

    @Column(nullable = false, length = 30, updatable = false)
    private String title;

    @Lob
    @Column(nullable = false)
    private String contents;

    @Column(nullable = false, length = 30)
    private String writeTime;

    public Question() {
        this.writeTime = calculateWriteTime();
    }

    public Question(User writer, String title, String contents) {
        this.writer = writer;
        this.title = title;
        this.contents = contents;
        this.writeTime = calculateWriteTime();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getWriter() {
        return writer;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public String getWriteTime() {
        return writeTime;
    }

    public void setWriteTime(String writeTime) {
        this.writeTime = writeTime;
    }

    @Override
    public String toString() {
        return "Question{" +
                "id=" + id +
                ", writer='" + writer + '\'' +
                ", title='" + title + '\'' +
                ", contents='" + contents + '\'' +
                ", writeTime='" + writeTime + '\'' +
                '}';
    }

    public void updateData(Question question) {
        this.contents = question.contents;
        this.title = question.title;
    }

    public boolean unEqualWriter(long id) {
        return !writer.getId().equals(id);
    }

    private String calculateWriteTime() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern(DATE_FORMAT);
        return dtf.format(LocalDateTime.now());
    }

}
