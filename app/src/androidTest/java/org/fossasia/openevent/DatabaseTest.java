package org.fossasia.openevent;

import android.database.sqlite.SQLiteDatabase;
import android.test.AndroidTestCase;
import android.util.Log;

import org.fossasia.openevent.data.Event;
import org.fossasia.openevent.data.Microlocation;
import org.fossasia.openevent.data.Session;
import org.fossasia.openevent.data.Speaker;
import org.fossasia.openevent.data.Sponsor;
import org.fossasia.openevent.data.Track;
import org.fossasia.openevent.data.Version;
import org.fossasia.openevent.dbutils.DbHelper;
import org.fossasia.openevent.dbutils.DbSingleton;
import org.fossasia.openevent.utils.randomStringGenerator;

import java.text.ParseException;

/**
 * Created by MananWason on 17-06-2015.
 */
public class DatabaseTest extends AndroidTestCase {
    private DbHelper db;
    private String DB_NAME;


    @Override
    protected void setUp() throws Exception {
        super.setUp();
        randomStringGenerator randomStringGenerator = new randomStringGenerator();
        DB_NAME = randomStringGenerator.generateRandomString();
        db = new DbHelper(mContext, DB_NAME);
        Event event = new Event(4, "foss", "a@b.com", "#000000", "img.png", "2015-06-05T12:00:00",
                "2015-06-06T12:00:00", 23.7f, 45.60f, "moscone centre", "www.event2.com", "swagger event");
        String eventQuery = event.generateSql();

        Sponsor sponsor = new Sponsor(5, "Google", "www.google.com", "google.png");
        String sponsorQuery = sponsor.generateSql();

        Speaker speaker = new Speaker(5, "manan", "manan.png", "manan wason", "IIITD",
                "mananwason.me", "twitter.com/mananwason", "facebook.com/mananwason",
                "github.com/mananwason", "linkedin.com/mananwason", "fossasia", "gsoc student", null, "india");
        String speakerQuery = speaker.generateSql();

        Microlocation microlocation = new Microlocation(4, "moscone centre", 35.6f, 112.5f, 2);
        String microlocationQuery = microlocation.generateSql();
        int[] speakers_array = {1};

        Session session = new Session(5, "abcd", "abc", "abcdefgh", "sdfjs dsjfnjs",
                "2015-06-05T00:00:00", "2015-06-06T00:00:00", "abcde", 1,
                "3", speakers_array, 2);

        String sessionQuery = session.generateSql();

        Version version = new Version(1, 2, 3, 4, 5, 6, 7);
        String versionQuery = version.generateSql();

        Track track = new Track(6, "android", "open source mobile os by google");
        String trackQuery = track.generateSql();

        SQLiteDatabase database = db.getWritableDatabase();
        database.beginTransaction();
        database.execSQL(versionQuery);
        database.execSQL(eventQuery);
        database.execSQL(speakerQuery);
        database.execSQL(sponsorQuery);
        database.execSQL(microlocationQuery);
        database.execSQL(sessionQuery);
        database.execSQL(trackQuery);
        database.setTransactionSuccessful();
        database.endTransaction();
        database.close();
    }

    public void testDropDB() {
        assertTrue(mContext.deleteDatabase(DB_NAME));
    }

    public void testCreateDB() {
        DbHelper dbHelper = new DbHelper(mContext);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        assertTrue(db.isOpen());
        db.close();
    }

    public void testSpeakersList() throws Exception {
        DbSingleton dbSingleton = DbSingleton.getInstance();

        assertNotNull(dbSingleton.getSpeakerList());
        assertTrue(dbSingleton.getSpeakerList().size() > 0);
    }

    public void testSessionsList() throws Exception {
        DbSingleton dbSingleton = DbSingleton.getInstance();

        assertNotNull(dbSingleton.getSessionList());
        assertTrue(dbSingleton.getSessionList().size() > 0);
    }

    public void testTracksList() throws Exception {
        DbSingleton dbSingleton = DbSingleton.getInstance();

        assertNotNull(dbSingleton.getTrackList());
        Log.d("TEST TRACKS", dbSingleton.getTrackList().size() + " " + DB_NAME);
        assertTrue(dbSingleton.getTrackList().size() > 0);
    }

    public void testVersionList() throws Exception {
        DbSingleton dbSingleton = DbSingleton.getInstance();

        assertNotNull(dbSingleton.getVersionIds());
    }

    public void testSponsorsList() throws Exception {
        DbSingleton dbSingleton = DbSingleton.getInstance();

        assertNotNull(dbSingleton.getSponsorList());
        assertTrue(dbSingleton.getSponsorList().size() >= 0);
    }

    public void addDummyData() throws ParseException {

    }

    @Override
    protected void tearDown() throws Exception {
        db.close();
        getContext().deleteDatabase(DB_NAME);
        super.tearDown();

    }
}