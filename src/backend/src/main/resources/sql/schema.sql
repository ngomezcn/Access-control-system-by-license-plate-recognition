--drop table messages;
CREATE TABLE IF NOT EXISTS messages (
                                        id                     VARCHAR(60)  DEFAULT gen_random_uuid() PRIMARY KEY,
                                        text                   VARCHAR      NOT NULL
);

--drop table auth_plates;
CREATE TABLE IF NOT EXISTS auth_plates (
                                               id                     VARCHAR(60) DEFAULT gen_random_uuid() PRIMARY KEY,
                                               plate                  VARCHAR(8) NOT NULL UNIQUE,
                                               register_date          timestamp default null,
                                               drop_out_date          timestamp default null,
                                               open_time              varchar(5) default null,
                                               close_time             varchar(5) default null);
