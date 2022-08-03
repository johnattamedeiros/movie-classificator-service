CREATE SCHEMA movies;

CREATE TABLE movies.movie (
id INT auto_increment NOT NULL,
year_date INT NOT NULL,
title VARCHAR(200) NULL,
studios  VARCHAR(200)  NULL,
producers VARCHAR(200) NULL,
winner boolean NULL
);

CREATE TABLE movies.producer (
id INT auto_increment NOT NULL,
name VARCHAR(200) NULL
);

CREATE TABLE movies.movie_producer (
id INT auto_increment NOT NULL,
id_movie INT NOT NULL,
id_producer INT NOT NULL,
winner boolean NULL
);

CREATE TABLE movies.interval_win_producer (
id INT auto_increment NOT NULL,
id_producer INT NOT NULL,
name VARCHAR(200) NULL,
interval_win INT NULL,
previous_win INT NULL,
following_win INT NULL
);

CREATE OR REPLACE VIEW movies.producer_winner
AS
SELECT M.ID,M.YEAR_DATE,P.ID as ID_PRODUCER,P.NAME,
FROM MOVIES.MOVIE M
JOIN MOVIES.MOVIE_PRODUCER MP ON (MP.ID_MOVIE = M.ID)
JOIN MOVIES.PRODUCER P ON (MP.ID_PRODUCER = P.ID)
WHERE M.WINNER = TRUE
AND (SELECT COUNT(ID_PRODUCER) FROM MOVIES.MOVIE_PRODUCER MPW WHERE MPW.WINNER = TRUE AND MPW.ID_PRODUCER = P.ID) > 1
ORDER BY M.YEAR_DATE;