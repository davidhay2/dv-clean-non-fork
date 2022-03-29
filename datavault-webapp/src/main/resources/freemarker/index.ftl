<#assign sec=JspTaglibs["http://www.springframework.org/security/tags"] />
<@sec.authentication var="principal" property="principal" />
<html>
INDEX PAGE
<@sec.authorize access="isAuthenticated()">
  logged in as <@sec.authentication property="principal.username" />
    ${principal.username}
</@sec.authorize>

<@sec.authorize access="! isAuthenticated()">
  Not logged in
</@sec.authorize>
</html>
