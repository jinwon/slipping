DROP TABLE IF EXISTS TEST;

CREATE TABLE TEST (
	id INTEGER NOT NULL IDENTITY PRIMARY KEY,
	who VARCHAR(12) NOT NULL,
	whenn DATE,
	resource VARCHAR(255) NOT NULL,
	action VARCHAR(30) NOT NULL
);