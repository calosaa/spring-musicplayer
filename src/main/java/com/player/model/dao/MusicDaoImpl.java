package com.player.model.dao;

import com.player.model.dao.MusicDao;
import com.player.model.entity.MusicEntity;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MusicDaoImpl implements MusicDao {

    private SessionFactory factory;
    @Override
    public List findAll() {
        return factory.getCurrentSession().createSQLQuery("select * from musicplayer.music;").addEntity(MusicEntity.class).getResultList();
    }

    @Override
    public List findByName(String name) {
        return factory.getCurrentSession().createSQLQuery("select * from musicplayer.music where NAME='"+name+"';").addEntity(MusicEntity.class).getResultList();
    }

    @Override
    public MusicEntity findById(int id) {
        return (MusicEntity) factory.getCurrentSession().createSQLQuery("select * from musicplayer.music where NAME="+id+";").addEntity(MusicEntity.class).getSingleResult();
    }

    @Override
    public List findBySinger(int id){
        return factory.getCurrentSession().createSQLQuery("select * from musicplayer.music where SINGER_ID="+id+";").addEntity(MusicEntity.class).getResultList();
    }
    @Override
    public void save(MusicEntity music) {
        factory.getCurrentSession().save(music);
    }

    @Override
    public void update(MusicEntity music) {
        factory.getCurrentSession().update(music);
    }

    @Override
    public void delete(MusicEntity music) {
        factory.getCurrentSession().delete(music);
    }

    @Autowired
    public void setFactory(SessionFactory factory) {
        this.factory = factory;
    }
}
