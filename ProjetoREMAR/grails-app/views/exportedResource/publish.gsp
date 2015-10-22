<%--
Created by IntelliJ IDEA.
User: loa
Date: 10/06/15
Time: 09:55
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="new-main-inside">
    <g:javascript src="platforms.js" />
    <title></title>
</head>
<body>

<div class="content">
    <div class="row">
        <div class="col-md-12">
            <div class="box box-body box-info">
                <div class="box-header with-border">
                    <h3 class="box-title">
                        <i class="fa fa-info-circle"></i>
                        Dados do recurso
                    </h3>
                </div><!-- /.box-header -->
                <div class="box-body">
                    <div class="direct-chat-messages page-size" style="width: 30%;" >

                        <p>Nome:
                        <g:if test="${resourceName != ""}">
                            <input id="name" data-resource-id="${resourceId}" type="text" value="${resourceName}" class="form-control-remar"/>
                        </g:if>
                        <g:else>
                            <input id="name" data-resource-id="${resourceId}" type="text" placeholder="${resourceName}" class="form-control-remar"/>
                        </g:else>
                        </p>
                        <fieldset>
                            <legend>Tipo</legend>
                            <div class="form-group">
                                <input type="radio" name="type" value="public" checked/>
                                <label>Público</label>
                            </div>
                            <div class="form-group">
                                <input type="radio" name="type" value="private" disabled readonly/>
                                <label>Privado</label>
                                <span class="label label-warning">Em breve</span>
                            </div>
                            <div class="form-group">
                                <input type="radio" name="type" value="group" disabled readonly />
                                <label>Grupo</label>
                                <select disabled>
                                    <option>Turma 1</option>
                                </select>
                                <span class="label label-warning">Em breve</span>
                            </div>

                        </fieldset>
                        <fieldset>
                            <legend>Plataformas</legend>
                            <g:each in="${platforms}" var="platform">
                                <g:if test="${platform.contains(':')}">
                                    <input type="checkbox" checked disabled readonly/>
                                    <b> ${platform.substring(0, platform.indexOf(':'))}:
                                        <a target="_blank" href="${platform.substring(platform.indexOf(':') + 1)}"> Acessar </a> </b>
                                </g:if>
                                <g:else>
                                    <g:if test="${platform.toLowerCase() == 'windows'}">
                                        <input type="checkbox" name="${platform.toLowerCase()}" id="${platform.toLowerCase()}" class="checkbox-platform" disabled readonly/>
                                        <label for="${platform.toLowerCase()}" data-resource-id="${resourceId}">${platform}</label>
                                        <span class="label label-warning">Em breve</span>
                                    </g:if>
                                    <g:else>
                                        <g:if test="${platform.toLowerCase() == 'moodle' && session.user.moodleHash == null}">
                                            <input type="checkbox" name="${platform.toLowerCase()}" id="${platform.toLowerCase()}" class="checkbox-platform" disabled readonly/>
                                            <label for="${platform.toLowerCase()}" data-resource-id="${resourceId}">${platform}</label>
                                            <span class="label label-danger">Vincule sua conta ao Moodle</span>
                                        </g:if>
                                        <g:else>
                                            <input type="checkbox" name="${platform.toLowerCase()}" id="${platform.toLowerCase()}" class="checkbox-platform"/>
                                            <label for="${platform.toLowerCase()}" data-resource-id="${resourceId}">${platform}</label>
                                        </g:else>
                                    </g:else>
                                </g:else>
                                <br>
                            </g:each>
                            <input type="checkbox" name="windows" id="windows" class="checkbox-platform" disabled readonly/>
                            <label for="windows">Windows <span class="label label-warning">Em breve</span></label>

                        </fieldset>
                        <g:submitButton id="send" name="send" class="btn btn-primary btn-block btn-flat" value="Enviar" style="max-width: 100px;" />

                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
