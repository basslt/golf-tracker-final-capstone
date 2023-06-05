BEGIN TRANSACTION;


DROP TABLE IF EXISTS users,Course,TeeTime,League,LeagueMembership,MatchPlayer,Match,Score,Message,leaderboard,LeagueInvite;


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


CREATE TABLE TeeTime (
  tee_time_id SERIAL PRIMARY KEY,
  course_id INTEGER REFERENCES Course(course_id),
  time TIMESTAMP NOT NULL,
  player_id INTEGER REFERENCES users(user_id),
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
  match_name VARCHAR(255) NOT NULL,
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