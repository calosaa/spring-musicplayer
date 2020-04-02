$(function () {
    $("#submit").click(function () {
        var formData = new FormData();
        formData.append("musicName",$("#data-1").val());
        formData.append("singerName",$("#data-2").val());
        formData.append("area",$("#data-3").val());
        formData.append("musicPicture",$("#data-4")[0].files[0]);
        formData.append("singerPicture",$("#data-5")[0].files[0]);
        formData.append("file",$("#data-6")[0].files[0]);
        $.post({
            url:'home/music_upload',
            processData:false,
            contentType:false,
            data:formData,
            cache:false,
            dataType:'json',
            /*async:false,*/
            success:function (data) {
                console.log(data);
                var infor = eval(data);
                console.log(infor);
                var len = window.musics.length;
                window.musics[len] = infor;
                $singer = $("<div class=\"singer row singer-"+len+"\">\n" +
                    "                    <div class=\"img-div col-md-3\"><img src=\""+data.image+"\" class=\"img\"></div>\n" +
                    "                    <div class=\"singer-infor col-md-4\">\n" +
                    "                        <h5>"+data.name+"</h5>\n" +
                    "                        <h6>"+data.singerBySingerId.name+"</h6>\n" +
                    "                    </div>\n" +
                    "                    <div class=\"col-md-3\"><button type=\"button\" class=\"btn btn-primary menu-play\" onclick=\"play2("+len+")\">play</button>\n" +
                    "                    </div>\n" +
                    "                        <div class=\"col-md-2\">\n" +
                    "                            <button type=\"button\" class=\"close\" data-dismiss=\"modal\" aria-label=\"Close\" style=\"margin-top: 12px\" onclick='deletes("+len+")'>\n" +
                    "                                <span aria-hidden=\"true\">&times;</span>\n" +
                    "                            </button>\n" +
                    "                        </div>\n" +
                    "                </div>");
                $(".menu-list").append($singer);
                alert("插入成功!");
            }
        })
    })
})