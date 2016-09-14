package br.ufscar.sead.loa.santograu.remar

import grails.plugin.springsecurity.annotation.Secured

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Secured(["isAuthenticated()"])
class FaseTCCController {

    def springSecurityService

    static allowedMethods = [save: "POST", update: "POST", delete: "DELETE", returnInstance: "GET"]

    @Secured(['permitAll'])
    def index(Integer max) {
        session.taskId = "57c42aca9e04b91a75a80f75"
        session.user = springSecurityService.currentUser

        def list = QuestionFaseTCC.findAllByOwnerId(session.user.id)

        if(list.size()==0){
            new QuestionFaseTCC(title: "Questão 1", answers: ["Alternativa A", "Alternativa B", "Alternativa C", "Alternativa D", "Alternativa E"], correctAnswer: 0, ownerId:  session.user.id, taskId: session.taskId).save flush: true
            new QuestionFaseTCC(title: "Questão 2", answers: ["Alternativa A", "Alternativa B", "Alternativa C", "Alternativa D", "Alternativa E"], correctAnswer: 0, ownerId:  session.user.id, taskId: session.taskId).save flush: true
            new QuestionFaseTCC(title: "Questão 3", answers: ["Alternativa A", "Alternativa B", "Alternativa C", "Alternativa D", "Alternativa E"], correctAnswer: 0, ownerId:  session.user.id, taskId: session.taskId).save flush: true
            new QuestionFaseTCC(title: "Questão 4", answers: ["Alternativa A", "Alternativa B", "Alternativa C", "Alternativa D", "Alternativa E"], correctAnswer: 0, ownerId:  session.user.id, taskId: session.taskId).save flush: true
        }

        list = QuestionFaseTCC.findAllByOwnerId(session.user.id)
        respond list, model: [questionFaseTccInstanceCount: QuestionFaseTCC.count()]
    }

    def show(QuestionFaseTCC faseTCCInstance) {
        respond faseTCCInstance
    }

    def create() {
        respond new QuestionFaseTCC(params)
    }

    @Transactional
    def save(QuestionFaseTCC questionFaseTCCInstance) {
        if (questionFaseTCCInstance == null) {
            notFound()
            return
        }

        questionFaseTCCInstance.answers[0]= params.answers1
        questionFaseTCCInstance.answers[1]= params.answers2
        questionFaseTCCInstance.answers[2]= params.answers3
        questionFaseTCCInstance.answers[3]= params.answers4
        questionFaseTCCInstance.answers[4]= params.answers5
        questionFaseTCCInstance.ownerId = session.user.id as long
        questionFaseTCCInstance.taskId = session.taskId as String
        questionFaseTCCInstance.save flush:true

        redirect(action: "index")
    }

    def edit(QuestionFaseTCC questionFaseTCCInstance) {
        respond questionFaseTCCInstance
    }

    @Transactional
    def update() {
        QuestionFaseTCC questionFaseTCCInstance = QuestionFaseTCC.findById(Integer.parseInt(params.faseTCCID))
        questionFaseTCCInstance.title = params.title
        questionFaseTCCInstance.answers[0]= params.answers1
        questionFaseTCCInstance.answers[1]= params.answers2
        questionFaseTCCInstance.answers[2]= params.answers3
        questionFaseTCCInstance.answers[3]= params.answers4
        questionFaseTCCInstance.answers[4]= params.answers5
        questionFaseTCCInstance.correctAnswer = Integer.parseInt(params.correctAnswer)
        questionFaseTCCInstance.ownerId = session.user.id as long
        questionFaseTCCInstance.taskId = session.taskId as String
        questionFaseTCCInstance.save flush:true

        redirect action: "index"
    }

    @Transactional
    def delete(QuestionFaseTCC questionFaseTCCInstance) {

        if (questionFaseTCCInstance == null) {
            notFound()
            return
        }

        questionFaseTCCInstance.delete flush:true
        redirect action: "index"
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'faseTCC.label', default: 'QuestionFaseTCC'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }

    @Secured(['permitAll'])
    def returnInstance(QuestionFaseTCC questionFaseTCCInstance){
        if (questionFaseTCCInstance == null) {
            //notFound()
            render "null"
        }
        else{
            render questionFaseTCCInstance.title + "%@!" +
                    questionFaseTCCInstance.answers[0] + "%@!" +
                    questionFaseTCCInstance.answers[1] + "%@!" +
                    questionFaseTCCInstance.answers[2] + "%@!" +
                    questionFaseTCCInstance.answers[3] + "%@!" +
                    questionFaseTCCInstance.answers[4] + "%@!" +
                    questionFaseTCCInstance.correctAnswer + "%@!" +
                    questionFaseTCCInstance.id
        }

    }

    @Transactional
    def exportQuestions(){
        //popula a lista de questoes a partir do ID de cada uma
        ArrayList<Integer> list_questionId = new ArrayList<Integer>() ;
        ArrayList<QuestionFaseTCC> questionList = new ArrayList<QuestionFaseTCC>();
        list_questionId.addAll(params.list_id);
        for (int i=0; i<list_questionId.size();i++)
            questionList.add(QuestionFaseTCC.findById(list_questionId[i]));

        //cria o arquivo json
        createJsonFile("questoesTCC.json", questionList)
        render "index"

        //def ids = []
        //def folder = servletContext.getRealPath("/data/${session.user.id}/${session.taskId}")

        //ids << MongoHelper.putFile(folder + '/pergFacil.json')
        //ids << MongoHelper.putFile(folder + '/pergMedio.json')
        //ids << MongoHelper.putFile(folder + '/pergDificil.json')

        //def port = request.serverPort
        //if (Environment.current == Environment.DEVELOPMENT) {
        //   port = 8080
        //}

        //render  "http://${request.serverName}:${port}/process/task/complete/${session.taskId}" +
        //        "?files=${ids[0]}&files=${ids[1]}&files=${ids[2]}"


    }

    void createJsonFile(String fileName, ArrayList<QuestionFaseTCC> questionList){
        def dataPath = servletContext.getRealPath("/data")
        def instancePath = new File("${dataPath}/${springSecurityService.currentUser.id}/${session.taskId}")
        instancePath.mkdirs()

        File file = new File("$instancePath/"+fileName);
        PrintWriter pw = new PrintWriter(file);
        pw.write("{\n");
        for(def i=0; i<5;i++){
            pw.write("\t\"" + (i+1) + "\": [\"" + questionList[i].title + "\", ")
            pw.write("\""+ questionList[i].answers[0] +"\", " + "\""+ questionList[i].answers[1] +"\", ")
            pw.write("\""+ questionList[i].answers[2] +"\", " + "\""+ questionList[i].answers[3] +"\", ")
            pw.write("\""+ questionList[i].answers[4] +"\", ")
            switch(questionList[i].correctAnswer){
                case 0:
                    pw.write("\"A\"]")
                    break;
                case 1:
                    pw.write("\"B\"]")
                    break;
                case 2:
                    pw.write("\"C\"]")
                    break;
                case 3:
                    pw.write("\"D\"]")
                    break;
                case 4:
                    pw.write("\"E\"]")
                    break;
                default:
                    println("Erro! Alternativa correta inválida")
            }
            if(i<4)
                pw.write(",")
            pw.write("\n")
        }
        pw.write("}");
        pw.close();
    }
}
