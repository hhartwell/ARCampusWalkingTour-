package harvey.com.omekadatabasetestingground;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Harvey on 2/3/2018.
 */

public class DBHelper extends SQLiteOpenHelper {
    static final String DATABASE_NAME = "omeka";
    static final String TABLE_OMEKA_COLLECTIONS = "omeka_collections";
    static final String TABLE_OMEKA_COMMENTS = "omeka_comments";
    static final String TABLE_OMEKA_ELEMENT_SETS = "omeka_element_sets";
    static final String TABLE_OMEKA_ELEMENT_TEXTS = "omeka_element_texts";
    static final String TABLE_OMEKA_ELEMENTS = "omeka_elements";
    static final String TABLE_OMEKA_EXHIBIT_BLOCK_ATTACHMENTS = "omeka_exhibit_block_attachments";
    static final String TABLE_OMEKA_EXHIBIT_PAGE_BLOCKS = "omeka_exhibit_page_blocks";
    static final String TABLE_OMEKA_EXHIBIT_PAGES = "omeka_exhibit_pages";
    static final String TABLE_OMEKA_EXHIBITS = "omeka_exhibits";
    static final String TABLE_OMEKA_FILES = "omeka_files";
    static final String TABLE_OMEKA_ITEM_TYPES = "omeka_item_types";
    static final String TABLE_OMEKA_ITEM_TYPE_ELEMENTS = "omeka_item_type_elements";
    static final String TABLE_OMEKA_ITEMS = "omeka_items";
    static final String TABLE_OMEKA_KEYS = "omeka_keys";
    static final String TABLE_OMEKA_NEATLINE_EXHIBITS = "omeka_neatline_exhibits";
    static final String TABLE_OMEKA_NEATLINE_FEATURES = "omeka_neatline_features";
    static final String TABLE_OMEKA_NEATLINE_RECORDS = "omeka_neatline_records";
    static final String TABLE_OMEKA_NEATLINE_SIMILE_EXHIBIT_EXPANSIONS = "omeka_neatline_simile_exhibit_expansions";
    static final String TABLE_OMEKA_NEATLINE_TIME_TIMELINES = "omeka_neatline_time_timelines";
    static final String TABLE_OMEKA_OPTIONS = "omeka_options";
    static final String TABLE_OMEKA_PLUGINS = "omeka_plugins";
    static final String TABLE_OMEKA_PROCESSES = "omeka_processes";
    static final String TABLE_OMEKA_RECORDS_TAGS = "omeka_records_tags";
    static final String TABLE_OMEKA_SCHEMA_MIGRATIONS = "omeka_schema_migrations";
    static final String TABLE_OMEKA_SEARCH_TEXTS = "omeka_search_texts";
    static final String TABLE_OMEKA_SESSIONS = "omeka_sessions";
    static final String TABLE_SIMPLE_PAGES_PAGES = "omeka_simple_pages_pages";
    static final String TABLE_OMEKA_TAGS = "omeka_tags";
    static final String TABLE_OMEKA_users = "omeka_users";
    static final String TABLE_OMEKA_USERS_ACTIVATIONS = "omeka_users_activations";

    static final int DATABASE_VERSION = 1;

    public DBHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
