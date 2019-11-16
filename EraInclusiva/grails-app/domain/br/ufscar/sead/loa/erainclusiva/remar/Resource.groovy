package br.ufscar.sead.loa.erainclusiva.remar

class Resource {

    String statement
    String answer
    String category
    String author

    long ownerId
    String taskId

    static constraints = {
        statement blank: false, maxSize: 150
        answer blank: false, maxSize: 48
        category blank: true, inList: ['Leituras', 'Videos', 'Recursos', 'Audios']
        author blank: false
        ownerId blank: false, nullable: false
        taskId nullable: true
    }

    private static List<String> categorias;

    static List<String> getCategorias() {
        if (categorias == null) {
            categorias = new ArrayList<String>();
            categorias.add("Leituras");
            categorias.add("Videos");
            categorias.add("Recursos");
            categorias.add("Audios");
        }
        return categorias;
    }
}
