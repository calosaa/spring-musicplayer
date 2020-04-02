package com.player.model.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "music", schema = "musicplayer", catalog = "")
public class MusicEntity {
    private int musicId;
    private String name;
    private String image;
    private String filePath;
    private SingerEntity singerBySingerId;

    @Id
    @Column(name = "MUSIC_ID")
    public int getMusicId() {
        return musicId;
    }

    public void setMusicId(int musicId) {
        this.musicId = musicId;
    }

    @Basic
    @Column(name = "NAME")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "IMAGE")
    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Basic
    @Column(name = "FILE_PATH")
    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MusicEntity that = (MusicEntity) o;
        return musicId == that.musicId &&
                Objects.equals(name, that.name) &&
                Objects.equals(filePath, that.filePath);
    }

    @Override
    public int hashCode() {
        return Objects.hash(musicId, name, filePath);
    }

    @ManyToOne
    @JoinColumn(name = "SINGER_ID", referencedColumnName = "ID", nullable = false)
    public SingerEntity getSingerBySingerId() {
        return singerBySingerId;
    }

    public void setSingerBySingerId(SingerEntity singerBySingerId) {
        this.singerBySingerId = singerBySingerId;
    }
}
