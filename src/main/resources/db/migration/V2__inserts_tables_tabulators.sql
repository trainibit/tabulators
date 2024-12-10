INSERT INTO public.geo_polygon
(uuid_gp, zone_gp, registred_gp, updated_gp, active_gp)
VALUES('3ffb04dd-dd3c-4f99-b3ea-f62b95e9dc7e'::uuid, 'Example Zone Test 1', '2024-12-09 17:11:11.672', '2024-12-09 18:41:02.592', true);
INSERT INTO public.geo_polygon
(uuid_gp, zone_gp, registred_gp, updated_gp, active_gp)
VALUES('844cc28f-a189-4290-9b4b-e96f27e76bab'::uuid, 'Example Zone Test 2', '2024-12-09 17:15:49.661', '2024-12-09 17:15:49.661', true);
INSERT INTO public.geo_polygon
(uuid_gp, zone_gp, registred_gp, updated_gp, active_gp)
VALUES('7546d3a5-7c8e-4162-b39a-915b179dc512'::uuid, 'Example Zone Test 3', '2024-12-09 17:19:33.527', '2024-12-09 18:50:18.378', true);

INSERT INTO public.polygon_vertex
(uuid_pv, geo_polygon_id, orden_pv, latitude_pv, length_pv, registred_pv, updated_pv, active_pv)
VALUES('21abd81f-7c98-4671-a34e-52f26c828bea'::uuid, 2, 1, 20.096983, -98.759318, '2024-12-09 17:15:49.661', '2024-12-09 17:15:49.661', true);
INSERT INTO public.polygon_vertex
(uuid_pv, geo_polygon_id, orden_pv, latitude_pv, length_pv, registred_pv, updated_pv, active_pv)
VALUES('1aa52a54-3ed4-45db-91b5-a49c272da268'::uuid, 2, 2, 20.106502, -98.753606, '2024-12-09 17:15:49.661', '2024-12-09 17:15:49.661', true);
INSERT INTO public.polygon_vertex
(uuid_pv, geo_polygon_id, orden_pv, latitude_pv, length_pv, registred_pv, updated_pv, active_pv)
VALUES('2e326ab4-a090-4c6e-aee3-d64570cd8e51'::uuid, 2, 3, 20.106125, -98.75086, '2024-12-09 17:15:49.661', '2024-12-09 17:15:49.661', true);
INSERT INTO public.polygon_vertex
(uuid_pv, geo_polygon_id, orden_pv, latitude_pv, length_pv, registred_pv, updated_pv, active_pv)
VALUES('5201b950-efdb-45c2-82d6-f932c76e68b2'::uuid, 2, 4, 20.091037, -98.751982, '2024-12-09 17:15:49.661', '2024-12-09 17:15:49.661', true);
INSERT INTO public.polygon_vertex
(uuid_pv, geo_polygon_id, orden_pv, latitude_pv, length_pv, registred_pv, updated_pv, active_pv)
VALUES('278ae0f1-953e-4337-823f-b1e3174e8cac'::uuid, 1, 1, 20.107139, -98.756619, '2024-12-09 17:11:11.672', '2024-12-09 18:41:02.592', true);
INSERT INTO public.polygon_vertex
(uuid_pv, geo_polygon_id, orden_pv, latitude_pv, length_pv, registred_pv, updated_pv, active_pv)
VALUES('f72c9876-e2d7-40cd-908c-631f3e907756'::uuid, 1, 2, 20.106514, -98.753798, '2024-12-09 17:11:11.672', '2024-12-09 18:41:02.592', true);
INSERT INTO public.polygon_vertex
(uuid_pv, geo_polygon_id, orden_pv, latitude_pv, length_pv, registred_pv, updated_pv, active_pv)
VALUES('02e57cf6-dbea-4837-aac9-75c3fc9b1422'::uuid, 1, 3, 20.097151, -98.75963, '2024-12-09 17:11:11.672', '2024-12-09 18:41:02.592', true);
INSERT INTO public.polygon_vertex
(uuid_pv, geo_polygon_id, orden_pv, latitude_pv, length_pv, registred_pv, updated_pv, active_pv)
VALUES('69be2677-2d76-4ace-87bd-24daed22f2e9'::uuid, 1, 4, 20.100471, -98.7632, '2024-12-09 17:11:11.672', '2024-12-09 18:41:02.592', true);
INSERT INTO public.polygon_vertex
(uuid_pv, geo_polygon_id, orden_pv, latitude_pv, length_pv, registred_pv, updated_pv, active_pv)
VALUES('1617ae82-7dd7-4676-9828-42bc9d4bf093'::uuid, 3, 1, 20.105943, -98.750677, '2024-12-09 17:19:33.527', '2024-12-09 18:50:18.378', true);
INSERT INTO public.polygon_vertex
(uuid_pv, geo_polygon_id, orden_pv, latitude_pv, length_pv, registred_pv, updated_pv, active_pv)
VALUES('06ce2933-0701-4853-88e1-614862571f43'::uuid, 3, 2, 20.105654, -98.748733, '2024-12-09 17:19:33.527', '2024-12-09 18:50:18.378', true);
INSERT INTO public.polygon_vertex
(uuid_pv, geo_polygon_id, orden_pv, latitude_pv, length_pv, registred_pv, updated_pv, active_pv)
VALUES('180047be-0ac3-4e31-aa28-e20183f94d5c'::uuid, 3, 3, 20.089949, -98.749578, '2024-12-09 17:19:33.527', '2024-12-09 18:50:18.378', true);
INSERT INTO public.polygon_vertex
(uuid_pv, geo_polygon_id, orden_pv, latitude_pv, length_pv, registred_pv, updated_pv, active_pv)
VALUES('85188671-e122-45e3-aaa1-8cf833475134'::uuid, 3, 4, 20.090915, -98.751727, '2024-12-09 17:19:33.527', '2024-12-09 18:50:18.378', true);

