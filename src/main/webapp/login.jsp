<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>登录页面</title>
</head>
<body>
<span style="color:red">${sessionScope.SPRING_SECURITY_LAST_EXCEPTION.message }</span>
<form action="/datatransfer/user/login" method="post">
    <table>
        <tr>
            <td>用户名：</td>
            <td><input type="text" name="loginid" /></td>
        </tr>
        <tr>
            <td>密码：</td>
            <td><input type="password" name="logingo" /></td>
        </tr>
        <tr>
            <td colspan="2" align="center"><input type="submit" value="登录" />
                <input type="reset" value="重置" /></td>
        </tr>
        <input type="hidden" name="${_csrf.parameterName}"   value="${_csrf.token}" />
    </table>
</form>
</body>
</html>