    //选取的Cash_apply
    var cash_applyList;
    var cash_applyIndex;
    var currentCash_apply;


////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////分类管理////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////
/**
 * 添加分类
 */
function addCash_apply(bodyParam){
    var httpR = new createHttpR(url+'addCash_apply','post','text',bodyParam,'callBack');
    httpR.HttpRequest(function(response){
        var obj = JSON.parse(response);
        var status = obj['status'];
        //var msg = obj['msg'];
        if(status=='0'){
            alert("新建成功！");
            window.location.reload();
        }
    });
}
/**
 * 查询商家
 */
function  selectCash_apply (id) {
    var bodyParam={'id':id};
    var httpR = new createHttpR(url+'selectCash_apply','post','text',bodyParam,'callBack');
    httpR.HttpRequest(function(response){
        var obj = JSON.parse(response);
        var status = obj['status'];
        //var msg = obj['msg'];
        if(status=='0'){
            var data = obj['msg'];
            currentCash_apply=data;
            for (var item in data) {
                $('#'+item).text(data[item]);
            }
            if(data.cash_status=='已处理'){
                $('#deal_date_div').removeClass('hide');
                $('#cash_type_div').removeClass('hide');
            }
            else if(data.cash_status=='待处理'){
                $('#updateCash_apply').removeClass('hide');
            }

            if(data.cash_type=='线下提现'){
                $('#cash_img_div').removeClass('hide');

                //图片
                if(data.cash_img!=''){
                    var images=JSON.parse(data.cash_img);
                    var html='<ul id="dowebok">';
                    for(var i in images){
                        html+='<li><img  src="'+images[i]+'" alt="图片1"></li>';
                    }
                    html+=' </ul>';
                    $('.album').html(html);
                    $('#dowebok').viewer({
                        url: 'data-original',
                    });
                }
            }

            var cash_info = JSON.parse(currentCash_apply.cash_info);
            $('#card_num').text(cash_info.card_num);
            $('#card_name').text(cash_info.card_name);
            $('#wechat').text(cash_info.wechat);
            if(cash_info.hasOwnProperty("open_id")){
                $('#wechat_div').removeClass('hide');
            }
            else{
                $('#card_num_div').removeClass('hide');
                $('#card_name_div').removeClass('hide');
            }

        }
    });
}
    function  selectCash_rate() {
        var bodyParam={};
        var httpR = new createHttpR(url+'getCashrate','post','text',bodyParam,'callBack');
        httpR.HttpRequest(function(response){
            var obj = JSON.parse(response);
            var status = obj['status'];
            //var msg = obj['msg'];
            if(status=='0'){
                $('#cash_rate').val(obj['msg']);
            }
        });
    }
    function  setCash_rate(bodyParam) {
        var httpR = new createHttpR(url+'setCashrate','post','text',bodyParam,'callBack');
        httpR.HttpRequest(function(response){
            var obj = JSON.parse(response);
            var status = obj['status'];
            //var msg = obj['msg'];
            if(status=='0'){
                alert(obj['msg']);
            }
        });
    }
/**
 * 修改分类
 * @param id
 */
function updateCash_apply(bodyParam){

    var httpR = new createHttpR(url+'updateCash_apply','post','text',bodyParam,'callBack');
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
function offlinePay(bodyParam){

    var httpR = new createHttpR(url+'offlinePay','post','text',bodyParam,'callBack');
    httpR.HttpRequest(function(response){
        var obj = JSON.parse(response);
        var status = obj['status'];
        //var msg = obj['msg'];
        if(status=='0'){
            alert("处理成功！");
            window.location.reload();
            //window.location.href="interface.html?index="+interfaceIndex;
        }
    });
}
function payToWechat(bodyParam){

    var httpR = new createHttpR(url+'payToWechat','post','text',bodyParam,'callBack');
    httpR.HttpRequest(function(response){
        var obj = JSON.parse(response);
        var status = obj['status'];
        //var msg = obj['msg'];
        if(status=='0'){
            alert("处理成功！");
            window.location.reload();
            //window.location.href="interface.html?index="+interfaceIndex;
        }
    });
}

/**
 * 删除分类
 * @param id
 */
function deleteCash_apply(id){
    var bodyParam={'id':id};
    var httpR = new createHttpR(url+'deleteCash_apply','post','text',bodyParam,'callBack');
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
     * 查询订单
     * @param cash_applyname
     * @param currentPage
     * @param pageSize
     */
    function  queryCash_applys (bodyParam,searchText,currentPage,pageSize) {

        //分页显示的页码数  必须为奇数
        var showPage=7;
        bodyParam['page']=currentPage;
        bodyParam['size']=pageSize;

        if(searchText!=null&&searchText!=''){
            bodyParam['search']=searchText;
        }
        else{
            delete bodyParam.search;
        }

        var httpR = new createHttpR(url+'listCash_apply','post','text',bodyParam,'callBack');
        httpR.HttpRequest(function(response){
            var obj = JSON.parse(response);
            var status = obj['status'];
            var msg = obj['msg'];
            if(status=='0'){
                var data=msg['data'];
                cash_applyList=msg['data'];
                var html='';

                for(var o in data){
                    var cash_info=JSON.parse(data[o].cash_info);
                    if(bodyParam.cash_status=='待处理'){
                        html+='<tr index='+o+' class="gradeX">\n' +
                            '<td>'+data[o].shop_name+'</td>\n' +
                            '<td>'+data[o].shop_tel+'</td>\n' +
                            '<td>'+data[o].cash_st_dt+'</td>\n' +
                            '<td>'+data[o].price+'</td>\n'  +
                            '<td>'+data[o].cash_code+'</td>\n';
                        if(cash_info.hasOwnProperty("open_id")){
                            html+='<td>微信</td>\n' ;
                        }
                        else{
                            html+='<td>银行卡</td>\n' ;
                        }
                        html+='<td>'+data[o].cash_price+'</td>\n'  +
                            '<td>'+data[o].cash_status+'</td>\n' ;
                        html+='<td><a class="scanCash_apply" href="ds_cash_details.html?id='+data[o].id+'" index='+o+'  ><span class="label label-info label-mini">查看</span></a>    ' +
                            '<a class="updateCash_apply" href="" index='+o+' data-toggle="modal" data-target="#update-box" ><span class="label label-info label-mini">处理</span></a></td>\n';

                    }
                    else if(bodyParam.cash_status=='已处理'){
                        html+='<tr index='+o+' class="gradeX">\n' +
                            '<td>'+data[o].shop_name+'</td>\n' +
                            '<td>'+data[o].shop_tel+'</td>\n' +
                            '<td>'+data[o].deal_date+'</td>\n' +
                            '<td>'+data[o].cash_price+'</td>\n';
                        if(cash_info.hasOwnProperty("open_id")){
                            html+='<td>微信</td>\n' ;
                        }
                        else{
                            html+='<td>银行卡</td>\n' ;
                        }
                        html+='<td>'+data[o].cash_type+'</td>\n'  +
                            '<td>'+data[o].cash_code+'</td>\n'  +
                            '<td>'+data[o].cash_status+'</td>\n' ;
                        html+='<td><a class="scanCash_apply" href="ds_cash_details.html?id='+data[o].id+'" index='+o+'  ><span class="label label-info label-mini">查看</span></a></td>\n';

                    }


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