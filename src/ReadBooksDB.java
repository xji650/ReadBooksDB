/*
 * Autors:
 * -Xiaolong Ji
 * -Aticor
 */

import java.io.RandomAccessFile;
import java.io.IOException;

public class ReadBooksDB {

    private RandomAccessFile booksDB;
    private int numBooks;

    /**
     * Conté els mètodes propis de la base de dades.
     * @param fileName Nombre del fitxer de base de dades (.dat).
     * @throws IOException
     */
    public ReadBooksDB (String fileName) throws IOException {
        booksDB = new RandomAccessFile (fileName, "rw");
        numBooks = (int)booksDB.length() / BookInfo.SIZE;
    }

    /**
     * Obtenir nombres de llibres.
     * @return Nombres de llibres.
     */
    public int getNumBooks() {
        return numBooks;
    }

    /**
     * Tancar la lectura del fitxer (RAF).
     * @throws IOException
     */
    public void close() throws IOException {
        booksDB.close();
    }

    /**
     * Recuperar tota la informació i deixar-ho igual com abans d'executar.
     * @throws IOException
     */
    public void reset() throws IOException {
        booksDB.setLength (0);
        numBooks = 0;
    }

    /**
     * Escriu la informació d'un llibre en un fitxer binari.
     * @param n Posició del registre en el fitxer binari.
     * @param book Llibre a llegir
     */
    public void writeBookInfo (int n, BookInfo book) throws IOException {
        booksDB.seek (n * BookInfo.SIZE);
        byte[] record = book.toBytes();
        booksDB.write (record);
    }

    /**
     * Llegeix la informació d'un llibre des del fitxer binari.
     * @param n Posició del registre en el fitxer binari.
     * @return BookInfo
     * @throws IOException
     */
    public BookInfo readBookInfo (int n) throws IOException {
        booksDB.seek (n * BookInfo.SIZE);
        byte[] record = new byte[BookInfo.SIZE];
        booksDB.read (record);
        return BookInfo.fromBytes (record);
    }

    /**
     * Genera informació del llibre en la base de dades (document.dat).
     * @param book Títol del llibre a escriure la informació sobre BBDD.
     * @throws IOException
     */
    public void appendBookInfo (BookInfo book) throws IOException {
        writeBookInfo (numBooks, book);
        numBooks++;
    }

    /**
     * Cerca un llibre a partir del seu nom.
     * @param title Nom del llibre a cercar.
     * @return Posició dins del fitxer, o -1 si no el troba.
     * @throws IOException
     */
    public int searchBookByTitle (String title) throws IOException {
        for (int i = 0; i < numBooks; i++) {
            BookInfo book = readBookInfo(i);
            //equalsIgnoreCase per no diferenciar majúscules i minúscules.
            if (book.getTitle().equalsIgnoreCase(title)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Cerca i esborra un llibre a partir del seu nom.
     * @param title Nom del llibre a cercar i a eliminar.
     * @return Booleà indicant si ha estat esborrat (true) o no (false).
     * @throws IOException
     */
    public boolean deleteByTitle (String title) throws IOException {
        int bookIndex = searchBookByTitle(title);
        if (bookIndex != -1) {
            int lastBookIndex = numBooks - 1;
            BookInfo lastBook = readBookInfo(lastBookIndex);
            //Moure darrer llibre cap a la posició del llibre esborrat.
            writeBookInfo(bookIndex, lastBook);
            numBooks--;
            return true;
        }
        return false;
    }

}
