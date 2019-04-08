//选取的Goods
var goodsList;
var goodsIndex;
var currentGoods;

////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////商品管理////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////
/**
 * 添加商品
 */
function addGoods(bodyParam){
    var httpR = new createHttpR(url+'addGoods','post','text',bodyParam,'callBack');
    httpR.HttpRequest(function(response){
        var obj = JSON.parse(response);
        var status = obj['status'];
        //var msg = obj['msg'];
        if(status=='0'){
            alert("新建成功！");
            //window.location.reload();
        }
    });
}

/**
 * 查询单条商品
 */
function selectGoods(id){
    var bodyParam={'id':id};
    var httpR = new createHttpR(url+'selectGoods','post','text',bodyParam,'callBack');
    httpR.HttpRequest(function(response){
        var obj = JSON.parse(response);
        var status = obj['status'];
        //var msg = obj['msg'];
        if(status=='0'){
            var data = obj['msg'];
            currentGoods=data;
            for (var item in data) {
                $('#'+item).text(data[item]);
            }
            //图片
            if(data['img']!=''){
                var images=JSON.parse(data['img']);
                var html='<ul id="dowebok">';
                for(var i in images){
                    html+='<li><img  src="'+images[i]+'" alt="图片1"></li>';
                }
                html+=' </ul>';
                $('.album').html(html);
            }

            //主打热门
            if(data['is_hot']=='1'){
                $('#is_hot_text').removeClass('hide');
                $('#canHot').removeClass('hide');
            }
            else if(data['is_hot']=='0'){
                $('#setHot').removeClass('hide');
            }
            if(data['is_main']=='1'){
                $('#is_main_text').removeClass('hide');
            }
            if(data['is_limit_amount']=='1'){
                $('#limit_amount_div').removeClass('hide');
            }
            $('#dowebok').viewer({
                url: 'data-original',
            });
            //详情图片
            if(data['goods_desc_img']!=''){
                var descImages=JSON.parse(data['goods_desc_img']);
                var html='';
                for(var i in descImages){
                    html+='<img src="'+descImages[i]+'" class="col-sm-8">';
                }
                $('#goods_desc_img').html(html);
            }

        }
    });
}
function selectGoods_bak(id){
    var bodyParam={'id':id};
    var httpR = new createHttpR(url+'selectGoods','post','text',bodyParam,'callBack');
    httpR.HttpRequest(function(response){
        var obj = JSON.parse(response);
        var status = obj['status'];
        //var msg = obj['msg'];
        if(status=='0'){
            var data = obj['msg'];
            currentGoods=data;
            for (var item in data) {
                $('#'+item).val(data[item]);
                if(item=='img'){
                    images=JSON.parse(data[item]);
                    for(var i in images){
                        configArray.push({caption: "图片"+(Number(i)+1), width: "120px", url: uploadUrl+"filesDelete", key: (Number(i)+1)+""});
                    }
                }
                else if(item=='seller_id'){
                    $("#seller_id").trigger("change");
                }
            }
            initFiles();
        }
    });
}
/**
 * 修改商品
 * @param id
 */
