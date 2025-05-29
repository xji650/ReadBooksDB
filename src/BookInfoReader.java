/*
 * Autors:
 * -Xiaolong Ji
 * -Aticor
 */
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;

public class BookInfoReader {

    /**
     * Llegeix un dels fitxers de text per carregar la informació d'un llibre.
     * @param fileName Nom del fitxer de text a llegir.
     * @return Informació del llibre llegit.
     * @throws IOException
     */
    public static BookInfo readBookFile (String fileName) throws IOException {
        //DECLARACIONS
        //variable on es guarda la cadena sencera de la línia llegida per br (BufferedReader).
        String line = "";
        //variable on en guarda el títol.
        String title = "";
        //variable on en guarda el autor.
        String author = "";
        //variable on en guarda la sèrie.
        String series = "";
        //variable on en guarda les nombres de pàgines.
        short pages = 0;
        //variable on en guarda si començat o no.
        boolean started = false;
        //variable on en guarda si acabat o no.
        boolean finished = false;
        //variable on en guarda la data de l'inici.
        String startDate = "";
        //variable on en guarda la data de la finalització.
        String endDate = "";

        //INSTRUCCIONS
        //BufferedReader llegeix sobre FileReader sobre el fitxer a llegir.
        BufferedReader br = new BufferedReader (new FileReader (fileName));
        //El contingut llegit per BufferedReader en guarda en variable "line"
        line = br.readLine();

        //Llegeix títol
        //si la línia llegida no buit, es guarda a la variable "títol".
        if(line != null){
            title = line;
        } 
        line = br.readLine(); //Llegeix la següent línia

        //Llegeix autor
        //si la línia llegida no buit, es guarda a la variable "autor".
        if (line != null){
            author = line;
        }
        line = br.readLine(); //Llegeix la següent línia

        //Llegeix sèrie
        //si la línia llegida no buit, es guarda a la variable "sèrie".
        if (line != null){
            series = line;                    
        }
        line = br.readLine(); //Llegeix la següent línia

        //Llegeix nombres de pàgines
        //si la línia llegida no buit, transforma string a tipus short i
        // es guarda a la variable "pàgina".
        if (line != null){
            pages = Short.parseShort(line);                   
        } 
        line = br.readLine(); //Llegeix la següent línia

        //Llegeix data d'inici de la lectura
        //si la línia llegida no buit, vol dir que ha estat començat
        // (started = true), i es guarda a la variable "data d'inici".
        if (line != null && !line.isEmpty()){
            started = true;
            startDate = line;               
        }
        line = br.readLine(); //Llegeix la següent línia

        //Llegeix data de finalització de la lectura
        //si la línia llegida no buit, vol dir que ha estat acabat
        // (started = true), i es guarda a la variable "data de finalització".
        if (line != null && !line.isEmpty()){
            finished = true;
            endDate = line;
        }
        br.close(); //Tancar el buffer

        //Retorna informació del llibre.
        return new BookInfo (title, author, series, pages, started, finished, startDate, endDate);
    } 

}


