BEGIN TRANSACTION;

DROP TABLE IF EXISTS users, Course, TeeTime, LeagueMembership, MatchPlayer, Score, Message, leaderboard, LeagueInvite, League;

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
  address VARCHAR(255) NOT NULL,
  city VARCHAR(255) NOT NULL,
  state VARCHAR(255) NOT NULL,
  zipcode VARCHAR(255) NOT NULL,
  latitude DECIMAL NOT NULL,
  longitude DECIMAL NOT NULL
);

CREATE TABLE League (
  league_id SERIAL PRIMARY KEY,
  name VARCHAR(255) NOT NULL,
  organizer_id INTEGER REFERENCES users(user_id)
);

CREATE TABLE TeeTime (
  tee_time_id SERIAL PRIMARY KEY,
  match_name VARCHAR(255),
  course_id INTEGER REFERENCES Course(course_id),
  time TIMESTAMP NOT NULL,
  organizer_id INTEGER REFERENCES users(user_id),
  league_id INTEGER REFERENCES League(league_id)
);

CREATE TABLE LeagueMembership (
  league_membership_id SERIAL PRIMARY KEY,
  league_id INTEGER REFERENCES League(league_id),
  user_id INTEGER REFERENCES users(user_id)
);

CREATE TABLE MatchPlayer (
  match_player_id SERIAL PRIMARY KEY,
  match_id INTEGER REFERENCES TeeTime(tee_time_id),
  player_id INTEGER REFERENCES users(user_id)
);

CREATE TABLE Score (
  score_id SERIAL PRIMARY KEY,
  match_id INTEGER REFERENCES TeeTime(tee_time_id),
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
  league_id INTEGER REFERENCES League(league_id),
  type VARCHAR(20),
  message_read BOOLEAN NOT NULL,
  timestamp TIMESTAMP
);

CREATE TABLE LeagueInvite (
  league_invite_id SERIAL PRIMARY KEY,
  sender_id INTEGER REFERENCES users(user_id),
  receiver_id INTEGER REFERENCES users(user_id),
  league_id INTEGER REFERENCES League(league_id),
  content TEXT,
  status varchar(50),
  timestamp TIMESTAMP
);

COMMIT TRANSACTION;


--ALTER TABLE Message
--DROP CONSTRAINT message_league_id_fkey;
--
--ALTER TABLE Message
--ALTER COLUMN league_id DROP NOT NULL;
--
--ALTER TABLE Message
--ADD CONSTRAINT fk_message_league
--FOREIGN KEY (league_id) REFERENCES League(league_id) ON DELETE CASCADE;



--SELECT * FROM TeeTime;
--
--INSERT INTO Score (match_id, player_id, score) VALUES (1, 1, 78);
--INSERT INTO Score (match_id, player_id, score) VALUES (1, 2, 75);
--INSERT INTO Score (match_id, player_id, score) VALUES (1, 3, 82);
--INSERT INTO Score (match_id, player_id, score) VALUES (1, 4, 85);
--
--INSERT INTO Score (match_id, player_id, score) VALUES (2, 5, 83);
--INSERT INTO Score (match_id, player_id, score) VALUES (2, 6, 90);
--INSERT INTO Score (match_id, player_id, score) VALUES (2, 3, 79);
--INSERT INTO Score (match_id, player_id, score) VALUES (2, 4, 81);
--
--INSERT INTO Score (match_id, player_id, score) VALUES (3, 1, 67);
--INSERT INTO Score (match_id, player_id, score) VALUES (3, 2, 89);
--INSERT INTO Score (match_id, player_id, score) VALUES (3, 5, 75);
--INSERT INTO Score (match_id, player_id, score) VALUES (3, 6, 85);
--
--INSERT INTO Score (match_id, player_id, score) VALUES (4, 1, 86);
--INSERT INTO Score (match_id, player_id, score) VALUES (4, 2, 95);
--INSERT INTO Score (match_id, player_id, score) VALUES (4, 3, 80);
--INSERT INTO Score (match_id, player_id, score) VALUES (4, 4, 82);
--
--INSERT INTO Score (match_id, player_id, score) VALUES (5, 5, 87);
--INSERT INTO Score (match_id, player_id, score) VALUES (5, 6, 77);
--INSERT INTO Score (match_id, player_id, score) VALUES (5, 3, 88);
--INSERT INTO Score (match_id, player_id, score) VALUES (5, 4, 90);
--
--INSERT INTO Score (match_id, player_id, score) VALUES (6, 5, 79);
--INSERT INTO Score (match_id, player_id, score) VALUES (6, 1, 82);
--INSERT INTO Score (match_id, player_id, score) VALUES (6, 3, 86);
--INSERT INTO Score (match_id, player_id, score) VALUES (6, 4, 76);
--
--SELECT * FROM Score;
--
--INSERT INTO Leaderboard (league_id, user_id, total_score) VALUES (1, 1, 78);
--INSERT INTO Leaderboard (league_id, user_id, total_score) VALUES (1, 2, 75);
--INSERT INTO Leaderboard (league_id, user_id, total_score) VALUES (1, 3, 82);
--INSERT INTO Leaderboard (league_id, user_id, total_score) VALUES (1, 4, 85);
--INSERT INTO Leaderboard (league_id, user_id, total_score) VALUES (1, 5, 83);
--INSERT INTO Leaderboard (league_id, user_id, total_score) VALUES (1, 6, 90);
--
--UPDATE Leaderboard SET total_score = total_score + 79 WHERE user_id = 3;
--UPDATE Leaderboard SET total_score = total_score + 81 WHERE user_id = 4;
--UPDATE Leaderboard SET total_score = total_score + 67 WHERE user_id = 1;
--UPDATE Leaderboard SET total_score = total_score + 89 WHERE user_id = 2;
--UPDATE Leaderboard SET total_score = total_score + 75 WHERE user_id = 5;
--UPDATE Leaderboard SET total_score = total_score + 85 WHERE user_id = 6;
--UPDATE Leaderboard SET total_score = total_score + 86 WHERE user_id = 1;
--UPDATE Leaderboard SET total_score = total_score + 95 WHERE user_id = 2;
--UPDATE Leaderboard SET total_score = total_score + 80 WHERE user_id = 3;
--UPDATE Leaderboard SET total_score = total_score + 82 WHERE user_id = 4;
--UPDATE Leaderboard SET total_score = total_score + 87 WHERE user_id = 5;
--UPDATE Leaderboard SET total_score = total_score + 77 WHERE user_id = 6;
--UPDATE Leaderboard SET total_score = total_score + 88 WHERE user_id = 3;
--UPDATE Leaderboard SET total_score = total_score + 90 WHERE user_id = 4;
--UPDATE Leaderboard SET total_score = total_score + 79 WHERE user_id = 5;
--UPDATE Leaderboard SET total_score = total_score + 82 WHERE user_id = 1;
--UPDATE Leaderboard SET total_score = total_score + 86 WHERE user_id = 3;
--UPDATE Leaderboard SET total_score = total_score + 76 WHERE user_id = 4;
--
--SELECT * FROM Leaderboard ORDER BY total_score;
--
--SELECT user_id, username, password_hash, role
--FROM users
--JOIN LeagueMembership USING (user_id)
--WHERE LeagueMembership.league_id = 1;
