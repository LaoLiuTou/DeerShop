    //选取的Return
    var returnList;
    var returnIndex;
    var currentReturn;


    ////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////分类管理////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////
    /**
     * 添加分类
     */
    function addReturn(bodyParam){
        var httpR = new createHttpR(url+'addReturns','post','text',bodyParam,'callBack');
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
    function  selectReturn (id) {
        var bodyParam={'id':id};
        var httpR = new createHttpR(url+'selectReturns','post','text',bodyParam,'callBack');
        httpR.HttpRequest(function(response){
            var obj = JSON.parse(response);
            var status = obj['status'];
            //var msg = obj['msg'];
            if(status=='0'){

                var data = obj['msg'];
                currentReturn=data;
                for (var item in data) {
                    $('#'+item).text(data[item]);
                }
                $('#firstImg').prop('src',data['firstImg']);
                if(data['status']=='待退款'){
                    $('#submit_div').removeClass('hide');
                }
                else if(data['status']=='退款中'){
                    $('#submit_div').removeClass('hide');
                    $('#agree_div').removeClass('hide');
                }
                else if(data['status']=='退款成功'||data['status']=='拒绝退款'){
                    $('#submit_div').removeClass('hide');
                    $('#agree_div').removeClass('hide');
                    $('#return_div').removeClass('hide');
                }

                //图片
                if(data['return_img']!=''){
                    try {
                        var images=JSON.parse(data['return_img']);
                        var html='<ul id="dowebok">';
                        for(var i in images){
                            html+='<li><img  src="'+images[i]+'" alt="图片1"></li>';
                        }
                        html+=' </ul>';
                        $('.album').html(html);
                        $('#dowebok').viewer({
                            url: 'data-original',
                        });
                    } catch(e) {
                        console.log('error：'+e);
                        //return false;
                    }
                }
                //拒绝凭证
                if(data['status']=='拒绝退款'&&data['refuse_img']!=''){
                    $('#refuse_div').removeClass('hide');
                    var images=JSON.parse(data['refuse_img']);
                    var html='<ul id="dowebok_refuse">';
                    for(var i in images){
                        html+='<li><img  src="'+images[i]+'" alt="图片1"></li>';
                    }
                    html+=' </ul>';
                    $('.album_refuse').html(html);
                    $('#dowebok_refuse').viewer({
                        url: 'data-original',
                    });
                }


                //状态
                statusReturns(id);
            }
        });
    }

    function  statusReturns (id) {
        var bodyParam={'id':id};
        var httpR = new createHttpR(url+'statusReturns','post','text',bodyParam,'callBack');
        httpR.HttpRequest(function(response){
            var obj = JSON.parse(response);
            var status = obj['status'];
            //var msg = obj['msg'];
            if(status=='0'){

                var data=obj['msg'].reverse();
                var html='';
                for(var o in data){
                    html+='<tr index='+o+' class="gradeX">\n' +
                        '<td>'+data[o].status+'</td>\n' +
                        '<td>'+data[o].time+'</td>\n' +
                        '<td>'+data[o].desc.replace("\n","<br>")+'</td>\n' ;
                    html+='</tr>';
                }
                $('#contentTbody').html(html);
            }
        });
    }

    /**
     * 修改分类
     * @param id
     */
    function updateReturn(bodyParam){

        var httpR = new createHttpR(url+'updateReturns','post','text',bodyParam,'callBack');
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
    function deleteReturn(id){
        var bodyParam={'id':id};
        var httpR = new createHttpR(url+'deleteReturns','post','text',bodyParam,'callBack');
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
     * 查询订单商品
     * @param returnname
     * @param currentPage
     * @param pageSize
     */
    function  queryReturns (bodyParam,searchText,currentPage,pageSize) {

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
        var httpR = new createHttpR(url+'listReturns','post','text',bodyParam,'callBack');
        httpR.HttpRequest(function(response){
            var obj = JSON.parse(response);
            var status = obj['status'];
            var msg = obj['msg'];
            if(status=='0'){
                var data=msg['data'];
                returnList=msg['data'];
                var html='';

                for(var o in data){
                    html+='<tr index='+o+' class="gradeX">\n' +
                        '<td>'+data[o].id+'</td>\n' +
                        '<td>'+data[o].order_num+'</td>\n' +
                        '<td>'+data[o].return_code+'</td>\n' +
                        '<td>'+data[o].shop_name+'</td>\n' +
                        '<td>'+data[o].return_type+'</td>\n' +
                        '<td>'+data[o].submit_dt+'</td>\n' ;


                    if(data[o].status=='退款中'){
                        html+='<td><span class="label label-warning label-mini">'+data[o].status+'</span></td>\n';
                    }
                    else if(data[o].status=='退款成功'){
                        html+='<td><span class="label label-success  label-mini">'+data[o].status+'</span></td>\n';
                    }
                    else if(data[o].status=='已取消'){
                        html+='<td><span class="label label-default label-mini">'+data[o].status+'</span></td>\n';
                    }
                    else {
                        html+='<td><span class="label label-default label-mini">'+data[o].status+'</span></td>\n';
                    }
                    html+='<td><a class="scanReturn" href="" index='+o+' data-toggle="modal" ><span class="label label-info label-mini">查看</span></a></td>\n';
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