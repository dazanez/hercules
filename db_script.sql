DROP TABLE IF EXISTS users, exercises, progress_monitorings, trainers, training_sessions, wroutines, wroutines_exercises CASCADE;

CREATE TABLE users(
	id SERIAL PRIMARY KEY,
	email VARCHAR(255) UNIQUE NOT NULL
		CONSTRAINT valid_email CHECK (email ~* '^[a-zA-Z0-9_+&-]+(?:.[a-zA-Z0-9_+&-]+)*@(?:[a-zA-Z0-9-]+.)+[a-zA-Z]{2,7}$'),
	pass VARCHAR(255) NOT NULL,
	names VARCHAR(100) NOT NULL,
	age SMALLINT,
	is_trainer BOOL DEFAULT FALSE,
	created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
	last_login TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE trainers(
	id INT PRIMARY KEY REFERENCES users(id), -- llave primaria y foránea a la vez
	profession VARCHAR(100),
	rating SMALLINT CONSTRAINT valid_rating CHECK (rating >= 0 AND rating <= 10),
	experience SMALLINT
);

CREATE TABLE wroutines(
	id SERIAL PRIMARY KEY,
	trainer_id INT REFERENCES trainers(id),
	name VARCHAR(100) NOT NULL,
	development VARCHAR(100)
		CONSTRAINT valid_environment CHECK (development IN ('HOME', 'GYM', 'OTHER')),
	requirement SMALLINT
		CONSTRAINT valid_requirement CHECK (requirement >= 0 AND requirement <= 10),
	focus VARCHAR(100),
	category VARCHAR(100),
	objetive TEXT
);

CREATE TABLE exercises(
	id SERIAL PRIMARY KEY,
	name VARCHAR(255) NOT NULL,
	description TEXT,
	type VARCHAR(100),
	muscles_worked VARCHAR(100)
);

CREATE TABLE wroutines_exercises(
	id BIGSERIAL PRIMARY KEY,
	exercise_id INT REFERENCES exercises(id),
	wroutine_id INT REFERENCES wroutines(id),
	name VARCHAR(100),
	performing TEXT,
	repetitions SMALLINT,
	series SMALLINT,
	rest SMALLINT,
	precautions TEXT
);

CREATE TABLE training_sessions(
	id BIGSERIAL PRIMARY KEY,
	user_id INT REFERENCES users(id),
	wroutine_id INT REFERENCES wroutines(id),
	date_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
	rating SMALLINT CONSTRAINT valid_rating CHECK (rating >= 0 AND rating <= 10)
);

CREATE TABLE progress_monitorings(
	id BIGSERIAL PRIMARY KEY,
	user_id INT REFERENCES users(id),
	progress_date DATE DEFAULT CURRENT_DATE,
	weigh DECIMAL(5, 2),
	height DECIMAL(3, 2),
	circumference DECIMAL(3, 2)
);

-- Inserta datos en la tabla users
INSERT INTO users (email, pass, names, age, is_trainer) VALUES
('user1@example.com', 'password1', 'User One', 25, FALSE),
('user2@example.com', 'password2', 'User Two', 30, TRUE),
('user3@example.com', 'password3', 'User Three', 22, TRUE),
('user4@example.com', 'password4', 'User Four', 28, FALSE),
('user5@example.com', 'password5', 'User Five', 35, TRUE),
('user6@example.com', 'password6', 'User Six', 27, FALSE),
('user7@example.com', 'password7', 'User Seven', 32, TRUE),
('user8@example.com', 'password8', 'User Eight', 29, FALSE),
('user9@example.com', 'password9', 'User Nine', 26, TRUE),
('user10@example.com', 'password10', 'User Ten', 31, FALSE),
('user11@example.com', 'password11', 'User Eleven', 24, TRUE),
('user12@example.com', 'password12', 'User Twelve', 33, FALSE),
('user13@example.com', 'password13', 'User Thirteen', 27, TRUE),
('user14@example.com', 'password14', 'User Fourteen', 23, FALSE),
('user15@example.com', 'password15', 'User Fifteen', 28, TRUE),
('user16@example.com', 'password16', 'User Sixteen', 26, FALSE),
('user17@example.com', 'password17', 'User Seventeen', 29, TRUE),
('user18@example.com', 'password18', 'User Eighteen', 30, FALSE),
('user19@example.com', 'password19', 'User Nineteen', 34, TRUE),
('user20@example.com', 'password20', 'User Twenty', 25, FALSE);

-- Inserta datos en la tabla trainers
INSERT INTO trainers (id, profession, rating, experience) VALUES
(2, 'Fitness Trainer', 8, 5),
(3, 'Yoga Instructor', 9, 6),
(5, 'Personal Trainer', 7, 4),
(7, 'Strength Coach', 8, 5),
(9, 'Pilates Instructor', 9, 6),
(11, 'CrossFit Coach', 7, 4),
(13, 'Bodybuilding Coach', 8, 5),
(15, 'Aerobics Instructor', 9, 6),
(17, 'Dance Fitness Instructor', 7, 4),
(19, 'HIIT Coach', 8, 5);

-- Inserta datos en la tabla wroutines
INSERT INTO wroutines (trainer_id, name, development, requirement, focus, category, objetive) VALUES
(2, 'Routine 1', 'GYM', 5, 'Strength', 'Category 1', 'Build muscle'),
(3, 'Routine 2', 'HOME', 3, 'Flexibility', 'Category 2', 'Improve flexibility'),
(5, 'Routine 3', 'GYM', 4, 'Endurance', 'Category 3', 'Increase stamina'),
(7, 'Routine 4', 'OTHER', 2, 'Cardio', 'Category 4', 'Lose weight'),
(9, 'Routine 5', 'GYM', 6, 'Balance', 'Category 5', 'Enhance balance'),
(11, 'Routine 6', 'HOME', 3, 'Strength', 'Category 6', 'Gain strength'),
(13, 'Routine 7', 'GYM', 4, 'Power', 'Category 7', 'Increase power'),
(15, 'Routine 8', 'OTHER', 2, 'Coordination', 'Category 8', 'Improve coordination'),
(17, 'Routine 9', 'GYM', 5, 'Agility', 'Category 9', 'Boost agility'),
(19, 'Routine 10', 'HOME', 3, 'Stamina', 'Category 10', 'Build endurance');

-- Inserta datos en la tabla exercises
INSERT INTO exercises (name, description, type, muscles_worked) VALUES
('Push-up', 'Push-up description', 'Strength', 'Chest, Triceps'),
('Squat', 'Squat description', 'Strength', 'Legs, Glutes'),
('Plank', 'Plank description', 'Endurance', 'Core'),
('Lunge', 'Lunge description', 'Strength', 'Legs, Glutes'),
('Burpee', 'Burpee description', 'Cardio', 'Full body'),
('Bicep Curl', 'Bicep Curl description', 'Strength', 'Biceps'),
('Tricep Dip', 'Tricep Dip description', 'Strength', 'Triceps'),
('Mountain Climber', 'Mountain Climber description', 'Cardio', 'Core, Legs'),
('Sit-up', 'Sit-up description', 'Strength', 'Abs'),
('Deadlift', 'Deadlift description', 'Strength', 'Back, Legs');

-- Inserta datos en la tabla wroutines_exercises
INSERT INTO wroutines_exercises (exercise_id, wroutine_id, name, performing, repetitions, series, rest, precautions) VALUES
(1, 1, 'Push-up Routine 1', 'Perform as described', 15, 3, 60, 'Keep your back straight'),
(2, 1, 'Squat Routine 1', 'Perform as described', 20, 3, 60, 'Keep your knees behind your toes'),
(3, 2, 'Plank Routine 2', 'Perform as described', 1, 3, 60, 'Keep your body in a straight line'),
(4, 2, 'Lunge Routine 2', 'Perform as described', 15, 3, 60, 'Do not let your knee touch the ground'),
(5, 3, 'Burpee Routine 3', 'Perform as described', 10, 3, 60, 'Land softly on your feet'),
(6, 3, 'Bicep Curl Routine 3', 'Perform as described', 12, 3, 60, 'Do not swing the weights'),
(7, 4, 'Tricep Dip Routine 4', 'Perform as described', 15, 3, 60, 'Keep your elbows close to your body'),
(8, 4, 'Mountain Climber Routine 4', 'Perform as described', 20, 3, 60, 'Keep your core tight'),
(9, 5, 'Sit-up Routine 5', 'Perform as described', 20, 3, 60, 'Do not pull on your neck'),
(10, 5, 'Deadlift Routine 5', 'Perform as described', 12, 3, 60, 'Keep your back straight');

-- Inserta datos en la tabla training_sessions
INSERT INTO training_sessions (user_id, wroutine_id, rating) VALUES
(1, 1, 8),
(2, 2, 9),
(3, 3, 7),
(4, 4, 6),
(5, 5, 8),
(6, 6, 9),
(7, 7, 7),
(8, 8, 6),
(9, 9, 8),
(10, 10, 9);

-- Inserta datos en la tabla progress_monitorings
INSERT INTO progress_monitorings (user_id, weigh, height, circumference) VALUES
(1, 70.50, 1.75, 0.85),
(2, 68.00, 1.70, 0.80),
(3, 75.00, 1.80, 0.90),
(4, 80.50, 1.85, 0.95),
(5, 65.00, 1.65, 0.75),
(6, 72.00, 1.78, 0.88),
(7, 74.50, 1.82, 0.89),
(8, 78.00, 1.80, 0.92),
(9, 69.50, 1.72, 0.81),
(10, 76.00, 1.76, 0.86);

