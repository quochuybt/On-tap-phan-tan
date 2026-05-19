--  1. GENRES
-- ============================================================
INSERT INTO genres (genre_id, name, description) VALUES
                                                     ('G001', 'Pop',        'Nhạc đại chúng, giai điệu bắt tai, dễ nghe'),
                                                     ('G002', 'Classic',    'Nhạc cổ điển phương Tây, hàn lâm'),
                                                     ('G003', 'Jazz',       'Nhạc jazz ngẫu hứng, phong cách tự do'),
                                                     ('G004', 'Rock',       'Nhạc rock điện tử, năng động'),
                                                     ('G005', 'Electronic', 'Nhạc điện tử, EDM, synthwave');

-- ============================================================
--  2. INSTRUCTORS
-- ============================================================
INSERT INTO instructors (instructor_id, full_name, email, major, degree) VALUES
                                                                             ('I001', 'Nguyễn Minh Tuấn',  'tuan.nguyen@music.edu.vn',  'Piano',        'Thạc sĩ'),
                                                                             ('I002', 'Trần Thị Lan Anh',  'lananh.tran@music.edu.vn',  'Thanh nhạc',  'Tiến sĩ'),
                                                                             ('I003', 'Lê Hoàng Phúc',     'phuc.le@music.edu.vn',      'Guitar',       'Thạc sĩ'),
                                                                             ('I004', 'Phạm Quỳnh Như',    'nhu.pham@music.edu.vn',     'Violin',       'Cử nhân'),
                                                                             ('I005', 'Đỗ Văn Khải',       'khai.do@music.edu.vn',      'Sản xuất âm nhạc', 'Thạc sĩ');

-- ============================================================
--  3. INSTRUCTOR_PHONES
-- ============================================================
INSERT INTO instructor_phones (instructor_id, phone_number) VALUES
                                                                ('I001', '0901234567'),
                                                                ('I001', '0281234567'),
                                                                ('I002', '0912345678'),
                                                                ('I003', '0923456789'),
                                                                ('I003', '0334567890'),
                                                                ('I004', '0945678901'),
                                                                ('I005', '0956789012'),
                                                                ('I005', '0967890123');

-- ============================================================
--  4. COURSES
-- ============================================================
INSERT INTO courses (course_id, name, description, tuition_fee, status, genre_id, instructor_id) VALUES
                                                                                                     ('C001', 'Piano cơ bản',          'Khóa học piano dành cho người mới bắt đầu',         2500000, 'OPEN',   'G001', 'I001'),
                                                                                                     ('C002', 'Piano nâng cao',        'Kỹ thuật piano nâng cao, sonata và etude',           4500000, 'OPEN',   'G002', 'I001'),
                                                                                                     ('C003', 'Thanh nhạc Pop',        'Luyện giọng và hát nhạc Pop chuyên nghiệp',          3000000, 'OPEN',   'G001', 'I002'),
                                                                                                     ('C004', 'Thanh nhạc cổ điển',    'Thanh nhạc opera và nhạc thính phòng',               5000000, 'CLOSED', 'G002', 'I002'),
                                                                                                     ('C005', 'Guitar acoustic',       'Guitar acoustic từ cơ bản đến fingerstyle',          2000000, 'OPEN',   'G001', 'I003'),
                                                                                                     ('C006', 'Guitar điện',           'Kỹ thuật guitar điện, blues và rock',                2800000, 'OPEN',   'G004', 'I003'),
                                                                                                     ('C007', 'Violin cơ bản',         'Học đàn violin từ đầu, tư thế và kỹ thuật cung',     3500000, 'FULL',   'G002', 'I004'),
                                                                                                     ('C008', 'Hòa âm nhạc Jazz',      'Lý thuyết và hòa âm trong nhạc Jazz',               4000000, 'OPEN',   'G003', 'I002'),
                                                                                                     ('C009', 'Sản xuất âm nhạc',      'Sử dụng DAW, mixing và mastering cơ bản',            6000000, 'OPEN',   'G005', 'I005'),
                                                                                                     ('C010', 'EDM Production',        'Tạo nhạc EDM với Ableton Live',                      7000000, 'CLOSED', 'G005', 'I005');

