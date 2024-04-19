    package com.example.khatta.database;

    import android.content.Context;

    import androidx.annotation.NonNull;
    import androidx.room.Room;
    import androidx.room.RoomDatabase;
    import androidx.sqlite.db.SupportSQLiteDatabase;

    import com.example.khatta.User;

    import java.util.concurrent.ExecutorService;
    import java.util.concurrent.Executors;

    public class DatabaseInitializer {
        private static final String DATABASE_NAME = "your_database_name";

        private static final Object LOCK = new Object();
        private static volatile DatabaseInitializer instance;
        private static final ExecutorService databaseWriteExecutor =
                Executors.newFixedThreadPool(4);

        private final AppDatabase database;

        private DatabaseInitializer(@NonNull Context context) {
            database = Room.databaseBuilder(context.getApplicationContext(),
                            AppDatabase.class, DATABASE_NAME)
                    .addCallback(new RoomDatabase.Callback() {
                        @Override
                        public void onCreate(@NonNull SupportSQLiteDatabase db) {
                            super.onCreate(db);
                            databaseWriteExecutor.execute(() -> {
                                UserDao userDao = instance.database.userDao();
                                userDao.deleteAll(); // Optional: Clear existing data

                                User user1 = new User();
                                user1.setUsername("Sumit2003");
                                user1.setPassword("chinalnotfound404");
                                userDao.insert(user1);

                                User user2 = new User();
                                user2.setUsername("Yash2002");
                                user2.setPassword("chinalfound101");
                                userDao.insert(user2);

                                User admin = new User();
                                admin.setUsername("YS2000");
                                admin.setPassword("chinalfoundandnotfound");
                                userDao.insert(admin);
                            });
                        }
                    })
                    .build();
        }

        public static DatabaseInitializer getInstance(Context context) {
            if (instance == null) {
                synchronized (LOCK) {
                    if (instance == null) {
                        instance = new DatabaseInitializer(context);
                    }
                }
            }
            return instance;
        }

        public AppDatabase getDatabase() {
            return database;
        }
    }