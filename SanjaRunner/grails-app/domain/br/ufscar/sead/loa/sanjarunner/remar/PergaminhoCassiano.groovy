package br.ufscar.sead.loa.sanjarunner.remar

class PergaminhoCassiano {

    String[] information = new String[5]

    long ownerId
    String taskId

    static constraints = {
        information (blank : false, size: 1..600)
    }
}
