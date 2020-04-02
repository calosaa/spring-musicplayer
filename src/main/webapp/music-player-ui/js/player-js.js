
function play() {
    var audio = $("audio")[0];
    $plays = $("#plays");
    if (audio.paused){
        audio.play();
        $plays.html("<img src=\"./music-player-ui/icon/pause.svg\" alt=\"control\">");
    }
    else{
        audio.pause();
        $plays.html("<img src=\"./music-player-ui/icon/play.svg\" alt=\"control\">");
    }
}
function play2(i){
    window.point = i;
    var audio = $("audio")[0];
    audio.src = musics[window.point]['filePath'];
    audio.play();
    $(".background-img").prop("src",musics[point]['image']);
    $(".music-img").prop("src",musics[point]['image']);
    $("#plays").html("<img src=\"./music-player-ui/icon/pause.svg\" alt=\"control\">");
}
function deletes(music){
    $(".singer-"+music).remove();
    musics[i] = null;
    for(var i=music;i<musics.length;i++){
        if(musics[i+1]!==null) {
            musics[i] = musics[i + 1];
        }else{
            musics[i]=null;
        }
    }
}
function last(){

    if (window.point>0) {
        window.point -= 1;
        play2(window.point);
    }else{
        window.point = window.musics.length-1;
        play2(window.point);
    }
    window.angle=0;
    $('.music-img-div').css('transform', 'rotate('+window.angle+'deg)');
}
function next(){
    if(window.point<window.musics.length-1) {
        window.point += 1;
        play2(window.point);
    }else{
        window.point = 0;
        play2(window.point);
    }
    window.angle=0;
    $('.music-img-div').css('transform', 'rotate('+window.angle+'deg)');
}
$(function () {
    var tag = false,dx = 0,left = 0,bgleft = 0,angle=0;
    $("#audio")[0].addEventListener("timeupdate",function () {
        console.log("listen");
        var audio = $("audio")[0];
        var time = audio.currentTime;
        var all_time = audio.duration;
        console.log(time+";"+all_time);
        var left = (time/all_time)*500;
        console.log(left);
        if(left===500) {
            window.angle=0;
            $('.music-img-div').css('transform', 'rotate('+window.angle+'deg)');
            $("#plays").html("<img src=\"./music-player-ui/icon/play.svg\" alt=\"control\">");
            next();
        }
        else{
            window.angle+=1.5;
            $('#pointer').css('left', left);
            $('#progress-line').animate({width: left}, 1);
            $('.music-img-div').css('transform', 'rotate('+window.angle+'deg)');

        }

    });
    $('#pointer').mousedown(function(e) {
        dx = e.pageX - left;
        tag = true;
    });
    $(document).mouseup(function(e) {
        tag = false;
    });
    $('#pointer').mousemove(function(e) {//鼠标移动
        if (tag) {
            left = e.pageX - dx;
            if (left <= 0) {
                left = 0;
            } else if (left > 500) {
                left = 500;
            }

            $('#pointer').css('left', left);
            $('#progress-line').animate({width: left}, 1);
            var audio = $("audio")[0];
            var volume = left / 500;
            audio.currentTime = volume * audio.duration;
        }

    });
    $('#progress-bar').click(function(e) {//鼠标点击
        if (!tag) {
            bgleft = $('#progress-bar').offset().left;
            left = e.pageX - bgleft;
            if (left <= 0) {
                left=0;
            }else if (left > 500) {
                left = 500;
            }

            $('#pointer').css('left', left);
            $('#progress-line').animate({width: left}, 300);//
            var audio = $("audio")[0];
            var volume = left / 500;
            audio.currentTime = volume * audio.duration;

        }
    });

});
