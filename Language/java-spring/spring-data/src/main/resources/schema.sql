create table if not exists Token (
	userId			varchar(10) not null,
	clientId 		varchar(10) not null,
	accessToken		varchar(10) not null,
	refreshToken 	varchar(10) not null,
	scopes 			varchar(20) not null
);