

# User
create table `User` (
	id int primary key auto_increment
);


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

# Ticket

create table `Ticket` (
	`id` int primary key AUTO_INCREMENT,
	`event` int not null,
	foreign key(`event`) references `Event`(`id`) on delete no action on update no action,
	`user` int not null, 
	foreign key(`user`) references `User`(`id`) on update no action on delete no action,
	`showDate` date
);


#Admin


create table Admin(
id int primary key,
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

    
# Review

create table `Review` (
	`id` int primary key auto_increment,
	`description` varchar(200),
    `rating` double not null,
	reviewTime date not null
);


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

#UserGenre
create table UserGenre(
	user int not null,
	foreign key(user) references `RegisteredUser`(id) 
          on update cascade
          on delete cascade,
	genreType enum ('Horror', 'Thriller', 'History', 'Drama', 'Comedy'),
	primary key (user, genreType)
);

# Artist

create table Artists(
id int primary key,
type enum('Director', 'Actor','Musician'),
foreign key(id)
	references RegisteredUser(id)
    on update cascade 
    on delete cascade
);
