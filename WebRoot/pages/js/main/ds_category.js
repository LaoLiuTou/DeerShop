    //选取的Category
    var categoryList;
    var categoryIndex;
    var currentCategory;


////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////分类管理////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////
/**
 * 添加分类
 */
function addCategory(bodyParam){
    var httpR = new createHttpR(url+'addGoods_category','post','text',bodyParam,'callBack');
    httpR.HttpRequest(function(response){
        var obj = JSON.parse(response);
        var status = obj['status'];
        //var msg = obj['msg'];
        if(status=='0'){

            //$("#new-box").removeData();

            alert("新建成功！");
            //window.location.reload();
            location.replace(location.href);
        }
    });
}
    /**
     * 查询分类
     */
    function  selectGoods_category () {
        var bodyParam={'pidisnull':'1'};
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
                $('#pid').html(html);
                $('#update_pid').html(html);

            }
        });
    }

/**
 * 修改分类
 * @param id
 */
function updateCategory(bodyParam){

    var httpR = new createHttpR(url+'updateGoods_category','post','text',bodyParam,'callBack');
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
function updateCategoryOrder(bodyParam){
    var httpR = new createHttpR(url+'updateGoods_categoryOrder','post','text',bodyParam,'callBack');
    httpR.HttpRequest(function(response){
        var obj = JSON.parse(response);
        var status = obj['status'];
        //var msg = obj['msg'];
        if(status=='0'){
            //alert("修改成功！");
            //window.location.reload();
            //window.location.href="interface.html?index="+interfaceIndex;
        }
    });
}
function deerGoods_category(bodyParam){
    var httpR = new createHttpR(url+'deerGoods_category','post','text',bodyParam,'callBack');
    httpR.HttpRequest(function(response){
        var obj = JSON.parse(response);
        var status = obj['status'];
        //var msg = obj['msg'];
        if(status=='0'){
            alert("修改成功！");
            window.location.reload();
            //window.location.href="interface.html?index="+interfaceIndex;
            //deerCategorys();
        }
    });
}

/**
 * 删除分类
 * @param id
 */
function deleteCategory(id){
    var bodyParam={'id':id};
    var httpR = new createHttpR(url+'deleteGoods_category','post','text',bodyParam,'callBack');
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
 * @param categoryname
 * @param currentPage
 * @param pageSize
 */
function  queryCategorys (searchText,currentPage,pageSize) {

    //分页显示的页码数  必须为奇数
    var showPage=7;
    if(searchText==null||searchText==''){
        var bodyParam={'page':currentPage,'size':pageSize};
    }
    else{
        var bodyParam={'page':currentPage,'size':pageSize,'searchText':searchText};
    }

    var httpR = new createHttpR(url+'listGoods_category','post','text',bodyParam,'callBack');
    httpR.HttpRequest(function(response){
        var obj = JSON.parse(response);
        var status = obj['status'];
        var msg = obj['msg'];
        if(status=='0'){
            var data=msg['data'];
            categoryList=msg['data'];
            var html='';

            for(var o in data){
                html+='<tr index='+o+' class="gradeX">\n';
                html+='<td><i class="fa fa-plus-square"></i> '+data[o].ctg_name+'</td>\n';
                html+='<td>'+data[o].effect+'</td>\n' +
                    '<td>'+data[o].cd_dt+'</td>\n' ;

                if(data[o].status=='1'){
                    html+='<td><span class="label label-success label-mini">可用</span></td>\n';
                }
                else{
                    html+='<td><span class="label label-default label-mini">禁用</span></td>\n';
                }
                html+='<td><a class="updateCategory" href="" index='+o+' data-toggle="modal" data-target="#update-box"><span class="label label-info label-mini">修改</span></a>   ' +
                    '<a class="deleteCategory" href="" index='+o+' data-toggle="modal" data-target="#delete-box"><span class="label label-info label-mini">删除</span></a></td>\n';
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
function  treeCategorys () {

    var bodyParam={'orderBy':'SEQ ASC,CD_DT DESC'};

    var httpR = new createHttpR(url+'treeGoods_category','post','text',bodyParam,'callBack');
    httpR.HttpRequest(function(response){
        var obj = JSON.parse(response);
        var status = obj['status'];
        var msg = obj['msg'];
        if(status=='0'){
            var data=msg['data'];
            categoryList=msg['data'];
            var html='';

            for(var o in data){
                html+='<tr index='+o+' class="gradeX">\n'+
                    '<td><i class="fa fa-plus-square" ></i>  '+data[o].ctg_name+'</td>\n'+
                    '<td>'+data[o].effect+'</td>\n' +
                    '<td>'+data[o].cd_dt+'</td>\n' ;

                if(data[o].status=='1'){
                    html+='<td><span class="label label-success label-mini">可用</span></td>\n';
                }
                else{
                    html+='<td><span class="label label-default label-mini">禁用</span></td>\n';
                }
                html+='<td><a class="upCategory" href="javascript:;" index='+o+'><span class="label label-info label-mini"><i class="fa fa-arrow-up" ></i></span></a>   ' +
                    '<a class="downCategory" href="javascript:;" index='+o+'><span class="label label-info label-mini"><i class="fa fa-arrow-down" ></i></span></a>   ' +
                    '<a class="updateCategory" href="" index='+o+' data-toggle="modal" data-target="#update-box"><span class="label label-info label-mini">修改</span></a>   ' +
                    '<a class="deleteCategory" href="" index='+o+' data-toggle="modal" data-target="#delete-box"><span class="label label-info label-mini">删除</span></a></td>\n';
                html+='</tr>';

                var subList=data[o].subcategory;
                var subhtml='<tr class="sub_tr hide"><td colspan="5"><table  class="display table table-bordered table-striped">' +
                    //'<thead><tr><th>分类名称</th><th>功效</th><th>创建时间</th><th>状态</th><th>操作</th></tr></thead>' +
                    '<thead><tr><th>分类名称</th><th>功效</th><th>创建时间</th><th>操作</th></tr></thead>' +
                    '<tbody  class="subContent" id="subContent'+data[o].id+'">';
                for(var sub in subList){
                    subhtml+='<tr index='+sub+' class="gradeX">\n'+
                        '<td>&nbsp;&nbsp;&nbsp;&nbsp;'+subList[sub].ctg_name+'</td>\n'+
                        '<td>'+subList[sub].effect+'</td>\n' +
                        '<td>'+subList[sub].cd_dt+'</td>\n' ;

                    /*if(subList[sub].status=='1'){
                        subhtml+='<td><span class="label label-success label-mini">可用</span></td>\n';
                    }
                    else{
                        subhtml+='<td><span class="label label-default label-mini">禁用</span></td>\n';
                    }*/
                    subhtml+='<td><a class="upSubCategory" href="javascript:;" subindex='+sub+' index='+o+'><span class="label label-info label-mini"><i class="fa fa-arrow-up" ></i></span></a>   ' +
                        '<a class="downSubCategory" href="javascript:;" subindex='+sub+' index='+o+'><span class="label label-info label-mini"><i class="fa fa-arrow-down" ></i></span></a>   ' +
                        '<a class="updateSubCategory" href="" subindex='+sub+' index='+o+' data-toggle="modal" data-target="#update-box"><span class="label label-info label-mini">修改</span></a>   ' +
                        '<a class="deleteSubCategory" href="" subindex='+sub+' index='+o+' data-toggle="modal" data-target="#delete-box"><span class="label label-info label-mini">删除</span></a></td>\n';
                    subhtml+='</tr>';
                }
                subhtml+='</tbody></table></td></tr>';
                html+=subhtml;

            }
            $('#contentTbody').html(html);
            deerCategorys();
            selectGoods_category();
        }
    });
}
    function  deerCategorys (deer_flag) {

        if(deer_flag==null||deer_flag==''){
            var bodyParam={'page':1,'size':500,'pidisnotnull':1};
        }
        else{
            var bodyParam={'page':1,'size':500,'pidisnotnull':1,'deer_flag':deer_flag};
        }

        var httpR = new createHttpR(url+'listGoods_category','post','text',bodyParam,'callBack');
        httpR.HttpRequest(function(response){
            var obj = JSON.parse(response);
            var status = obj['status'];
            var msg = obj['msg'];
            if(status=='0'){

                var data=msg['data'];
                var select= new Array();
                var html='';
                for (var item in data) {
                    html+='<option value="'+data[item].id+'" >'+data[item].ctg_name+'</option>\n';
                    if(data[item].deer_flag=='1'){
                        select.push(data[item].id);
                    }
                }
                $('#deer_category').html(html);
                $('#deer_category').multiSelect({
                    selectableOptgroup: false,
                    selectableHeader: "未选择",
                    selectionHeader: "已选择",
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

                $('#deer_category').val(select);
                $("#deer_category").multiSelect('refresh');
            }
        });
    }
