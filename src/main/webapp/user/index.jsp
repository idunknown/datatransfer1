<html>
<%@ page language="java" pageEncoding="UTF-8" %>
<%@ page contentType="text/html;charset=utf-8" language="java" %>
<head>
<body>
<script src="../js/jquery-3.2.1.js"></script>
<div class="click">点我发送请求</div>
<script>
    $(document).ready(function(){
        $(".click").bind("click",function () {
            $.ajax(
                    {
                        url:"/datatransfer/index/testjson?nihfao",
                        type:"get",
                        dataType:"json",
                        success:function(data){
                            console.log(data);
                        }

                    }
            )
        })
    })
</script>
</body>

</html>
