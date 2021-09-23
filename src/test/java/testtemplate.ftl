<html>
<#assign brandTextCol = "blue">

<#include "stylecss.ftl">
<head>
    <title>Todays serving of users who have locked themselves out.</title>
</head>
<body>
<h1>
    Todays serving of users who have locked themselves out.
</h1>

<table border=1>
    <tr><td><strong>Full name</strong><td><strong>Email address</strong><td><strong>Failed attempts</strong></tr>
    <#list lll as user>
        <tr><td>${user.firstName} ${user.secondName} <td> ${user.email} <td> ${user.attempts} </tr>
    </#list>
</table>

<p>Regards,
<p>The padoq backend
</body>
</html>