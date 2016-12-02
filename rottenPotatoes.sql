

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

alter table event add column `genreType` enum ('Horror', 'Thriller', 'History', 'Drama', 'Comedy');
insert into event values (2, 'Aladdin', 'Carpe diem, this book and play is good', 4.2, '2016-12-02', '2016-12-31', '7.30pm', 'Play', 20, 15);
insert into event values (3, 'The Omen', 'It is really scary', 3.8, '2016-12-02', '2016-12-31', '9.30am', 'Play', 20, 35, 'Horror');
UPDATE event set type = 'Musical' where id = 2;
UPDATE event set endDate = '2016-12-13' where id = 1;
select id, name, description, type, genreType from event 
order by genreType;

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

delete  from Ticket;

select if(max(id)+1 is null, 1, max(id) + 1) from Ticket;

#Artists





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

insert into RegisteredUser values (1, 'rohit', 'rohit', 'rohit@rohit.com', 'Rohit', 'Dumb', 1);

#UserGenre
create table UserGenre(
	id int primary key auto_increment,
	foreign key(id) references `RegisteredUser`(id) 
          on update cascade
          on delete cascade,
	genreType enum ('Horror', 'Thriller', 'History', 'Drama', 'Comedy')
);

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