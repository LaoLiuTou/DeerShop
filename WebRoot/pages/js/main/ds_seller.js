    //选取的Seller
    var sellerList;
    var sellerIndex;
    var currentSeller;


////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////分类管理////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////
/**
 * 添加分类
 */
function addSeller(bodyParam){
    var httpR = new createHttpR(url+'addSeller','post','text',bodyParam,'callBack');
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
function  selectSeller (id) {
    var bodyParam={'id':id};
    var httpR = new createHttpR(url+'selectSeller','post','text',bodyParam,'callBack');
    httpR.HttpRequest(function(response){
        var obj = JSON.parse(response);
        var status = obj['status'];
        //var msg = obj['msg'];
        if(status=='0'){
            var data = obj['msg'];
            currentSeller=data;
            for (var item in data) {
                $('#'+item).text(data[item]);
                if(item=='shop_img'||item=='business_license'){
                    $('#'+item).prop('src',data[item]);
                }
                if(data['audit_status']=='未审核'){
                    $('#verifyGoods').removeClass('hide');
                }
                else if(data['audit_status']=='已驳回'){
                    $('#reject_reason_div').removeClass('hide');
                }
            }
        }
    });
}

/**
 * 修改分类
 * @param id
 */
function updateSeller(bodyParam){

    var httpR = new createHttpR(url+'updateSeller','post','text',bodyParam,'callBack');
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
function deleteSeller(id){
    var bodyParam={'id':id};
    var httpR = new createHttpR(url+'deleteSeller','post','text',bodyParam,'callBack');
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
 * 查询分类
 * @param sellername
 * @param currentPage
 * @param pageSize
 */
function  querySellers (audit_status,searchText,currentPage,pageSize) {

    //分页显示的页码数  必须为奇数
    var showPage=7;
    if(searchText==null||searchText==''){
        var bodyParam={'audit_status':audit_status,'page':currentPage,'size':pageSize};
    }
    else{
        var bodyParam={'audit_status':audit_status,'page':currentPage,'size':pageSize,'search':searchText};
    }
    var httpR = new createHttpR(url+'listSeller','post','text',bodyParam,'callBack');
    httpR.HttpRequest(function(response){
        var obj = JSON.parse(response);
        var status = obj['status'];
        var msg = obj['msg'];
        if(status=='0'){
            var data=msg['data'];
            sellerList=msg['data'];
            var html='';

            for(var o in data){
                html+='<tr index='+o+' class="gradeX">\n' +
                    '<td>'+data[o].id+'</td>\n' +
                    '<td>'+data[o].shop_name+'</td>\n' +
                    '<td>'+data[o].shop_tel+'</td>\n' +
                    '<td>'+data[o].cd_dt+'</td>\n' ;

                if(data[o].audit_status=='未审核'){
                    html+='<td><span class="label label-warning label-mini">'+data[o].audit_status+'</span></td>\n';
                    html+='<td><a class="scanSeller" href="" index='+o+' data-toggle="modal" ><span class="label label-info label-mini">查看</span></a>   ' +
                        '<a class="verifySeller" href="" index='+o+' data-toggle="modal" data-target="#verify-box"><span class="label label-info label-mini">审核</span></a></td>\n';

                }
                else if(data[o].audit_status=='已审核'){
                    html+='<td><span class="label label-success  label-mini">'+data[o].audit_status+'</span></td>\n';
                    html+='<td><a class="scanSeller" href="" index='+o+' data-toggle="modal" ><span class="label label-info label-mini">查看</span></a></td>\n';

                }
                else{
                    html+='<td><span class="label label-default label-mini">'+data[o].audit_status+'</span></td>\n';
                    html+='<td><a class="scanSeller" href="" index='+o+' data-toggle="modal" ><span class="label label-info label-mini">查看</span></a></td>\n';

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

