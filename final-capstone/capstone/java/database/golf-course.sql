




BEGIN TRANSACTION;


DROP TABLE IF EXISTS users,Course,TeeTime,League,LeagueMembership,MatchPlayer,Match,Score,Message,leaderboard;


CREATE TABLE users (
   user_id SERIAL,
   username varchar(50) NOT NULL UNIQUE,
   password_hash varchar(200) NOT NULL,
   role varchar(50) NOT NULL,
   CONSTRAINT PK_user PRIMARY KEY (user_id)
);


CREATE TABLE Course (
  course_id SERIAL PRIMARY KEY,
  name VARCHAR(255) NOT NULL,
  location VARCHAR(255) NOT NULL,
  latitude FLOAT NOT NULL,
  longitude FLOAT NOT NULL
);


CREATE TABLE TeeTime (
  tee_time_id SERIAL PRIMARY KEY,
  course_id INTEGER REFERENCES Course(course_id),
  time TIMESTAMP NOT NULL,
  organizer_id INTEGER REFERENCES users(user_id)
);


CREATE TABLE League (
  league_id SERIAL PRIMARY KEY,
  name VARCHAR(255) NOT NULL,
  organizer_id INTEGER REFERENCES users(user_id)
);


CREATE TABLE LeagueMembership (
  league_membership_id SERIAL PRIMARY KEY,
  league_id INTEGER REFERENCES League(league_id),
  user_id INTEGER REFERENCES users(user_id)
);


CREATE TABLE Match (
  match_id SERIAL PRIMARY KEY,
  league_id INTEGER REFERENCES League(league_id),
  tee_time_id INTEGER REFERENCES TeeTime(tee_time_id)
);


CREATE TABLE MatchPlayer (
  match_player_id SERIAL PRIMARY KEY,
  match_id INTEGER REFERENCES Match(match_id),
  player_id INTEGER REFERENCES users(user_id)
);


CREATE TABLE Score (
  score_id SERIAL PRIMARY KEY,
  match_id INTEGER REFERENCES Match(match_id),
  player_id INTEGER REFERENCES users(user_id),
  score INTEGER
);

CREATE TABLE Leaderboard (
  leaderboard_id SERIAL PRIMARY KEY,
  league_id INTEGER REFERENCES League(league_id),
  user_id INTEGER REFERENCES users(user_id),
  total_score INTEGER
);

CREATE TABLE Message (
  message_id SERIAL PRIMARY KEY,
  sender_id INTEGER REFERENCES users(user_id),
  receiver_id INTEGER REFERENCES users(user_id),
  content TEXT,
  timestamp TIMESTAMP
);
INSERT INTO users (username,password_hash,role) VALUES ('user','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER');
INSERT INTO users (username,password_hash,role) VALUES ('admin','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_ADMIN');


COMMIT TRANSACTION;