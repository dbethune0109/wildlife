--
-- PostgreSQL database dump
--

-- Dumped from database version 9.6rc1
-- Dumped by pg_dump version 9.6rc1

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;

--
-- Name: plpgsql; Type: EXTENSION; Schema: -;
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -;
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET search_path = public, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- Name: animals; Type: TABLE; Schema: public;
--

CREATE TABLE animals (
    id integer NOT NULL,
    name character varying,
    health character varying,
    age character varying,
    endangered boolean,
    species character varying
);

--
-- Name: animals_id_seq; Type: SEQUENCE; Schema: public;
--

CREATE SEQUENCE animals_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: animals_id_seq; Type: SEQUENCE OWNED BY; Schema: public;
--

ALTER SEQUENCE animals_id_seq OWNED BY animals.id;


--
-- Name: animals_sightings; Type: TABLE; Schema: public;
--

CREATE TABLE animals_sightings (
    id integer NOT NULL,
    animal_id integer,
    sighting_id integer
);



--
-- Name: animals_sightings_id_seq; Type: SEQUENCE; Schema: public;
--

CREATE SEQUENCE animals_sightings_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;



--
-- Name: animals_sightings_id_seq; Type: SEQUENCE OWNED BY; Schema: public;
--

ALTER SEQUENCE animals_sightings_id_seq OWNED BY animals_sightings.id;


--
-- Name: sightings; Type: TABLE; Schema: public;
--

CREATE TABLE sightings (
    id integer NOT NULL,
    location character varying,
    rangername character varying,
    date_sighted timestamp without time zone
);



--
-- Name: sightings_id_seq; Type: SEQUENCE; Schema: public;
--

CREATE SEQUENCE sightings_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: sightings_id_seq; Type: SEQUENCE OWNED BY; Schema: public;
--

ALTER SEQUENCE sightings_id_seq OWNED BY sightings.id;


--
-- Name: animals id; Type: DEFAULT; Schema: public;
--

ALTER TABLE ONLY animals ALTER COLUMN id SET DEFAULT nextval('animals_id_seq'::regclass);


--
-- Name: animals_sightings id; Type: DEFAULT; Schema: public;
--

ALTER TABLE ONLY animals_sightings ALTER COLUMN id SET DEFAULT nextval('animals_sightings_id_seq'::regclass);


--
-- Name: sightings id; Type: DEFAULT; Schema: public;
--

ALTER TABLE ONLY sightings ALTER COLUMN id SET DEFAULT nextval('sightings_id_seq'::regclass);


--
-- Data for Name: animals; Type: TABLE DATA; Schema: public;
--

COPY animals (id, name, health, age, endangered, species) FROM stdin;
\.


--
-- Name: animals_id_seq; Type: SEQUENCE SET; Schema: public;
--

SELECT pg_catalog.setval('animals_id_seq', 8, true);


--
-- Data for Name: animals_sightings; Type: TABLE DATA; Schema: public;
--

COPY animals_sightings (id, animal_id, sighting_id) FROM stdin;
\.


--
-- Name: animals_sightings_id_seq; Type: SEQUENCE SET; Schema: public;

SELECT pg_catalog.setval('animals_sightings_id_seq', 6, true);


--
-- Data for Name: sightings; Type: TABLE DATA; Schema: public;
--

COPY sightings (id, location, rangername, date_sighted) FROM stdin;
\.


--
-- Name: sightings_id_seq; Type: SEQUENCE SET; Schema: public;
--

SELECT pg_catalog.setval('sightings_id_seq', 8, true);


--
-- Name: animals animals_pkey; Type: CONSTRAINT; Schema: public;
--

ALTER TABLE ONLY animals
    ADD CONSTRAINT animals_pkey PRIMARY KEY (id);


--
-- Name: animals_sightings animals_sightings_pkey; Type: CONSTRAINT; Schema: public; 
--

ALTER TABLE ONLY animals_sightings
    ADD CONSTRAINT animals_sightings_pkey PRIMARY KEY (id);


--
-- Name: sightings sightings_pkey; Type: CONSTRAINT; Schema: public;
--

ALTER TABLE ONLY sightings
    ADD CONSTRAINT sightings_pkey PRIMARY KEY (id);


--
-- PostgreSQL database dump complete
--
