/*
 * Autors:
 * -Xiaolong Ji
 * -Aticor
 */

public class BookInfo {

    private String  title;
    private String author;
    private String series;
    private short   pages;
    private boolean  started;
    private boolean finished;
    private String startDate;
    private String   endDate;

    private static final int  TITLE_LIMIT = 32;
    private static final int AUTHOR_LIMIT = 26;
    private static final int SERIES_LIMIT = 32;
    private static final int   DATE_LIMIT = 10;

    /**
     * Nombre total de bytes d'informació per a un llibre.
     * Cada chars és 2 bytes, String és la cadena de chars.
     * Short és 2 bytes.
     * Booleà és d'1 byte.
     */
    public static final int SIZE = (2*TITLE_LIMIT)+(2*AUTHOR_LIMIT)+(2*SERIES_LIMIT)+2+1+1+(2*DATE_LIMIT)+(2*DATE_LIMIT);

    //Método constructor
    /**
     * Informació d'un llibre on conté següents informacions:
     * @param title El títol.
     * @param author L'autor.
     * @param series La sèrie.
     * @param pages Les pàgines totals que conté.
     * @param started Està començat o no.
     * @param finished Està acabat o no.
     * @param startDate En cas d'haver començat, la data d'inici.
     * @param endDate En cas d'haver acabat, la data de finalització.
     */
    public BookInfo (String title, String author, String series, short pages,
                     boolean started, boolean finished, String startDate, String endDate) {
        this.title  = title;
        this.author = author;
        this.series = series;
        this.pages  = pages;
        this.started   = started;
        this.finished  = finished;
        this.startDate = startDate;
        this.endDate   = endDate;
    }

    // Getters
    /**
     * Obtenir títol.
     * @return Títol.
     */
    public String getTitle  () { return title;  }

    /**
     *  Obtenir autor.
     * @return Autor.
     */
    public String getAuthor () { return author; }

    /**
     *  Obtenir la sèrie.
     * @return Sèrie.
     */
    public String getSeries () { return series; }

    /**
     * Obtenir pàgina total.
     * @return Pàgina total.
     */
    public short  getPages  () { return pages;  }

    /**
     * Estat del llibre.
     * @return Començat o no.
     */
    public boolean isStarted   () { return  started;  }

    /**
     * Estat del llibre.
     * @return Acabat o no.
     */
    public boolean isFinished  () { return finished;  }

    /**
     * Obtenir data d'inici de la lectura.
     * @return Data d'inici de la lectura.
     */
    public String getStartDate () { return startDate; }

    /**
     * Obtenir la data de finalització de la lectura.
     * @return Data de finalització de la lectura.
     */
    public String getEndDate   () { return   endDate; }

    /**
     * Empaqueta un objecte de la classe en un array de bytes.
     * @return Array de bytes empaquetats.
     */
    public byte[] toBytes() {
        //Crear l'objecte
        byte[] record = new byte[SIZE];
        int offset = 0; //posició inicial de la informació del llibre.

        //Empaquetament del "títol" de tipus "string" amb límits de "TITLE_LIMIT bytes",
        //que s'emmagatzema al "record", començant per la posició "offset".
        PackUtils.packString (title, TITLE_LIMIT, record, offset);
        offset +=2*TITLE_LIMIT; //posició acumulada de l'inici següent byte a tractar.

        //Empaquetament de l'"autor" de tipus "string" amb límits de "AUTHOR_LIMIT bytes",
        //que s'emmagatzema al "record", començant per la posició "offset".
        PackUtils.packString (author, AUTHOR_LIMIT, record, offset);
        offset += 2*AUTHOR_LIMIT; //posició acumulada de l'inici següent byte a tractar.

        //Empaquetament del "sèries" de tipus "string" amb límits de "SERIES_LIMIT bytes",
        //que s'emmagatzema al "record", començant per la posició "offset".
        PackUtils.packString (series, SERIES_LIMIT, record, offset);
        offset += 2*SERIES_LIMIT; //posició acumulada de l'inici següent byte a tractar.

        //Empaquetament de les "pàgines" de tipus "short",
        //que s'emmagatzema al "record", començant per la posició "offset".
        PackUtils.packShort (pages, record, offset);
        offset += 2; //posició acumulada de l'inici següent byte a tractar.

        //Empaquetament de "si ha començat la lectura" de tipus "booleà",
        //que s'emmagatzema al "record", començant per la posició "offset".
        PackUtils.packBoolean (started, record, offset);
        offset += 1; //posició acumulada de l'inici següent byte a tractar.

        //Empaquetament de "si ha acabat la lectura" de tipus "booleà"
        //que s'emmagatzema al "record", començant per la posició "offset".
        PackUtils.packBoolean (finished, record, offset);
        offset += 1; //posició acumulada de l'inici següent byte a tractar.

        //Empaquetament de "la data d'inici de la lectura" del tipus "strings" amb límit
        //de "DATE_LIMIT bytes", que s'emmagatzema al "record", començant a la posició "offset".
        PackUtils.packString (startDate, DATE_LIMIT, record, offset);
        offset += 2*DATE_LIMIT; //posició acumulada de l'inici següent byte a tractar.

        //Empaquetament de "la data de la finalització de la lectura" del tipus "strings" amb límit
        //de "DATE_LIMIT bytes", que s'emmagatzema al "record", començant a la posició "offset".
        PackUtils.packString (endDate, DATE_LIMIT, record, offset);

        return record;
    }

