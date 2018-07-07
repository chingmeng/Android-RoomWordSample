package mengws.com.roomwordsample;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

/*
 Each @Entity class represents an entity in a table.
 Annotate your class declaration to indicate that it's an entity.
 Specify the name of the table if you want it to be different from
 the name of the class.
 */

/*
*
* Tip: You can autogenerate unique keys by annotating the
* primary key as follows:

        @Entity(tableName = "word_table")
        public class Word {

        @PrimaryKey(autoGenerate = true)
        private int id;

        @NonNull
        private String word;
        //..other fields, getters, setters
        }
*
* */

@Entity(tableName = "word_table")
public class Word {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "word")
    private String mWord;

    public Word(@NonNull String word) {this.mWord = mWord;}

    public String getWord() {
        return this.mWord;
    }
}
