package com.player.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "singer", schema = "musicplayer", catalog = "")
public class SingerEntity {
    private int id;
    private String name;
    private String area;
    private String picturePath;
    private Collection<MusicEntity> musicById;

    @Id
    @Column(name = "ID")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
    @Column(name = "AREA")
    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    @Basic
    @Column(name = "PICTURE_PATH")
    public String getPicturePath() {
        return picturePath;
    }

    public void setPicturePath(String picturePath) {
        this.picturePath = picturePath;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SingerEntity that = (SingerEntity) o;
        return id == that.id &&
                Objects.equals(name, that.name) &&
                Objects.equals(area, that.area) &&
                Objects.equals(picturePath, that.picturePath);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, area, picturePath);
    }

    @OneToMany(mappedBy = "singerBySingerId")
    @JsonIgnore
    public Collection<MusicEntity> getMusicById() {
        return musicById;
    }

    public void setMusicById(Collection<MusicEntity> musicById) {
        this.musicById = musicById;
    }
}
