
<%@ page import="br.ufscar.sead.loa.quiforca.remar.Theme" %>
<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main">
        <g:javascript src="scriptTheme.js"/>
        <g:javascript src="iframeResizer.contentWindow.min.js"/>
        <!--<g:set var="entityName" value="${message(code: 'Theme.label', default: 'Theme')}" />-->
    </head>
    <body>

    <div class="cluster-header">
        <p class="text-teal text-darken-3 left-align margin-bottom" style="font-size: 28px;">
            <i class="small material-icons left"></i>Meus Temas
        </p>
    </div>

    <div class="row">
    </div>

    <div class="row">
        <div>
            %{--<p> Download tema ESR</p>--}%
            <p style="margin-left: 10px;">Tema modelo <a class="btn-floating waves-effect waves-light my-orange" href="../samples/tema-forca.zip"> <i class="material-icons">file_download</i> </a>
        </p>
        </div>
    </div>

    <div class="row">
        <form class="col s12" name="formName">
    <table class="" id="table">
        <thead>
        <tr>
            <th>Selecionar</th>
            <th>Ícone</th>
            <th>Tela de Abertura</th>
            <th>Tela de Fundo</th>
            <th>Ação</th>
        </tr>
        </thead>
        <tbody>
            <g:each in="${themeInstanceList}" status="i" var="themeInstance">
                <tr data-id="${fieldValue(bean: themeInstance, field: "id")}" class="${(i % 2) == 0 ? 'even' : 'odd'}">
                    <td align="center"><input class="with-gap" name="radio" type="radio" id="theme${i}"
                                          value="${fieldValue(bean: themeInstance, field: "id")}" ${i == 0 ? "checked" : ""}> <label for="theme${i}"></label>
                    </td>
                    <td align="center"><img src="../data/${fieldValue(bean: themeInstance, field: "ownerId")}/themes/${fieldValue(bean: themeInstance, field: "id")}/icon.png" class="" width="250"/></td>
                    <td align="center"><img src="../data/${fieldValue(bean: themeInstance, field: "ownerId")}/themes/${fieldValue(bean: themeInstance, field: "id")}/inicio.png" class="" width="250"/></td>
                    <td align="center"><img src="../data/${fieldValue(bean: themeInstance, field: "ownerId")}/themes/${fieldValue(bean: themeInstance, field: "id")}/papel.png" class="" width="250"/></td>
                    <td align="center"><i id="deleteIcon${i}" style="color: #7d8fff" class="material-icons delete">delete</i></td>
                </tr>
            </g:each>
        </tbody>
    </table>
    <div class="row">
        <div class="col s12">
            <g:submitButton name="save" class="save btn btn-success btn-lg my-orange" value="Enviar"/>
            <g:link class="btn btn-success btn-lg my-orange" action="create">Novo tema</g:link>
        </div>
    </div>
    </form>
    </div>
    </body>
</html>
