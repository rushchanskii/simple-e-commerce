insert into Catalogue_Category (id, category_name) values(1001, 'Books');
insert into Catalogue_Category (id, category_name) values(1002, 'Audio');
insert into Catalogue_Category (id, category_name) values(1003, 'Video');
insert into Catalogue_Category (id, category_name) values(1004, 'Games');
insert into Catalogue_Category (id, category_name) values(1005, 'Fun Packs');

insert into Catalogue_Sub_Category (id, category_id, sub_category_name) values(1001, 1001, 'Sience Fiction & Fantasy');
insert into Catalogue_Sub_Category (id, category_id, sub_category_name) values(1002, 1001, 'History');
insert into Catalogue_Sub_Category (id, category_id, sub_category_name) values(1003, 1001, 'Detective Series');
insert into Catalogue_Sub_Category (id, category_id, sub_category_name) values(1004, 1001, 'Comics');
insert into Catalogue_Sub_Category (id, category_id, sub_category_name) values(1005, 1001, 'Science & Tech');

insert into Catalogue_Sub_Category (id, category_id, sub_category_name) values(2001, 1002, 'Players');
insert into Catalogue_Sub_Category (id, category_id, sub_category_name) values(2002, 1002, 'CD Collections');
insert into Catalogue_Sub_Category (id, category_id, sub_category_name) values(2003, 1002, 'Vinil');

insert into Catalogue_Sub_Category (id, category_id, sub_category_name) values(3001, 1003, 'DVD Collections');
insert into Catalogue_Sub_Category (id, category_id, sub_category_name) values(3002, 1003, 'Blue Ray');
insert into Catalogue_Sub_Category (id, category_id, sub_category_name) values(3003, 1003, 'Streeming Services');


insert into Catalogue_Sub_Category (id, category_id, sub_category_name) values(4001, 1004, 'Nintendo Switch');
insert into Catalogue_Sub_Category (id, category_id, sub_category_name) values(4002, 1004, 'Play Station 4');
insert into Catalogue_Sub_Category (id, category_id, sub_category_name) values(4003, 1004, 'XBox');

insert into Catalogue_Sub_Category (id, category_id, sub_category_name) values(4004, 1005, 'Pokemons');

insert into Users (id,login_name, encripted_password, first_name, last_name, address,  phone_number, e_mail, user_role) 
values
(1000, 'admin', '', 'Admin', 'Admin', 'Administrator Street, NY', '555-55-55', 'admin@localhost', 'ROLE_ADMIN'),
(2000, 'manager', '', 'Manager', 'Manager', 'Manager Street, Chicago', '555-55-66', 'manager@localhost', 'ROLE_MANAGER'),
(3000, 'client', '', 'Client', 'Client', 'Clients Street, Los Angeles', '555-66-66', 'client@localhost', 'ROLE_CLIENT'),
(4000, 'service', '$2a$10$rpucRAtnxWtbZ.4Q6LPpneQmT54WmjZN7RxDjTnn7LL79GcSX/YrW', 
'Administrator', 'Person', 'Clients Street, Los Angeles', '555-66-66', 'service@localhost', 'ROLE_MANAGER');


