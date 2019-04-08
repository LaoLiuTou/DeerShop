//后台服务地址
var url = 'http://192.168.1.144/DeerShop/';
//图片上传路径
var uploadUrl = 'http://192.168.1.144/FileUS/';
//var uploadUrl = 'http://192.168.1.144/FileUS/CKUploadImg';
//websocket地址
var websocketurl = 'ws://192.168.1.144:8888';

//后台服务地址
//var url = 'http://10.63.6.243/Wheelshop/';
//websocket地址
//var websocketurl = 'ws://10.63.6.243:8888';
//secret key
var sk = '!@QWASZX';



$(document).ready(function(){
    //logo
    $('.logo').html(' <a href="index.html" style="padding-top: 10px;">茸鹿销售管理</a>');
    $('footer').html("版权所有 © 2018  ");
    //$('.logo').html(' <a href=""><img src="images/logo.png" alt=""></a>');
    //$('.logo-icon').html(' <a href=""><img src="images/logo.png" alt=""></a>');
    $('#logoutBtn').click(function () {
        sessionStorage.clear();
        window.location.href='login.html';
    });



    var userinfo=sessionStorage.getItem('userinfo');
    if(userinfo!=null){
        $('#loginName').text(JSON.parse(userinfo)['nickname']);
    }

    //修改密码
    changePwd();
    $('#changepwd-box').on('click','#passwordChangeBtn',function () {
        if($('#old_password').val()==''){
            alert("原密码不能为空！");
            return false;
        }
        if($('#new_password').val()==''){
            alert("新密码不能为空！");
            return false;
        }
        if($('#new_password').val()!=$('#re_new_repassword').val()){
            alert("两次输入密码不一致！");
            return false;
        }

        var bodyParam={'id':JSON.parse(userinfo)['id'],'old_password':$('#old_password').val(),'new_password':$('#new_password').val()};
        changePasswordUser(bodyParam);

    });

});
/**
 * 修改密码
 * @param id
 */
function changePwd(){
    var html='<div aria-hidden="true" aria-labelledby="myModalLabel" role="dialog" tabindex="-1" id="changepwd-box" class="modal fade">\n' +
        '    <div class="modal-dialog">\n' +
        '        <div class="modal-content">\n' +
        '            <div class="modal-header">\n' +
        '                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>\n' +
        '                <h4 class="modal-title">重置密码</h4>\n' +
        '            </div>\n' +
        '            <div class="modal-body">\n' +
        '                <form role="form">\n' +
        '                    <div class="form-group">\n' +
        '                        <label>原密码</label>\n' +
        '                        <input type="text" class="form-control" id="old_password" placeholder="请输入原密码">\n' +
        '                    </div>\n' +
        '                    <div class="form-group">\n' +
        '                        <label>新密码</label>\n' +
        '                        <input type="text" class="form-control" id="new_password" placeholder="请输入新密码">\n' +
        '                    </div>\n' +
        '                    <div class="form-group">\n' +
        '                        <label>确认密码</label>\n' +
        '                        <input type="text" class="form-control" id="re_new_repassword" placeholder="请再次输入新密码">\n' +
        '                    </div>\n' +
        '                </form>\n' +
        '            </div>\n' +
        '            <div class="clearfix"></div>\n' +
        '            <div class="modal-footer">\n' +
        '                <button data-dismiss="modal" class="btn btn-default" type="button">取消</button>\n' +
        '                <button class="btn btn-info popovers" type="button" id="passwordChangeBtn">确定</button>\n' +
        '            </div>\n' +
        '        </div>\n' +
        '    </div>\n' +
        '</div>';
    $('body').append(html);
}

function changePasswordUser(bodyParam){

    var httpR = new createHttpR(url+'changePasswordUser','post','text',bodyParam,'callBack');
    httpR.HttpRequest(function(response){
        var obj = JSON.parse(response);
        var status = obj['status'];
        //var msg = obj['msg'];
        if(status=='0'){
            alert("修改成功,请重新登录！");
            window.location.href="login.html";
        }
    });
}

/**
 * 登录
 */
function login() {
    $.ajax({
        url : url+'login',
        type : 'POST',
        dataType:'JSON',
        data : {
            'username' : $('#username').val(),
            'password' : $('#password').val()
        },
        headers: {
            'source' : 'pf'
        },
        success : function(response) {
            console.log(JSON.stringify(response));
            if(response['status']=='0'){
                var token = response['token'];
                var userinfo = JSON.stringify(response['msg']);
                //var timestamp = Date.parse(new Date());
                //var hash = md5(token + timestamp + sk);
                sessionStorage.setItem('username',$('#username').val());
                sessionStorage.setItem('userpwd',$('#password').val());
                sessionStorage.setItem('userinfo',userinfo);
                sessionStorage.setItem('token',token);

                //window.location.href='default-page.html?backurl='+window.location.href;
                window.location.href='index.html';
            }
            else{
                alert(response['msg']);
            }

        },
        error : function(response) {
            alert('登录失败！');
        }
    });

}
 
