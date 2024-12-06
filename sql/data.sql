--
-- PostgreSQL database dump
--

-- Dumped from database version 16.4 (Homebrew)
-- Dumped by pg_dump version 16.4

-- Started on 2024-12-06 16:19:23 MSK

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- TOC entry 3729 (class 0 OID 25298)
-- Dependencies: 222
-- Data for Name: client; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3723 (class 0 OID 25277)
-- Dependencies: 216
-- Data for Name: authorization_token; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3725 (class 0 OID 25283)
-- Dependencies: 218
-- Data for Name: base_room_configuration; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.base_room_configuration VALUES (1, 21, 4200, 'Номер повышенной комфортности с одной двуспальной или двумя раздельными кроватями.', 'Современный номер первой категории с двуспальной кроватью для тех, кто ценит удобство и комфорт.

Лаконичный дизайн, качественная отделка и профессиональное оснащение служат основой для вашего отдыха.', 3, 'Стандарт', 1);
INSERT INTO public.base_room_configuration VALUES (3, 35, 9200, 'Просторная студия с увеличенной площадью номера и ванной комнаты напоминает уютную квартиру.', 'Просторная студия с увеличенной площадью номера и ванной комнаты напоминает уютную квартиру. Пространство визуально разделено на спальню и гостиную, что позволяет обустроить его с удобством для вас.

Безупречный вариант, если вам важно иметь много пространства и раздельные зоны для работы, сна и отдыха.', 3, 'Студия', 1);
INSERT INTO public.base_room_configuration VALUES (4, 21, 5200, 'Новые дизайнерские номера первой категории с двуспальной кроватью, расположенные на самом высоком жилом этаже отеля, откуда открывается вид на город с высоты птичьего полета.', 'Новые дизайнерские номера первой категории с двуспальной кроватью, расположенные на самом высоком жилом этаже отеля, откуда открывается вид на город с высоты птичьего полета. Шесть из номеров «Арт» предусмотрены для одноместного размещения.

В дизайне номеров, коридоров и лифтовых холлов применены технические решения для энергосбережения: свет включается по сигналам датчиков движения, навигация по коридорам продублирована направлением светильников для удобства гостей.', 2, 'Арт', 1);
INSERT INTO public.base_room_configuration VALUES (5, 21, 5500, 'Вас ждет уникальная атмосфера и необычные стилистические решения в номерах категории Смарт.', 'Вас ждет уникальная атмосфера и необычные стилистические решения в одноместных и двухместных номерах категории Смарт, зеркальные поверхности, удобное и мобильное рабочее место, расположенное в зоне окна, технологичность и умный комфорт в сочетании с демократичной ценой.

Номера выполнены в смелом цветовом решении в серо-черной цветовой гамме с яркими красными акцентами.', 2, 'Смарт', 1);
INSERT INTO public.base_room_configuration VALUES (2, 35, 6800, 'Комфортный номер с двумя изолированными комнатами, гостиной и спальней.', 'Современный семейный номер с двумя изолированными комнатами: гостиной и спальней. Обустройство номера предполагает размещение 3-х взрослых или двух взрослых и одного/двух детей.

Трехместный семейный номер — идеальный выбор, если вы едете отдыхать в Москву с семьей и вам важно не расставаться с близкими.', 4, 'Стандарт Семейный', 2);
INSERT INTO public.base_room_configuration VALUES (6, 42, 10800, 'Современный дизайн номера в сочетании с визуальным разделением пространства создадут комфортные условия для работы и отдыха рядом с близкими.', 'Студия Смарт оснащена по последним современным стандартам: технологичность и яркий стиль номера создадут комфортную атмосферу для отдыха и работы вдали от дома.

Пространство Студии визуально разделено на спальню и гостиную с оборудованной мини-кухней. Также в вашем распоряжении: просторная прихожая, большая ванная комната с современной душевой кабиной и ванной.

В некоторых из номеров вы обнаружите балкон с панорамным остеклением, где сможете насладиться чашечкой кофе, любуясь прекрасным видом на город.', 3, 'Студия Смарт', 1);
INSERT INTO public.base_room_configuration VALUES (7, 43, 12500, 'в вашем распоряжении - просторная гостиная, спальня, зона отдыха, барная стойка, ванная комната, гардеробная зона.', 'Апартамент Смарт, выполненный в урбанистическом стиле и в серо-черной цветовой гамме с яркими красными акцентами, станет полноценной заменой дома в длительной поездке для вас и вашей семьи.

В вашем распоряжении: просторная прихожая, гостиная, спальня, зона для отдыха, оборудованная кухня, большая ванная комната с современной душевой и ванной.

Этот тип номеров будет идеальным решением для семей и компаний друзей, подойдет для тех, кому важно находиться рядом со своими близкими.

При необходимости возможно объединить апартамент с расположенным рядом стандартным номером для увеличения общей площади и количества проживающих гостей.', 3, 'Апартамент Смарт', 2);
INSERT INTO public.base_room_configuration VALUES (8, 56, 14400, 'Беспроигрышный вариант, если вы хотите эффектно провести время, погрузившись в атмосферу люкса.', 'Просторный номер с большой ванной и барной стойкой, выполненный в классическом стиле располагаются на отдельном этаже с собственной входной группой, что обеспечивает приватность и быстрый беспрепятственный доступ. Отдельная lounge зона с чаем, кофе и бутилированной водой, а также профессиональный консьерж-сервис являются неотъемлемой частью номеров категории Люкс.

Беспроигрышный вариант, если вы хотите эффектно провести время, погрузившись в атмосферу роскоши.', 3, 'Люкс VIP', 1);
INSERT INTO public.base_room_configuration VALUES (9, 113, 17800, 'Правильный выбор для продолжительной бизнес поездки, в которой вы хотите чувствовать себя раскованно и пользоваться услугами представительского класса.', 'Президентский Люкс оформлен в классических, сдержанных тонах. Высокие потолки и большие панорамные окна делают этот номер идеальным для ценителей роскошных и просторных интерьеров, а наличие собственного кабинета, гостиной, кухни, гардеробной, камина и сауны только подчеркнет Ваш высокий статус и индивидуальность.

Номер расположен на представительском этаже, доступ к которому организован особым образом, позволяющим наслаждаться уединенным отдыхом или, находясь в деловой поездке, работать и принимать гостей в приватной обстановке и без суеты.', 2, 'Президентский Люкс', 3);


--
-- TOC entry 3731 (class 0 OID 25306)
-- Dependencies: 224
-- Data for Name: extended_room_configuration; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.extended_room_configuration VALUES (1, 'Стандарт с двуспальной кроватью', true, 1);
INSERT INTO public.extended_room_configuration VALUES (3, 'Стандарт семейный', true, 2);
INSERT INTO public.extended_room_configuration VALUES (4, 'Студия', true, 3);
INSERT INTO public.extended_room_configuration VALUES (5, 'Арт с двуспальной кроватью', true, 4);
INSERT INTO public.extended_room_configuration VALUES (6, 'Арт с двумя раздельными кроватями', false, 4);
INSERT INTO public.extended_room_configuration VALUES (7, 'Смарт с двуспальной кроватью', true, 5);
INSERT INTO public.extended_room_configuration VALUES (8, 'Смарт с двумя раздельными кроватями', false, 5);
INSERT INTO public.extended_room_configuration VALUES (9, 'Студия Смарт', true, 6);
INSERT INTO public.extended_room_configuration VALUES (10, 'Апартамент Смарт', true, 7);
INSERT INTO public.extended_room_configuration VALUES (11, 'Люкс VIP', true, 8);
INSERT INTO public.extended_room_configuration VALUES (12, 'Президентский Люкс', true, 9);
INSERT INTO public.extended_room_configuration VALUES (2, 'Стандарт с двумя раздельными кроватями', false, 1);


--
-- TOC entry 3735 (class 0 OID 25318)
-- Dependencies: 228
-- Data for Name: room; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.room VALUES (1, 1);
INSERT INTO public.room VALUES (2, 1);
INSERT INTO public.room VALUES (3, 2);
INSERT INTO public.room VALUES (4, 2);
INSERT INTO public.room VALUES (5, 3);
INSERT INTO public.room VALUES (6, 3);
INSERT INTO public.room VALUES (7, 4);
INSERT INTO public.room VALUES (8, 4);
INSERT INTO public.room VALUES (9, 5);
INSERT INTO public.room VALUES (10, 5);
INSERT INTO public.room VALUES (11, 6);
INSERT INTO public.room VALUES (12, 6);
INSERT INTO public.room VALUES (13, 7);
INSERT INTO public.room VALUES (14, 7);
INSERT INTO public.room VALUES (15, 8);
INSERT INTO public.room VALUES (16, 8);
INSERT INTO public.room VALUES (17, 9);
INSERT INTO public.room VALUES (18, 9);
INSERT INTO public.room VALUES (19, 10);
INSERT INTO public.room VALUES (20, 10);
INSERT INTO public.room VALUES (21, 11);
INSERT INTO public.room VALUES (22, 11);
INSERT INTO public.room VALUES (23, 12);
INSERT INTO public.room VALUES (24, 12);


--
-- TOC entry 3727 (class 0 OID 25291)
-- Dependencies: 220
-- Data for Name: booking_record; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3733 (class 0 OID 25312)
-- Dependencies: 226
-- Data for Name: price_schedule; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3737 (class 0 OID 25324)
-- Dependencies: 230
-- Data for Name: room_photo; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.room_photo VALUES (1, 1, 'standard/1_1.png', 1);
INSERT INTO public.room_photo VALUES (2, 5, 'standard/2.png', 1);
INSERT INTO public.room_photo VALUES (3, 10, 'standard/3.png', 1);
INSERT INTO public.room_photo VALUES (4, 15, 'standard/4.png', 1);
INSERT INTO public.room_photo VALUES (5, 1, 'standard/1_1.png', 2);
INSERT INTO public.room_photo VALUES (6, 5, 'standard/2.png', 2);
INSERT INTO public.room_photo VALUES (7, 10, 'standard/3.png', 2);
INSERT INTO public.room_photo VALUES (8, 15, 'standard/4.png', 2);
INSERT INTO public.room_photo VALUES (9, 1, 'art/1_1.png', 5);
INSERT INTO public.room_photo VALUES (10, 5, 'art/2.png', 5);
INSERT INTO public.room_photo VALUES (11, 10, 'art/3.png', 5);
INSERT INTO public.room_photo VALUES (12, 15, 'art/4.png', 5);
INSERT INTO public.room_photo VALUES (13, 1, 'art/1_2.png', 6);
INSERT INTO public.room_photo VALUES (14, 5, 'art/2.png', 6);
INSERT INTO public.room_photo VALUES (15, 10, 'art/3.png', 6);
INSERT INTO public.room_photo VALUES (16, 15, 'art/4.png', 6);
INSERT INTO public.room_photo VALUES (17, 1, 'smart/1_1.png', 7);
INSERT INTO public.room_photo VALUES (18, 5, 'smart/2.png', 7);
INSERT INTO public.room_photo VALUES (19, 10, 'smart/3.png', 7);
INSERT INTO public.room_photo VALUES (20, 1, 'smart/1_2.png', 8);
INSERT INTO public.room_photo VALUES (21, 5, 'smart/2.png', 8);
INSERT INTO public.room_photo VALUES (22, 10, 'smart/3.png', 8);
INSERT INTO public.room_photo VALUES (23, 1, 'standard_family/1.png', 3);
INSERT INTO public.room_photo VALUES (24, 5, 'standard_family/2.png', 3);
INSERT INTO public.room_photo VALUES (25, 10, 'standard_family/3.png', 3);
INSERT INTO public.room_photo VALUES (26, 15, 'standard_family/4.png', 3);
INSERT INTO public.room_photo VALUES (27, 1, 'studio/1.png', 4);
INSERT INTO public.room_photo VALUES (28, 5, 'studio/2.png', 4);
INSERT INTO public.room_photo VALUES (29, 10, 'studio/3.png', 4);
INSERT INTO public.room_photo VALUES (30, 1, 'studio_smart/1.png', 9);
INSERT INTO public.room_photo VALUES (31, 5, 'studio_smart/2.png', 9);
INSERT INTO public.room_photo VALUES (32, 10, 'studio_smart/3.png', 9);
INSERT INTO public.room_photo VALUES (33, 1, 'apartment_smart/1.png', 10);
INSERT INTO public.room_photo VALUES (34, 5, 'apartment_smart/2.png', 10);
INSERT INTO public.room_photo VALUES (35, 10, 'apartment_smart/3.png', 10);
INSERT INTO public.room_photo VALUES (36, 1, 'lux_vip/1.png', 11);
INSERT INTO public.room_photo VALUES (37, 5, 'lux_vip/2.png', 11);
INSERT INTO public.room_photo VALUES (38, 10, 'lux_vip/3.png', 11);
INSERT INTO public.room_photo VALUES (39, 15, 'lux_vip/4.png', 11);
INSERT INTO public.room_photo VALUES (40, 1, 'president_lux/1.png', 12);
INSERT INTO public.room_photo VALUES (41, 5, 'president_lux/2.png', 12);
INSERT INTO public.room_photo VALUES (42, 10, 'president_lux/3.png', 12);
INSERT INTO public.room_photo VALUES (43, 15, 'president_lux/4.png', 12);


--
-- TOC entry 3743 (class 0 OID 0)
-- Dependencies: 215
-- Name: authorization_token_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.authorization_token_id_seq', 129, true);


--
-- TOC entry 3744 (class 0 OID 0)
-- Dependencies: 217
-- Name: base_room_configuration_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.base_room_configuration_id_seq', 9, true);


--
-- TOC entry 3745 (class 0 OID 0)
-- Dependencies: 219
-- Name: booking_record_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.booking_record_id_seq', 14, true);


--
-- TOC entry 3746 (class 0 OID 0)
-- Dependencies: 221
-- Name: client_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.client_id_seq', 29, true);


--
-- TOC entry 3747 (class 0 OID 0)
-- Dependencies: 223
-- Name: extended_room_configuration_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.extended_room_configuration_id_seq', 12, true);


--
-- TOC entry 3748 (class 0 OID 0)
-- Dependencies: 225
-- Name: price_schedule_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.price_schedule_id_seq', 1, false);


--
-- TOC entry 3749 (class 0 OID 0)
-- Dependencies: 227
-- Name: room_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.room_id_seq', 24, true);


--
-- TOC entry 3750 (class 0 OID 0)
-- Dependencies: 229
-- Name: room_photo_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.room_photo_id_seq', 43, true);


-- Completed on 2024-12-06 16:19:23 MSK

--
-- PostgreSQL database dump complete
--

