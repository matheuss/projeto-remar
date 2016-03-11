package br.ufscar.sead.loa.remar

import grails.util.Environment
import org.camunda.bpm.engine.RuntimeService

class IndexController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]
    def springSecurityService
    RuntimeService runtimeService

    def index() {
        if (springSecurityService.isLoggedIn()) {
            def model = [:]

//            model.gameInstanceList = Resource.findAll("from Resource as r where r.status = 'approved' order by r.id desc")
            model.gameInstanceList = Resource.findAllByStatus('approved',[max:8, sort: "id", order: "desc"])

            model.userName = session.user.firstName

            model.userGender = User.findById(session.user.id).gender

//            model.publicExportedResourcesList = ExportedResource.findAll("from ExportedResource as e where e.type = 'public' order by e.id desc")
            model.publicExportedResourcesList = ExportedResource.findAllByType('public',[max:8, sort: "id", order: "desc"])

//            model.myExportedResourcesList = ExportedResource.findAll("from ExportedResource as e where e.type='public' and e.owner=:owner order by e.id desc",[owner: User.get(session.user.id)])
            model.myExportedResourcesList = ExportedResource.findAllByTypeAndOwner('public',User.get(session.user.id),[max:8, sort: "id", order: "desc"])

//
//
//            log.debug model
//            log.debug "RESULT: " + model.publicExportedResourcesList.size()


            def instances = []
            runtimeService.createProcessInstanceQuery().variableValueEquals("ownerId", "1").list().each {instance ->
                def i = []
                i.push(runtimeService.getVariable(instance.processInstanceId, "gameName"))

            }
            render view: "dashboard", model: model
        } else {
            render view: "index"
        }
    }

    def frame() {
        def model = [:]
        model.development = Environment.current == Environment.DEVELOPMENT
        model.uri = params.remove('uri')

        params.remove("controller")
        params.remove("action")
        params.remove("format")

        model.uri += "?" + params.collect { k,v -> "$k=$v" }.join('&')

        if (model.development) {
            if (model.uri.indexOf('escola') != -1) {
                model.uri = "http://localhost:7070${model.uri}"
            } else if (model.uri.indexOf('forca') != -1) {
                model.uri = "http://localhost:8080${model.uri}"
            }
        }

        model.uri += "&h=" + (session.user.username as String).bytes.encodeBase64().toString()

        render view: "frame", model: model
    }

}