function updateGoods(bodyParam){

    var httpR = new createHttpR(url+'updateGoods','post','text',bodyParam,'callBack');
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
function setHotGoods(bodyParam){

    var httpR = new createHttpR(url+'updateGoods','post','text',bodyParam,'callBack');
    httpR.HttpRequest(function(response){
        var obj = JSON.parse(response);
        var status = obj['status'];
        //var msg = obj['msg'];
        if(status=='0'){
            queryGoodss($('#searchText').val(),currentPage,pageSize);
            //window.location.href="interface.html?index="+interfaceIndex;
        }
    });
}
function setSinHotGoods(bodyParam){

    var httpR = new createHttpR(url+'updateGoods','post','text',bodyParam,'callBack');
    httpR.HttpRequest(function(response){
        var obj = JSON.parse(response);
        var status = obj['status'];
        //var msg = obj['msg'];
        if(status=='0'){
            alert("修改成功！");
            window.location.reload();
        }
    });
}

function mulUpdateGoods(bodyParam){

    var httpR = new createHttpR(url+'mulUpdateGoods','post','text',bodyParam,'callBack');
    httpR.HttpRequest(function(response){
        var obj = JSON.parse(response);
        var status = obj['status'];
        //var msg = obj['msg'];
        if(status=='0'){
            queryGoodss($('#searchText').val(),currentPage,pageSize);
        }
    });
}

/**
 * 删除商品
 * @param id
 */
function deleteGoods(id){
    var bodyParam={'id':id};
    var httpR = new createHttpR(url+'deleteGoods','post','text',bodyParam,'callBack');
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
 * 查询商品
 * @param goodsname
 * @param currentPage
 * @param pageSize
 */
function  queryGoodss (searchText,currentPage,pageSize) {

    //分页显示的页码数  必须为奇数
    var showPage=7;
    if(searchText==null||searchText==''){
        var bodyParam={'page':currentPage,'size':pageSize};
    }
    else{
        var bodyParam={'page':currentPage,'size':pageSize,'searchtext':searchText};
    }

    var httpR = new createHttpR(url+'listGoods','post','text',bodyParam,'callBack');
    httpR.HttpRequest(function(response){
        var obj = JSON.parse(response);
        var status = obj['status'];
        var msg = obj['msg'];
        if(status=='0'){
            var data=msg['data'];
            goodsList=msg['data'];
            var html='';

            for(var o in data){
                html+='<tr index='+o+' class="gradeX">\n' +
                    '<td class="updateCheckBoxTr">\n' +
                    '<input index='+o+' class="updateCheckbox" type="checkbox" >\n' +
                    '</td>' +
                    '<td>'+data[o].goods_num+'</td>\n' +
                    '<td>'+data[o].goods_name+'</td>\n' +
                    '<td>'+data[o].ctg_name+'</td>\n' +
                    '<td>'+data[o].shop_name+'</td>\n' +
                    '<td>'+data[o].sell_price+'</td>\n' +
                    '<td>'+data[o].new_stock_amount+'</td>\n' +
                    '<td>'+data[o].sales+'</td>\n' ;

                html+='<td>';
                if(data[o].is_on_shelf=='1'){
                    html+='<span class="label label-success label-mini">已上架</span> ';
                }
                else{
                    html+='<span class="label label-default label-mini">未上架</span> ';
                }
                /*if(data[o].is_promotion=='1'){
                    html+='<span class="label label-success label-mini">优惠券</span> ';
                }*/
                if(data[o].is_main=='1'){
                    html+='<span class="label label-success label-mini">主打</span> ';
                }
                if(data[o].is_hot=='1'){
                    html+='<span class="label label-success label-mini">热门</span> ';
                }
                html+='</td>\n';

                if(data[o].status=='1'){
                    html+='<td><span class="label label-success label-mini">可用</span></td>\n';
                }
                else{
                    html+='<td><span class="label label-default label-mini">禁用</span></td>\n';
                }
                html+='<td><a class="updateGoods" href="#" index='+o+' data-toggle="modal" ><span class="label label-info label-mini">查看</span></a>   ';
                if(data[o].is_hot=='1'){
                    html+='<a class="singleCanGoods" href="javascript:;"  index='+o+'><span class="label label-info label-mini">取消热门</span></a> ';
                }
                else{
                    html+='<a class="singleSetGoods" href="javascript:;" index='+o+'><span class="label label-info label-mini">设置热门</span></a> ';
                }
                html+='</td>';
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
 * 查询商家
 */
function  selectSeller (id) {
    var bodyParam={'audit_status':'已审核'};
    var httpR = new createHttpR(url+'allSeller','post','text',bodyParam,'callBack');
    httpR.HttpRequest(function(response){
        var obj = JSON.parse(response);
        var status = obj['status'];
        var msg = obj['msg'];
        if(status=='0'){
            var data=msg['data'];

            var html='';
            html+='<option value="" ></option>\n';
            for(var o=0;o<data.length;o++){
                html+='<option value="'+data[o].id+'" >'+data[o].shop_name+'</option>\n';
            }
            $('#seller_id').html(html);
        }
        selectGoods_category(id);
    });
}
/**
 * 查询商品费用
 */
function  selectGoods_category (id) {
    var bodyParam={};
    var httpR = new createHttpR(url+'allGoods_category','post','text',bodyParam,'callBack');
    httpR.HttpRequest(function(response){
        var obj = JSON.parse(response);
        var status = obj['status'];
        var msg = obj['msg'];
        if(status=='0'){
            var data=msg['data'];

            var html='';
            html+='<option value="" ></option>\n';
            for(var o=0;o<data.length;o++){
                html+='<option value="'+data[o].id+'" >'+data[o].ctg_name+'</option>\n';
            }
            $('#ctg_id').html(html);

            //update赋值
            if(id!=null&&id!='0'){
                selectGoods(id);
            }
        }
    });
}
/**
 * 查询商品费用
 */
function  selectPostage (seller_id) {
    var bodyParam={'seller_id':seller_id};
    var httpR = new createHttpR(url+'allPostage','post','text',bodyParam,'callBack');
    httpR.HttpRequest(function(response){
        var obj = JSON.parse(response);
        var status = obj['status'];
        var msg = obj['msg'];
        if(status=='0'){
            var data=msg['data'];

            var html='';
            html+='<option value="" ></option>\n';
            for(var o=0;o<data.length;o++){
                html+='<option value="'+data[o].id+'" >'+data[o].postage_name+'</option>\n';
            }
            $('#postage_id').html(html);

            //udpate
            if(currentGoods!=null){
                $('#postage_id').val(currentGoods.postage_id);
            }
        }
    });
}

/**
 * 所有商品
 */
function allGoods(){
    var bodyParam={'status':1,'is_on_shelf':'1'};
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
                html+='<optgroup label="group'+index+'" name="'+item+'">\n';
                for(var o=0;o<data[item].length;o++){
                    html+='<option value="'+data[item][o].id+'" >'+data[item][o].goods_name+'</option>\n';
                }
                html+='</optgroup>\n';
                index++;
            }
            $('#news_goods').html(html);


            $('#news_goods').multiSelect({
                selectableOptgroup: true,
                selectableHeader: "未选择<input type='text' class='form-control search-input' autocomplete='off' placeholder='搜索...'>",
                selectionHeader: "已选择<input type='text' class='form-control search-input' autocomplete='off' placeholder='搜索...'>",
                afterInit: function (ms) {
                    var that = this,
                        $selectableSearch = that.$selectableUl.prev(),
                        $selectionSearch = that.$selectionUl.prev(),
                        selectableSearchString = '#' + that.$container.attr('id') + ' .ms-elem-selectable:not(.ms-selected)',
                        selectionSearchString = '#' + that.$container.attr('id') + ' .ms-elem-selection.ms-selected';

                    that.qs1 = $selectableSearch.quicksearch(selectableSearchString)
                        .on('keydown', function (e) {
                            if (e.which === 40) {
                                that.$selectableUl.focus();
                                return false;
                            }
                        });

                    that.qs2 = $selectionSearch.quicksearch(selectionSearchString)
                        .on('keydown', function (e) {
                            if (e.which == 40) {
                                that.$selectionUl.focus();
                                return false;
                            }
                        });
                },
                afterSelect: function () {
                    this.qs1.cache();
                    this.qs2.cache();
                },
                afterDeselect: function () {
                    this.qs1.cache();
                    this.qs2.cache();
                }
            });


            if(currentNews!=null){
                $('#news_goods').val(JSON.parse(currentNews.googs_ids));
                $("#news_goods").multiSelect('refresh');
            }

            //$('#news_goods').val([12,8]);
            //$("#news_goods").multiSelect('refresh');
        }
    });
}
