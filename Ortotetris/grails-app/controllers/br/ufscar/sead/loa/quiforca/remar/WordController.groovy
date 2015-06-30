package br.ufscar.sead.loa.quiforca.remar


import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional



@Transactional(readOnly = true)
class WordController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    /* Funções que manipulam word e answer */
    def initialize_word(Word wordInstance){
        String aux = ""+wordInstance.getAnswer()
        if (wordInstance.getAnswer().length() < 10) {
            for (int i = (10 - wordInstance.getAnswer().length()); i > 0; i--)
                aux+=("ì")
        }
        wordInstance.setWord(aux)
        wordInstance.setInitial_position(0)
    } //copia answer para word e completa word com 'ì' caso answer.lenght()<10

    def move_to_left(Word wordInstance) {
        if (wordInstance.getWord().charAt(0) == 'ì') {
            String aux = wordInstance.getWord().substring(1, 10)
            aux+=("ì")
            wordInstance.setWord(aux)
            wordInstance.setInitial_position(wordInstance.getInitial_position()-1)
        }
    }//move word para a esquerda

    def move_to_right(Word wordInstance) {
        if (wordInstance.getWord().charAt(9) == 'ì') {
            String aux = "ì"
            aux+=(wordInstance.getWord().substring(0, 9))
            wordInstance.setWord(aux)
            wordInstance.setInitial_position(wordInstance.getInitial_position()+1)
        }
    }//move word para a direita

    def mark_letter(Word wordInstance, int position) {
        if ((position-1 >= wordInstance.getInitial_position()) && (position-1 <= wordInstance.getInitial_position() + wordInstance.getAnswer().length()-1)) {
            String aux
            aux = wordInstance.getWord().substring(0, position-1)
            aux+=("0")
            aux+=(wordInstance.getWord().substring(position, 10))
            wordInstance.setWord(aux)
        }
    }//marca o caractere como '0' (esconde o caractere)

    def clear_position(Word wordInstance, int position) {
        if ((position-1 >= wordInstance.getInitial_position()) && (position-1 <= wordInstance.getInitial_position() + wordInstance.getAnswer().length()-1)) {
            String aux
            aux = wordInstance.getWord().substring(0, position-1)
            aux+=(wordInstance.getAnswer().charAt(position - initial_position-1))
            aux+=((wordInstance.getWord().substring(position, 10)))
            wordInstance.setWord(aux)
        }
    }//acessa answer e recupera o caractere que havia sido escondido



    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Word.list(params), model:[wordInstanceCount: Word.count()]
    }

    def show(Word wordInstance) {
        respond wordInstance
    }

    def create() {
        respond new Word(params)
    }

    @Transactional
    def save(Word wordInstance) {
        if (wordInstance == null) {
            notFound()
            return
        }

        if (wordInstance.hasErrors()) {
            respond wordInstance.errors, view:'create'
            return
        }

        initialize_word(wordInstance)

        wordInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'word.label', default: 'Word'), wordInstance.id])
                redirect wordInstance
            }
            '*' { respond wordInstance, [status: CREATED] }
        }
    }

    def edit(Word wordInstance) {
        respond wordInstance
    }

    @Transactional
    def update(Word wordInstance) {
        if (wordInstance == null) {
            notFound()
            return
        }

        if (wordInstance.hasErrors()) {
            respond wordInstance.errors, view:'edit'
            return
        }

        wordInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'Word.label', default: 'Word'), wordInstance.id])
                redirect wordInstance
            }
            '*'{ respond wordInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(Word wordInstance) {

        if (wordInstance == null) {
            notFound()
            return
        }

        wordInstance.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'Word.label', default: 'Word'), wordInstance.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'word.label', default: 'Word'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
