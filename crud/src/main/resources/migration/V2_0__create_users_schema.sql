create table if not exists users (
    id	            	    	        SERIAL PRIMARY KEY,
    userName                	    	varchar(20) not null,
    userStatus                        	varchar(10) not null
);