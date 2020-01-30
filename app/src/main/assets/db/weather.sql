create table WEATHER(
    CITY_ID integer,
    TEMP real,
    ICON text,
    DT integer
    constraint CITY_ID_CONSTRAINT primary key(CITY_ID)
)