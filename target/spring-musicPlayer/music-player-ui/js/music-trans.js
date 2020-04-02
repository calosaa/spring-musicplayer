
$(function () {

    $.post({
        url:'home/music_list',
        dataType:'json',
        success:function (data) {
            var list = eval(data);
                for (var i=0;i<list.length;i++){
                    window.musics[i] = list[i];
                    $singer = $("<div class=\"singer row singer-"+i+"\">\n" +
                        "                    <div class=\"img-div col-md-3\"><img src=\""+list[i].image+"\" class=\"img\"></div>\n" +
                        "                    <div class=\"singer-infor col-md-4\">\n" +
                        "                        <h5>"+list[i].name+"</h5>\n" +
                        "                        <h6>"+list[i].singerBySingerId.name+"</h6>\n" +
                        "                    </div>\n" +
                        "                    <div class=\"col-md-3\"><button type=\"button\" class=\"btn btn-primary menu-play\" onclick=\"play2("+i+")\">play</button>\n" +
                        "                    </div>\n" +
                        "                        <div class=\"col-md-2\">\n" +
                        "                            <button type=\"button\" class=\"close\" data-dismiss=\"modal\" aria-label=\"Close\" style=\"margin-top: 12px\" onclick='deletes("+i+")'>\n" +
                        "                                <span aria-hidden=\"true\">&times;</span>\n" +
                        "                            </button>\n" +
                        "                        </div>\n" +
                        "                </div>");
                    $(".menu-list").append($singer);
                }
        }

    })
});
function add() {

}