function createHttpR(url,type,dataType,bodyParam){
    this.url = url;
    this.type = type;
    this.dataType = dataType;
    this.bodyParam = bodyParam;
}
createHttpR.prototype.HttpRequest = function(callBack){

    if(sessionStorage.getItem('username')!=null||sessionStorage.getItem('token')!=null){
        var  token = sessionStorage.getItem('token');
        var timestamp = Date.parse(new Date());
        var hash = md5(token+timestamp+sk);
        $.ajax({
            url:this.url,
            type:this.type,
            cache:false,
            timeout:20,
            dataType:this.dataType,
            data :this.bodyParam,
            async:false,
            headers: {
                'token' : token,
                'timesamp' : timestamp,
                'sign' : hash,
                'source' : 'pf'
                //'content-Type': 'application/json'
            },
            success:function(response) {
                var obj = JSON.parse(response);
                var status = obj['status'];
                var msg = obj['msg'];
                if(status=='mismatch'||status=='expire'){
                    console.log(msg);
                    alert('验证信息错误，请重新登录！');
                    //无用户信息，重新登录
                    window.location.href='login.html';
                    //login();
                }
                else if(status=='0'){
                    callBack(response);
                }
                else{
                    alert(msg);
                }
            },
            error:function(response){
                if(response['status']=='404'){
                    window.location.href='404.html';
                }
                else  if(response['status']=='500'){
                    window.location.href='500.html';
                }
                else{
                    alert('请求失败！');
                    return false;
                }
            },
            beforeSend:function(){
                //alert('before');
            },
            complete:function(){
                //alert('complete');
            }

        });
    }
    else{
        alert('访问权限已过期，请重新登录！');
        //无用户信息，重新登录
        window.location.href='login.html';
    }

}

function createJSONHttpR(url,type,dataType,bodyParam){
    this.url = url;
    this.type = type;
    this.dataType = dataType;
    this.bodyParam = bodyParam;
}
createJSONHttpR.prototype.HttpRequest = function(callBack){

    if(sessionStorage.getItem('username')!=null||sessionStorage.getItem('token')!=null){
        var  token = sessionStorage.getItem('token');
        var timestamp = Date.parse(new Date());
        var hash = md5(token+timestamp+sk);
        $.ajax({
            url:this.url,
            type:this.type,
            cache:false,
            timeout:20,
            dataType:this.dataType,
            data :this.bodyParam,
            async:false,
            headers: {
                'token' : token,
                'timesamp' : timestamp,
                'sign' : hash,
                'source' : 'pf',
                'content-Type': 'application/json'
            },
            success:function(response) {
                var obj = JSON.parse(response);
                var status = obj['status'];
                var msg = obj['msg'];
                if(status=='mismatch'||status=='expire'){
                    console.log(msg);
                    alert('验证信息错误，请重新登录！');
                    //无用户信息，重新登录
                    window.location.href='login.html';
                    //login();
                }
                else if(status=='0'){
                    callBack(response);
                }
                else{
                    alert(msg);
                }
            },
            error:function(response){
                alert('请求失败！');
                return false;
            },
            beforeSend:function(){
                //alert('before');
            },
            complete:function(){
                //alert('complete');
            }

        });
    }
    else{
        alert('访问权限已过期，请重新登录！');
        //无用户信息，重新登录
        window.location.href='login.html';
    }

}


function GetQueryString(name)
{
    var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
    var r = window.location.search.substr(1).match(reg);
    if(r!=null)return  unescape(r[2]); return null;
}


function initwebsocket(type){

    //chrome://flags/#autoplay-policy

    var websocket = null;
    //判断当前浏览器是否支持WebSocket
    if ('WebSocket' in window) {
        websocket = new WebSocket(websocketurl);
    }
    else {
        alert('当前浏览器不支持websocket')
    }


    //连接发生错误的回调方法
    websocket.onerror = function () {
        alert("WebSocket连接发生错误");
        //console.log('WebSocket连接成功');
    };
    //连接成功建立的回调方法
    websocket.onopen = function () {
        //alert("WebSocket连接成功");
        console.log('WebSocket连接成功');
        var sendMsg={"T":"1","UI":type+"_"+addNumber(6),"UN":type,"UH":""};
        websocket.send(JSON.stringify(sendMsg));
    }

    //接收到消息的回调方法
    websocket.onmessage = function (event) {
        console.log(event.data);
        var msg=JSON.parse(event.data);

        if(msg['T']=='3'){

          
        }
        
    }

    //连接关闭的回调方法
    websocket.onclose = function () {
        //setMessageInnerHTML("WebSocket连接关闭");
        console.log('WebSocket连接关闭');
    }

    //监听窗口关闭事件，当窗口关闭时，主动去关闭websocket连接，防止连接还没断开就关闭窗口，server端会抛异常。
    window.onbeforeunload = function () {
        websocket.close();
    }

}
function addNumber(_idx){
    var str = '';
    for(var i = 0; i < _idx; i += 1){
        str += Math.floor(Math.random() * 10);
    }
    return str;
}



Date.prototype.format = function(fmt) {
    var o = {
        "M+" : this.getMonth()+1,                 //月份
        "d+" : this.getDate(),                    //日
        "h+" : this.getHours(),                   //小时
        "m+" : this.getMinutes(),                 //分
        "s+" : this.getSeconds(),                 //秒
        "q+" : Math.floor((this.getMonth()+3)/3), //季度
        "S"  : this.getMilliseconds()             //毫秒
    };
    if(/(y+)/.test(fmt)) {
        fmt=fmt.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length));
    }
    for(var k in o) {
        if(new RegExp("("+ k +")").test(fmt)){
            fmt = fmt.replace(RegExp.$1, (RegExp.$1.length==1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length)));
        }
    }
    return fmt;
}

