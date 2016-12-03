

# User
create table `User` (
	id int primary key auto_increment
);

insert into User values(1);


# Event
create table `Event` (
	`id` int primary key AUTO_INCREMENT,
	`name` varchar(200) not null,
	`description` varchar(1000) not null,
	`calculatedRating` double,
	`startDate` date,
	`endDate` date,
	`showTime` varchar(100),
	`type` enum ('Play', 'Musical') not null,
	`availableTickets` int,
	`ticketPrice` int,
	`genreType` enum ('Horror', 'Thriller', 'History', 'Drama', 'Comedy')
);

select e.id, e.name, e.description, e.calculatedRating, e.type, e.genreType from Event e, UserGenre ug where ug.user = 1 and e.genreType = ug.genreType;

select e.id, e.name, e.description, e.calculatedRating,
e.type, e.genreType from Event e, UserGenre ug where ug.user = 1 
and e.genreType = ug.genreType;

alter table event add column `genreType` enum ('Horror', 'Thriller', 'History', 'Drama', 'Comedy');
insert into event values (2, 'Aladdin', 'Carpe diem, this book and play is good', 4.2, '2016-12-02', '2016-12-31', '7.30pm', 'Play', 20, 15);
insert into event values (3, 'The Omen', 'It is really scary', 3.8, '2016-12-02', '2016-12-31', '9.30am', 'Play', 20, 35, 'Horror');
UPDATE event set type = 'Musical' where id = 2;
UPDATE event set endDate = '2016-12-13' where id = 1;
select id, name, description, type, genreType from event 
order by genreType;
select * from Ticket;
delete from Ticket;

# Multi valued show dates
create table `EventShowDate` (
	`event` int not null,
	`showDate` date not null,
	primary key(`event`, `showDate`),
	foreign key(`event`) references `Event`(id) on delete cascade on update cascade
); 

# Ticket

create table `Ticket` (
	`id` int primary key AUTO_INCREMENT,
	`event` int not null,
	foreign key(`event`) references `Event`(`id`) on delete no action on update no action,
	`user` int not null, 
	foreign key(`user`) references `User`(`id`) on update no action on delete no action,
	`showDate` date
);

insert into Ticket (event, showDate, user) values (1, '2016-12-05', 1);

select * from Ticket;
delete  from Ticket;

select * from event;

select if(max(id)+1 is null, 1, max(id) + 1) from Ticket;

#Artists

select * from event;


#Admin


create table Admin(
id int primary key,
password varchar(200) not null,
email varchar(200) not null,
unique(email),
foreign key(id)
	references `User`(id)
    on update cascade 
    on delete cascade
);

# Comment

create table `Comment` (
	`id` int primary key,
	`commentText` varchar(500) not null,
	`commentTime` date not null
);

select * from comment;

select if(max(id)+1 is null, 1, max(id) + 1) from Review;

insert into Comment values (1, 'This book sucks.', '2016-12-01');
insert into Comment values (2, 'Ruined my childhood.', '2016-12-02');

create table `UserComment` (
	`commentOn` int not null,
    foreign key(`commentOn`)
		references `Event`(`id`)
        on update cascade
        on delete cascade,
	`commentedOnBy` int not null,
    foreign key(`commentedOnBy`)
		references `User`(`id`)
        on update cascade
        on delete cascade,
	`comment` int not null,
	foreign key(`comment`) references `Comment`(id) on update cascade on delete cascade,
	primary key(`comment`, `commentOn`, `commentedOnBy`)
);

select comment from UserComment where commentOn = 1;

insert into UserComment values (1, 1, 1);
insert into UserComment values (1, 1, 2);
    
# Review

create table `Review` (
	`id` int primary key auto_increment,
	`description` varchar(200),
    `rating` double not null,
	reviewTime date not null
);

 

alter table Review modify column rating double not null;

INSERT into Review values (1, 'Blah blah blah', 3.8, '2016-11-28');
INSERT into Review values (2, 'Blah blah blah more blah', 3.2, '2016-11-29');

create table `UserReview` (
	`reviews` int not null,
    foreign key(`reviews`)
		references `Event`(`id`)
        on update cascade
        on delete cascade,
	`reviewedBy` int not null,
    foreign key(`reviewedBy`)
		references `User`(`id`)
        on update cascade
        on delete cascade,
	`reviewId` int not null,
	foreign key(`reviewId`) references `Review`(`id`) on update cascade on delete cascade,
	primary key(`reviewId`, `reviews`, `reviewedBy`)
);

select id, name, description, calculatedRating, type, genreType
from Event where calculatedRating > 4.5 order by genreType;

update event set calculatedRating = 4.9 where id = 1;
select * from Event;

insert into UserReview values (1, 1, 1);
insert into UserReview values (1, 1, 2);

select reviewId from UserReview where reviews = 1;

# Registered User    
create table `RegisteredUser`(
    `id` int primary key auto_increment,
    `username` varchar(200) not null,
    unique(username),
    `password` varchar(200) not null,
    `email` varchar(200) not null,
    unique(email),
    `firstName` varchar(200) not null,
    `lastName` varchar(200) not null,	
    foreign key(id) references `User`(id) 
          on update no action
          on delete no action,
    `hasAccess` boolean not null);

-- private int id;
-- private String username;
-- private String email,password;
-- private List<Comments> comment_list = new ArrayList<Comments>();
-- private List<UserGenre> Genre = new ArrayList<UserGenre>();
-- private boolean has_access;
-- private String firstName;
-- private String lastName;
-- private Connection conn;



select id from RegisteredUser where username = 'rohit' and password = 'rohit';

insert into RegisteredUser values (1, 'rohit', 'rohit', 'rohit@rohit.com', 'Rohit', 'Dumb', 1);

#UserGenre
create table UserGenre(
	user int not null,
	foreign key(user) references `RegisteredUser`(id) 
          on update cascade
          on delete cascade,
	genre enum ('Horror', 'Thriller', 'History', 'Drama', 'Comedy'),
	primary key (user, genre)
);

select uc.comment, uc.commentedOnBy from UserComment uc where uc.commentOn = 1;

insert into UserGenre values (1, 'Horror'), (1, 'Thriller'), (1, 'History'), (1, 'Drama'), (1, 'Comedy');

select * from usergenre;

select e.id from Event e where e.genreType in (select ug.genre from UserGenre ug where ug.user = 1);

select e.id, e.name, e.description, e.calculatedRating, e.type, e.genreType from Event e where e.genreType in (select ug.genre from UserGenre ug where ug.user = 1);
# Artist

create table Artists(
id int primary key,
username varchar(200) not null,
email varchar(200) not null,
password varchar(200) not null,
type enum('Director', 'Actor','Musician'),
unique(username),
unique(email),
foreign key(id)
	references RegisteredUser(id)
    on update cascade 
    on delete cascade
);

select if(max(id)+1 is null, 1, max(id) + 1) from Review;

select * from review;

select * from userReview;

select avg(rating) from review r where exists (select * from UserReview ur where ur.reviews = 1 and ur.reviewId = r.id);

select * from Admin;

select * from registeredUser;

insert into user values (2);
insert into RegisteredUser values (2, 'admin', 'admin', 'admin@rohit.com', 'Admin', 'Admin', 2);

insert into Admin values (2, 'admin', 'admin@rohit.com');