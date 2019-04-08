    //选取的Banner
    var bannerList;
    var bannerIndex;
    var currentBanner;


////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////资讯管理////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////
/**
 * 添加资讯
 */
function addBanner(bodyParam){
    var httpR = new createHttpR(url+'addBanner','post','text',bodyParam,'callBack');
    httpR.HttpRequest(function(response){
        var obj = JSON.parse(response);
        var status = obj['status'];
        //var msg = obj['msg'];
        if(status=='0'){
            alert("新建成功！");
            //window.location.reload();
            window.history.go(-1);
        }
    });
}
/**
 * 查询单条资讯
 */
function selectBanner(id){
    var bodyParam={'id':id};
    var httpR = new createHttpR(url+'selectBanner','post','text',bodyParam,'callBack');
    httpR.HttpRequest(function(response){
        var obj = JSON.parse(response);
        var status = obj['status'];
        //var msg = obj['msg'];
        if(status=='0'){
            var data = obj['msg'];
            currentBanner=data;
            for (var item in data) {
                $('#'+item).val(data[item]);
                if(item=='img'){
                    image=data[item];
                }
            }
            initFiles();
            allGoods();
        }
    });
}


/**
 * 修改资讯
 * @param id
 */
function updateBanner(bodyParam){

    var httpR = new createHttpR(url+'updateBanner','post','text',bodyParam,'callBack');
    httpR.HttpRequest(function(response){
        var obj = JSON.parse(response);
        var status = obj['status'];
        //var msg = obj['msg'];
        if(status=='0'){
            alert("修改成功！");
            window.location.reload();
            //window.location.href="interface.html?index="+interfaceIndex;
        }
    });
}

/**
 * 删除资讯
 * @param id
 */
function deleteBanner(id){
    var bodyParam={'id':id};
    var httpR = new createHttpR(url+'deleteBanner','post','text',bodyParam,'callBack');
    httpR.HttpRequest(function(response){
        var obj = JSON.parse(response);
        var status = obj['status'];
        //var msg = obj['msg'];
        if(status=='0'){
            alert("删除成功！");
            window.location.reload();
        }
    });
}
/**
 * 查询资讯
 * @param bannername
 * @param currentPage
 * @param pageSize
 */
