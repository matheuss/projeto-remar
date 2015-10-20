<%--
  Created by IntelliJ IDEA.
  User: loa
  Date: 25/06/15
  Time: 11:04
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="dashboard">
</head>
<body>

<div class="container">
    <div class="row"> <!-- REA personalizaveis-->
        <div class="col s12 m12 l12 session-title" >
            <p class="session-title text-teal text-darken-3">
              <i class="left small material-icons">open_in_new</i>R.E.A. personalizáveis
                <span class="new badge orange" style="right: inherit; margin-left: 5px;"></span>
            </p>
            <a class="right btn-floating  waves-effect waves-light red"><i class="material-icons">add</i></a>
            <div class="divider"></div>
        </div>

        <g:if test="${gameInstanceList.size() == 0}">
            <p>Não há nenhum R.E.A disponível para ser personalizado :(</p>
        </g:if>
        <g:each in="${gameInstanceList}" var="gameInstance">
            <div class="card card-small hoverable">
                <a href="/resource/show/${gameInstance.id}" >
                    <div class="card-image waves-effect waves-block waves-light">
                        <img class="activator" src="/images/${gameInstance.uri}-banner.png">
                    </div>
                    <div class="card-content">
                        <span class="card-title activator truncate">
                            ${gameInstance.name}
                            %{--<i class="material-icons right">more_vert</i>--}%
                        </span>
                        <div class="developer">
                            <img src="../data/users/${gameInstance.owner.username}/profile-picture"
                                 class="circle left">
                            <span class="truncate">${gameInstance.owner.firstName}</span>
                        </div>
                    </div> <!-- End card-content -->
                    <div class="card-action" >
                        <div class="right">
                            <i class="fa fa-globe"></i>
                            <g:if test="${gameInstance.android}">
                                <i class="fa fa-android"></i>
                            </g:if>
                            <g:if test="${gameInstance.linux}">
                                <i class="fa fa-linux"></i>
                            </g:if>
                            <g:if test="${gameInstance.moodle}">
                                <i class="fa fa-graduation-cap"></i>
                            </g:if>
                        </div>
                    </div> <!-- End card-action -->
                    %{--<div class="card-reveal">--}%
                        %{--<span class="card-title grey-text text-darken-4">Card Title<i class="material-icons right">close</i></span>--}%
                        %{--<p>Here is some more information about this product that is only revealed once clicked on.</p>--}%
                    %{--</div> <!-- End card-reveal -->--}%
                </a>
            </div> <!-- End Card-->
        </g:each>
    </div> <!-- End Row R.E.A Personalizaveis -->


    <div class="row"> <!-- R.E.A. publicos -->
        <div class="col s12 m12 l12 session-title" >
            <p class="session-title text-teal text-darken-3">
                <i class="left small material-icons">supervisor_account</i>R.E.A. públicos
                <span class="new badge orange" style="right: inherit; margin-left: 5px;"></span>
            </p>
            <a class="right btn-floating  waves-effect waves-light red"><i class="material-icons">add</i></a>
            <div class="divider"></div>
        </div>

        <g:if test="${publicExportedResourcesList.size() == 0}">
            <p>Não há nenhum R.E.A público :(</p>
        </g:if>
        <g:each in="${publicExportedResourcesList}" var="exportedResourceInstance">
            <div class="card card-small hoverable">
                <div class="card-image waves-effect waves-block waves-light">
                    <img class="activator" src="/images/${exportedResourceInstance.resource.uri}-banner.png">
                </div>
                <div class="card-content">
                    <span class="card-title activator truncate">
                        ${exportedResourceInstance.name}
                        %{--<i class="material-icons right">more_vert</i>--}%
                    </span>
                    <div class="developer">
                        <img src="../data/users/${exportedResourceInstance.owner.username}/profile-picture"
                             class="circle left">
                        <span class="truncate">${exportedResourceInstance.owner.firstName}</span>
                    </div>
                </div> <!-- End card-content -->
                <div class="card-action" >
                    <div class="right">
                        <g:if test="${exportedResourceInstance.webUrl != null}">
                            <i class="fa fa-globe"></i>
                        </g:if>
                        <g:if test="${exportedResourceInstance.androidUrl != null}">
                            <i class="fa fa-android"></i>
                        </g:if>
                        <g:if test="${exportedResourceInstance.linuxUrl != null}">
                            <i class="fa fa-linux"></i>
                        </g:if>
                        <g:if test="${exportedResourceInstance.moodleUrl != null}">
                            <i class="fa fa-graduation-cap"></i>
                        </g:if>
                    </div>
                </div> <!-- End card-action -->
                <div class="card-reveal">
                    <span class="card-title grey-text text-darken-4">Card Title<i class="material-icons right">close</i></span>
                    <g:if test="${exportedResourceInstance.webUrl != null}">
                        <a href="${exportedResourceInstance.webUrl}" style="color: white;">
                            <i class="fa fa-globe"></i>
                        </a>
                    </g:if>
                    <g:if test="${exportedResourceInstance.androidUrl != null}">
                        <a href="${exportedResourceInstance.androidUrl}">
                            <i class="fa fa-android"></i>
                        </a>
                    </g:if>
                    <g:if test="${exportedResourceInstance.linuxUrl != null}">
                        <a href="${exportedResourceInstance.linuxUrl}">
                            <i class="fa fa-linux"></i>
                        </a>
                    </g:if>
                    <g:if test="${exportedResourceInstance.moodleUrl != null}">
                        <a href="${exportedResourceInstance.moodleUrl}">
                            <i class="fa fa-graduation-cap"></i>
                        </a>
                    </g:if>
                </div> <!-- End card-reveal -->
            </div> <!-- End Card-->
        </g:each>
    </div> <!-- End Row -->

    <div class="row"> <!-- Meus R.E.A.  -->
        <div class="col s12 m12 l12 session-title" >
            <p class="session-title text-teal text-darken-3">
                <i class="left small material-icons">https</i>Meus R.E.A.
                <span class="new badge orange" style="right: inherit; margin-left: 5px;"></span>
            </p>
            <a class="right btn-floating  waves-effect waves-light red"><i class="material-icons">add</i></a>
            <div class="divider"></div>
        </div>

        <g:if test="${gameInstanceList.size() == 0}">
            <p>Não há nenhum R.E.A disponível para ser personalizado :(</p>
        </g:if>
        <g:each in="${gameInstanceList}" var="gameInstance">
            <div class="card card-small hoverable">
                <a href="/resource/show/${gameInstance.id}" >
                    <div class="card-image waves-effect waves-block waves-light">
                        <img class="activator" src="/images/${gameInstance.uri}-banner.png">
                    </div>
                    <div class="card-content">
                        <span class="card-title activator truncate">
                            ${gameInstance.name}
                            %{--<i class="material-icons right">more_vert</i>--}%
                        </span>
                        <div class="developer">
                            <img src="../data/users/${gameInstance.owner.username}/profile-picture"
                                 class="circle left">
                            <span class="truncate">${gameInstance.owner.firstName}</span>
                        </div>
                    </div> <!-- End card-content -->
                    <div class="card-action" >
                        <div class="right">
                            <i class="fa fa-globe"></i>
                            <g:if test="${gameInstance.android}">
                                <i class="fa fa-android"></i>
                            </g:if>
                            <g:if test="${gameInstance.linux}">
                                <i class="fa fa-linux"></i>
                            </g:if>
                            <g:if test="${gameInstance.moodle}">
                                <i class="fa fa-graduation-cap"></i>
                            </g:if>
                        </div>
                    </div> <!-- End card-action -->
                    <div class="card-reveal">
                        <span class="card-title grey-text text-darken-4">Card Title<i class="material-icons right">close</i></span>
                        <p>Here is some more information about this product that is only revealed once clicked on.</p>
                    </div> <!-- End card-reveal -->
                </a>
            </div> <!-- End Card-->
        </g:each>
    </div> <!-- End Row -->


</div> <!-- End Container -->


</body>
</html>
