package com.player.controller;

import com.player.model.entity.MusicEntity;
import com.player.model.entity.SingerEntity;
import com.player.service.MusicService;
import com.player.service.SingerService;
import org.jetbrains.annotations.NotNull;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.sound.midi.Soundbank;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/home")
public class HomeController {


    @RequestMapping("/music")
    @ResponseBody
    public MusicEntity getMusic(){
        MusicEntity music = new MusicEntity();
        music.setMusicId(1);
        music.setName("abc");
        music.setFilePath("../music/Aimer - Ref：rain.ogg");
        SingerEntity singer = new SingerEntity();
        singer.setId(2);
        singer.setName("li");

        music.setSingerBySingerId(singer);
        return music;
    }

    @RequestMapping("/music_list")
    @ResponseBody
    public List<MusicEntity> getList(){
        ApplicationContext context = new ClassPathXmlApplicationContext("ApplicationContext.xml");
        MusicService musicService = context.getBean("MusicServiceImpl",MusicService.class);
        return musicService.findAll();

    }

    @RequestMapping("/music_download")
    @ResponseBody
    public MusicEntity musicDownload(@RequestParam(value = "music_file",required = false) MultipartFile uploadFile) throws IOException {
        ApplicationContext context = new ClassPathXmlApplicationContext("ApplicationContext.xml");
        System.out.println("收到数据");
        String fileName = uploadFile.getOriginalFilename();
        String filePath = "E:\\spring-musicPlayer\\src\\main\\webapp\\music\\";
        String relativePath = "../music/";
        File f = new File(filePath);
        String path = filePath+fileName;
        FileOutputStream out = new FileOutputStream(path);
        out.write(uploadFile.getBytes());
        out.flush();
        out.close();
        assert fileName != null;
        String singerName = fileName.split("-")[0];
        String musicName = fileName.split("-")[1];
        SingerEntity singer = new SingerEntity();
        singer.setName(singerName);

        MusicEntity music = new MusicEntity();

        music.setName(musicName);
        music.setFilePath(relativePath+fileName);
        music.setSingerBySingerId(singer);
        MusicService musicService = context.getBean("MusicServiceImpl",MusicService.class);
        musicService.save(music);
        SingerService singerService = context.getBean("SingerServiceImpl",SingerService.class);
        List<SingerEntity> singerEntity = singerService.findByName(singerName);
        if(singerEntity==null){
            singer.setArea("undefined");
            singer.setPicturePath("undefined");
            singerService.save(singer);
        }
        return music;
    }

    @ResponseBody
    @RequestMapping(value = "/music_upload",method = RequestMethod.POST)
    public MusicEntity upload(@RequestParam(value = "musicName") String musicName,@RequestParam(value = "singerName") String singerName,@RequestParam(value = "area") String area,@RequestParam(value = "musicPicture") MultipartFile musicPicture,@RequestParam(value = "singerPicture") MultipartFile singerPicture,@RequestParam(value = "file") MultipartFile file) throws IOException{
        System.out.println("传入参数");
        String musicPath = "E:\\spring-musicPlayer\\src\\main\\webapp\\music-player-ui\\music\\";
        String picturePath = "E:\\spring-musicPlayer\\src\\main\\webapp\\music-player-ui\\image\\";
        //文件上传
        uploadFile(musicName,musicPath,file);
        uploadFile(musicName,picturePath,musicPicture);
        uploadFile(singerName,picturePath,singerPicture);
        System.out.println("文件上传成功");
        //数据库存储
        ApplicationContext context = new ClassPathXmlApplicationContext("ApplicationContext.xml");
        SingerService singerService = context.getBean("SingerServiceImpl",SingerService.class);

        if(singerService.findByName(singerName).size()==0) {
            SingerEntity singer = new SingerEntity();
            singer.setName(singerName);
            singer.setArea(area);
            String fileName1 = singerPicture.getOriginalFilename();
            assert fileName1 != null;
            String fileType = fileName1.split("\\.")[1];
            singer.setPicturePath("./music-player-ui/image/" + singerName + "." + fileType);

            singerService.save(singer);
            System.out.println("singer数据库保存");
        }else{
            SingerEntity singer = singerService.findByName(singerName).get(0);
            singer.setArea(area);
            String fileName1 = singerPicture.getOriginalFilename();
            assert fileName1 != null;
            String fileType = fileName1.split("\\.")[1];
            singer.setPicturePath("./music-player-ui/image/" + singerName + "." + fileType);
            singerService.update(singer);
        }
        SingerEntity getSinger = singerService.findByName(singerName).get(0);

        MusicService musicService = context.getBean("MusicServiceImpl",MusicService.class);

        if (musicService.findByName(musicName).size()==0) {
            System.out.println("插入music");
            MusicEntity music = new MusicEntity();
            music.setName(musicName);
            String fileName2 = musicPicture.getOriginalFilename();
            assert fileName2 != null;
            String fileType2 = fileName2.split("\\.")[1];
            music.setImage("./music-player-ui/image/" + musicName + "." + fileType2);
            String fileName3 = file.getOriginalFilename();
            assert fileName3 != null;
            String fileType3 = fileName3.split("\\.")[1];
            music.setFilePath("./music-player-ui/music/" + musicName + "." + fileType3);
            music.setSingerBySingerId(getSinger);
            musicService.save(music);
            System.out.println("music数据库保存");
            /*return "redirect:index.html";*/
            return music;
        }
        else {
            System.out.println("存在相似文件");
            /*return "redirect:index.html";*/
            return null;
        }

    }

    private void uploadFile(String name, String path,@NotNull MultipartFile file) throws IOException {
        String fileName = file.getOriginalFilename();
        assert fileName != null;
        String fileType = fileName.split("\\.")[1];
        String localPath = path + name + "." + fileType;
        FileOutputStream out = new FileOutputStream(localPath);
        out.write(file.getBytes());
        out.flush();
        out.close();
    }

}
