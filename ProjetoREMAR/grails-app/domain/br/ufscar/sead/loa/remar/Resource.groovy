package br.ufscar.sead.loa.remar



class Resource {

    static belongsTo = [owner: User, category: Category]

    static hasMany = [ratings: Rating]

    static constraints = {
        submittedAt blank: false
        name blank: false
        comment nullable: true, blank: true
        bpmn nullable: true
        moodle defaultValue: false
        android defaultValue: false
        web defaultValue: true
        linux defaultValue: false
        files nullable: true
        width nullable: true
        height nullable: true
        description nullable: true, maxSize: 1000
        pending nullable: true
        sumStars nullable: false
        sumUser nullable: false
    }

    String name
    boolean active
    int version
    String uri

    boolean web
    boolean android
    boolean linux
    boolean moodle
    String files

    // deploy
    Date submittedAt
    String status
    String comment
    boolean valid
    String bpmn

    int width
    int height

    String description
    boolean pending

    float sumStars //total de estrelas
    int   sumUser  //total de usuários que comentaram o jogo
}