    /**
     * Carrega un objecte de la classe a partir de un array de bytes.
     * @param record Array de bytes empaquetats.
     * @return Informació del llibre desempaquetat.
     */
    public static BookInfo fromBytes (byte[] record) {
        int offset= 0; //posició inicial de la informació del llibre.

        //Desempaquetament del "títol" de tipus "string",
        //que s'emmagatzema al "record", començant per la posició "offset".
        String 	title = PackUtils.unpackString(TITLE_LIMIT, record, offset);
        offset += 2*TITLE_LIMIT; //posició acumulada de l'inici següent byte a tractar.

        //Desempaquetament de l'"autor" de tipus "string",
        //que s'emmagatzema al "record", començant per la posició "offset".
        String author = PackUtils.unpackString(AUTHOR_LIMIT, record, offset);
        offset += 2*AUTHOR_LIMIT; //posició acumulada de l'inici següent byte a tractar.

        //Desempaquetament del "sèries" de tipus "string",
        //que s'emmagatzema al "record", començant per la posició "offset".
        String series = PackUtils.unpackString(SERIES_LIMIT, record, offset);
        offset += 2*SERIES_LIMIT; //posició acumulada de l'inici següent byte a tractar.

        //Desempaquetament de les "pàgines" de tipus "short",
        //que s'emmagatzema al "record", començant per la posició "offset".
        Short pages = PackUtils.unpackShort(record, offset);
        offset += 2; //posició acumulada de l'inici següent byte a tractar.

        //Desempaquetament de "si ha començat la lectura" de tipus "booleà",
        //que s'emmagatzema al "record", començant per la posició "offset".
        Boolean started = PackUtils.unpackBoolean(record, offset);
        offset += 1; //posició acumulada de l'inici següent byte a tractar.

        //Desempaquetament de "si ha acabat la lectura" de tipus "booleà"
        //que s'emmagatzema al "record", començant per la posició "offset".
        Boolean finished = PackUtils.unpackBoolean(record, offset);
        offset += 1; //posició acumulada de l'inici següent byte a tractar.

        //Desempaquetament de "la data d'inici de la lectura", que
        //s'emmagatzema al "record", començant a la posició "offset".
        String startDate = PackUtils.unpackString(DATE_LIMIT, record, offset);
        offset += 2*DATE_LIMIT; //posició acumulada de l'inici següent byte a tractar.

        //Desempaquetament de "la data de finalització de la lectura", que
        //s'emmagatzema al "record", començant a la posició "offset".
        String endDate = PackUtils.unpackString(DATE_LIMIT, record, offset);

        //Retorna la informació del llibre desempaquetat.
        return new BookInfo (title, author, series, pages, started, finished, startDate, endDate);
    }

    /**
     * toString
     * @return String
     */
    public String toString() {
        String result = title;
        if (! series.isEmpty()) result += " (" + series + ")";
        if (! author.isEmpty()) result += " by " + author;
        result += " with " + pages + " pages.";
        if (finished) {
            result += " Read from " + startDate + " to " + endDate + ".";
        } else if (started) {
            result += " Started on " + startDate + ".";
        }
        return result;
    }

}
