CREATE TABLE sqlite_sequence(name,seq);
CREATE TABLE Drivers
(
    ID   integer not null
        constraint Drivers_pk
            primary key autoincrement,
    Name TEXT    not null
);
CREATE TABLE IF NOT EXISTS "Grand Prix Names"
(
    ID   integer not null
        constraint "Grand Prix Names_pk"
            primary key autoincrement,
    Name TEXT    not null
);
CREATE TABLE Races
(
    ID           integer not null
        constraint Races_pk
            primary key autoincrement,
    "Grand Prix" integer not null
        constraint "Races_Grand Prix Names_ID_fk"
            references "Grand Prix Names",
    Date         TEXT    not null,
    CC           TEXT    not null
);
create table Results
(
    ID          integer not null
        constraint Results_pk
            primary key autoincrement,
    "Race ID"   integer not null
        constraint Results_Races_ID_fk
            references Races,
    "Driver ID" integer not null
        constraint Drivers_Driver_ID_fk
            references Drivers,
    Position    integer not null,
    Points      integer not null
);