function  queryBanners (searchText,currentPage,pageSize) {

    //分页显示的页码数  必须为奇数
    var showPage=7;
    if(searchText==null||searchText==''){
        var bodyParam={'page':currentPage,'size':pageSize};
    }
    else{
        var bodyParam={'page':currentPage,'size':pageSize,'searchText':searchText};
    }

    var httpR = new createHttpR(url+'listBanner','post','text',bodyParam,'callBack');
    httpR.HttpRequest(function(response){
        var obj = JSON.parse(response);
        var status = obj['status'];
        var msg = obj['msg'];
        if(status=='0'){
            var data=msg['data'];
            bannerList=msg['data'];
            var html='';

            for(var o in data){
                html+='<tr index='+o+' class="gradeX">\n' +
                    '<td style="line-height: 50px;">'+data[o].id+'</td>\n' +
                    '<td style="line-height: 50px;width:120px"><img src="'+data[o].img+'" width="100px" height="50px"></td>\n' +
                    '<td style="line-height: 50px;">'+data[o].goods_name+'</td>\n' +
                    '<td style="line-height: 50px;">'+data[o].img_desc+'</td>\n' ;

                html+='<td style="line-height: 50px;"><a class="updateBanner" href="" index='+o+' data-toggle="modal" ><span class="label label-info label-mini">修改</span></a>   ' +
                    '<a class="deleteBanner" href="" index='+o+' data-toggle="modal" data-target="#delete-box"><span class="label label-info label-mini">删除</span></a></td>\n';
                html+='</tr>';
            }
            $('#contentTbody').html(html);
            var num=msg['num'];
            if(num>0) {
                var pageHtml = '';
                var totalPage = 0;
                if (num % pageSize == 0) {
                    totalPage = num / pageSize;
                }
                else {
                    totalPage = Math.ceil(num / pageSize);
                }

                if (currentPage == 1) {
                    pageHtml += '<li class="disabled"><a href="#">|&laquo;</a></li>';
                    pageHtml += '<li class="disabled"><a href="#">&laquo;</a></li>';
                }
                else {
                    pageHtml += '<li ><a href="#" class="pageBtn" index="1">|&laquo;</a></li>';
                    pageHtml += '<li ><a href="#" class="prevBtn" index="">&laquo;</a></li>';
                }
                if (totalPage <= showPage) {
                    for (var i = 1; i < Number(totalPage) + 1; i++) {
                        if (currentPage == i) {
                            pageHtml += '<li class="active"><a href="#" >' + i + '</a></li>';
                        }
                        else {
                            pageHtml += '<li><a href="#" class="pageBtn" index="' + i + '">' + i + '</a></li>';
                        }
                    }
                }
                else {
                    if (currentPage <= (showPage - 1) / 2) {
                        for (var i = 1; i <= showPage; i++) {
                            if (currentPage == i) {
                                pageHtml += '<li class="active"><a href="#" >' + i + '</a></li>';
                            }
                            else {
                                pageHtml += '<li><a href="#" class="pageBtn" index="' + i + '">' + i + '</a></li>';
                            }
                        }
                    }
                    else if (totalPage - currentPage < (showPage - 1) / 2) {
                        for (var i = Number(totalPage) - showPage; i <= totalPage; i++) {
                            if (currentPage == i) {
                                pageHtml += '<li class="active"><a href="#" >' + i + '</a></li>';
                            }
                            else {
                                pageHtml += '<li><a href="#" class="pageBtn" index="' + i + '">' + i + '</a></li>';
                            }
                        }
                    }
                    else {
                        for (var i = Number(currentPage) - (showPage - 1) / 2; i <= Number(currentPage) + (showPage - 1) / 2; i++) {
                            if (currentPage == i) {
                                pageHtml += '<li class="active"><a href="#" >' + i + '</a></li>';
                            }
                            else {
                                pageHtml += '<li><a href="#" class="pageBtn" index="' + i + '">' + i + '</a></li>';
                            }
                        }
                    }


                }

                if (currentPage == totalPage) {
                    pageHtml += '<li class="disabled"><a href="#">&raquo;</a></li>';
                    pageHtml += '<li class="disabled"><a href="#">&raquo;|</a></li>';
                }
                else {
                    pageHtml += '<li class="nextBtn" index=""><a href="#">&raquo;</a></li>';
                    pageHtml += '<li class="pageBtn" index="' + totalPage + '"><a href="#">&raquo;|</a></li>';
                }
                /* pageHtml+='<li><input type="text" id="jumpPageText" class="paging-inpbox form-control"></li>\n' +
                     '<li><button type="button" id="jumpBtn" class="paging-btnbox btn btn-primary">跳转</button></li>\n' +
                     '<li><span class="number-of-pages">共'+totalPage+'页</span></li>';*/

                $('.pagination').html(pageHtml);
            }


        }
    });
}


    /**
     * 所有商品
     */
    function allGoods(){
        var bodyParam={'status':1};
        var httpR = new createHttpR(url+'allGoods','post','text',bodyParam,'callBack');
        httpR.HttpRequest(function(response){
            var obj = JSON.parse(response);
            var status = obj['status'];
            //var msg = obj['msg'];
            if(status=='0'){
                var data = obj['msg']['data'];
                var html='';
                var index=1;
                for (var item in data) {
                    html+='<optgroup label="'+item+'" name="'+item+'">\n';
                    for(var o=0;o<data[item].length;o++){
                        html+='<option value="'+data[item][o].id+'" >'+data[item][o].goods_name+'</option>\n';
                    }
                    html+='</optgroup>\n';
                    index++;
                }
                $('#goods_id').html(html);
                $('#goods_id').multiselect({
                    enableClickableOptGroups: true,
                    enableCollapsibleOptGroups: true,
                    includeSelectAllOption: false,
                    dropRight: true,
                    maxHeight: 200,
                    /*onChange: function(option, checked) {
                        alert($('#required').val());

                    },*/
                    nonSelectedText: '请选择',
                    numberDisplayed: 4,
                    enableFiltering: true,
                    //allSelectedText:'全部',
                });


                if(currentBanner!=null){
                    $('#goods_id').val(currentBanner.goods_id);
                    $("#goods_id").multiselect("refresh");

                }

            }
        });
    }