INSERT INTO CATALOGUE_PRODUCT_ITEM (main_picture_id, price, category_id, id, sub_category_id, description, name) VALUES
(0, 74.95, 1005, 10016, 4004, ' 9 booster packs / 1 promo card / 65 card sleeves / 45 energy cards / 1 game instructions / 6 damage token cubes / 1 coin throw cube / 2 plastic marks for special conditions / 1 collection box / 4 card separators / 1 code card', ' Pokemon - Obsidian Flames - Top Trainer Box - German - Original Packaging with LMS Trading Greeting Card'),
(0, 29.99, 1001, 10001, 1001, ' by J.K. Rowling', ' Harry Potter and the Chamber of Secrets, Book 2'),
(0, 15.99, 1001, 10002, 1001, U&' Winner of the 2023 Audie Award for Best Male Narrator  A #1 New York Times Bestseller and New York Times Book Review Editors\2019 Choice!', ' Fairy Tale by Stephen King'),
(0, 65.0, 1004, 10003, 4001, U&' F\00fcr den Nachfolger zu The Legend of Zelda: Breath of the Wild ist eine Ver\00f6ffentlichung im Fr\00fchjahr 2023 f\00fcr Nintendo Switch vorgesehen. Der Handlungsort f\00fcr dieses Abenteuer wurde um die L\00fcfte \00fcber Hyrule erweitert.', 'The Legend of Zelda: Tears of the Kingdom - [Nintendo Switch]'),
(0, 19.99, 1001, 10004, 1001, 'Feyre has undergone more trials than one human woman can carry in her heart. ', ' A Court of Mist and Fury (A Court of Thorns and Roses, 2) by  Sarah J. Maas'),
(0, 249.0, 1002, 10005, 2001, U&' Dustcover, felt mat, power supply, RCA cable, standard flat belt for 33/45 RPM, cartridge alignment protractor, round belt for 78 RPM, anti skating weight, adapter for 7\201c singles', ' Pro-Ject Debut III Phono SB Turntable with Ortofon OM5e (High Gloss Black)'),
(0, 199.0, 1002, 10006, 2001, ' Fully automatic operation with 2 speeds: 33-1/3 rpm and 45 rpm Anti-resonance, die-cast aluminum platter', ' Audio-Technica AT-LP60XBT-BK Fully Automatic Bluetooth Belt-Drive Stereo Turntable, Black, Hi-Fi, 2 Speed, Dust Cover, Anti-Resonance, Die-cast Aluminum Platter'),
(0, 1099.0, 1002, 10007, 2001, ' Turntable, Turntable sheet, Dust cover, EP record adaptor, Balance weight, Head shell, Cartridge(audio-technica AT-VM95C), PHONO cable, PHONO earth lead, AC power supply cord, Owner''s Manual', U&' Technics Turntable, Premium Class HiFi Record Player with Coreless Direct, Stable Playback, Audiophile-Grade Cartridge and Auto-Lift Tonearm, Dustcover Included \2013 SL-100C, Black (SL-100C-K)'),
(0, 59.99, 1002, 10008, 2002, ' 2014 release celebrating what would have been the 60th birthday of Stevie Ray Vaughan (1954-1990) Stevie Ray Vaughan and Double Trouble', 'Stevie Ray Vaughan, The Complete Epic Recordings Collection'),
(0, 89.99, 1002, 10009, 2002, ' 2014 twenty CD box set. Elvis is best known for his musical talents, but he was also blessed with a charismatic presence on screen and these qualities are perfectly married together in this essential collection of his original movie soundtracks.', ' The Elvis Presley Soundtrack Collection'),
(0, 24.99, 1002, 10010, 2002, ' 5 CD collection', U&' 5 CD 100 Classical Music Pieces, Baroque, Classical, Romantic, Piano and Strings Music, Mozart, Chopin, Bach\2026'),
(0, 29.95, 1002, 10011, 2003, 'Original album on 180g vinyl.Tracklist:1 So Far Away 2 Money for Nothing 3 Walk of Life 4 Your Latest Trick 5 Why Worry 6 Ride Across the River 7 The Man''s Too Strong 8 One World 9 Brothers in Arms ', 'Dire Straits "Brothers In Arms"'),
(0, 20.0, 1002, 10012, 2003, ' Lana Del Rey proudly presents the highly anticipated BORN TO DIE, featuring the hit singles "Video Games" and "Blue Jeans".', '  LP Lana Del Rey, "Born To Die"'),
(0, 14.99, 1001, 10013, 1001, 'An Instant New York Times Bestseller A Goodreads Most Anticipated Book', 'Fourth Wing (The Empyrean, 1) by Rebecca Yarros '),
(0, 7.99, 1003, 10014, 3002, 'Film legend Martin Scorsese directs Robert De Niro in this tale about the rise and fall of a Las Vegas gangster. Sharon Stone earned an Academy Award nomination for her performance as the gangsters slowly unraveling wife.', 'Casino'),
(0, 24.99, 1004, 10015, 4002, U&' Das Beste aus zwei der gr\00f6\00dften Assassin''s Creed Abenteuer: Erkunden Sie das alte \00c4gypten oder das antike Griechenland - zwei der idealen Geschichten aus dem Assassin''s Creed Franchise in einer Box', ' Assassin''s Creed Odyssey + Assassin''s Creed Origins DOPPELPACK [PS4]');


