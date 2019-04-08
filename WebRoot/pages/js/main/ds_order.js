    //选取的Order
    var orderList;
    var orderIndex;
    var currentOrder;


////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////分类管理////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////
/**
 * 添加分类
 */
function addOrder(bodyParam){
    var httpR = new createHttpR(url+'addOrders','post','text',bodyParam,'callBack');
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
function  selectOrder (id) {
    var bodyParam={'id':id};
    var httpR = new createHttpR(url+'selectCdOrders','post','text',bodyParam,'callBack');
    httpR.HttpRequest(function(response){
        var obj = JSON.parse(response);
        var status = obj['status'];
        //var msg = obj['msg'];
        if(status=='0'){
            var data = obj['msg'];
            currentOrder=data;
            for (var item in data) {
                $('#'+item).text(data[item]);
            }

            try {
                var address = JSON.parse(data['addr']);
                var addr = address['state']+address['city']+address['county']+address['addr'];
                $('#addr').text(addr);
            } catch (e) {
                console.log("地址数据异常！");
            }


            if(data['is_pay']=='Y'){
                $('#pay_dt_div').removeClass("hide");
            }
            if(data['is_deliver']=='Y'){
                $('#express_code_div').removeClass("hide");
                $('#send_dt_div').removeClass("hide");
            }
            if(data['is_confirm']=='Y'){
                $('#complete_dt_div').removeClass("hide");
            }
            if(data['is_cancel']=='Y'){
                $('#complete_dt_div').removeClass("hide");
                $('#order_status').text("已取消");
            }
            if(data['is_delete']=='Y'){
                $('#order_status').text("已删除");
            }
            if(data['postage_price']!=''){
                $('#postage_price_par').removeClass("hide");
            }

        }
    });
}

/**
 * 修改分类
 * @param id
 */
function updateOrder(bodyParam){

    var httpR = new createHttpR(url+'updateOrders','post','text',bodyParam,'callBack');
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
 * 删除分类
 * @param id
 */
function deleteOrder(id){
    var bodyParam={'id':id};
    var httpR = new createHttpR(url+'deleteOrders','post','text',bodyParam,'callBack');
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
 * 查询子订单
 * @param ordername
 * @param currentPage
 * @param pageSize
 */
function  querySubOrders (orders_id,currentPage,pageSize) {
    //分页显示的页码数  必须为奇数
    var showPage=7;
    var bodyParam={'page':currentPage,'size':pageSize};

    if(orders_id!=null&&orders_id!=''){
        bodyParam['orders_id']=orders_id;
    }
    else{
        delete bodyParam.orders_id;
    }
    var httpR = new createHttpR(url+'listOrder_item','post','text',bodyParam,'callBack');
    httpR.HttpRequest(function(response){
        var obj = JSON.parse(response);
        var status = obj['status'];
        var msg = obj['msg'];
        if(status=='0'){
            var data=msg['data'];
            orderList=msg['data'];
            var html='';

            for(var o in data){
                html+='<tr index='+o+' class="gradeX">\n' +
                    '<td>'+data[o].goods_name+'</td>\n' +
                    '<td>'+data[o].goods_num+'</td>\n' +
                    '<td>'+data[o].amount+'</td>\n' +
                    '<td>'+data[o].sell_price+'</td>\n' ;
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
     * 查询订单
     * @param ordername
     * @param currentPage
     * @param pageSize
     */
    function  queryOrders (bodyParam,searchText,currentPage,pageSize) {

        //分页显示的页码数  必须为奇数
        var showPage=7;
        bodyParam['page']=currentPage;
        bodyParam['size']=pageSize;

        if(searchText!=null&&searchText!=''){
            bodyParam['order_num']='%'+searchText+'%';
        }
        else{
            delete bodyParam.order_num;
        }
        var httpR = new createHttpR(url+'listOrders','post','text',bodyParam,'callBack');
        httpR.HttpRequest(function(response){
            var obj = JSON.parse(response);
            var status = obj['status'];
            var msg = obj['msg'];
            if(status=='0'){
                var data=msg['data'];
                orderList=msg['data'];
                var html='';

                for(var o in data){
                    html+='<tr index='+o+' class="gradeX">\n' +
                        '<td>'+data[o].id+'</td>\n' +
                        '<td>'+data[o].order_num+'</td>\n' +
                        '<td>'+data[o].create_dt+'</td>\n' +
                        '<td>'+data[o].shop_name+'</td>\n' ;

                    if(data[o].is_delete=='Y'){
                        html+='<td><span class="label label-default label-mini">已删除</span></td>\n';
                    }
                    else if(data[o].is_cancel=='Y'){
                        html+='<td><span class="label label-default label-mini">已取消</span></td>\n';
                    }
                    else if(data[o].order_status=='待支付'||data[o].order_status=='待发货'||data[o].order_status=='待收货'){
                        html+='<td><span class="label label-warning label-mini">'+data[o].order_status+'</span></td>\n';
                    }
                    else if(data[o].order_status=='已完成'||data[o].status=='已完成'){
                        html+='<td><span class="label label-success  label-mini">'+data[o].order_status+'</span></td>\n';
                    }
                    else if(data[o].order_status=='已取消'){
                        html+='<td><span class="label label-default label-mini">'+data[o].order_status+'</span></td>\n';
                    }
                    //
                    else if(data[o].order_status=='待退款'){
                        html+='<td><span class="label label-danger label-mini">'+data[o].order_status+'</span></td>\n';
                    }

                    else {
                        html+='<td><span class="label label-default label-mini">'+data[o].order_status+'</span></td>\n';
                    }
                    html+='<td><a class="scanOrder" href="" index='+o+' data-toggle="modal" ><span class="label label-info label-mini">查看</span></a></td>\n';
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