-- ============================================================
--  5. STUDENTS
-- ============================================================
INSERT INTO students (student_id, full_name, email, birth_date, address, level) VALUES
                                                                                    ('S001', 'Nguyễn Văn An',       'an.nv@gmail.com',        '2000-05-12', 'Quận 1, TP.HCM',         'BEGINNER'),
                                                                                    ('S002', 'Trần Thị Bích',       'bich.tt@gmail.com',      '1999-08-23', 'Quận 3, TP.HCM',         'INTERMEDIATE'),
                                                                                    ('S003', 'Lê Quốc Cường',       'cuong.lq@gmail.com',     '2001-03-17', 'Bình Thạnh, TP.HCM',     'BEGINNER'),
                                                                                    ('S004', 'Phạm Ngọc Diệu',      'dieu.pn@gmail.com',      '1998-11-05', 'Quận 7, TP.HCM',         'ADVANCED'),
                                                                                    ('S005', 'Hoàng Minh Đức',      'duc.hm@gmail.com',       '2002-07-30', 'Thủ Đức, TP.HCM',        'BEGINNER'),
                                                                                    ('S006', 'Vũ Thị Thanh',        'thanh.vt@gmail.com',     '2000-01-14', 'Quận 10, TP.HCM',        'INTERMEDIATE'),
                                                                                    ('S007', 'Đặng Hữu Phát',       'phat.dh@gmail.com',      '1997-09-22', 'Gò Vấp, TP.HCM',         'ADVANCED'),
                                                                                    ('S008', 'Bùi Thị Kim Loan',    'loan.btk@gmail.com',     '2003-04-08', 'Quận 12, TP.HCM',        'BEGINNER'),
                                                                                    ('S009', 'Ngô Thanh Tùng',      'tung.nt@gmail.com',      '1999-12-19', 'Bình Dương',              'INTERMEDIATE'),
                                                                                    ('S010', 'Lý Cẩm Nhung',        'nhung.lc@gmail.com',     '2001-06-25', 'Đồng Nai',                'ADVANCED');

-- ============================================================
--  6. ENROLLMENTS
--  Composite PK: student_id + course_id + enroll_date
-- ============================================================
INSERT INTO enrollments (student_id, course_id, enroll_date, score, status) VALUES
-- S001 - An (BEGINNER): học Piano cơ bản, Guitar acoustic
('S001', 'C001', '2024-01-10', 7.5,  'COMPLETED'),
('S001', 'C005', '2024-03-01', NULL, 'REGISTERED'),

-- S002 - Bích (INTERMEDIATE): học Piano nâng cao, Thanh nhạc Pop
('S002', 'C002', '2024-01-15', 8.0,  'COMPLETED'),
('S002', 'C003', '2024-02-20', 8.5,  'COMPLETED'),

-- S003 - Cường (BEGINNER): học Guitar acoustic, bỏ 1 khóa
('S003', 'C005', '2024-02-01', NULL, 'REGISTERED'),
('S003', 'C001', '2024-01-20', NULL, 'DROPPED'),

-- S004 - Diệu (ADVANCED): học nhiều khóa
('S004', 'C002', '2024-01-05', 9.5,  'COMPLETED'),
('S004', 'C004', '2024-02-10', 9.0,  'COMPLETED'),
('S004', 'C008', '2024-03-15', NULL, 'REGISTERED'),

-- S005 - Đức (BEGINNER): học Piano cơ bản
('S005', 'C001', '2024-03-01', NULL, 'REGISTERED'),

-- S006 - Thanh (INTERMEDIATE): học Thanh nhạc, Violin
('S006', 'C003', '2024-01-25', 7.0,  'COMPLETED'),
('S006', 'C007', '2024-02-15', 8.0,  'COMPLETED'),

-- S007 - Phát (ADVANCED): học Guitar điện, Sản xuất âm nhạc, EDM
('S007', 'C006', '2024-01-10', 9.0,  'COMPLETED'),
('S007', 'C009', '2024-02-01', 8.5,  'COMPLETED'),
('S007', 'C010', '2024-03-20', NULL, 'REGISTERED'),

-- S008 - Loan (BEGINNER): học Guitar acoustic
('S008', 'C005', '2024-03-10', NULL, 'REGISTERED'),

-- S009 - Tùng (INTERMEDIATE): học Jazz, Sản xuất âm nhạc
('S009', 'C008', '2024-02-05', 7.5,  'COMPLETED'),
('S009', 'C009', '2024-03-01', NULL, 'REGISTERED'),

-- S010 - Nhung (ADVANCED): học Violin, Piano nâng cao, Jazz
('S010', 'C007', '2024-01-08', 9.0,  'COMPLETED'),
('S010', 'C002', '2024-02-12', 9.5,  'COMPLETED'),
('S010', 'C008', '2024-03-05', NULL, 'REGISTERED');
