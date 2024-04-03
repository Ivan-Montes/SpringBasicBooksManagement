INSERT INTO PUBLISHERS (creation_timestamp, update_timestamp, name)
VALUES(CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'Infinity Publications'),
(CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'Live and let live Publications'),
(CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'I want to believe Publications');

INSERT INTO AUTHORS (name, surname, creation_timestamp, update_timestamp)
VALUES('Isaac', 'Newton', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('Koyoharu ', 'Gotouge', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('Richard', 'Helm', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('Erich', 'Gamma', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('Ralph', 'Johnson', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

INSERT INTO GENRES (name, description, creation_timestamp, update_timestamp)
VALUES('Drama','Sucesos desafortunados en la vida',CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('Ciencia Ficción','Literatura fantástica y narrativa de terror',CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('Manga','Historietas de origen japonés',CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('Autoayuda','Desarrollo personal y aceptación emocional',CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('Acción', 'Emocionantes escenas de lucha y aventura', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('Comedia', 'Historias divertidas y situaciones cómicas', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('Ciencia ficción', 'Exploración de futuros posibles y tecnología avanzada', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('Romance', 'Historias de amor y relaciones personales', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('Terror', 'Escenas aterradoras y elementos sobrenaturales', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('Fantasía', 'Mundos mágicos, criaturas y aventuras épicas', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('Animación', 'Películas dibujadas o generadas por computadora', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('Documental', 'Exploración de hechos reales y eventos históricos', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

INSERT INTO BOOKSHOPS (name, creation_timestamp, update_timestamp)
VALUES('The Cornershop', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('Libros, libros, libros', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('Fly you fools Books', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

INSERT INTO BOOKS (isbn, title, genre_id, publisher_id, creation_timestamp, update_timestamp)
VALUES(1231231231231, 'Principia Mathematica', 1, 3, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(1231231231232, 'Kimetsu No Yaiba Vol I', 3, 2, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(1231231231233, 'Design patterns', 2, 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(1231231231234, 'Sin Librerias en tu vida', 3, 2, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

INSERT INTO BOOKS_AUTHORS (book_id, author_id)
VALUES(1,1),
(2,2),
(3,3),
(3,4),
(3,5),
(4,5);

INSERT INTO BOOKS_BOOKSHOPS (book_book_id, bookshop_bookshop_id, price, units)
VALUES(1,3,13,25),
(2,2,9.99,10),
(3,1,17.5,5);