INSERT INTO public.tabulator
(uuid_tab, originpolygon_id, destinationpolygon_id, cost_tab, registred_tab, updated_tab, active_tab)
VALUES('f10ae6d0-f3ed-4be0-9420-5d7cc7b31c93'::uuid, 1, 1, 10.0, '2024-12-09 17:21:50.852', '2024-12-09 17:21:50.852', true);
INSERT INTO public.tabulator
(uuid_tab, originpolygon_id, destinationpolygon_id, cost_tab, registred_tab, updated_tab, active_tab)
VALUES('582f669d-dda3-4cd4-a32d-9f7c74722fb7'::uuid, 1, 2, 25.0, '2024-12-09 17:22:23.168', '2024-12-09 17:22:23.168', true);
INSERT INTO public.tabulator
(uuid_tab, originpolygon_id, destinationpolygon_id, cost_tab, registred_tab, updated_tab, active_tab)
VALUES('d94408f1-306b-4bd2-9ca7-ff8810072c87'::uuid, 1, 3, 35.0, '2024-12-09 17:22:48.419', '2024-12-09 17:22:48.419', true);
INSERT INTO public.tabulator
(uuid_tab, originpolygon_id, destinationpolygon_id, cost_tab, registred_tab, updated_tab, active_tab)
VALUES('068decfc-451a-4188-a62c-71b6b89f9ea2'::uuid, 2, 3, 25.0, '2024-12-09 17:23:23.514', '2024-12-09 17:23:23.514', true);
INSERT INTO public.tabulator
(uuid_tab, originpolygon_id, destinationpolygon_id, cost_tab, registred_tab, updated_tab, active_tab)
VALUES('24c69dd0-b5aa-4ee4-a3b6-1785e1bc65a2'::uuid, 2, 2, 15.0, '2024-12-09 17:23:34.771', '2024-12-09 17:23:34.771', true);
INSERT INTO public.tabulator
(uuid_tab, originpolygon_id, destinationpolygon_id, cost_tab, registred_tab, updated_tab, active_tab)
VALUES('f17c189d-65cf-43dd-869e-1951e970ef6f'::uuid, 2, 1, 35.0, '2024-12-09 17:23:49.451', '2024-12-09 17:23:49.451', true);
INSERT INTO public.tabulator
(uuid_tab, originpolygon_id, destinationpolygon_id, cost_tab, registred_tab, updated_tab, active_tab)
VALUES('30be66c3-7d67-4647-9c3e-6e1d216b933e'::uuid, 3, 1, 35.0, '2024-12-09 17:24:17.124', '2024-12-09 17:24:17.124', true);
INSERT INTO public.tabulator
(uuid_tab, originpolygon_id, destinationpolygon_id, cost_tab, registred_tab, updated_tab, active_tab)
VALUES('051a667b-1869-491f-a501-e5fd2872b2a9'::uuid, 3, 2, 25.0, '2024-12-09 17:24:31.879', '2024-12-09 17:24:31.879', true);
INSERT INTO public.tabulator
(uuid_tab, originpolygon_id, destinationpolygon_id, cost_tab, registred_tab, updated_tab, active_tab)
VALUES('38496362-a9ba-43ad-adb8-8e5b30d74844'::uuid, 3, 3, 18.52, '2024-12-09 17:24:44.185', '2024-12-09 18:47:56.442', false);