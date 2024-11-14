package dev.erickystn.literalura.model;

public enum Idioma {
    ESPANHOL("es"),
    INGLES("en"),
    FRANCES("fr"),
    PORTUGUES("pt");


    private final String codigo;

    Idioma(String codigo) {
        this.codigo = codigo;
    }

    public String getCodigo() {
        return codigo;
    }

    public static Idioma porCodigo(String codigo) {
        for (Idioma idioma : values()) {
            if (idioma.codigo.equals(codigo)) {
                return idioma;
            }
        }
        System.out.println("Código não encontrado: " + codigo);
        return null;
    }
}
