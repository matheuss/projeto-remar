
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="new-main-inside">
    <g:javascript src="versions.js"/>
    <style>
    #moodleForm {
        display: none;
    }
    </style>
    <title></title>
</head>

<body>
<div class="content">
    <div class="row">
        <div class="col-md-12">
            <div class="box box-body box-info">
                <div class="box-header with-border">
                    <h3 class="box-title">
                        <i class="fa fa-lock"></i>
                        Meus processos
                    </h3>
                </div><!-- /.box-header -->
                <div class="box-body">
                    <div class="direct-chat-messages page-size" >

                        <h1>Processo Finalizado!</h1>
                        <g:form controller="process" action="publishGame">
                        %{--<div>--}%
                        %{--<label for="moodle">--}%
                        %{--Deseja publicar para o moodle?--}%
                        %{--<input class="checkbox.moodle" type="checkbox" id="moodle" name="moodle" value="${false}"/> <br>--}%
                        %{--<a id="moodleForm" target="_blank" href="/moodleGame/gamePublishConfig/">Formulario para o Moodle</a>--}%
                        %{--</label>--}%
                        %{--</div>--}%
                            <g:if test="${web}">
                                <div>
                                    <label for="web">
                                        Deseja publicar para a Web?
                                        <input name="web" id="web2" type="checkbox" id="web" name="web"/> <br>
                                        <a id="webLink" target="_blank" href="a" />
                                    </label>
                                </div>
                            </g:if>
                        %{--<div>--}%
                        %{--<label for="android">--}%
                        %{--Deseja publicar para Android?--}%
                        %{--<input type="checkbox" id="android" name="android" value="${false}"/> <br>--}%
                        %{--<a id="androidLink" target="_blank" />--}%
                        %{--</label>--}%
                        %{--</div>--}%
                            <input id="send" name="send" type="submit" value="Enviar"/>
                        </g:form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

</body>
</html>