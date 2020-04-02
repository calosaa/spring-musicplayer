package com.player.model.dao;

import com.player.model.dao.SingerDao;
import com.player.model.entity.SingerEntity;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SingerDaoImpl implements SingerDao {

    private SessionFactory factory;

    @Override
    public List findAll() {
        return factory.getCurrentSession().createSQLQuery("select * from musicplayer.singer;").addEntity(SingerEntity.class).getResultList();
    }

    @Override
    public List findByName(String name) {
        return  factory.getCurrentSession().createSQLQuery("select * from musicplayer.singer where NAME='"+name+"';").addEntity(SingerEntity.class).getResultList();
    }

    @Override
    public SingerEntity findById(int id) {
        return (SingerEntity) factory.getCurrentSession().createSQLQuery("select * from musicplayer.singer where NAME="+id+";").addEntity(SingerEntity.class).getSingleResult();
    }

    @Override
    public void save(SingerEntity singer) {
        factory.getCurrentSession().save(singer);
    }

    @Override
    public void update(SingerEntity singer) {
        factory.getCurrentSession().update(singer);
    }

    @Override
    public void delete(SingerEntity singer) {
        factory.getCurrentSession().delete(singer);
    }

    @Autowired
    public void setFactory(SessionFactory factory) {
        this.factory = factory;
    }
}
